package online_quiz_system;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddQuestion extends JFrame {
	JTextField qField, aField, bField, cField, dField, correctField;

    public AddQuestion() {
        setTitle("Add Quiz Question");
        setLayout(null);

        JLabel ql = new JLabel("Question:");
        ql.setBounds(20, 20, 100, 25);
        add(ql);
        qField = new JTextField();
        qField.setBounds(130, 20, 300, 25);
        add(qField);

        JLabel a = new JLabel("Option A:");
        a.setBounds(20, 60, 100, 25);
        add(a);
        aField = new JTextField();
        aField.setBounds(130, 60, 300, 25);
        add(aField);

        JLabel b = new JLabel("Option B:");
        b.setBounds(20, 100, 100, 25);
        add(b);
        bField = new JTextField();
        bField.setBounds(130, 100, 300, 25);
        add(bField);

        JLabel c = new JLabel("Option C:");
        c.setBounds(20, 140, 100, 25);
        add(c);
        cField = new JTextField();
        cField.setBounds(130, 140, 300, 25);
        add(cField);

        JLabel d = new JLabel("Option D:");
        d.setBounds(20, 180, 100, 25);
        add(d);
        dField = new JTextField();
        dField.setBounds(130, 180, 300, 25);
        add(dField);

        JLabel correct = new JLabel("Correct Option (A/B/C/D):");
        correct.setBounds(20, 220, 200, 25);
        add(correct);
        correctField = new JTextField();
        correctField.setBounds(230, 220, 50, 25);
        add(correctField);

        JButton addBtn = new JButton("Add Question");
        addBtn.setBounds(150, 260, 150, 30);
        add(addBtn);

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DBConnection.getConnection();
                    String sql = "INSERT INTO questions (question, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, qField.getText());
                    pst.setString(2, aField.getText());
                    pst.setString(3, bField.getText());
                    pst.setString(4, cField.getText());
                    pst.setString(5, dField.getText());
                    pst.setString(6, correctField.getText().toUpperCase());

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Question Added!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        setSize(500, 350);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
