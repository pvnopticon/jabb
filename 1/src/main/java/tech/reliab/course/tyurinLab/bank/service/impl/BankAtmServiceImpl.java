package tech.reliab.course.tyurinLab.bank.service.impl;

import tech.reliab.course.tyurinLab.bank.entity.BankAtm;
import tech.reliab.course.tyurinLab.bank.service.BankAtmService;

public class BankAtmServiceImpl implements BankAtmService {
    public BankAtm create(BankAtm bankAtm) {
        if (bankAtm == null) {
            return null;
        }

        if (bankAtm.getId() < 0) {
            System.out.println("Error: id must be non-negative.");
            return null;
        }

        if (bankAtm.getTotalMoney() < 0) {
            System.out.println("Error: totalMoney must be non-negative.");
            return null;
        }

        if (bankAtm.getMaintenanceCost() < 0) {
            System.out.println("Error: maintenanceCost must be non-negative.");
            return null;
        }

        if (bankAtm.getBankOffice() == null) {
            System.out.println("Error: bankOffice cant be NULL");
            return null;
        }

        return new BankAtm(bankAtm);
    }

    public boolean depositMoney(BankAtm bankAtm, double amount) {
        if (bankAtm == null) {
            System.out.println("Error: can't deposit money to non existing ATM");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Error: can't deposit money - deposit amount must be positive");
            return false;
        }

        if (!bankAtm.getIsCashDepositAvailable()) {
            System.out.println("Error: can't deposit money - ATM ain't allow deposits");
            return false;
        }

        bankAtm.setTotalMoney(bankAtm.getTotalMoney() + amount);
        // Добавить добавление денег в оффис и банк

        return true;
    }

    public boolean withdrawMoney(BankAtm bankAtm, double amount) {
        if (bankAtm == null) {
            System.out.println("Error: can't withdraw money from non existing ATM");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Error: can't withdraw money - withdraw amount must be positive");
            return false;
        }

        if (!bankAtm.getIsCashWithdrawalAvailable()) {
            System.out.println("Error: can't withdraw money - ATM ain't allow withdrawals");
            return false;
        }

        if (bankAtm.getTotalMoney() < amount) {
            System.out.println("Error: can't withdraw money - ATM does not have enough money. Please come back later.");
            return false;
        }

        bankAtm.setTotalMoney(bankAtm.getTotalMoney() - amount);
        // Вычитать деньги из офиса и банка

        return true;
    }
}
