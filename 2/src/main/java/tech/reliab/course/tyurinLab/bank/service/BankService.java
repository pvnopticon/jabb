package tech.reliab.course.tyurinLab.bank.service;

import tech.reliab.course.tyurinLab.bank.entity.Bank;
import tech.reliab.course.tyurinLab.bank.entity.BankOffice;
import tech.reliab.course.tyurinLab.bank.entity.CreditAccount;
import tech.reliab.course.tyurinLab.bank.entity.Employee;
import tech.reliab.course.tyurinLab.bank.entity.User;

import java.util.List;

public interface BankService {
    public void setBankOfficeService(BankOfficeService bankOfficeService);
    public void setUserService(UserService userService);
    public Bank create(Bank bank);
    public void printBankData(int id);
    public Bank getBankById(int id);
    public List<Bank> getAllBanks();
    public boolean addOffice(int bankId, BankOffice bankOffice);
    public boolean removeOffice(int bankId, BankOffice bankOffice);
    public List<BankOffice> getAllOfficesByBankId(int bankId);
    public boolean addEmployee(Bank bank, Employee employee);
    public boolean removeEmployee(Bank bank, Employee employee);
    public boolean addClient(int bankId, User user);
    public boolean removeClient(Bank bank, User user);
    public double calculateInterestRate(Bank bank);
    public boolean depositMoney(int bankId, double amount);
    public boolean withdrawMoney(Bank bank, double amount);
    public boolean approveCredit(Bank bank, CreditAccount account, Employee employee);

}
