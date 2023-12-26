package tech.reliab.course.tyurinLab.bank.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import tech.reliab.course.tyurinLab.bank.entity.Bank;
import tech.reliab.course.tyurinLab.bank.entity.CreditAccount;
import tech.reliab.course.tyurinLab.bank.entity.PaymentAccount;
import tech.reliab.course.tyurinLab.bank.entity.User;
import tech.reliab.course.tyurinLab.bank.exception.AccountTransferException;
import tech.reliab.course.tyurinLab.bank.exception.UniquenessException;
import tech.reliab.course.tyurinLab.bank.service.BankService;
import tech.reliab.course.tyurinLab.bank.service.CreditAccountService;
import tech.reliab.course.tyurinLab.bank.service.PaymentAccountService;
import tech.reliab.course.tyurinLab.bank.service.UserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    private final Map<Long, PaymentAccount> paymentAccountsTable = new HashMap<>();
    private final UserService userService;
    private BankService bankService;
    private CreditAccountService creditAccountService;

    public PaymentAccountServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public void setCreditAccountService(CreditAccountService creditAccountService) {
        this.creditAccountService = creditAccountService;
    }

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    public PaymentAccount create(PaymentAccount paymentAccount) throws UniquenessException {
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

        if (paymentAccountsTable.containsKey(createdPaymentAccount.getId())) {
            throw new UniquenessException("PaymentAccount", createdPaymentAccount.getId());
        }

        paymentAccountsTable.put(createdPaymentAccount.getId(), createdPaymentAccount);
        userService.addPaymentAccount(createdPaymentAccount.getUser().getId(), createdPaymentAccount);

        return createdPaymentAccount;
    }

    public void printPaymentData(long id) {
        PaymentAccount paymentAccount = paymentAccountsTable.get(id);

        if (paymentAccount == null) {
            System.out.println("Payment account with id: " + id + " was not found.");
            return;
        }

        System.out.println(paymentAccount);
    }

    public PaymentAccount getPaymentAccountById(long id) {
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

    public boolean importAccountsFromTxtAndTransferToAnotherBank(String fileName, long newBankId) throws AccountTransferException {
        File file = new File(fileName);
        if (!file.exists())
            throw new AccountTransferException("File not found!");

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        JsonReader reader = null;

        try {
            reader = new JsonReader(new FileReader(fileName));
        } catch (FileNotFoundException ignored) {}

        assert reader != null;
        CreditAccount[] accounts = gson.fromJson(reader, CreditAccount[].class);

        for (CreditAccount a : accounts) {
            CreditAccount creditAccount = creditAccountService.getCreditAccountById(a.getId());
            if (creditAccount.getBank().getId() == newBankId) {
                System.out.println("Account with id: " + creditAccount.getId() + " already belongs to selected bank!");
            } else {
                Bank newBank = bankService.getBankById(newBankId);
                creditAccount.setBank(newBank);
                creditAccount.getPaymentAccount().setBank(newBank);
            }

            User user = userService.getUserById(creditAccount.getUser().getId());
            if (user.getBank().getId() != newBankId)
                userService.transferUserToAnotherBank(user, newBankId);
        }

        return true;
    }
}
