package tech.reliab.course.tyurinLab.bank.service;

import tech.reliab.course.tyurinLab.bank.entity.Bank;
import tech.reliab.course.tyurinLab.bank.entity.BankOffice;
import tech.reliab.course.tyurinLab.bank.entity.CreditAccount;
import tech.reliab.course.tyurinLab.bank.entity.Employee;
import tech.reliab.course.tyurinLab.bank.entity.User;
import tech.reliab.course.tyurinLab.bank.exception.CreditException;
import tech.reliab.course.tyurinLab.bank.exception.NotFoundException;
import tech.reliab.course.tyurinLab.bank.exception.UniquenessException;

import java.util.List;

public interface BankService {
    public void setBankOfficeService(BankOfficeService bankOfficeService);
    public void setUserService(UserService userService);
    public Bank create(Bank bank) throws UniquenessException;
    public void printBankData(long id);
    public Bank getBankById(long id);
    public List<Bank> getAllBanks();
    public boolean addOffice(long bankId, BankOffice bankOffice);
    public List<BankOffice> getAllOfficesByBankId(long bankId);
    public boolean addEmployee(Bank bank, Employee employee);
    public boolean addClient(long bankId, User user);
    public double calculateInterestRate(Bank bank);
    public boolean depositMoney(long bankId, double amount);
    public boolean withdrawMoney(Bank bank, double amount);
    public boolean approveCredit(Bank bank, CreditAccount account, Employee employee) throws CreditException;

    public List<Bank> getBanksSuitable(double sum, int countMonth) throws NotFoundException, CreditException;

    public boolean isBankSuitable(Bank bank, double sum) throws NotFoundException;

    public List<BankOffice> getBankOfficeSuitableInBank(Bank bank, double sum) throws NotFoundException;

    public boolean transferClient(User user, long newBankId);
}
