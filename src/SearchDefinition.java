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

        definitionField = new JTextField(20);
        searchButton = new JButton("Search Definition");
        resultArea = new JTextArea(5, 20);

        add(new JLabel("Enter Definition to Search:"));
        add(definitionField);
        add(searchButton);
        add(new JScrollPane(resultArea));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String definition = definitionField.getText();
                List<String> slangWords = slangDictionary.searchByDefinition(definition);
                resultArea.setText(String.join("\n", slangWords));
            }
        });
    }
}
