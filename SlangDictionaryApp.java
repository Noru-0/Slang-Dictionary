import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Add to panel
        panel.add(searchSlangButton);
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
