/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author avina
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PayrollGUI extends JFrame implements ActionListener {

    private JTextField txtId, txtName, txtSalary, txtExtra;
    private JComboBox<String> comboType;
    private JButton btnCalculate, btnClear, btnExit;
    private JLabel lblResult;

    public PayrollGUI() {

        setTitle("Employee Payroll Management System");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== TITLE PANEL =====
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(30, 144, 255));

        JLabel titleLabel = new JLabel("EMPLOYEE PAYROLL MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);

        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // ===== CENTER PANEL (FORM + RESULT) =====
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(new Color(240, 248, 255));

        // FORM PANEL
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        formPanel.setBackground(new Color(240, 248, 255));

        formPanel.add(new JLabel("Employee ID:"));
        txtId = new JTextField();
        formPanel.add(txtId);

        formPanel.add(new JLabel("Employee Name:"));
        txtName = new JTextField();
        formPanel.add(txtName);

        formPanel.add(new JLabel("Basic Salary:"));
        txtSalary = new JTextField();
        formPanel.add(txtSalary);

        formPanel.add(new JLabel("Employee Type:"));
        comboType = new JComboBox<>(new String[]{"Manager", "Developer"});
        formPanel.add(comboType);

        formPanel.add(new JLabel("Bonus / Overtime:"));
        txtExtra = new JTextField();
        formPanel.add(txtExtra);

        centerPanel.add(formPanel, BorderLayout.CENTER);

        // RESULT LABEL
        lblResult = new JLabel("Result will appear here", JLabel.CENTER);
        lblResult.setFont(new Font("Arial", Font.BOLD, 16));
        lblResult.setForeground(new Color(0, 100, 0));
        lblResult.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        centerPanel.add(lblResult, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));

        btnCalculate = new JButton("Calculate");
        btnClear = new JButton("Clear");
        btnExit = new JButton("Exit");

        buttonPanel.add(btnCalculate);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnExit);

        add(buttonPanel, BorderLayout.SOUTH);

        btnCalculate.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnCalculate) {

            try {

                int id = Integer.parseInt(txtId.getText());
                String name = txtName.getText();

                if (name.isEmpty()) {
                    throw new Exception("Name cannot be empty!");
                }

                double salary = Double.parseDouble(txtSalary.getText());
                double extra = Double.parseDouble(txtExtra.getText());

                Employee emp;

                if (comboType.getSelectedItem().equals("Manager")) {
                    emp = new Manager(id, name, salary, extra);
                } else {
                    emp = new Developer(id, name, salary, (int) extra);
                }

                Thread.sleep(400);

                double total = emp.calculateSalary();
                lblResult.setText("Total Salary: ₹ " + total);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btnClear) {
            txtId.setText("");
            txtName.setText("");
            txtSalary.setText("");
            txtExtra.setText("");
            lblResult.setText("Result will appear here");
        }

        if (e.getSource() == btnExit) {
            System.exit(0);
        }
    }
}