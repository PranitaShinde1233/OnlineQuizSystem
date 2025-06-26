package online_quiz_system;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class TakeQuiz extends JFrame {
	JLabel questionLabel;
    JRadioButton aBtn, bBtn, cBtn, dBtn;
    JButton nextBtn;
    ButtonGroup options;
    Connection con;
    ResultSet rs;
    int score = 0;
    String username;

    public TakeQuiz(String user) {
        username = user;
        setTitle("Take Quiz - " + username);
        setLayout(null);
        questionLabel = new JLabel("Question will appear here");
        questionLabel.setBounds(20, 20, 450, 30);
        add(questionLabel);

        aBtn = new JRadioButton();
        bBtn = new JRadioButton();
        cBtn = new JRadioButton();
        dBtn = new JRadioButton();
        aBtn.setBounds(50, 70, 300, 25);
        bBtn.setBounds(50, 100, 300, 25);
        cBtn.setBounds(50, 130, 300, 25);
        dBtn.setBounds(50, 160, 300, 25);

        add(aBtn); add(bBtn); add(cBtn); add(dBtn);
        options = new ButtonGroup();
        options.add(aBtn); options.add(bBtn); options.add(cBtn); options.add(dBtn);

        nextBtn = new JButton("Next");
        nextBtn.setBounds(180, 200, 100, 30);
        add(nextBtn);

        try {
            con = DBConnection.getConnection();
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("SELECT * FROM questions");
            rs.next();
            showQuestion();
        } catch (Exception e) {
            System.out.println(e);
        }

        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                try {
                    if (rs.next()) {
                        showQuestion();
                    } else {
                        JOptionPane.showMessageDialog(null, "Quiz Finished. Your Score: " + score);
                        PreparedStatement pst = con.prepareStatement("INSERT INTO userscore (username, score) VALUES (?, ?)");
                        pst.setString(1, username);
                        pst.setInt(2, score);
                        pst.executeUpdate();
                        dispose();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setSize(500, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void showQuestion() throws SQLException {
        questionLabel.setText(rs.getString("question"));
        aBtn.setText("A. " + rs.getString("option_a"));
        bBtn.setText("B. " + rs.getString("option_b"));
        cBtn.setText("C. " + rs.getString("option_c"));
        dBtn.setText("D. " + rs.getString("option_d"));
        options.clearSelection();
    }

    void checkAnswer() {
        try {
            String correct = rs.getString("correct_option");
            if ((aBtn.isSelected() && correct.equals("A")) ||
                (bBtn.isSelected() && correct.equals("B")) ||
                (cBtn.isSelected() && correct.equals("C")) ||
                (dBtn.isSelected() && correct.equals("D"))) {
                score++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }


