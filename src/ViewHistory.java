import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewHistory extends JPanel {
    private JTextArea historyArea;
    private SlangDictionary slangDictionary;

    // Constructor to initialize ViewHistory with a reference to SlangDictionary
    public ViewHistory(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;

        // Set layout for the panel
        setLayout(new BorderLayout(10, 10));

        // Create label for the panel
        JLabel label = new JLabel("Search History:");

        // Create JTextArea to display the search history
        historyArea = new JTextArea(10, 30);
        historyArea.setEditable(false); // Make it read-only
        historyArea.setLineWrap(true); // Enable line wrapping
        historyArea.setWrapStyleWord(true); // Wrap whole words

        // Add JScrollPane to the text area to enable scrolling when content exceeds the
        // visible area
        JScrollPane scrollPane = new JScrollPane(historyArea);

        // Add label and scrollPane to the panel using BorderLayout
        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Display the search history
        displayHistory();
    }

    // Method to display the search history in the JTextArea
    private void displayHistory() {
        // Get the search history from SlangDictionary
        List<String> history = slangDictionary.getSearchHistory();

        // Check if the history is empty and set appropriate text
        if (history.isEmpty()) {
            historyArea.setText("No search history available.");
        } else {
            // Build a string representing the search history
            StringBuilder historyStr = new StringBuilder();
            for (String entry : history) {
                historyStr.append(entry).append("\n");
            }
            // Set the text of the JTextArea to the formatted search history
            historyArea.setText(historyStr.toString());
        }
    }
}
