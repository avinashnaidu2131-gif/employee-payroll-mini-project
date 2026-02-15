/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author avina
 */
class Manager extends Employee {

    private double bonus;

    Manager(int empId, String empName, double basicSalary, double bonus)
            throws InvalidSalaryException {

        super(empId, empName, basicSalary);
        this.bonus = bonus;
    }

    @Override
    double calculateSalary() {
        return basicSalary + bonus;
    }
}
