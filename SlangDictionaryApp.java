import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SlangDictionaryApp extends JFrame {
    private SlangDictionary slangDictionary;

    public SlangDictionaryApp() {
        slangDictionary = new SlangDictionary();

        // Set JFrame
        setTitle("Slang Dictionary");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

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
        panel.add(searchSlangButton);
        panel.add(searchDefinitionButton);
        panel.add(historyButton);
        panel.add(addSlangButton);
        panel.add(editSlangButton);
        panel.add(deleteSlangButton);
        panel.add(resetButton);
        panel.add(randomButton);
        panel.add(quizSlangButton);
        panel.add(quizDefinitionButton);

        // Action Listeners

        // Search slang word
        searchSlangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slangWord = JOptionPane.showInputDialog("Input slang word to find: ");
                if (slangWord != null && !slangWord.isEmpty()) {
                    String definition = slangDictionary.searchBySlangWord((slangWord));
                    if (definition != null) {
                        JOptionPane.showMessageDialog(null, "Definition: " + definition);
                    } else {
                        JOptionPane.showMessageDialog(null, "Slang word is invalid.");
                    }
                }
                
            }
        });

        // Search by difinition
        searchDefinitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String definition = JOptionPane.showInputDialog("Input definition to find: ");
                if (definition != null && !definition.isEmpty()) {
                    List<String> slangWords = slangDictionary.searchByDefinition(definition);
                    if (!slangWords.isEmpty()) {
                        StringBuilder result = new StringBuilder("Slang words were found: ");
                        for (String slang : slangWords) {
                            result.append(slang).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, result.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Could not find suitable slang word.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please input valid definition.");
                }
            }
        });

        // Edit Slang Word
        editSlangButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldSlang = JOptionPane.showInputDialog("Input slang word to edit: ");
                if (oldSlang != null && !oldSlang.isEmpty()) {
                    if (slangDictionary.containsSlangWord(oldSlang)) {
                        String newDefinition = JOptionPane.showInputDialog("Input new definition: ");
                        if (newDefinition != null && newDefinition.isEmpty()) {
                            boolean edited = slangDictionary.editSlangWord(oldSlang, newDefinition);
                            if(edited) {
                                JOptionPane.showMessageDialog(null, "Edit succesfully.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error to edit.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Slang word is not exists.");
                        }
                    }
                }
            }
        }); 

        deleteSlangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slang = JOptionPane.showInputDialog("Input Slang word to delete: ");
                if (slang != null && !slang.isEmpty()) {
                    int confirm = JOptionPane.showConfirmDialog(null, "You want to delete this slang word?", "Yes", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean deleted = slangDictionary.deleteSlangWord(slang);
                        if (deleted) {
                            JOptionPane.showMessageDialog(null, "Delete successfully");
                        } else {
                            JOptionPane.showMessageDialog(null, "Slang word is not exists");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please input valid Slang word");
                }
            }
        });
        // Add to panel
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SlangDictionaryApp app = new SlangDictionaryApp();
                app.setVisible(true);
            }
        });
    }
}
