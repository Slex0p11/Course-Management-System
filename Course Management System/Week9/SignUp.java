package Week9;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SignUp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textuserName;
    private JTextField em;
    private JPasswordField textPassword;
    private JPasswordField p2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SignUp frame = new SignUp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void la() {
        try {
            LoginPg frame = new LoginPg();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SignUp() {
        setBackground(Color.CYAN);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 597, 437);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSignUpPage = new JLabel("Sign Up ");
        lblSignUpPage.setForeground(Color.BLUE);
        lblSignUpPage.setFont(new Font("Times New Roman", Font.BOLD, 17));
        lblSignUpPage.setBounds(10, 22, 129, 19);
        contentPane.add(lblSignUpPage);

        JLabel lblUserId = new JLabel("User Id");
        lblUserId.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblUserId.setBounds(100, 72, 129, 13);
        contentPane.add(lblUserId);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblPassword.setBounds(100, 112, 129, 13);
        contentPane.add(lblPassword);

        JLabel lblSignUpPage_2_1 = new JLabel("Confirm Password");
        lblSignUpPage_2_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblSignUpPage_2_1.setBounds(100, 152, 153, 13);
        contentPane.add(lblSignUpPage_2_1);

        JLabel lblSignUpPage_2_2 = new JLabel("Email");
        lblSignUpPage_2_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblSignUpPage_2_2.setBounds(100, 188, 129, 13);
        contentPane.add(lblSignUpPage_2_2);

        JLabel lblSignUpPage_2_2_1 = new JLabel("User Mode");
        lblSignUpPage_2_2_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblSignUpPage_2_2_1.setBounds(100, 231, 129, 13);
        contentPane.add(lblSignUpPage_2_2_1);

        textuserName = new JTextField();
        textuserName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        textuserName.setBounds(257, 70, 96, 19);
        contentPane.add(textuserName);
        textuserName.setColumns(10);

        em = new JTextField();
        em.setColumns(10);
        em.setBounds(257, 186, 153, 19);
        contentPane.add(em);

        JComboBox comboSelectMode = new JComboBox();
        comboSelectMode.setModel(new DefaultComboBoxModel(new String[] { "Student", "Admin", "Teacher" }));
        comboSelectMode.setMaximumRowCount(3);
        comboSelectMode.setBounds(257, 228, 96, 21);
        contentPane.add(comboSelectMode);

        JButton btnNewButton = new JButton("SignUp");
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setBackground(Color.CYAN);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databaseconnection",
                            "root", "");
                    Statement st = con.createStatement();

                    String query = "CREATE TABLE IF NOT EXISTS user(username varchar(255), email varchar(255), password varchar(255), role varchar(255))";
                    st.execute(query);

                    String username = textuserName.getText();
                    String em1 = em.getText();
                    String password = textPassword.getText();
                    String comboValue = (String) comboSelectMode.getSelectedItem();

                    String insertQuery = "INSERT INTO user VALUES ('" + username + "','" + em1 + "','" + password + "','"
                            + comboValue + "')";
                    st.execute(insertQuery);

                    System.out.println("Connection success");
                    con.close();
                } catch (SQLException | ClassNotFoundException ee) {
                    ee.printStackTrace();
                }
            }
        });

        btnNewButton.setBounds(268, 321, 85, 21);
        contentPane.add(btnNewButton);

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnReset.setForeground(Color.RED);
        btnReset.setBackground(Color.WHITE);
        btnReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textuserName.setText("");
                textPassword.setText("");
                p2.setText("");
                em.setText("");
            }
        });
        btnReset.setBounds(412, 321, 85, 21);
        contentPane.add(btnReset);

        JButton btnLogin = new JButton("Login ");
        btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setBackground(new Color(0, 0, 255));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                la();
                dispose();
            }
        });
        btnLogin.setBounds(118, 321, 85, 21);
        contentPane.add(btnLogin);

        textPassword = new JPasswordField();
        textPassword.setBounds(257, 110, 96, 19);
        contentPane.add(textPassword);

        p2 = new JPasswordField();
        p2.setBounds(257, 150, 96, 19);
        contentPane.add(p2);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\hgf\\Pictures\\Saved Pictures\\download.png"));
        lblNewLabel.setBounds(363, 22, 208, 163);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setForeground(Color.RED);
        lblNewLabel_1.setBackground(Color.CYAN);
        lblNewLabel_1.setIcon(new ImageIcon(""));
        lblNewLabel_1.setBounds(0, 0, 583, 400);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("It's quick and easy");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 12));
        lblNewLabel_2.setBounds(10, 41, 96, 21);
        contentPane.add(lblNewLabel_2);
    }
}
