package tech.reliab.course.tyurinLab.bank.service;

import tech.reliab.course.tyurinLab.bank.entity.BankAtm;
import tech.reliab.course.tyurinLab.bank.entity.BankOffice;
import tech.reliab.course.tyurinLab.bank.entity.Employee;
import tech.reliab.course.tyurinLab.bank.exception.NotFoundException;
import tech.reliab.course.tyurinLab.bank.exception.UniquenessException;

import java.util.List;

public interface BankOfficeService {
    public void setAtmService(BankAtmService atmService);
    public void setEmployeeService(EmployeeService employeeService);
    BankOffice create(BankOffice bankOffice) throws UniquenessException;
    public void printBankOfficeData(long id);
    public BankOffice getBankOfficeById(long id);
    public List<BankOffice> getAllOffices();
    public List<Employee> getAllEmployeesByOfficeId(long officeId);
    public boolean installAtm(long bankOfficeId, BankAtm bankAtm);
    public boolean depositMoney(BankOffice bankOffice, double amount);
    public boolean withdrawMoney(BankOffice bankOffice, double amount);
    public boolean addEmployee(long bankOfficeId, Employee employee);
    public List<BankAtm> getAllOfficeAtms(long id);
    public boolean isSuitableBankOffice(BankOffice bankOffice, double sum) throws NotFoundException;
    public List<BankAtm> getSuitableBankAtmInOffice(BankOffice bankOffice, double sum);
    public List<Employee> getSuitableEmployeeInOffice(BankOffice bankOffice) throws NotFoundException;

}
