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

        // Sử dụng GridBagLayout để tùy chỉnh vị trí các thành phần
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tạo trường nhập
        JLabel promptLabel = new JLabel("Enter Slang Word to Delete:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(promptLabel, gbc);

        slangField = new JTextField(20);
        gbc.gridy = 1;
        add(slangField, gbc);

        // Tạo nút delete
        deleteButton = new JButton("Delete Slang");
        gbc.gridy = 2;
        add(deleteButton, gbc);

        // Tạo nhãn hiển thị thông báo
        messageLabel = new JLabel("", SwingConstants.CENTER);
        gbc.gridy = 3;
        gbc.weighty = 1.0; // Chừa khoảng trống bên dưới
        add(messageLabel, gbc);

        // Xử lý sự kiện nút delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slang = slangField.getText().trim();
                if (slangDictionary.deleteSlangWord(slang)) {
                    messageLabel.setText("Slang word deleted successfully.");
                } else {
                    messageLabel.setText("Slang word not found.");
                }
            }
        });
    }
}
