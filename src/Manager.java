/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author avina
 */
class Manager extends Employee {

    Manager(int empId, String empName, double basicSalary,
            double allowance, double taxPercent, double otherDeduction)
            throws InvalidSalaryException {

        super(empId, empName, basicSalary, allowance, taxPercent, otherDeduction);
    }
}