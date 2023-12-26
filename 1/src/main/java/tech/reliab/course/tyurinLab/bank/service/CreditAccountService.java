package tech.reliab.course.tyurinLab.bank.service;

import tech.reliab.course.tyurinLab.bank.entity.CreditAccount;

public interface CreditAccountService {
    CreditAccount create(CreditAccount creditAccount);
    boolean makeMonthlyPayment(CreditAccount account);
}
