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
        tableModel = new DefaultTableModel(new String[] { "No.", "Slang Word", "Definition" }, 0);

        // Create JTable using the table model
        historyTable = new JTable(tableModel);
        historyTable.setFillsViewportHeight(true); // Fills empty space in the table
        historyTable.setEnabled(false); // Make it read-only

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
    }

    // Method to display the search history in the JTable
    private void displayHistory() {
        // Clear the table model
        tableModel.setRowCount(0);

        // Get the search history from SlangDictionary
        List<String> history = slangDictionary.getSearchHistory();

        // Check if the history is empty and set appropriate data
        if (history.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No search history available.", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Populate the table model with history data
            int index = 1;
            for (String slangWord : history) {
                String definition = slangDictionary.searchBySlangWord(slangWord);
                tableModel.addRow(new Object[] { index++, slangWord, definition });
            }
        }
    }

    // Method to show or hide the history panel
    public void toggleVisibility() {
        this.setVisible(!this.isVisible());
        if (this.isVisible()) {
            displayHistory(); // Refresh the table content when showing
        }
    }
}
