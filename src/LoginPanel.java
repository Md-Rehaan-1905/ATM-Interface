import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {

    ATMInterface atm;
    JLabel titleLabel, subtitleLabel, userIDLabel, pinLabel, feedbackLabel;
    JTextField userIDField;
    JPasswordField pinField;
    JButton loginButton;

    public LoginPanel(ATMInterface atm) {
        this.atm = atm;

        setBackground(new Color(18, 28, 48));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(60, 80, 60, 80));

        titleLabel = new JLabel("💳 ATM Interface");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(new Color(100, 180, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        subtitleLabel = new JLabel("Please login to continue");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(150, 150, 180));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userIDLabel = new JLabel("User ID");
        userIDLabel.setFont(new Font("Arial", Font.BOLD, 13));
        userIDLabel.setForeground(new Color(200, 200, 220));
        userIDLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        userIDField = new JTextField(15);
        userIDField.setMaximumSize(new Dimension(300, 40));
        userIDField.setFont(new Font("Arial", Font.PLAIN, 14));
        userIDField.setBackground(new Color(30, 40, 65));
        userIDField.setForeground(Color.WHITE);
        userIDField.setCaretColor(Color.WHITE);
        userIDField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 180, 255), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        userIDField.setAlignmentX(Component.LEFT_ALIGNMENT);

        pinLabel = new JLabel("PIN");
        pinLabel.setFont(new Font("Arial", Font.BOLD, 13));
        pinLabel.setForeground(new Color(200, 200, 220));
        pinLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        pinField = new JPasswordField(15);
        pinField.setMaximumSize(new Dimension(300, 40));
        pinField.setFont(new Font("Arial", Font.PLAIN, 14));
        pinField.setBackground(new Color(30, 40, 65));
        pinField.setForeground(Color.WHITE);
        pinField.setCaretColor(Color.WHITE);
        pinField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 180, 255), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        pinField.setAlignmentX(Component.LEFT_ALIGNMENT);

        feedbackLabel = new JLabel(" ");
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        feedbackLabel.setForeground(new Color(255, 100, 100));
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 15));
        loginButton.setBackground(new Color(100, 180, 255));
        loginButton.setForeground(new Color(18, 28, 48));
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setMaximumSize(new Dimension(300, 45));
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        add(titleLabel);
        add(Box.createVerticalStrut(8));
        add(subtitleLabel);
        add(Box.createVerticalStrut(40));
        add(userIDLabel);
        add(Box.createVerticalStrut(6));
        add(userIDField);
        add(Box.createVerticalStrut(20));
        add(pinLabel);
        add(Box.createVerticalStrut(6));
        add(pinField);
        add(Box.createVerticalStrut(10));
        add(feedbackLabel);
        add(Box.createVerticalStrut(10));
        add(loginButton);
    }

    void handleLogin() {
        String enteredID = userIDField.getText().trim();
        String enteredPIN = new String(pinField.getPassword());

        if (enteredID.equals(atm.userID) && enteredPIN.equals(atm.userPIN)) {
            feedbackLabel.setText(" ");
            userIDField.setText("");
            pinField.setText("");
            atm.showScreen("menu");
        } else {
            feedbackLabel.setText("Invalid User ID or PIN. Try again.");
        }
    }
}