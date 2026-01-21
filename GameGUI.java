package numberguessinggame;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameGUI extends JFrame {

    private int randomNumber;
    private int attempts = 0;
    private final int MAX_ATTEMPTS = 7;

    JTextField guessField;
    JLabel messageLabel;

    public GameGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        randomNumber = new Random().nextInt(100) + 1;

        JLabel title = new JLabel("Guess Number (1 to 100)");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        guessField = new JTextField();
        JButton guessBtn = new JButton("Guess");
        messageLabel = new JLabel("Attempts left: 7", SwingConstants.CENTER);

        guessBtn.addActionListener(e -> checkGuess());

        setLayout(new GridLayout(5, 1));
        add(title);
        add(guessField);
        add(guessBtn);
        add(messageLabel);

        setVisible(true);
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if (guess == randomNumber) {
                int score = (MAX_ATTEMPTS - attempts + 1) * 10;
                String name = JOptionPane.showInputDialog(this, "You Win! Enter your name:");
                GameDAO.saveResult(name, attempts, score);

                JOptionPane.showMessageDialog(this,
                        "Correct!\nAttempts: " + attempts + "\nScore: " + score);

                System.exit(0);
            } else if (guess > randomNumber) {
                messageLabel.setText("Too High! Attempts left: " + (MAX_ATTEMPTS - attempts));
            } else {
                messageLabel.setText("Too Low! Attempts left: " + (MAX_ATTEMPTS - attempts));
            }

            if (attempts == MAX_ATTEMPTS) {
                JOptionPane.showMessageDialog(this,
                        "Game Over! Number was: " + randomNumber);
                System.exit(0);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter valid number!");
        }
    }

    public static void main(String[] args) {
        new GameGUI();
    }
}
