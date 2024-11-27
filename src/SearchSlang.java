import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchSlang extends JPanel {
    private SlangDictionary slangDictionary;
    private JTextField slangField;
    private JButton searchButton;
    private JTextArea resultArea;

    public SearchSlang(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Panel hold label and textfield
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("Enter Slang to Search:");
        inputPanel.add(label);

        slangField = new JTextField(30);
        inputPanel.add(slangField);

        // Panel have search button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchButton = new JButton("Search Slang");
        buttonPanel.add(searchButton);

        // Answer Area
        resultArea = new JTextArea(5, 20);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(200, 100));

        // Add panel
        add(inputPanel);
        add(buttonPanel);
        add(scrollPane);

        add(Box.createRigidArea(new Dimension(0, 200)));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slang = slangField.getText();
                String definition = slangDictionary.searchBySlangWord(slang, false);

                // Cập nhật kết quả
                resultArea.setText(definition != null ? "Definition: " + definition : "Slang not found.");
            }
        });
    }
}
