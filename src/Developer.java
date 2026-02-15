/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author avina
 */
class Developer extends Employee {

    private int overtimeHours;
    private double overtimeRate = 500;

    Developer(int empId, String empName, double basicSalary, int overtimeHours)
            throws InvalidSalaryException {

        super(empId, empName, basicSalary);
        this.overtimeHours = overtimeHours;
    }

    @Override
    double calculateSalary() {
        return basicSalary + (overtimeHours * overtimeRate);
    }
}