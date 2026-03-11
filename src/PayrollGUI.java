/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author avina
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PayrollGUI extends JFrame implements ActionListener {

    JTextField txtId, txtName, txtBasic, txtAllowance, txtTax, txtDeduction;
    JComboBox<String> comboType;

    JButton btnGenerate, btnPrint, btnExport, btnDashboard, btnClear, btnExit;

    JTable historyTable;
    DefaultTableModel tableModel;

    String lastSlip = "";

    public PayrollGUI() {

        setTitle("Employee Payroll Management System");
        setSize(1000,600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // HEADER
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(30,144,255));

        JLabel title = new JLabel("EMPLOYEE PAYROLL MANAGEMENT SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        // FORM PANEL
        JPanel formPanel = new JPanel(new GridLayout(8,2,10,10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));
        formPanel.setPreferredSize(new Dimension(300,400));

        txtId = new JTextField();
        txtName = new JTextField();
        txtBasic = new JTextField();
        txtAllowance = new JTextField();
        txtTax = new JTextField();
        txtDeduction = new JTextField();

        comboType = new JComboBox<>(new String[]{"Manager","Developer"});

        formPanel.add(new JLabel("Employee ID"));
        formPanel.add(txtId);

        formPanel.add(new JLabel("Employee Name"));
        formPanel.add(txtName);

        formPanel.add(new JLabel("Basic Salary"));
        formPanel.add(txtBasic);

        formPanel.add(new JLabel("Allowance"));
        formPanel.add(txtAllowance);

        formPanel.add(new JLabel("Tax (%)"));
        formPanel.add(txtTax);

        formPanel.add(new JLabel("Other Deduction"));
        formPanel.add(txtDeduction);

        formPanel.add(new JLabel("Employee Type"));
        formPanel.add(comboType);

        add(formPanel, BorderLayout.WEST);

        // TABLE
        String[] columns = {"ID","Name","Type","Basic","Allowance","Tax","Deduction","Net Salary"};

        tableModel = new DefaultTableModel(columns,0);
        historyTable = new JTable(tableModel);

        historyTable.setRowHeight(25);
        historyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        historyTable.setFont(new Font("Arial", Font.PLAIN, 13));

        JScrollPane tableScroll = new JScrollPane(historyTable);
        add(tableScroll, BorderLayout.CENTER);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel();

        btnGenerate = new JButton("Generate Slip");
        btnPrint = new JButton("Print");
        btnExport = new JButton("Export Excel");
        btnDashboard = new JButton("Dashboard");
        btnClear = new JButton("Clear");
        btnExit = new JButton("Exit");

        btnGenerate.setBackground(new Color(46,204,113));
        btnPrint.setBackground(new Color(52,152,219));
        btnExport.setBackground(new Color(39,174,96));
        btnDashboard.setBackground(new Color(155,89,182));
        btnClear.setBackground(new Color(241,196,15));
        btnExit.setBackground(new Color(231,76,60));

        btnGenerate.setForeground(Color.WHITE);
        btnPrint.setForeground(Color.WHITE);
        btnExport.setForeground(Color.WHITE);
        btnDashboard.setForeground(Color.WHITE);
        btnExit.setForeground(Color.WHITE);

        buttonPanel.add(btnGenerate);
        buttonPanel.add(btnPrint);
        buttonPanel.add(btnExport);
        buttonPanel.add(btnDashboard);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnExit);

        add(buttonPanel, BorderLayout.SOUTH);

        btnGenerate.addActionListener(this);
        btnPrint.addActionListener(this);
        btnExport.addActionListener(this);
        btnDashboard.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnGenerate){

            try{

                int id = Integer.parseInt(txtId.getText());
                String name = txtName.getText();
                double basic = Double.parseDouble(txtBasic.getText());
                double allowance = Double.parseDouble(txtAllowance.getText());
                double tax = Double.parseDouble(txtTax.getText());
                double deduction = Double.parseDouble(txtDeduction.getText());

                String type = comboType.getSelectedItem().toString();

                Employee emp;

                if(type.equals("Manager")){
                    emp = new Manager(id,name,basic,allowance,tax,deduction);
                }else{
                    emp = new Developer(id,name,basic,allowance,tax,deduction);
                }

                double gross = emp.calculateGrossSalary();
                double taxAmount = emp.calculateTax();
                double net = emp.calculateNetSalary();

                LocalDate today = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                lastSlip =
                        "==============================\n"
                        +" ABC TECHNOLOGIES PVT LTD\n"
                        +" Chennai, Tamil Nadu\n"
                        +"==============================\n\n"
                        +"Date : "+today.format(formatter)+"\n\n"
                        +"Employee ID : "+id+"\n"
                        +"Employee Name : "+name+"\n"
                        +"Employee Type : "+type+"\n\n"
                        +"-----EARNINGS-----\n"
                        +"Basic Salary : "+basic+"\n"
                        +"Allowance : "+allowance+"\n"
                        +"Gross Salary : "+gross+"\n\n"
                        +"-----DEDUCTIONS-----\n"
                        +"Tax : "+taxAmount+"\n"
                        +"Other Deduction : "+deduction+"\n\n"
                        +"NET SALARY : "+net+"\n\n"
                        +"HR Department\n";

                JTextArea area = new JTextArea(lastSlip);
                area.setFont(new Font("Monospaced",Font.PLAIN,14));
                area.setEditable(false);

                JOptionPane.showMessageDialog(this,new JScrollPane(area),
                        "Salary Slip",JOptionPane.INFORMATION_MESSAGE);

                tableModel.addRow(new Object[]{
                        id,name,type,basic,allowance,tax,deduction,net
                });

                savePayrollToFile(id,name,type,basic,allowance,taxAmount,deduction,net);

            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(this,"Invalid input!");
            }
        }

        if(e.getSource()==btnPrint){

            if(lastSlip.isEmpty()){
                JOptionPane.showMessageDialog(this,"Generate salary slip first!");
                return;
            }

            JTextArea printArea = new JTextArea(lastSlip);

            try{
                printArea.print();
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(this,"Printing Failed");
            }
        }

        if(e.getSource()==btnExport){

            try{

                JFileChooser chooser = new JFileChooser();
                chooser.setSelectedFile(new File("PayrollData.csv"));

                int option = chooser.showSaveDialog(this);

                if(option == JFileChooser.APPROVE_OPTION){

                    File file = chooser.getSelectedFile();
                    FileWriter writer = new FileWriter(file);

                    for(int i=0;i<tableModel.getColumnCount();i++){
                        writer.write(tableModel.getColumnName(i)+",");
                    }
                    writer.write("\n");

                    for(int i=0;i<tableModel.getRowCount();i++){

                        for(int j=0;j<tableModel.getColumnCount();j++){
                            writer.write(tableModel.getValueAt(i,j).toString()+",");
                        }

                        writer.write("\n");
                    }

                    writer.close();

                    JOptionPane.showMessageDialog(this,"Excel file exported successfully!");

                }

            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(this,"Export failed!");
            }
        }

        if(e.getSource()==btnDashboard){

            int rows = tableModel.getRowCount();

            if(rows==0){
                JOptionPane.showMessageDialog(this,"No payroll data available!");
                return;
            }

            double totalPayroll = 0;
            double highest = Double.MIN_VALUE;
            double lowest = Double.MAX_VALUE;

            for(int i=0;i<rows;i++){

                double salary = Double.parseDouble(tableModel.getValueAt(i,7).toString());

                totalPayroll += salary;

                if(salary > highest) highest = salary;
                if(salary < lowest) lowest = salary;
            }

            double average = totalPayroll / rows;

            String stats =
                    "=========== PAYROLL DASHBOARD ===========\n\n"
                    +"Total Employees : "+rows+"\n\n"
                    +"Total Payroll ₹ : "+totalPayroll+"\n\n"
                    +"Average Salary ₹ : "+average+"\n\n"
                    +"Highest Salary ₹ : "+highest+"\n\n"
                    +"Lowest Salary ₹ : "+lowest+"\n";

            JTextArea area = new JTextArea(stats);
            area.setFont(new Font("Monospaced",Font.BOLD,14));
            area.setEditable(false);

            JOptionPane.showMessageDialog(this,new JScrollPane(area),
                    "Payroll Dashboard",JOptionPane.INFORMATION_MESSAGE);
        }

        if(e.getSource()==btnClear){

            txtId.setText("");
            txtName.setText("");
            txtBasic.setText("");
            txtAllowance.setText("");
            txtTax.setText("");
            txtDeduction.setText("");
        }

        if(e.getSource()==btnExit){
            System.exit(0);
        }
    }

    private void savePayrollToFile(int id,String name,String type,
                                   double basic,double allowance,
                                   double tax,double deduction,double net){

        try{

            FileWriter writer = new FileWriter("payroll_records.txt",true);

            writer.write("Employee ID : "+id+"\n");
            writer.write("Name : "+name+"\n");
            writer.write("Type : "+type+"\n");
            writer.write("Basic Salary : "+basic+"\n");
            writer.write("Allowance : "+allowance+"\n");
            writer.write("Tax : "+tax+"\n");
            writer.write("Deduction : "+deduction+"\n");
            writer.write("Net Salary : "+net+"\n");
            writer.write("----------------------------------\n");

            writer.close();

        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}