package tech.reliab.course.tyurinLab.bank.service;

import tech.reliab.course.tyurinLab.bank.entity.CreditAccount;
import tech.reliab.course.tyurinLab.bank.entity.PaymentAccount;
import tech.reliab.course.tyurinLab.bank.entity.User;

import java.util.List;

public interface UserService {
    User create(User user);
    public void printUserData(long id, boolean withAccounts);
    public User getUserById(long id);
    public List<User> getAllUsers();
    public boolean addPaymentAccount(long userId, PaymentAccount paymentAccount);
    public boolean addCreditAccount(long userId, CreditAccount creditAccount);
    public List<PaymentAccount> getAllPaymentAccountsByUserId(long userId);
    public int calculateCreditRating(User user);
}
