package tech.reliab.course.tyurinLab.bank.service.impl;

import tech.reliab.course.tyurinLab.bank.entity.PaymentAccount;
import tech.reliab.course.tyurinLab.bank.service.PaymentAccountService;
import tech.reliab.course.tyurinLab.bank.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    private final Map<Integer, PaymentAccount> paymentAccountsTable = new HashMap<>();
    private final UserService userService;

    public PaymentAccountServiceImpl(UserService userService) {
        this.userService = userService;
    }

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

        PaymentAccount createdPaymentAccount = new PaymentAccount(paymentAccount);
        paymentAccountsTable.put(createdPaymentAccount.getId(), createdPaymentAccount);
        userService.addPaymentAccount(createdPaymentAccount.getUser().getId(), createdPaymentAccount);

        return createdPaymentAccount;
    }

    public void printPaymentData(int id) {
        PaymentAccount paymentAccount = paymentAccountsTable.get(id);

        if (paymentAccount == null) {
            System.out.println("Payment account with id: " + id + " was not found.");
            return;
        }

        System.out.println(paymentAccount);
    }

    public PaymentAccount getPaymentAccountById(int id) {
        PaymentAccount paymentAccount = paymentAccountsTable.get(id);

        if (paymentAccount == null) {
            System.out.println("Payment account with id: " + id + " was not found.");
        }

        return paymentAccount;
    }

    public List<PaymentAccount> getAllPaymentAccounts() {
        return new ArrayList<PaymentAccount>(paymentAccountsTable.values());
    }

    public boolean depositMoney(PaymentAccount account, double amount) {
        if (account == null) {
            System.out.println("Error: can't deposit money to not existing payment account");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Error: can't deposit money to payment account - deposit amount must be positive");
            return false;
        }

        account.setBalance(account.getBalance() + amount);
        return true;
    }

    public boolean withdrawMoney(PaymentAccount account, double amount) {
        if (account == null) {
            System.out.println("Error: can't withdraw money from not existing payment account");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Error: can't withdraw money from payment account - withdrawal amount must be positive");
            return false;
        }

        if (account.getBalance() < amount) {
            System.out.println("Error: can't withdraw money from payment account - not enough money!");
            return false;
        }

        account.setBalance(account.getBalance() - amount);

        return true;
    }
}
