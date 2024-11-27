import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;

public class SearchDefinition extends JPanel {
    private SlangDictionary slangDictionary;
    private JTextField definitionField;
    private JButton searchButton;
    private JTextArea resultArea;

    public SearchDefinition(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Panel chứa label và textfield
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Căn trái
        inputPanel.add(new JLabel("Enter Definition to Search:"));

        definitionField = new JTextField(30); // Tăng chiều dài textfield
        inputPanel.add(definitionField);

        // Panel chứa nút Search
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Căn trái
        searchButton = new JButton("Search Definition");
        buttonPanel.add(searchButton);

        // Vùng kết quả
        resultArea = new JTextArea(5, 20); // Tăng chiều rộng để nhìn rõ hơn
        resultArea.setLineWrap(true); // Tự động xuống dòng
        resultArea.setWrapStyleWord(true); // Xuống dòng theo từ
        resultArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(200, 100)); // Kích thước của vùng kết quả

        // Thêm các panel vào main panel
        add(inputPanel);
        add(buttonPanel);
        add(scrollPane); // Vùng kết quả ngay dưới nút Search

        // Thêm khoảng trống ở dưới cùng
        add(Box.createRigidArea(new Dimension(0, 200))); // Khoảng trống 20px ở dưới

        // Xử lý sự kiện cho nút Search
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String definition = definitionField.getText();

                List<String> slangWords = slangDictionary.searchByDefinition(definition);

                // Hiển thị kết quả
                resultArea.setText(String.join("\n", slangWords));
            }
        });
    }
}
