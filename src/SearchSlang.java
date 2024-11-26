import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchSlang extends JPanel {
    private SlangDictionary slangDictionary;
    private JTextField slangField;
    private JButton searchButton;
    private JTextArea resultArea;
    private JLabel timeLabel;

    public SearchSlang(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        slangField = new JTextField(5);
        searchButton = new JButton("Search Slang");
        resultArea = new JTextArea(5, 10);
        timeLabel = new JLabel("Search Time: 0 ms"); // Nhãn hiển thị thời gian tìm kiếm

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(searchButton);
        searchPanel.add(timeLabel); // Thêm nhãn kế bên nút search

        add(new JLabel("Enter Slang to Search:"));
        add(slangField);
        add(searchPanel); // Thêm panel chứa nút và nhãn thời gian
        add(new JScrollPane(resultArea));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slang = slangField.getText();

                // Bắt đầu đo thời gian
                long startTime = System.currentTimeMillis();

                String definition = slangDictionary.searchBySlangWord(slang);

                // Kết thúc đo thời gian
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;

                // Cập nhật nhãn thời gian
                timeLabel.setText("Search Time: " + elapsedTime + " ms");
                resultArea.setText(definition != null ? "Definition: " + definition : "Slang not found.");
            }
        });
    }
}
