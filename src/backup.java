import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SlangDictionaryApp extends JFrame {
    private SlangDictionary slangDictionary;
    private JPanel featurePanel;
    private JTextField inputField;
    private JTextArea outputArea;

    public SlangDictionaryApp() {
        slangDictionary = new SlangDictionary();

        // Set JFrame
        setTitle("Slang Dictionary");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));

        // Create title
        JLabel titleLabel = new JLabel("SLANG WORDS DICTIONARY", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create panel contain button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 1, 10, 10));

        // Create button
        JButton searchSlangButton = new JButton("1. Search by slang word");
        JButton searchDefinitionButton = new JButton("2. Search by definition");
        JButton historyButton = new JButton("3. View search history");
        JButton addSlangButton = new JButton("4. Add new slang word");
        JButton editSlangButton = new JButton("5. Edit slang word");
        JButton deleteSlangButton = new JButton("6. Delete slang word");
        JButton resetButton = new JButton("7. Reset the original slang word list");
        JButton randomButton = new JButton("8. Show random slang word");
        JButton quizSlangButton = new JButton("9. Quiz (Choose the right definition)");
        JButton quizDefinitionButton = new JButton("10. Quiz (Choose the right slang word)");

        // Add button to panel
        buttonPanel.add(searchSlangButton);
        buttonPanel.add(searchDefinitionButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(addSlangButton);
        buttonPanel.add(editSlangButton);
        buttonPanel.add(deleteSlangButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(randomButton);
        buttonPanel.add(quizSlangButton);
        buttonPanel.add(quizDefinitionButton);

        // Create the feature panel where results will be displayed
        featurePanel = new JPanel();
        featurePanel.setLayout(new BorderLayout());

        // Create the input area for user to type
        JPanel inputPanel = new JPanel();
        inputField = new JTextField(15);
        inputPanel.add(inputField);

        // Create the output area for displaying results
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        featurePanel.add(scrollPane, BorderLayout.CENTER);

        // Add panels to main panel
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(featurePanel, BorderLayout.CENTER);
        add(mainPanel);

        // Action Listener - Search slang word
        searchSlangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slangWord = inputField.getText().trim();
                if (!slangWord.isEmpty()) {
                    slangDictionary.addSearchHistory(slangWord);
                    String definition = slangDictionary.searchBySlangWord(slangWord);
                    displayFeaturePanel(
                            "Definition for '" + slangWord + "': " + (definition != null ? definition : "Not found"));
                } else {
                    displayFeaturePanel("Please enter a slang word.");
                }
            }
        });

        // Action Listener - Search by definition
        searchDefinitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String definition = inputField.getText().trim();
                if (!definition.isEmpty()) {
                    List<String> slangWords = slangDictionary.searchByDefinition(definition);
                    StringBuilder result = new StringBuilder("Slang words for the definition '" + definition + "':\n");
                    if (!slangWords.isEmpty()) {
                        for (String slang : slangWords) {
                            result.append(slang).append("\n");
                        }
                    } else {
                        result.append("No slang words found.");
                    }
                    displayFeaturePanel(result.toString());
                } else {
                    displayFeaturePanel("Please enter a definition.");
                }
            }
        });

        // Action Listener - Add Slang Word
        addSlangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slang = inputField.getText().trim();
                String definition = outputArea.getText().trim(); // Use outputArea as definition input
                if (!slang.isEmpty() && !definition.isEmpty()) {
                    boolean success = slangDictionary.addSlangWord(slang, definition);
                    displayFeaturePanel(success ? "Slang word added successfully!" : "Failed to add slang word.");
                } else {
                    displayFeaturePanel("Slang word and definition cannot be empty.");
                }
            }
        });

        // Action Listener - Edit Slang Word
        editSlangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slang = inputField.getText().trim();
                String newDefinition = outputArea.getText().trim(); // Use outputArea as definition input
                if (!slang.isEmpty() && !newDefinition.isEmpty()) {
                    boolean edited = slangDictionary.editSlangWord(slang, newDefinition);
                    displayFeaturePanel(edited ? "Slang word edited successfully!" : "Error editing slang word.");
                } else {
                    displayFeaturePanel("Please enter valid slang word and definition.");
                }
            }
        });

        // Action Listener - Delete Slang Word
        deleteSlangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slang = inputField.getText().trim();
                if (!slang.isEmpty()) {
                    boolean deleted = slangDictionary.deleteSlangWord(slang);
                    displayFeaturePanel(deleted ? "Slang word deleted successfully!" : "Slang word not found.");
                } else {
                    displayFeaturePanel("Please enter a slang word.");
                }
            }
        });

        // Action Listener - View Search History
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> history = slangDictionary.getSearchHistory();
                StringBuilder historyStr = new StringBuilder("Search History:\n");
                if (history.isEmpty()) {
                    historyStr.append("No search history available.");
                } else {
                    for (String entry : history) {
                        historyStr.append(entry).append("\n");
                    }
                }
                displayFeaturePanel(historyStr.toString());
            }
        });

        // Action Listener - Reset Dictionary
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slangDictionary.resetDictionary();
                displayFeaturePanel("Slang dictionary has been reset to the original version.");
            }
        });

        // Action Listener - Show Random Slang Word
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String randomSlang = slangDictionary.getRandomSlangWord();
                if (randomSlang != null) {
                    String definition = slangDictionary.searchBySlangWord(randomSlang);
                    displayFeaturePanel("Random Slang Word: " + randomSlang + " - Definition: "
                            + (definition != null ? definition : "Not found"));
                } else {
                    displayFeaturePanel("No slang words available.");
                }
            }
        });

        // Action Listener - Quiz Slang Word (Choose the right definition)
        quizSlangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String randomSlang = slangDictionary.getRandomSlangWord();
                String correctDefinition = slangDictionary.searchBySlangWord(randomSlang);
                displayFeaturePanel("Quiz - Slang Word: " + randomSlang + " - Definition: "
                        + (correctDefinition != null ? correctDefinition : "Not found"));
            }
        });

        // Action Listener - Quiz Definition (Choose the right slang word)
        // quizDefinitionButton.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // String randomDefinition = slangDictionary.getRandomDefinition();
        // List<String> correctSlangWords =
        // slangDictionary.searchByDefinition(randomDefinition);
        // displayFeaturePanel(
        // "Quiz - Definition: " + randomDefinition + "\nPossible Slang Words: " +
        // correctSlangWords);
        // }
        // });

        setVisible(true);
    }

    // Method to display feature panel content
    private void displayFeaturePanel(String content) {
        outputArea.setText(content);
    }

    public static void main(String[] args) {
        new SlangDictionaryApp();
    }
}
