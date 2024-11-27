import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DeleteSlang extends JPanel {
    private SlangDictionary slangDictionary;
    private JTextField slangField;
    private JButton deleteButton;
    private JLabel messageLabel;

    public DeleteSlang(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel promptLabel = new JLabel("Enter Slang Word to Delete:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(promptLabel, gbc);

        slangField = new JTextField(20);
        gbc.gridy = 1;
        add(slangField, gbc);

        deleteButton = new JButton("Delete Slang");
        gbc.gridy = 2;
        add(deleteButton, gbc);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        gbc.gridy = 3;
        gbc.weighty = 1.0; // Chừa khoảng trống bên dưới
        add(messageLabel, gbc);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slang = slangField.getText().trim();

                if (slang.isEmpty()) {
                    messageLabel.setText("Please enter a slang word.");
                    return;
                }

                // Comfirm before deleting
                int response = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete the slang word: " + slang + "?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    if (slangDictionary.deleteSlangWord(slang)) {
                        messageLabel.setText("Slang word deleted successfully.");
                    } else {
                        messageLabel.setText("Slang word not found.");
                    }
                }
            }
        });
    }
}
