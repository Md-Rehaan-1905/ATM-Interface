import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TransactionPanel extends JPanel {

    ATMInterface atm;
    String currentMode;

    JLabel titleLabel, amountLabel, feedbackLabel;
    JTextField amountField;
    JTextArea historyArea;
    JButton actionButton, backButton;

    public TransactionPanel(ATMInterface atm) {
        this.atm = atm;

        setBackground(new Color(18, 28, 48));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        titleLabel = new JLabel("Transaction");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(100, 180, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        amountLabel = new JLabel("Enter Amount (Rs.)");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 13));
        amountLabel.setForeground(new Color(200, 200, 220));
        amountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        amountField = new JTextField(15);
        amountField.setMaximumSize(new Dimension(300, 40));
        amountField.setFont(new Font("Arial", Font.PLAIN, 14));
        amountField.setBackground(new Color(30, 40, 65));
        amountField.setForeground(Color.WHITE);
        amountField.setCaretColor(Color.WHITE);
        amountField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 180, 255), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        amountField.setAlignmentX(Component.LEFT_ALIGNMENT);

        feedbackLabel = new JLabel(" ");
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        historyArea = new JTextArea(10, 20);
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Arial", Font.PLAIN, 13));
        historyArea.setBackground(new Color(30, 40, 65));
        historyArea.setForeground(new Color(200, 220, 255));
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(350, 200));

        actionButton = new JButton("CONFIRM");
        actionButton.setFont(new Font("Arial", Font.BOLD, 14));
        actionButton.setBackground(new Color(50, 160, 100));
        actionButton.setForeground(Color.WHITE);
        actionButton.setFocusPainted(false);
        actionButton.setBorderPainted(false);
        actionButton.setMaximumSize(new Dimension(300, 45));
        actionButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        backButton = new JButton("← BACK TO MENU");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(160, 50, 50));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setMaximumSize(new Dimension(300, 45));
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        actionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleTransaction();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atm.menuPanel.refreshBalance();
                atm.showScreen("menu");
            }
        });

        add(titleLabel);
        add(Box.createVerticalStrut(25));
        add(amountLabel);
        add(Box.createVerticalStrut(8));
        add(amountField);
        add(Box.createVerticalStrut(10));
        add(scrollPane);
        add(Box.createVerticalStrut(10));
        add(feedbackLabel);
        add(Box.createVerticalStrut(15));
        add(actionButton);
        add(Box.createVerticalStrut(10));
        add(backButton);
    }

    void showTransaction(String mode) {
        currentMode = mode;
        feedbackLabel.setText(" ");
        amountField.setText("");

        if (mode.equals("history")) {
            titleLabel.setText("Transaction History");
            amountLabel.setVisible(false);
            amountField.setVisible(false);
            actionButton.setVisible(false);
            historyArea.setVisible(true);
            historyArea.setText(atm.transactionHistory);
        } else {
            titleLabel.setText(
                    mode.equals("deposit") ? "Deposit" :
                            mode.equals("withdraw") ? "Withdraw" : "Transfer"
            );
            amountLabel.setVisible(true);
            amountField.setVisible(true);
            actionButton.setVisible(true);
            historyArea.setVisible(false);
        }
    }

    void handleTransaction() {
        double amount;
        try {
            amount = Double.parseDouble(amountField.getText().trim());
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Please enter a valid amount!");
            feedbackLabel.setForeground(new Color(255, 100, 100));
            return;
        }

        if (amount <= 0) {
            feedbackLabel.setText("Amount must be greater than 0!");
            feedbackLabel.setForeground(new Color(255, 100, 100));
            return;
        }

        if (currentMode.equals("deposit")) {
            atm.balance += amount;
            atm.transactionHistory += "Deposited: Rs." + amount + "\n";
            feedbackLabel.setText("Successfully deposited Rs." + amount);
            feedbackLabel.setForeground(new Color(100, 220, 140));

        } else if (currentMode.equals("withdraw")) {
            if (amount > atm.balance) {
                feedbackLabel.setText("Insufficient balance!");
                feedbackLabel.setForeground(new Color(255, 100, 100));
                return;
            }
            atm.balance -= amount;
            atm.transactionHistory += "Withdrew: Rs." + amount + "\n";
            feedbackLabel.setText("Successfully withdrew Rs." + amount);
            feedbackLabel.setForeground(new Color(100, 220, 140));

        } else if (currentMode.equals("transfer")) {
            if (amount > atm.balance) {
                feedbackLabel.setText("Insufficient balance!");
                feedbackLabel.setForeground(new Color(255, 100, 100));
                return;
            }
            atm.balance -= amount;
            atm.transactionHistory += "Transferred: Rs." + amount + "\n";
            feedbackLabel.setText("Successfully transferred Rs." + amount);
            feedbackLabel.setForeground(new Color(100, 220, 140));
        }

        amountField.setText("");
    }
}