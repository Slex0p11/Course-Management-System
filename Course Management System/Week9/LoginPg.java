package Week9;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;

public class LoginPg extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textem;
    private JPasswordField tPassword;
    private JComboBox<String> txtcomb;  

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginPg frame = new LoginPg();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginPg() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 614, 478);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login To System");
        lblNewLabel.setForeground(Color.BLUE);
        lblNewLabel.setBackground(Color.BLUE);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNewLabel.setBounds(26, 36, 216, 56);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(" "));
        lblNewLabel_1.setBounds(439, 36, 63, 50);
        contentPane.add(lblNewLabel_1);

        JButton btnNewButton = new JButton("Login");
        btnNewButton.setBackground(new Color(30, 144, 255));
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnNewButton.setBounds(139, 360, 85, 29);
        contentPane.add(btnNewButton);

        JButton btnSignUp = new JButton("Sign up");
        btnSignUp.setBackground(Color.CYAN);
        btnSignUp.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnSignUp.setBounds(339, 360, 105, 29);
        contentPane.add(btnSignUp);

        textem = new JTextField();
        textem.setBounds(234, 160, 164, 24);
        contentPane.add(textem);
        textem.setColumns(10);

        tPassword = new JPasswordField();
        tPassword.setBounds(234, 209, 164, 21);
        contentPane.add(tPassword);

        JLabel lblUserMode = new JLabel("User Mode");
        lblUserMode.setFont(new Font("Times New Roman", Font.BOLD, 17));
        lblUserMode.setBounds(131, 254, 93, 24);
        contentPane.add(lblUserMode);

        // Corrected JComboBox initialization
        txtcomb = new JComboBox<String>();
        txtcomb.setModel(new DefaultComboBoxModel<String>(new String[] { "Student", "Admin", "Teacher" }));
        txtcomb.setBounds(234, 256, 162, 24);
        contentPane.add(txtcomb);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblEmail.setBounds(161, 164, 63, 13);
        contentPane.add(lblEmail);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblPassword.setBounds(138, 207, 78, 21);
        contentPane.add(lblPassword);

        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\hgf\\Pictures\\Saved Pictures\\Login.jpg"));
        lblNewLabel_4.setBounds(408, 36, 192, 141);
        contentPane.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setIcon(new ImageIcon(" "));
        lblNewLabel_5.setBounds(0, 0, 600, 441);
        contentPane.add(lblNewLabel_5);
        
        JLabel lblNewLabel_2 = new JLabel("Not Registered?");
        lblNewLabel_2.setForeground(new Color(255, 0, 0));
        lblNewLabel_2.setBackground(new Color(255, 0, 0));
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblNewLabel_2.setBounds(352, 332, 92, 14);
        contentPane.add(lblNewLabel_2);

        btnSignUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SignUp signUpFrame = new SignUp();
                signUpFrame.setVisible(true);
                dispose(); // This will close the current LoginPg frame
            }
        });

        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String email = textem.getText();
                String password = new String(tPassword.getPassword());
                String value = txtcomb.getSelectedItem().toString();

                try {
                    String url = "jdbc:mysql://localhost:3306/databaseconnection";
                    String username1 = "root";
                    String password1 = "";
                    Connection con = DriverManager.getConnection(url, username1, password1);

                    Statement stm = con.createStatement();
                    String databaseUname = "SELECT * from user WHERE role = '" + value + "'";
                    ResultSet rs = stm.executeQuery(databaseUname);

                    if (rs.next()) {
                        String email1 = rs.getString("email");
                        String dpassword = rs.getString("password");

                        if (email.equals(email1) && password.equals(dpassword)) {
                            // Successful login
                            handleRoleRedirect(value);
                            dispose();  // Close the current LoginPg frame
                        } else {
                            // Incorrect password
                            JOptionPane.showMessageDialog(null, "Incorrect password");
                        }
                    } else {
                        // No user found for the selected role
                        JOptionPane.showMessageDialog(null, "Invalid user mode selected");
                    }

                    con.close();
                } catch (SQLException ee) {
                    ee.printStackTrace();
                }
            }
        });
    }

    private void handleRoleRedirect(String role) {
        switch (role) {
            case "Admin":
                AdminPanel adminPanel = new AdminPanel();
                adminPanel.setVisible(true);
                break;
            case "Student":
                StudentPanel studentPanel = new StudentPanel();
                studentPanel.setVisible(true);
                break;
            case "Teacher":
                TeacherPanel teacherPanel = new TeacherPanel();
                teacherPanel.setVisible(true);
                break;
            
        }
    }
}