package tech.reliab.course.tyurinLab.bank.service;

import tech.reliab.course.tyurinLab.bank.entity.BankOffice;
import tech.reliab.course.tyurinLab.bank.entity.Employee;

public interface EmployeeService {
    Employee create(Employee employee);
    boolean transferEmployee(Employee employee, BankOffice bankOffice);
}
