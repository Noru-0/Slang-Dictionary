import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddSlang extends JPanel {
    private SlangDictionary slangDictionary;
    private JTextField slangField;
    private JTextField definitionField;
    private JButton addButton;
    private JLabel messageLabel;

    public AddSlang(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        slangField = new JTextField(20);
        definitionField = new JTextField(20);
        addButton = new JButton("Add Slang");
        messageLabel = new JLabel();

        add(new JLabel("Enter Slang Word:"));
        add(slangField);
        add(new JLabel("Enter Definition:"));
        add(definitionField);
        add(addButton);
        add(messageLabel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slang = slangField.getText();
                String definition = definitionField.getText();
                if (slangDictionary.addSlangWord(slang, definition)) {
                    messageLabel.setText("Slang word added successfully.");
                } else {
                    messageLabel.setText("Slang word already exists.");
                }
            }
        });
    }
}
