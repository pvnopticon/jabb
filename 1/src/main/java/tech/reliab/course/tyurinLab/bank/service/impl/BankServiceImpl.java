package tech.reliab.course.tyurinLab.bank.service.impl;

import tech.reliab.course.tyurinLab.bank.entity.Bank;
import tech.reliab.course.tyurinLab.bank.entity.BankOffice;
import tech.reliab.course.tyurinLab.bank.entity.CreditAccount;
import tech.reliab.course.tyurinLab.bank.entity.Employee;
import tech.reliab.course.tyurinLab.bank.entity.User;
import tech.reliab.course.tyurinLab.bank.service.BankService;

import java.util.Random;

import static tech.reliab.course.tyurinLab.bank.util.Constants.MAX_BANK_INTEREST_RATE;
import static tech.reliab.course.tyurinLab.bank.util.Constants.MAX_BANK_RATING;
import static tech.reliab.course.tyurinLab.bank.util.Constants.MAX_BANK_TOTAL_MONEY;

public class BankServiceImpl implements BankService {
    public double calculateInterestRate(Bank bank) {
        if (bank != null) {
            final Random random = new Random();
            final byte rating = bank.getRating();

            final double centralBankInterestRate = random.nextDouble() * MAX_BANK_INTEREST_RATE;
            final double maxBankInterestRateMargin = MAX_BANK_INTEREST_RATE - centralBankInterestRate;
            final double bankInterestRateMargin = (random.nextDouble() * maxBankInterestRateMargin) * ((110 - rating) / 100);
            final double interestRate = centralBankInterestRate + bankInterestRateMargin;

            bank.setInterestRate(interestRate);
            return interestRate;
        }
        return 0;
    }

    public Bank create(Bank bank) {
        if (bank == null) {
            return null;
        }

        if (bank.getId() < 0) {
            System.out.println("Error: id must be non-negative");
            return null;
        }

        Bank createdBank = new Bank(bank.getId(), bank.getName());

        final Random random = new Random();

        createdBank.setRating((byte)random.nextInt(MAX_BANK_RATING + 1));
        createdBank.setTotalMoney(random.nextDouble() * MAX_BANK_TOTAL_MONEY);
        calculateInterestRate(createdBank);

        return createdBank;
    }

    public boolean addOffice(Bank bank, BankOffice bankOffice) {
        if (bank != null && bankOffice != null) {
            bankOffice.setBank(bank);
            bank.setOfficeCount((short)(bank.getOfficeCount() + 1));
            return true;
        }
        return false;
    }

    public boolean removeOffice(Bank bank, BankOffice bankOffice) {
        if (bank != null && bankOffice != null) {
            final short newOfficeCount = (short)(bank.getOfficeCount() - 1);

            if (newOfficeCount < 0) {
                System.out.println("Error: cannot remove office, bank has no offices");
                return false;
            }

            bank.setOfficeCount(newOfficeCount);

            return true;
        }
        return false;
    }

    public boolean addEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            employee.setBank(bank);
            bank.setEmployeeCount(bank.getEmployeeCount() + 1);
            return true;
        }
        return false;
    }

    public boolean removeEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            final int newEmployeeCount = bank.getEmployeeCount() - 1;

            if (newEmployeeCount < 0) {
                System.out.println("Error: cannot remove employee, bank has no employees");
                return false;
            }

            bank.setEmployeeCount(newEmployeeCount);

            return true;
        }
        return false;
    }

    public boolean addClient(Bank bank, User user) {
        if (bank != null && user != null) {
            user.setBank(bank);
            bank.setUserCount(bank.getUserCount() + 1);
            return true;
        }
        return false;
    }

    public boolean removeClient(Bank bank, User user) {
        if (bank != null && user != null) {
            int newUserCount = bank.getUserCount() - 1;

            if (newUserCount < 0) {
                System.out.println("Error: cannot remove user, bank has no users");
                return false;
            }

            bank.setUserCount(newUserCount);
            return true;
        }
        return false;
    }

    public boolean depositMoney(Bank bank, double amount) {
        if (bank == null) {
            System.out.println("Error: cannot deposit money to uninitialized bank");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Error: cannot deposit money - deposit amount must be positive");
            return false;
        }

        bank.setTotalMoney(bank.getTotalMoney() + amount);
        return true;
    }

    public boolean withdrawMoney(Bank bank, double amount) {
        if (bank == null) {
            System.out.println("Error: cannot withdraw money from uninitialized bank");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Error: cannot withdraw money - withdraw amount must be positive");
            return false;
        }

        if (bank.getTotalMoney() < amount) {
            System.out.println("Error: cannot withdraw money - bank does not have enough money. Please come back later.");
            return false;
        }

        bank.setTotalMoney(bank.getTotalMoney() - amount);
        return true;
    }

    public boolean approveCredit(Bank bank, CreditAccount account, Employee employee) {
        return false; // дописать когда станет понятнее логика кредитов
    }
}
