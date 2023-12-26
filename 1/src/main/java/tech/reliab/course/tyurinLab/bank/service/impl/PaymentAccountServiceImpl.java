package tech.reliab.course.tyurinLab.bank.service.impl;

import tech.reliab.course.tyurinLab.bank.entity.PaymentAccount;
import tech.reliab.course.tyurinLab.bank.service.PaymentAccountService;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    public PaymentAccount create(PaymentAccount paymentAccount) {
        if (paymentAccount == null) {
            return null;
        }

        if (paymentAccount.getId() < 0) {
            System.out.println("Error: id must be non-negative");
            return null;
        }

        if (paymentAccount.getBalance() < 0) {
            System.out.println("Error: payment account balance must be non-negative");
            return null;
        }

        return new PaymentAccount(paymentAccount);
    }

    public boolean depositMoney(PaymentAccount account, double amount) {
        if (account == null) {
            System.out.println("Error: can not deposit money to not existing payment account");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Error: can not deposit money to payment account - deposit amount must be positive");
            return false;
        }

        account.setBalance(account.getBalance() + amount);
        return true;
    }

    public boolean withdrawMoney(PaymentAccount account, double amount) {
        if (account == null) {
            System.out.println("Error: can not withdraw money from not existing payment account");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Error: can not withdraw money from payment account - withdrawal amount must be positive");
            return false;
        }

        if (account.getBalance() < amount) {
            System.out.println("Error: can not withdraw money from payment account - not enough money!");
            return false;
        }

        account.setBalance(account.getBalance() - amount);

        return true;
    }
}
