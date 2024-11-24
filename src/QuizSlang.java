import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class QuizSlang extends JPanel {
    private SlangDictionary slangDictionary;
    private JButton startQuizButton;
    private JLabel questionLabel;
    private JButton[] answerButtons;

    public QuizSlang(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        startQuizButton = new JButton("Start Quiz");
        questionLabel = new JLabel("Pick the correct definition for the slang word");
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
        String slang = slangDictionary.getRandomSlangWord();
        String correctDefinition = slangDictionary.searchBySlangWord(slang);
        questionLabel.setText("What is the definition of: " + slang);

        List<String> options = new ArrayList<>(slangDictionary.getSlangMap().values());
        Collections.shuffle(options);

        for (int i = 0; i < 4; i++) {
            answerButtons[i].setText(options.get(i));
            final String answer = options.get(i);
            answerButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (answer.equals(correctDefinition)) {
                        JOptionPane.showMessageDialog(null, "Correct!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect!");
                    }
                }
            });
        }
    }
}
