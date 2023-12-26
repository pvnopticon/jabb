package tech.reliab.course.tyurinLab.bank.service;

import tech.reliab.course.tyurinLab.bank.entity.BankOffice;
import tech.reliab.course.tyurinLab.bank.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    public Employee getEmployeeById(int id);
    public List<Employee> getAllEmployees();
    boolean transferEmployee(Employee employee, BankOffice bankOffice);
}
