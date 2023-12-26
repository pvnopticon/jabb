package tech.reliab.course.tyurinLab.bank.entity;

import tech.reliab.course.tyurinLab.bank.entity.common.Account;

public class PaymentAccount extends Account {
    private double balance = 0;

    public PaymentAccount() {
        super();
    }

    public PaymentAccount(double balance) {
        super();
        this.balance = balance;
    }

    public PaymentAccount(User user, Bank bank, double balance) {
        super(user, bank);
        this.balance = balance;
    }

    public PaymentAccount(PaymentAccount paymentAccount) {
        super(paymentAccount.getId(), paymentAccount.getUser(), paymentAccount.getBank());
        this.balance = paymentAccount.getBalance();
    }

    @Override
    public String toString() {
        return "Payment Account {\n" +
                    "\tid: " + id + ",\n" +
                    "\tuser: " + (user == null ? "null" : user.getName()) + ",\n" +
                    "\tbank: " + (bank == null ? "null" : bank.getName()) + ",\n" +
                    "\tbalance: " + String.format("%.2f", balance) + ",\n" +
                "}\n";
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}
