import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SlangDictionaryApp {
    public static void main(String[] args) {
        SlangDictionary slangDictionary = new SlangDictionary();

        JFrame frame = new JFrame("Slang Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 450);
        frame.setLocationRelativeTo(null);

        // TabbedPane for different features
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create panels for each feature
        JPanel addSlangPanel = new AddSlang(slangDictionary);
        JPanel deleteSlangPanel = new DeleteSlang(slangDictionary);
        JPanel editSlangPanel = new EditSlang(slangDictionary);
        JPanel quizDefinitionPanel = new QuizDefinition(slangDictionary);
        JPanel quizSlangPanel = new QuizSlang(slangDictionary);
        JPanel randomSlangPanel = new RandomSlang(slangDictionary);
        JPanel resetPanel = new Reset(slangDictionary);
        JPanel searchDefinitionPanel = new SearchDefinition(slangDictionary);
        JPanel searchSlangPanel = new SearchSlang(slangDictionary);
        JPanel viewHistoryPanel = new ViewHistory(slangDictionary);

        // Create a panel to hold buttons on the left side using GridBagLayout
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some space around buttons

        // Create buttons for each feature with numbering
        JButton searchSlangButton = createMenuButton("Search Slang", searchSlangPanel, tabbedPane);
        JButton searchDefinitionButton = createMenuButton("Search Definition", searchDefinitionPanel, tabbedPane);
        JButton viewHistoryButton = createMenuButton("Search History", viewHistoryPanel, tabbedPane);
        JButton addButton = createMenuButton("Add Slang", addSlangPanel, tabbedPane);
        JButton deleteButton = createMenuButton("Delete Slang", deleteSlangPanel, tabbedPane);
        JButton editButton = createMenuButton("Edit Slang", editSlangPanel, tabbedPane);
        JButton resetButton = createMenuButton("Reset Dictionary", resetPanel, tabbedPane);
        JButton randomSlangButton = createMenuButton("Random Slang", randomSlangPanel, tabbedPane);
        JButton quizSlangButton = createMenuButton("Quiz Slang", quizSlangPanel, tabbedPane);
        JButton quizDefinitionButton = createMenuButton("Quiz Definition", quizDefinitionPanel, tabbedPane);

        // Add the MENU label with centered alignment
        JLabel menuLabel = new JLabel("-----------MENU-----------");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Make the label span across columns
        gbc.anchor = GridBagConstraints.CENTER;
        menuPanel.add(menuLabel, gbc);

        // Add buttons to the menuPanel with equal width
        JButton[] buttons = { searchSlangButton, searchDefinitionButton, viewHistoryButton, addButton, deleteButton,
                editButton, resetButton, randomSlangButton, quizDefinitionButton, quizSlangButton };
        for (int i = 0; i < buttons.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.gridwidth = 2;
            menuPanel.add(buttons[i], gbc);
        }

        // Add space at the bottom to center the buttons
        gbc.gridx = 0;
        gbc.gridy = buttons.length + 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1;
        menuPanel.add(new JPanel(), gbc); // Add empty space at the bottom

        // Set the layout for the frame
        frame.setLayout(new BorderLayout());
        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(tabbedPane, BorderLayout.CENTER);

        // Add tabs for each panel
        tabbedPane.addTab("Search Slang", searchSlangPanel);
        tabbedPane.addTab("Search Definition", searchDefinitionPanel);
        tabbedPane.addTab("Search History", viewHistoryPanel);
        tabbedPane.addTab("Add Slang", addSlangPanel);
        tabbedPane.addTab("Delete Slang", deleteSlangPanel);
        tabbedPane.addTab("Edit Slang", editSlangPanel);
        tabbedPane.addTab("Reset Dictionary", resetPanel);
        tabbedPane.addTab("Random Slang", randomSlangPanel);
        tabbedPane.addTab("Quiz Definition", quizDefinitionPanel);
        tabbedPane.addTab("Quiz Slang", quizSlangPanel);

        // Show the frame
        frame.setVisible(true);
    }

    // Helper method to create a clickable button
    private static JButton createMenuButton(String text, JPanel panel, JTabbedPane tabbedPane) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to the left
        button.setFont(new Font("Arial", Font.PLAIN, 16)); // Font for menu buttons
        button.setPreferredSize(new Dimension(200, 25)); // Set button size (optional)

        // Add action listener to switch to corresponding panel
        button.addActionListener(e -> tabbedPane.setSelectedComponent(panel));

        return button;
    }
}
