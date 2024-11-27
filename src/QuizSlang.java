import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class QuizSlang extends JPanel {
    private SlangDictionary slangDictionary;
    private JButton startQuizButton;
    private JLabel questionLabel, scoreLabel, questionCountLabel;
    private JButton[] answerButtons;
    private int score;
    private int questionCount;
    private static final int TOTAL_QUESTIONS = 10;

    public QuizSlang(SlangDictionary slangDictionary) {
        this.slangDictionary = slangDictionary;

        // Sử dụng BoxLayout để căn giữa tất cả các thành phần
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Tạo các thành phần giao diện
        startQuizButton = new JButton("Start Quiz");
        questionLabel = new JLabel("Pick the correct definition for the slang word", SwingConstants.CENTER);
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        questionCountLabel = new JLabel("Question: 0/" + TOTAL_QUESTIONS, SwingConstants.CENTER);

        // Đặt các label căn giữa
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startQuizButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Thêm các thành phần giao diện
        add(Box.createVerticalStrut(20)); // Khoảng cách trên
        add(scoreLabel);
        add(Box.createVerticalStrut(10));
        add(questionCountLabel);
        add(Box.createVerticalStrut(10));
        add(questionLabel);
        add(Box.createVerticalStrut(20));
        add(startQuizButton);
        add(Box.createVerticalStrut(20));

        // Tạo panel chứa các nút trả lời
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // Lưới 2x2, khoảng cách giữa các nút là 10px
        answerButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton();
            answerButtons[i].setPreferredSize(new Dimension(150, 30)); // Kích thước nút cố định
            buttonPanel.add(answerButtons[i]);
        }
        add(buttonPanel);

        // Gắn sự kiện cho nút Start Quiz
        startQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetQuiz();
                startQuiz();
            }
        });

        // Đặt khoảng cách bên dưới
        add(Box.createVerticalStrut(40));
    }

    private void resetQuiz() {
        score = 0;
        questionCount = 0;
        scoreLabel.setText("Score: 0");
        questionCountLabel.setText("Question: 0/" + TOTAL_QUESTIONS);
        startQuizButton.setEnabled(false);
    }

    private void startQuiz() {
        if (questionCount >= TOTAL_QUESTIONS) {
            JOptionPane.showMessageDialog(null, "Quiz finished! Your final score is: " + score);
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                resetQuiz();
                startQuiz();
            } else {
                startQuizButton.setEnabled(true);
            }
            return;
        }

        questionCount++;
        questionCountLabel.setText("Question: " + questionCount + "/" + TOTAL_QUESTIONS);

        String slang = slangDictionary.getRandomSlangWord();
        String correctDefinition = slangDictionary.searchBySlangWord(slang, true);

        questionLabel.setText("<html><center>What is the definition of: <b>" + slang + "</b></center></html>");

        // Lấy danh sách định nghĩa
        List<String> allDefinitions = new ArrayList<>(slangDictionary.getSlangMap().values());
        allDefinitions.remove(correctDefinition);

        // Chọn 3 đáp án sai ngẫu nhiên
        Collections.shuffle(allDefinitions);
        List<String> wrongDefinitions = allDefinitions.subList(0, 3);

        // Thêm câu trả lời đúng vào danh sách
        List<String> options = new ArrayList<>(wrongDefinitions);
        options.add(correctDefinition);
        Collections.shuffle(options);

        // Xóa tất cả ActionListener cũ
        for (JButton button : answerButtons) {
            for (ActionListener al : button.getActionListeners()) {
                button.removeActionListener(al);
            }
        }

        // Gán đáp án cho các nút
        for (int i = 0; i < 4; i++) {
            answerButtons[i].setText(options.get(i));
            final String answer = options.get(i);
            answerButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (answer.equals(correctDefinition)) {
                        JOptionPane.showMessageDialog(null, "Correct!");
                        score += 10; // Cộng điểm
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Incorrect! The correct answer is: " + correctDefinition);
                    }
                    scoreLabel.setText("Score: " + score);
                    startQuiz();
                }
            });
        }
    }

    public static void main(String[] args) {
        // Tạo JFrame chứa trò chơi
        JFrame frame = new JFrame("Slang Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setResizable(false);

        // Tạo đối tượng SlangDictionary và QuizSlang
        SlangDictionary slangDictionary = new SlangDictionary();
        QuizSlang quizSlang = new QuizSlang(slangDictionary);

        // Thêm QuizSlang vào JFrame
        frame.add(quizSlang);
        frame.setVisible(true);
    }
}
