package tech.reliab.course.tyurinLab.bank.entity;

import tech.reliab.course.tyurinLab.bank.entity.common.Account;
import tech.reliab.course.tyurinLab.bank.entity.common.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class User extends Person {
    private String placeOfWork = "null";
    private double monthlyIncome = 0;
    private Bank bank = null;
    private double creditRating = 0;
    private List<Account> accounts = new ArrayList<>();

    public User() {
        super();
    }

    public User(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    public User(String placeOfWork, double monthlyIncome, Bank bank, double creditRating) {
        super();
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }

    public User(String name, LocalDate birthDate, String placeOfWork, double monthlyIncome, Bank bank, double creditRating) {
        super(name, birthDate);
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }

    public User(User user) {
        super(user.getId(), user.getName(), user.getBirthDate());
        this.placeOfWork = user.getPlaceOfWork();
        this.monthlyIncome = user.getMonthlyIncome();
        this.bank = user.getBank();
        this.creditRating = user.getCreditRating();
    }

    @Override
    public String toString() {
        return "User {\n" +
                    "\tid: " + id + ",\n" +
                    "\tname: " + name + ",\n" +
                    "\tbirthDate: " + (birthDate == null ? "null" : birthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))) + ",\n" +
                    "\tplaceOfWork: " + placeOfWork + ",\n" +
                    "\tmonthlyIncome: " + String.format("%.2f", monthlyIncome) + ",\n" +
                    "\tbank: " + (bank == null ? "null" : bank.getName()) + ",\n" +
                    "\tcreditRating: " + creditRating + ",\n" +
                "}\n";
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    public void setCreditRating(int creditRating) {
        this.creditRating = creditRating;
    }

    public double getCreditRating() {
        return this.creditRating;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> geAccounts() {
        return accounts;
    }
}
