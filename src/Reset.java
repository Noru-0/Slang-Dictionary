import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Reset extends JPanel {
    private SlangDictionary slangDictionary;
    private JButton resetButton;
    private JLabel messageLabel;

    public Reset(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        resetButton = new JButton("Reset Dictionary");
        messageLabel = new JLabel();

        add(resetButton);
        add(messageLabel);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slangDictionary.resetDictionary();
                messageLabel.setText("Dictionary has been reset to original.");
            }
        });
    }
}
