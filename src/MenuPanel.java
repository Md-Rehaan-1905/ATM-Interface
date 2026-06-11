import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel {

    ATMInterface atm;
    JLabel titleLabel, balanceLabel;
    JButton historyButton, depositButton, withdrawButton, transferButton, logoutButton;

    public MenuPanel(ATMInterface atm) {
        this.atm = atm;

        setBackground(new Color(18, 28, 48));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        titleLabel = new JLabel("Main Menu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(100, 180, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        balanceLabel = new JLabel("Balance: Rs." + atm.balance);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        balanceLabel.setForeground(new Color(100, 220, 140));
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        historyButton  = createButton("📋  Transaction History", new Color(70, 100, 160));
        depositButton  = createButton("💰  Deposit",             new Color(50, 160, 100));
        withdrawButton = createButton("💸  Withdraw",            new Color(70, 100, 160));
        transferButton = createButton("🔄  Transfer",            new Color(70, 100, 160));
        logoutButton   = createButton("🚪  Logout",              new Color(160, 50, 50));

        historyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atm.transactionPanel.showTransaction("history");
                atm.showScreen("transaction");
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atm.transactionPanel.showTransaction("deposit");
                atm.showScreen("transaction");
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atm.transactionPanel.showTransaction("withdraw");
                atm.showScreen("transaction");
            }
        });

        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atm.transactionPanel.showTransaction("transfer");
                atm.showScreen("transaction");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atm.showScreen("login");
            }
        });

        add(titleLabel);
        add(Box.createVerticalStrut(8));
        add(balanceLabel);
        add(Box.createVerticalStrut(30));
        add(historyButton);
        add(Box.createVerticalStrut(12));
        add(depositButton);
        add(Box.createVerticalStrut(12));
        add(withdrawButton);
        add(Box.createVerticalStrut(12));
        add(transferButton);
        add(Box.createVerticalStrut(12));
        add(logoutButton);
    }

    void refreshBalance() {
        balanceLabel.setText("Balance: Rs." + atm.balance);
    }

    JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setMaximumSize(new Dimension(320, 48));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }
}