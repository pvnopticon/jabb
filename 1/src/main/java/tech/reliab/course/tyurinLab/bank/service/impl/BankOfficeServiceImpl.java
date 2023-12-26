package tech.reliab.course.tyurinLab.bank.service.impl;

import tech.reliab.course.tyurinLab.bank.entity.BankAtm;
import tech.reliab.course.tyurinLab.bank.entity.BankOffice;
import tech.reliab.course.tyurinLab.bank.entity.Employee;
import tech.reliab.course.tyurinLab.bank.service.BankOfficeService;

public class BankOfficeServiceImpl implements BankOfficeService {
    public BankOffice create(BankOffice bankOffice) {
        if (bankOffice == null) {
            return null;
        }

        if (bankOffice.getId() < 0) {
            System.out.println("Error: id must be non-negative");
            return null;
        }

        if (bankOffice.getTotalMoney() < 0) {
            System.out.println("Error: bankOffice total money must be non-negative");
            return null;
        }

        if (bankOffice.getBank() == null) {
            System.out.println("Error: bankOffice must have bank");
            return null;
        }

        if (bankOffice.getRentPrice() < 0) {
            System.out.println("Error: bankOffice rentPrice must be non-negative");
            return null;
        }

        return new BankOffice(bankOffice);
    }

    public boolean installAtm(BankOffice bankOffice, BankAtm bankAtm) {
        if (bankOffice != null && bankAtm != null) {
            if (!bankOffice.getIsAtmPlaceable()) {
                System.out.println("Error: cannot install atm at office " + bankOffice.getId());
                return false;
            }

            bankOffice.setAtmCount(bankOffice.getAtmCount() + 1);
            bankAtm.setBankOffice(bankOffice);
            bankAtm.setAddress(bankOffice.getAddress());
            // Добавить добавление atm в банк

            return true;
        }
        return false;
    }

    public boolean removeAtm(BankOffice bankOffice, BankAtm bankAtm) {
        if (bankOffice != null && bankAtm != null) {
            // Добавить поиск банкомата в офисе / банка и удаление оттуда
            final int newAtmCountOffice = bankOffice.getAtmCount() - 1;
            if (newAtmCountOffice < 0) {
                System.out.println("Error: cannot remove ATM, office has no ATMs");
                return false;
            }

            bankOffice.setAtmCount(newAtmCountOffice);
            return true;
        }
        return false;
    }

    public boolean depositMoney(BankOffice bankOffice, double amount) {
        if (bankOffice == null) {
            System.out.println("Error: cannot deposit money to not existing office");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Error: cannot deposit money - deposit amount must be positive");
            return false;
        }

        if (!bankOffice.getIsCashDepositAvailable()) {
            System.out.println("Error: cannot deposit money - office aint accepting deposits");
            return false;
        }

        bankOffice.setTotalMoney(bankOffice.getTotalMoney() + amount);
        // Добавить добавление денег в банк

        return true;
    }

    public boolean withdrawMoney(BankOffice bankOffice, double amount) {
        if (bankOffice == null) {
            System.out.println("Error: cannot withdraw money from not existing office");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Error: cannot withdraw money - withdraw amount must be positive");
            return false;
        }

        if (!bankOffice.getIsCashWithdrawalAvailable()) {
            System.out.println("Error: cannot withdraw money - office aint giving withdrawals");
            return false;
        }

        if (bankOffice.getTotalMoney() < amount) {
            System.out.println("Error: cannot withdraw money - office does not have enough money. Please come back later.");
            return false;
        }

        bankOffice.setTotalMoney(bankOffice.getTotalMoney() - amount);
        // Добавить вычитание денег из банка

        return true;
    }

    public boolean addEmployee(BankOffice bankOffice, Employee employee) {
        if (bankOffice != null && employee != null) {
            employee.setBankOffice(bankOffice);;
            employee.setBank(bankOffice.getBank());
            // Добавить добавление работника в банк

            return true;
        }
        return false;
    }

    public boolean removeEmployee(BankOffice bankOffice, Employee employee) {
        if (bankOffice != null && employee != null) {
            // добавить поиск работника в офисе и его удаление
            // добавить поиск работника в банке и его удаление
            return true;
        }
        return false;
    }
}
