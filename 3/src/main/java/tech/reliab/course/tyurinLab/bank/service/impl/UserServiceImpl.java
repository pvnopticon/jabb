package tech.reliab.course.tyurinLab.bank.service.impl;

import tech.reliab.course.tyurinLab.bank.entity.CreditAccount;
import tech.reliab.course.tyurinLab.bank.entity.PaymentAccount;
import tech.reliab.course.tyurinLab.bank.entity.User;
import tech.reliab.course.tyurinLab.bank.exception.NoPaymentAccountException;
import tech.reliab.course.tyurinLab.bank.exception.NotFoundException;
import tech.reliab.course.tyurinLab.bank.exception.UniquenessException;
import tech.reliab.course.tyurinLab.bank.service.BankService;
import tech.reliab.course.tyurinLab.bank.service.UserService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static tech.reliab.course.tyurinLab.bank.util.Constants.MAX_USER_MONTHLY_INCOME;

public class UserServiceImpl implements UserService {
    private final Map<Long, User> usersTable = new HashMap<>();
    private final Map<Long, List<PaymentAccount>> paymentAccountsByUserIdTable = new HashMap<>();
    private final Map<Long, List<CreditAccount>> creditAccountsByUserIdTable = new HashMap<>();
    private final BankService bankService;

    public UserServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    public int calculateCreditRating(User user) {
        final int rating = (int)Math.ceil(user.getMonthlyIncome() / 1000) * 100;
        user.setCreditRating(rating);
        return rating;
    }

    public User create(User user) throws UniquenessException {
        if (user == null) {
            return null;
        }

        if (user.getId() < 0) {
            System.out.println("Error: user id must be non-negative");
            return null;
        }

        if (user.getBank() == null) {
            System.out.println("Error: can not create user without bank");
            return null;
        }

        User createdUser = new User(user);

        if (usersTable.containsKey(createdUser.getId())) {
            throw new UniquenessException("User", createdUser.getId());
        }

        final Random random = new Random();

        final double monthlyIncome = random.nextDouble() * MAX_USER_MONTHLY_INCOME;
        createdUser.setMonthlyIncome(monthlyIncome);
        calculateCreditRating(createdUser);

        usersTable.put(createdUser.getId(), createdUser);
        paymentAccountsByUserIdTable.put(createdUser.getId(), new ArrayList<>());
        creditAccountsByUserIdTable.put(createdUser.getId(), new ArrayList<>());
        bankService.addClient(user.getBank().getId(), createdUser);

        return createdUser;
    }

    public void printUserData(long id, boolean withAccounts) {
        User user = usersTable.get(id);

        if (user == null) {
            System.out.println("User with id: " + id + " was not found.");
            return;
        }

        System.out.println(user);

        if (withAccounts) {
            List<PaymentAccount> paymentAccounts = paymentAccountsByUserIdTable.get(id);
            if (paymentAccounts != null) {
                System.out.println("Payment accounts:");
                paymentAccounts.forEach(System.out::println);
            }

            List<CreditAccount> creditAccounts = creditAccountsByUserIdTable.get(id);
            if (creditAccounts != null) {
                System.out.println("Credit accounts:");
                creditAccounts.forEach(System.out::println);
            }
        }
    }

    public User getUserById(long id) {
        User user = usersTable.get(id);

        if (user == null) {
            System.out.println("User with id: " + id + " was not found.");
        }

        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<User>(usersTable.values());
    }

    public boolean addPaymentAccount(long userId, PaymentAccount paymentAccount) {
        User user = usersTable.get(userId);
        if (user != null) {
            List<PaymentAccount> userPaymentAccounts = paymentAccountsByUserIdTable.get(userId);
            userPaymentAccounts.add(paymentAccount);
            return true;
        }

        return false;
    }

    public boolean addCreditAccount(long userId, CreditAccount creditAccount) {
        User user = usersTable.get(userId);
        if (user != null) {
            List<CreditAccount> userCreditAccounts = creditAccountsByUserIdTable.get(userId);
            userCreditAccounts.add(creditAccount);
            return true;
        }

        return false;
    }

    public List<PaymentAccount> getAllPaymentAccountsByUserId(long userId) {
        return paymentAccountsByUserIdTable.get(userId);
    }

    public PaymentAccount getBestPaymentAccount(long id) throws NotFoundException, NoPaymentAccountException {
        List<PaymentAccount> paymentAccounts = getAllPaymentAccountsByUserId(id);

        return paymentAccounts
                .stream()
                .min(Comparator.comparing(PaymentAccount::getBalance))
                .orElseThrow(NoPaymentAccountException::new);
    }
}
