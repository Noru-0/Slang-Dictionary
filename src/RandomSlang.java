import java.awt.*;
import javax.swing.*;
import java.util.*;

public class RandomSlang extends JPanel {
    private SlangDictionary slangDictionary;
    private JButton randomButton;
    private JLabel slangLabel;
    private JLabel definitionLabel;

    public RandomSlang(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;

        // Set the layout for the panel
        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(400, 200));

        // Create components
        randomButton = new JButton("Get Random Slang");
        slangLabel = new JLabel("Random slang will appear here", SwingConstants.CENTER);
        definitionLabel = new JLabel("Definition will appear here", SwingConstants.CENTER);

        // Styling components
        slangLabel.setFont(new Font("Arial", Font.BOLD, 20));
        definitionLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        randomButton.setFont(new Font("Arial", Font.PLAIN, 16));
        randomButton.setFocusPainted(false);

        // Add components to the panel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(slangLabel);
        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(definitionLabel);

        add(randomButton, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

        // Add action listener to the random button
        randomButton.addActionListener(e -> {
            String randomSlang = slangDictionary.getRandomSlangWord();
            if (randomSlang != null) {
                String definition = slangDictionary.searchBySlangWord(randomSlang, true);
                slangLabel.setText(randomSlang);
                definitionLabel.setText(definition != null ? definition : "No definition available");
            } else {
                slangLabel.setText("No slang available");
                definitionLabel.setText("");
            }
        });
    }
}
