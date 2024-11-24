import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class SlangQuiz extends JFrame {
    private SlangDictionary slangDictionary;
    private String correctAnswer;
    private List<JButton> optionButtons;

    public SlangQuiz(SlangDictionary dictionary, boolean isWordQuiz) {
        this.slangDictionary = dictionary;
        this.optionButtons = new ArrayList<>();
        setTitle(isWordQuiz ? "Slang Quiz - Guess the Definition" : "Slang Quiz - Guess the Word");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create Quiz Content
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        quizPanel.add(questionLabel);

        List<String> options = isWordQuiz ? generateDefinitionQuiz(questionLabel) : generateWordQuiz(questionLabel);

        // Add Buttons for options
        for (String option : options) {
            JButton optionButton = new JButton(option);
            optionButtons.add(optionButton);
            optionButton.addActionListener(new OptionButtonListener(option));
            quizPanel.add(optionButton);
        }

        add(quizPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    // Generate Quiz with a random slang word, show definition options
    private List<String> generateDefinitionQuiz(JLabel questionLabel) {
        String randomSlang = slangDictionary.getRandomSlangWord();
        correctAnswer = slangDictionary.searchBySlangWord(randomSlang);

        questionLabel.setText("What is the definition of: " + randomSlang + "?");

        List<String> options = new ArrayList<>();
        options.add(correctAnswer);

        // Add 3 random incorrect answers
        while (options.size() < 4) {
            String randomDefinition = getRandomDefinition();
            if (!options.contains(randomDefinition)) {
                options.add(randomDefinition);
            }
        }
        Collections.shuffle(options);
        return options;
    }

    // Generate Quiz with a random definition, show slang word options
    private List<String> generateWordQuiz(JLabel questionLabel) {
        String randomDefinition = getRandomDefinition();
        correctAnswer = slangDictionary.getDefinitionMap().get(randomDefinition).get(0);

        questionLabel.setText("Which slang word matches the definition: " + randomDefinition + "?");

        List<String> options = new ArrayList<>();
        options.add(correctAnswer);

        // Add 3 random incorrect slang words
        while (options.size() < 4) {
            String randomSlang = slangDictionary.getRandomSlangWord();
            if (!options.contains(randomSlang)) {
                options.add(randomSlang);
            }
        }
        Collections.shuffle(options);
        return options;
    }

    // Helper to get a random definition
    private String getRandomDefinition() {
        List<String> keys = new ArrayList<>(slangDictionary.getDefinitionMap().keySet());
        return keys.get(new Random().nextInt(keys.size()));
    }

    // Listener for Option Buttons
    private class OptionButtonListener implements ActionListener {
        private String selectedOption;

        public OptionButtonListener(String selectedOption) {
            this.selectedOption = selectedOption;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedOption.equals(correctAnswer)) {
                JOptionPane.showMessageDialog(SlangQuiz.this, "Correct! The answer is: " + correctAnswer, "Result",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(SlangQuiz.this, "Wrong! The correct answer is: " + correctAnswer,
                        "Result", JOptionPane.ERROR_MESSAGE);
            }
            dispose(); // Close the quiz window
        }
    }

    public static void main(String[] args) {
        SlangDictionary dictionary = new SlangDictionary();
        new SlangQuiz(dictionary, true); // Slang word -> Definition
        new SlangQuiz(dictionary, false); // Definition -> Slang word
    }
}
