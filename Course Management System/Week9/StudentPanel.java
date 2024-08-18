package Week9;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentPanel extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StudentPanel frame = new StudentPanel();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public StudentPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 757, 514);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(204, 153, 204));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 123, 711, 341);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("Welcome To Student Panel");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblNewLabel.setForeground(new Color(0, 0, 255));
        lblNewLabel.setBounds(10, 11, 231, 33);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("Assignment");
        btnNewButton.addActionListener(e -> displayData("assignment"));  
        btnNewButton.setBounds(28, 89, 131, 23);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Results");
        btnNewButton_1.addActionListener(e -> displayData("result"));  
        btnNewButton_1.setBounds(218, 89, 131, 23);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Courses");
        btnNewButton_2.addActionListener(e -> displayCoursesData());
        btnNewButton_2.setBounds(404, 89, 131, 23);
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Tutors");
        btnNewButton_3.addActionListener(e -> displayTutorsData());
        btnNewButton_3.setBounds(579, 89, 116, 23);
        contentPane.add(btnNewButton_3);
    }

    private void displayCoursesData() {
        displayData("courses");
    }

    private void displayTutorsData() {
        displayData("tutors");
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
}
