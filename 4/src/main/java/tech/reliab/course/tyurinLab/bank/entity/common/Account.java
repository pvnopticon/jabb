package tech.reliab.course.tyurinLab.bank.entity.common;

import tech.reliab.course.tyurinLab.bank.entity.Bank;
import tech.reliab.course.tyurinLab.bank.entity.User;

public class Account {
    private static long currentId = 0;
    protected long id;
    protected User user = null;
    protected Bank bank = null;

    private void initializeId() {
        id = currentId++;
    }

    public Account() {
        initializeId();
    }

    public Account(User user, Bank bank) {
        initializeId();
        this.user = user;
        this.bank = bank;
    }

    public Account(long id, User user, Bank bank) {
        this.id = id;
        this.user = user;
        this.bank = bank;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }
}
