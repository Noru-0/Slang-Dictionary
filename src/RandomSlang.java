import java.awt.*;
import javax.swing.*;
import java.util.*;

public class RandomSlang extends JPanel {
    private SlangDictionary slangDictionary;
    private JButton randomButton;
    private JLabel slangLabel;

    public RandomSlang(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        randomButton = new JButton("Get Random Slang");
        slangLabel = new JLabel("Random slang will appear here");

        add(randomButton);
        add(slangLabel);

        randomButton.addActionListener(e -> {
            String randomSlang = slangDictionary.getRandomSlangWord();
            slangLabel.setText(randomSlang);
        });
    }
}
