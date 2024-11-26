import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;

public class SearchDefinition extends JPanel {
    private SlangDictionary slangDictionary;
    private JTextField definitionField;
    private JButton searchButton;
    private JTextArea resultArea;
    private JLabel timeLabel;

    public SearchDefinition(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        definitionField = new JTextField(20);
        searchButton = new JButton("Search Definition");
        resultArea = new JTextArea(5, 20);
        timeLabel = new JLabel("Search Time: 0 ms");

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(searchButton);
        searchPanel.add(timeLabel);

        add(new JLabel("Enter Definition to Search:"));
        add(definitionField);
        add(searchPanel);
        add(new JScrollPane(resultArea));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String definition = definitionField.getText();

                long startTime = System.currentTimeMillis();

                List<String> slangWords = slangDictionary.searchByDefinition(definition);

                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;

                timeLabel.setText("Search Time: " + elapsedTime + " ms");
                resultArea.setText(String.join("\n", slangWords));
            }
        });
    }
}
