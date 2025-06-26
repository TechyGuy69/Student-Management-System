import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentManager extends JFrame {
    DefaultTableModel model;
    JTextField nameField, rollField, searchField;

    public StudentManager() {
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table
        model = new DefaultTableModel(new String[]{"Name", "Roll"}, 0);
        JTable table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        add(pane, BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        nameField = new JTextField(10);
        rollField = new JTextField(10);
        JButton addBtn = new JButton("Add");
        JButton delBtn = new JButton("Delete Selected");

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Roll:"));
        inputPanel.add(rollField);
        inputPanel.add(addBtn);
        inputPanel.add(delBtn);

        add(inputPanel, BorderLayout.NORTH);

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(15);
        JButton searchBtn = new JButton("Search");

        searchPanel.add(new JLabel("Search Name:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        add(searchPanel, BorderLayout.SOUTH);

        // Listeners
        addBtn.addActionListener(e -> {
            model.addRow(new Object[]{nameField.getText(), rollField.getText()});
            nameField.setText("");
            rollField.setText("");
        });

        delBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) model.removeRow(row);
        });

        searchBtn.addActionListener(e -> {
            String key = searchField.getText().toLowerCase();
            for (int i = 0; i < model.getRowCount(); i++) {
                String name = model.getValueAt(i, 0).toString().toLowerCase();
                if (name.contains(key)) {
                    table.setRowSelectionInterval(i, i);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Student not found.");
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentManager();
    }
}
