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

        slangField = new JTextField(20);
        searchButton = new JButton("Search Slang");
        resultArea = new JTextArea(5, 20);

        add(new JLabel("Enter Slang to Search:"));
        add(slangField);
        add(searchButton);
        add(new JScrollPane(resultArea));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slang = slangField.getText();
                String definition = slangDictionary.searchBySlangWord(slang);
                resultArea.setText(definition != null ? definition : "Slang not found.");
            }
        });
    }
}
