package Week9;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class TeacherPanel extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnAddAssignment;
    private JButton btnRemoveAssignment;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TeacherPanel frame = new TeacherPanel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TeacherPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 756, 491);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(143, 188, 143));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("Students");
        btnNewButton.setBounds(148, 78, 97, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Assignment");
        btnNewButton_1.setBounds(357, 78, 113, 23);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Result");
        btnNewButton_2.setBounds(600, 78, 107, 23);
        contentPane.add(btnNewButton_2);

        JLabel lblNewLabel = new JLabel("Welcome To Teacher Panel");
        lblNewLabel.setBounds(10, 11, 220, 41);
        contentPane.add(lblNewLabel);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayData("students");
                showStudentOptions();
            }
        });

        
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayData("assignment");
                showAssignmentOptions();
            }
        });

        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayData("result");
            }
        });

        // Add Assignment button
        btnAddAssignment = new JButton("Add Assignment");
        btnAddAssignment.setBackground(new Color(30, 144, 255));
        btnAddAssignment.setBounds(0, 192, 160, 23);
        contentPane.add(btnAddAssignment);
        btnAddAssignment.setVisible(false); // Initially invisible
        btnAddAssignment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddAssignmentDialog();
            }
        });

        // Remove Assignment button
        btnRemoveAssignment = new JButton("Remove Assignment");
        btnRemoveAssignment.setBackground(new Color(255, 99, 71));
        btnRemoveAssignment.setForeground(new Color(0, 0, 0));
        btnRemoveAssignment.setBounds(0, 226, 160, 23);
        contentPane.add(btnRemoveAssignment);
        btnRemoveAssignment.setVisible(false); // Initially invisible
        btnRemoveAssignment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRemoveAssignmentDialog();
            }
        });

        // Table to display data
        table = new JTable();
        table.setBounds(158, 112, 572, 334);
        contentPane.add(table);
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
        btnAddAssignment.setVisible(false);
        btnRemoveAssignment.setVisible(false);
    }

    private void showAssignmentOptions() {
        btnAddAssignment.setVisible(true);
        btnRemoveAssignment.setVisible(true);
    }

    private void showAddAssignmentDialog() {
        AddAssignmentDialog addAssignmentDialog = new AddAssignmentDialog(this);
        addAssignmentDialog.setVisible(true);
    }

    private void showRemoveAssignmentDialog() {
        RemoveAssignmentDialog removeAssignmentDialog = new RemoveAssignmentDialog(this);
        removeAssignmentDialog.setVisible(true);
    }

    public class AddAssignmentDialog extends JDialog {
        private JTextField NameField;
        private JTextField CourseField;
        private JTextField ModuleField;
        private JTextField LevelField;
        private JTextField MarksField;

        public AddAssignmentDialog(JFrame parent) {
            super(parent, "Add Assignment", true);
            setSize(300, 250);
            setLocationRelativeTo(parent);

            JPanel panel = new JPanel();
            getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(new GridLayout(6, 2));

            JLabel nameLabel = new JLabel("Name:");
            panel.add(nameLabel);

            NameField = new JTextField();
            panel.add(NameField);

            JLabel CourseLabel = new JLabel("Course:");
            panel.add(CourseLabel);

            CourseField = new JTextField();
            panel.add(CourseField);

            JLabel ModuleLabel = new JLabel("Module:");
            panel.add(ModuleLabel);

            ModuleField = new JTextField();
            panel.add(ModuleField);

            JLabel LevelLabel = new JLabel("Level:");
            panel.add(LevelLabel);

            LevelField = new JTextField();
            panel.add(LevelField);

            JLabel MarksLabel = new JLabel("Marks:");
            panel.add(MarksLabel);

            MarksField = new JTextField();
            panel.add(MarksField);

            JButton addButton = new JButton("Add Assignment");
            panel.add(addButton);

            JButton closeButton = new JButton("Close");
            panel.add(closeButton);

            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = NameField.getText();
                    String course = CourseField.getText();
                    String module = ModuleField.getText();
                    String level = LevelField.getText();
                    String marks = MarksField.getText();

                    addAssignmentToDatabase(name, course, module, level, marks);
                    dispose();
                }
            });

            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }

        private void addAssignmentToDatabase(String Name, String Course, String Module, String Level, String Marks) {
            try {
                String url = "jdbc:mysql://localhost:3306/databaseconnection";
                String username = "root";
                String password = ""; // Change this to your database password
                Connection con = DriverManager.getConnection(url, username, password);

                String query = "INSERT INTO assignment (Name, Course, Module, Level, Marks) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, Name);
                pst.setString(2, Course);
                pst.setString(3, Module);
                pst.setString(4, Level);
                pst.setString(5, Marks);

                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Assignment added successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add assignment");
                }

                pst.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public class RemoveAssignmentDialog extends JDialog {
        private JTextField nameField;

        public RemoveAssignmentDialog(JFrame parent) {
            super(parent, "Remove Assignment", true);
            setSize(300, 100);
            setLocationRelativeTo(parent);

            JPanel panel = new JPanel();
            getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(new GridLayout(2, 2));

            JLabel nameLabel = new JLabel("Name:");
            panel.add(nameLabel);

            nameField = new JTextField();
            panel.add(nameField);

            JButton removeButton = new JButton("Remove");
            panel.add(removeButton);

            JButton closeButton = new JButton("Close");
            panel.add(closeButton);

            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    if (removeAssignmentFromDatabase(name)) {
                        JOptionPane.showMessageDialog(RemoveAssignmentDialog.this, "Assignment removed successfully");
                    } else {
                        JOptionPane.showMessageDialog(RemoveAssignmentDialog.this, "Assignment not found or failed to remove");
                    }
                    dispose();
                }
            });

            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }

        private boolean removeAssignmentFromDatabase(String name) {
            try {
                String url = "jdbc:mysql://localhost:3306/databaseconnection";
                String username = "root";
                String password = ""; // Change this to your database password
                Connection con = DriverManager.getConnection(url, username, password);

                String query = "DELETE FROM assignment WHERE Name=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, name);

                int rowsAffected = pst.executeUpdate();

                pst.close();
                con.close();

                return rowsAffected > 0;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }
}
