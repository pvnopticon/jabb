package tech.reliab.course.tyurinLab.bank.service.impl;

import tech.reliab.course.tyurinLab.bank.entity.CreditAccount;
import tech.reliab.course.tyurinLab.bank.exception.PaymentException;
import tech.reliab.course.tyurinLab.bank.exception.UniquenessException;
import tech.reliab.course.tyurinLab.bank.service.CreditAccountService;
import tech.reliab.course.tyurinLab.bank.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreditAccountServiceImpl implements CreditAccountService {
    private final Map<Long, CreditAccount> creditAccountsTable = new HashMap<>();
    private final UserService userService;

    public CreditAccountServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public CreditAccount create(CreditAccount creditAccount) throws UniquenessException {
        if (creditAccount == null) {
            return null;
        }

        if (creditAccount.getId() < 0) {
            System.out.println("Error: creditAccount id must be non-negative");
            return null;
        }

        if (creditAccount.getMonthCount() < 1) {
            System.out.println("Error: monthCount must be at least 1");
            return null;
        }

        if (creditAccount.getCreditAmount() <= 0) {
            System.out.println("Error: creditAmount must be positive");
            return null;
        }

        if (creditAccount.getBank() == null) {
            System.out.println("Error: creditAccount can't be created without bank info");
            return null;
        }

        CreditAccount createdCreditAccount = new CreditAccount(creditAccount);

        if (creditAccountsTable.containsKey(createdCreditAccount.getId())) {
            throw new UniquenessException("CreditAccount", createdCreditAccount.getId());
        }

        creditAccountsTable.put(createdCreditAccount.getId(), createdCreditAccount);
        userService.addCreditAccount(createdCreditAccount.getUser().getId(), createdCreditAccount);

        return createdCreditAccount;
    }

    public CreditAccount getCreditAccountById(long id) {
        CreditAccount creditAccount = creditAccountsTable.get(id);

        if (creditAccount == null) {
            System.out.println("Credit account with id: " + id + " was not found.");
        }

        return creditAccount;
    }

    public List<CreditAccount> getAllCreditAccounts() {
        return new ArrayList<CreditAccount>(creditAccountsTable.values());
    }

    public boolean makeMonthlyPayment(CreditAccount account) throws PaymentException {
        if (account == null || account.getPaymentAccount() == null) {
            System.out.println("makeMonthlyPayment Error: no account to take money from!");
            return false;
        }

        final double monthlyPayment = account.getMonthlyPayment();
        final double paymentAccountBalance = account.getPaymentAccount().getBalance();

        if (paymentAccountBalance < monthlyPayment) {
            throw new PaymentException("makeMonthlyPayment: unable to proceed operation - not enough balance for monthly payment.");
        }

        account.getPaymentAccount().setBalance(paymentAccountBalance - monthlyPayment);
        account.setRemainingCreditAmount(account.getRemainingCreditAmount() - monthlyPayment);

        return true;
    }
}
