/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author avina
 */
class Employee {

    protected int empId;
    protected String empName;
    protected double basicSalary;
    protected double allowance;
    protected double taxPercent;
    protected double otherDeduction;

    Employee(int empId, String empName, double basicSalary,
             double allowance, double taxPercent, double otherDeduction)
            throws InvalidSalaryException {

        if (basicSalary < 0) {
            throw new InvalidSalaryException("Basic Salary cannot be negative!");
        }

        this.empId = empId;
        this.empName = empName;
        this.basicSalary = basicSalary;
        this.allowance = allowance;
        this.taxPercent = taxPercent;
        this.otherDeduction = otherDeduction;
    }

    double calculateGrossSalary() {
        return basicSalary + allowance;
    }

    double calculateTax() {
        return (calculateGrossSalary() * taxPercent) / 100;
    }

    double calculateNetSalary() {
        return calculateGrossSalary() - calculateTax() - otherDeduction;
    }
}