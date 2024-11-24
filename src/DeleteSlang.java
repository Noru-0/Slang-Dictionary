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

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        slangField = new JTextField(20);
        deleteButton = new JButton("Delete Slang");
        messageLabel = new JLabel();

        add(new JLabel("Enter Slang Word to Delete:"));
        add(slangField);
        add(deleteButton);
        add(messageLabel);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slang = slangField.getText();
                if (slangDictionary.deleteSlangWord(slang)) {
                    messageLabel.setText("Slang word deleted successfully.");
                } else {
                    messageLabel.setText("Slang word not found.");
                }
            }
        });
    }
}
