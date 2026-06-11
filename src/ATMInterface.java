import javax.swing.*;
import java.awt.*;

public class ATMInterface extends JFrame {

    // CardLayout and main container
    CardLayout cardLayout;
    JPanel mainContainer;

    // Account data — hardcoded for now
    String userID = "rehaan";
    String userPIN = "1234";
    double balance = 10000.00;
    String transactionHistory = "Account created with balance: 10000.00\n";

    // Panel references
    LoginPanel loginPanel;
    MenuPanel menuPanel;
    TransactionPanel transactionPanel;

    public ATMInterface() {
        setTitle("ATM Interface");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set up CardLayout
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // create all panels
        loginPanel = new LoginPanel(this);
        menuPanel = new MenuPanel(this);
        transactionPanel = new TransactionPanel(this);

        // add panels as cards with a name
        mainContainer.add(loginPanel, "login");
        mainContainer.add(menuPanel, "menu");
        mainContainer.add(transactionPanel, "transaction");

        add(mainContainer);

        // show login screen first
        cardLayout.show(mainContainer, "login");

        setVisible(true);
    }

    // called from any panel to switch screens
    void showScreen(String screenName) {
        cardLayout.show(mainContainer, screenName);
    }

    public static void main(String[] args) {
        new ATMInterface();
    }
}