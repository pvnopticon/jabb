package tech.reliab.course.tyurinLab.bank.service;

import tech.reliab.course.tyurinLab.bank.entity.PaymentAccount;

public interface PaymentAccountService {
    PaymentAccount create(PaymentAccount paymentAccount);
    boolean depositMoney(PaymentAccount account, double amount);
    boolean withdrawMoney(PaymentAccount account, double amount);
}
