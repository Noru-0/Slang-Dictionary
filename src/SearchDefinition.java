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

        // Panel hold label and textfield
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("Enter Definition to Search:"));

        definitionField = new JTextField(30);
        inputPanel.add(definitionField);

        // Panel have search button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchButton = new JButton("Search Definition");
        buttonPanel.add(searchButton);

        // Answer area
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
                String definition = definitionField.getText();

                List<String> slangWords = slangDictionary.searchByDefinition(definition);

                resultArea.setText(String.join("\n", slangWords));
            }
        });
    }
}
