package tech.reliab.course.tyurinLab.bank.service;

import tech.reliab.course.tyurinLab.bank.entity.BankAtm;

import java.util.List;

public interface BankAtmService {
    BankAtm create(BankAtm bankAtm);
    public BankAtm getBankAtmById(int id);
    public List<BankAtm> getAllBankAtms();
    boolean depositMoney(BankAtm bankAtm, double amount);
    boolean withdrawMoney(BankAtm bankAtm, double amount);
}
