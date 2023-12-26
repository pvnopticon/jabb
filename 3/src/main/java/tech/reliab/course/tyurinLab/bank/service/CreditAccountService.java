package tech.reliab.course.tyurinLab.bank.service;

import tech.reliab.course.tyurinLab.bank.entity.CreditAccount;
import tech.reliab.course.tyurinLab.bank.exception.PaymentException;
import tech.reliab.course.tyurinLab.bank.exception.UniquenessException;

import java.util.List;

public interface CreditAccountService {
    CreditAccount create(CreditAccount creditAccount) throws UniquenessException;
    public CreditAccount getCreditAccountById(long id);
    public List<CreditAccount> getAllCreditAccounts();
    boolean makeMonthlyPayment(CreditAccount account) throws PaymentException;

}
