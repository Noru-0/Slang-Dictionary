import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditSlang extends JPanel {
    private SlangDictionary slangDictionary;
    private JTextField slangField;
    private JTextField newDefinitionField;
    private JButton editButton;
    private JLabel messageLabel;

    public EditSlang(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        slangField = new JTextField(20);
        newDefinitionField = new JTextField(20);
        editButton = new JButton("Edit Slang");
        messageLabel = new JLabel();

        add(new JLabel("Enter Slang Word to Edit:"));
        add(slangField);
        add(new JLabel("Enter New Definition:"));
        add(newDefinitionField);
        add(editButton);
        add(messageLabel);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slang = slangField.getText();
                String newDefinition = newDefinitionField.getText();
                if (slangDictionary.editSlangWord(slang, newDefinition)) {
                    messageLabel.setText("Slang word edited successfully.");
                } else {
                    messageLabel.setText("Slang word not found.");
                }
            }
        });
    }
}
