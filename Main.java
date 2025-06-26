package online_quiz_system;
import javax.swing.*;
import java.awt.event.*;

public class Main {
	public static void main(String[] args) {
        JFrame frame = new JFrame("Online Quiz System");
        frame.setLayout(null);

        JButton adminBtn = new JButton("Admin - Add Question");
        adminBtn.setBounds(50, 50, 200, 40);
        frame.add(adminBtn);

        JButton userBtn = new JButton("User - Take Quiz");
        userBtn.setBounds(50, 110, 200, 40);
        frame.add(userBtn);

        adminBtn.addActionListener(e -> new AddQuestion());
        userBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter your name:");
            if (name != null && !name.trim().isEmpty()) {
                new TakeQuiz(name.trim());
            }
        });

        frame.setSize(320, 250);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
