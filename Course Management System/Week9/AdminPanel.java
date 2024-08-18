package Week9;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;

public class AdminPanel extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JButton addStudentButton;
    private JButton removeStudentButton;
    private JButton addTutorButton;
    private JButton removeTutorButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminPanel frame = new AdminPanel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AdminPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 769, 489);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(135, 206, 250));
        contentPane.setForeground(new Color(135, 206, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 95, 724, 2);
        contentPane.add(separator);

        JButton studentsButton = new JButton("Students");
        studentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayData("students");
                showStudentOptions();
            }
        });
        studentsButton.setBounds(116, 61, 89, 23);
        contentPane.add(studentsButton);

        JButton tutorsButton = new JButton("Tutors");
        tutorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayData("tutors");
                showTutorOptions();
            }
        });
        tutorsButton.setBounds(226, 61, 89, 23);
        contentPane.add(tutorsButton);

        JButton assignmentButton = new JButton("Assignment");
        assignmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayData("assignment");  
            }
        });
        assignmentButton.setBounds(340, 61, 104, 23);
        contentPane.add(assignmentButton);
        JButton courseButton = new JButton("Course");
        courseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayData("courses");
            }
        });
        courseButton.setBounds(472, 61, 89, 23);
        contentPane.add(courseButton);

        JButton resultButton = new JButton("Result");
        resultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayData("result");  
            }
        });
        resultButton.setBounds(592, 61, 89, 23);
        contentPane.add(resultButton);

        JLabel lblNewLabel = new JLabel("Welcome To Admin Panel");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setForeground(Color.BLUE);
        lblNewLabel.setBounds(10, 11, 195, 39);
        contentPane.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(133, 95, 601, 322);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        contentPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hideStudentButtons();
                hideTutorButtons();
            }
        });

        addStudentButton = new JButton("Add Student");
        addStudentButton.setBackground(new Color(102, 205, 170));
        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddStudentDialog();
            }
        });
        addStudentButton.setBounds(0, 105, 131, 23);
        contentPane.add(addStudentButton);
        addStudentButton.setVisible(false);

        removeStudentButton = new JButton("Remove Student");
        removeStudentButton.setBackground(new Color(240, 128, 128));
        removeStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRemoveStudentDialog();
            }
        });
        removeStudentButton.setBounds(0, 128, 131, 23);
        contentPane.add(removeStudentButton);
        removeStudentButton.setVisible(false);

        addTutorButton = new JButton("Add Tutor");
        addTutorButton.setBackground(new Color(102, 205, 170));
        addTutorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddTutorDialog();
            }
        });
        addTutorButton.setBounds(0, 150, 131, 23);
        contentPane.add(addTutorButton);
        addTutorButton.setVisible(false);

        removeTutorButton = new JButton("Remove Tutor");
        removeTutorButton.setBackground(new Color(240, 128, 128));
        removeTutorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRemoveTutorDialog();
            }
        });
        removeTutorButton.setBounds(0, 173, 131, 23);
        contentPane.add(removeTutorButton);
        removeTutorButton.setVisible(false);
    }

    private void hideStudentButtons() {
        addStudentButton.setVisible(false);
        removeStudentButton.setVisible(false);
    }

    private void hideTutorButtons() {
        addTutorButton.setVisible(false);
        removeTutorButton.setVisible(false);
    }

    private void displayData(String tableName) {
        try {
            String url = "jdbc:mysql://localhost:3306/databaseconnection";
            String username = "root";
            String password = ""; // Change this to your database password
            Connection con = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM " + tableName;
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(rs.getMetaData().getColumnName(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            table.setModel(model);

            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void showStudentOptions() {
        addStudentButton.setVisible(true);
        removeStudentButton.setVisible(true);
    }

    private void showTutorOptions() {
        addTutorButton.setVisible(true);
        removeTutorButton.setVisible(true);
    }

    private void showAddStudentDialog() {
        AddStudentDialog addStudentDialog = new AddStudentDialog(this);
        addStudentDialog.setVisible(true);
    }

    private void showRemoveStudentDialog() {
        RemoveStudentDialog removeStudentDialog = new RemoveStudentDialog(this);
        removeStudentDialog.setVisible(true);
    }

    private void showAddTutorDialog() {
        AddTutorDialog addTutorDialog = new AddTutorDialog(this);
        addTutorDialog.setVisible(true);
    }

    private void showRemoveTutorDialog() {
        RemoveTutorDialog removeTutorDialog = new RemoveTutorDialog(this);
        removeTutorDialog.setVisible(true);
    }

    public class AddStudentDialog extends JDialog {
        private JTextField studentIDField;
        private JTextField studentNameField;
        private JTextField emailField;
        private JTextField phoneField;

        public AddStudentDialog(JFrame parent) {
            super(parent, "Add Student", true);
            setSize(300, 200);
            setLocationRelativeTo(parent);

            JPanel panel = new JPanel();
            getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(new GridLayout(5, 2));

            JLabel studentIDLabel = new JLabel("ID:");
            panel.add(studentIDLabel);

            studentIDField = new JTextField();
            panel.add(studentIDField);

            JLabel studentNameLabel = new JLabel("Name:");
            panel.add(studentNameLabel);

            studentNameField = new JTextField();
            panel.add(studentNameField);

            JLabel emailLabel = new JLabel("Email:");
            panel.add(emailLabel);

            emailField = new JTextField();
            panel.add(emailField);

            JLabel phoneLabel = new JLabel("Phone_no:");
            panel.add(phoneLabel);

            phoneField = new JTextField();
            panel.add(phoneField);

            JButton addButton = new JButton("Add Student");
            panel.add(addButton);

            JButton closeButton = new JButton("Close");
            panel.add(closeButton);

            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String studentID = studentIDField.getText();
                    String studentName = studentNameField.getText();
                    String email = emailField.getText();
                    String phone = phoneField.getText();

                    addStudentToDatabase(studentID, studentName, email, phone);
                    dispose();
                }
            });

            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }

        private void addStudentToDatabase(String studentID, String studentName, String email, String phone) {
            try {
                String url = "jdbc:mysql://localhost:3306/databaseconnection";
                String username = "root";
                String password = ""; // Change this to your database password
                Connection con = DriverManager.getConnection(url, username, password);

                String query = "INSERT INTO students (ID, Name, Email, Phone_no) VALUES (?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, studentID);
                pst.setString(2, studentName);
                pst.setString(3, email);
                pst.setString(4, phone);

                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Student added successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add student");
                }

                pst.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public class RemoveStudentDialog extends JDialog {
        private JTextField studentIDField;

        public RemoveStudentDialog(JFrame parent) {
            super(parent, "Remove Student", true);
            setSize(300, 100);
            setLocationRelativeTo(parent);

            JPanel panel = new JPanel();
            getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(new GridLayout(1, 2));

            JLabel studentIDLabel = new JLabel("StudentID:");
            panel.add(studentIDLabel);

            studentIDField = new JTextField();
            panel.add(studentIDField);

            JButton removeButton = new JButton("Remove Student");
            panel.add(removeButton);

            JButton closeButton = new JButton("Close");
            panel.add(closeButton);

            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String studentID = studentIDField.getText();
                    removeStudentFromDatabase(studentID);
                    dispose();
                }
            });

            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }

        private void removeStudentFromDatabase(String studentID) {
            try {
                String url = "jdbc:mysql://localhost:3306/databaseconnection";
                String username = "root";
                String password = ""; // Change this to your database password
                Connection con = DriverManager.getConnection(url, username, password);

                String query = "DELETE FROM students WHERE ID=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, studentID);

                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Student removed successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to remove student. Student ID not found.");
                }

                pst.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public class AddTutorDialog extends JDialog {
        private JTextField tutorIDField;
        private JTextField tutorNameField;
        private JTextField emailField;
        private JTextField phoneField;

        public AddTutorDialog(JFrame parent) {
            super(parent, "Add Tutor", true);
            setSize(300, 200);
            setLocationRelativeTo(parent);

            JPanel panel = new JPanel();
            getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(new GridLayout(5, 2));

            JLabel tutorIDLabel = new JLabel("ID:");
            panel.add(tutorIDLabel);

            tutorIDField = new JTextField();
            panel.add(tutorIDField);

            JLabel tutorNameLabel = new JLabel("Name:");
            panel.add(tutorNameLabel);

            tutorNameField = new JTextField();
            panel.add(tutorNameField);

            JLabel emailLabel = new JLabel("Email:");
            panel.add(emailLabel);

            emailField = new JTextField();
            panel.add(emailField);

            JLabel phoneLabel = new JLabel("Phone_no:");
            panel.add(phoneLabel);

            phoneField = new JTextField();
            panel.add(phoneField);

            JButton addButton = new JButton("Add Tutor");
            panel.add(addButton);

            JButton closeButton = new JButton("Close");
            panel.add(closeButton);

            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String tutorID = tutorIDField.getText();
                    String tutorName = tutorNameField.getText();
                    String email = emailField.getText();
                    String phone = phoneField.getText();

                    addTutorToDatabase(tutorID, tutorName, email, phone);
                    dispose();
                }
            });

            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }

        private void addTutorToDatabase(String tutorID, String tutorName, String email, String phone) {
            try {
                String url = "jdbc:mysql://localhost:3306/databaseconnection";
                String username = "root";
                String password = ""; // Change this to your database password
                Connection con = DriverManager.getConnection(url, username, password);

                String query = "INSERT INTO tutors (ID, Name, Email, Phone_no) VALUES (?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, tutorID);
                pst.setString(2, tutorName);
                pst.setString(3, email);
                pst.setString(4, phone);

                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Tutor added successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add tutor");
                }

                pst.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public class RemoveTutorDialog extends JDialog {
        private JTextField tutorIDField;

        public RemoveTutorDialog(JFrame parent) {
            super(parent, "Remove Tutor", true);
            setSize(300, 100);
            setLocationRelativeTo(parent);

            JPanel panel = new JPanel();
            getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(new GridLayout(1, 2));

            JLabel tutorIDLabel = new JLabel("TutorID:");
            panel.add(tutorIDLabel);

            tutorIDField = new JTextField();
            panel.add(tutorIDField);

            JButton removeButton = new JButton("Remove Tutor");
            panel.add(removeButton);

            JButton closeButton = new JButton("Close");
            panel.add(closeButton);

            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String tutorID = tutorIDField.getText();
                    removeTutorFromDatabase(tutorID);
                    dispose();
                }
            });

            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }

        private void removeTutorFromDatabase(String tutorID) {
            try {
                String url = "jdbc:mysql://localhost:3306/databaseconnection";
                String username = "root";
                String password = ""; // Change this to your database password
                Connection con = DriverManager.getConnection(url, username, password);

                String query = "DELETE FROM tutors WHERE ID=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, tutorID);

                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Tutor removed successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to remove tutor. Tutor ID not found.");
                }

                pst.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}