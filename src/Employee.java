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

    Employee(int empId, String empName, double basicSalary)
            throws InvalidSalaryException {

        if (basicSalary < 0) {
            throw new InvalidSalaryException("Salary cannot be negative!");
        }

        this.empId = empId;
        this.empName = empName;
        this.basicSalary = basicSalary;
    }

    double calculateSalary() {
        return basicSalary;
    }

    void displayDetails() {
        System.out.println("Employee ID: " + empId);
        System.out.println("Employee Name: " + empName);
        System.out.println("Basic Salary: " + basicSalary);
    }
}    
