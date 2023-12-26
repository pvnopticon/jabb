package tech.reliab.course.tyurinLab.bank.service;

import tech.reliab.course.tyurinLab.bank.entity.BankOffice;
import tech.reliab.course.tyurinLab.bank.entity.Employee;
import tech.reliab.course.tyurinLab.bank.exception.UniquenessException;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee) throws UniquenessException;
    public Employee getEmployeeById(long id);
    public List<Employee> getAllEmployees();
    boolean transferEmployee(Employee employee, BankOffice bankOffice);
    public boolean isEmployeeSuitable(Employee employee);
}
