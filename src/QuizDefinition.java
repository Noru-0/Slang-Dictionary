import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class QuizDefinition extends JPanel {
    private SlangDictionary slangDictionary;
    private JButton startQuizButton;
    private JLabel questionLabel;
    private JButton[] answerButtons;

    public QuizDefinition(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        startQuizButton = new JButton("Start Quiz");
        questionLabel = new JLabel("Pick the correct slang word for the definition");
        answerButtons = new JButton[4];

        add(startQuizButton);
        add(questionLabel);

        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton();
            add(answerButtons[i]);
        }

        startQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        String definition = slangDictionary.getRandomSlangWord();
        questionLabel.setText("Which slang matches this definition: " + definition);

        List<String> options = new ArrayList<>(slangDictionary.getSlangMap().keySet());
        Collections.shuffle(options);

        for (int i = 0; i < 4; i++) {
            answerButtons[i].setText(options.get(i));
            final String answer = options.get(i);
            answerButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (answer.equals(definition)) {
                        JOptionPane.showMessageDialog(null, "Correct!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect!");
                    }
                }
            });
        }
    }
}
