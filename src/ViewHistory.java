import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewHistory extends JPanel {
    private JTable historyTable;
    private DefaultTableModel tableModel;
    private SlangDictionary slangDictionary;

    // Constructor
    public ViewHistory(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;

        // Set layout for the panel
        setLayout(new BorderLayout(10, 10));

        // Create label for the panel
        JLabel label = new JLabel("Search History:");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Create table model with column names
        tableModel = new DefaultTableModel(new String[] { "Time", "Slang Word", "Definition" }, 0);

        // Create JTable using the table model
        historyTable = new JTable(tableModel);
        historyTable.setFillsViewportHeight(true);
        historyTable.setEnabled(false);

        // Center align the table content
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < historyTable.getColumnCount(); i++) {
            historyTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Add JScrollPane to the table to enable scrolling
        JScrollPane scrollPane = new JScrollPane(historyTable);

        // Add label and scrollPane to the panel using BorderLayout
        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add a refresh button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> displayHistory());
        add(refreshButton, BorderLayout.SOUTH);

        // Initially hide the panel
        this.setVisible(false);

        displayHistory();
    }

    private void displayHistory() {
        // Clear the table model
        tableModel.setRowCount(0);

        // Get the search history from SlangDictionary
        List<Pair<String, String>> history = slangDictionary.getSearchHistory();

        // Check if the history is empty and set appropriate data
        if (!history.isEmpty()) {
            // Populate the table model with history data
            for (int i = history.size() - 1; i >= 0; i--) {
                Pair<String, String> entry = history.get(i);
                String slangWord = entry.getKey();
                String time = entry.getValue();

                // Check if the slang word exists in the dictionary
                String definition = slangDictionary.getSlangMap().get(slangWord);

                // If the slang word has been deleted or its definition is missing, display a
                // message
                if (definition == null) {
                    definition = "Definition no longer available";
                }

                // Add the row with the time, slang word, and its definition
                tableModel.addRow(new Object[] { time, slangWord, definition });
            }
        } else if (this.isVisible()) {
            JOptionPane.showMessageDialog(this, "No search history available.", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Method to show or hide the history panel
    public void toggleVisibility() {
        this.setVisible(!this.isVisible());
        if (this.isVisible()) {
            displayHistory();
        }
    }
}
