package tech.reliab.course.tyurinLab.bank.service.impl;

import tech.reliab.course.tyurinLab.bank.entity.Bank;
import tech.reliab.course.tyurinLab.bank.entity.BankOffice;
import tech.reliab.course.tyurinLab.bank.entity.CreditAccount;
import tech.reliab.course.tyurinLab.bank.entity.Employee;
import tech.reliab.course.tyurinLab.bank.entity.User;
import tech.reliab.course.tyurinLab.bank.exception.CreditException;
import tech.reliab.course.tyurinLab.bank.exception.NotFoundException;
import tech.reliab.course.tyurinLab.bank.exception.UniquenessException;
import tech.reliab.course.tyurinLab.bank.service.BankOfficeService;
import tech.reliab.course.tyurinLab.bank.service.BankService;
import tech.reliab.course.tyurinLab.bank.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static tech.reliab.course.tyurinLab.bank.util.Constants.ASCII_BLUE_COLOR;
import static tech.reliab.course.tyurinLab.bank.util.Constants.ASCII_PURPLE_COLOR;
import static tech.reliab.course.tyurinLab.bank.util.Constants.ASCII_RESET;
import static tech.reliab.course.tyurinLab.bank.util.Constants.MAX_BANK_INTEREST_RATE;
import static tech.reliab.course.tyurinLab.bank.util.Constants.MAX_BANK_RATING;
import static tech.reliab.course.tyurinLab.bank.util.Constants.MAX_BANK_TOTAL_MONEY;

public class BankServiceImpl implements BankService {
    private final Map<Long, Bank> banksTable = new HashMap<>();
    private final Map<Long, List<BankOffice>> officesByBankIdTable = new HashMap<>();
    private final Map<Long, List<User>> usersByBankIdTable = new HashMap<>();
    private BankOfficeService bankOfficeService;
    private UserService userService;

    public void setBankOfficeService(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public double calculateInterestRate(Bank bank) {
        if (bank != null) {
            final Random random = new Random();
            final byte rating = bank.getRating();

            final double centralBankInterestRate = random.nextDouble() * MAX_BANK_INTEREST_RATE;
            final double maxBankInterestRateMargin = MAX_BANK_INTEREST_RATE - centralBankInterestRate;
            final double bankInterestRateMargin = (random.nextDouble() * maxBankInterestRateMargin) * ((110 - rating) / 100);
            final double interestRate = centralBankInterestRate + bankInterestRateMargin;

            bank.setInterestRate(interestRate);
            return interestRate;
        }
        return 0;
    }

    public Bank create(Bank bank) throws UniquenessException {
        if (bank == null) {
            return null;
        }

        if (bank.getId() < 0) {
            System.out.println("Error: id must be non-negative");
            return null;
        }

        Bank createdBank = new Bank(bank.getId(), bank.getName());

        final Random random = new Random();

        createdBank.setRating((byte)random.nextInt(MAX_BANK_RATING + 1));
        createdBank.setTotalMoney(random.nextDouble() * MAX_BANK_TOTAL_MONEY);
        calculateInterestRate(createdBank);

        if (banksTable.containsKey((createdBank.getId()))) {
            throw new UniquenessException("Bank", createdBank.getId());
        }

        banksTable.put(createdBank.getId(), createdBank);
        officesByBankIdTable.put(createdBank.getId(), new ArrayList<>());
        usersByBankIdTable.put(createdBank.getId(), new ArrayList<>());

        return createdBank;
    }

    public void printBankData(long id) {
        Bank bank = banksTable.get(id);

        if (bank == null) {
            System.out.println("Bank with id: " + id + " was not found.");
            return;
        }

        System.out.println(ASCII_BLUE_COLOR + "=========================" + ASCII_RESET);
        System.out.println(bank);
        List<BankOffice> offices = officesByBankIdTable.get(id);
        if (offices != null) {
            System.out.println(ASCII_PURPLE_COLOR + "Bank offices:" + ASCII_RESET);
            offices.forEach((BankOffice office) -> {
                bankOfficeService.printBankOfficeData(office.getId());
                // System.out.println(office);
            });
        }

        List<User> users = usersByBankIdTable.get(id);
        if (users != null) {
            System.out.println(ASCII_PURPLE_COLOR + "Users:" + ASCII_RESET);
            users.forEach((User user) -> {
                userService.printUserData(user.getId(), false);
            });
        }
        System.out.println(ASCII_BLUE_COLOR + "=========================" + ASCII_RESET);
    }

    public Bank getBankById(long id) {
        Bank bank = banksTable.get(id);

        if (bank == null) {
            System.out.println("Bank with id: " + id + " was not found.");
        }

        return bank;
    }

    public List<Bank> getAllBanks() {
        return new ArrayList<Bank>(banksTable.values());
    }

    public boolean addOffice(long bankId, BankOffice bankOffice) {
        Bank bank = getBankById(bankId);

        if (bank != null && bankOffice != null) {
            depositMoney(bankId, bankOffice.getTotalMoney());
            List<BankOffice> bankOffices = officesByBankIdTable.get(bankId);
            bankOffices.add(bankOffice);
            bank.addOffice(bankOffice);
            return true;
        }
        return false;
    }

    public List<BankOffice> getAllOfficesByBankId(long bankId) {
        Bank bank = getBankById(bankId);

        if (bank != null) {
            return officesByBankIdTable.get(bankId);
        }

        return new ArrayList<>();
    }

    public boolean addEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            employee.setBank(bank);
            bank.addEmployee(employee);
            return true;
        }
        return false;
    }

    public boolean addClient(long bankId, User user) {
        Bank bank = getBankById(bankId);

        if (bank != null && user != null) {
            user.setBank(bank);
            List<User> users = usersByBankIdTable.get(bankId);
            users.add(user);
            bank.addUser(user);
            return true;
        }
        return false;
    }

    public boolean depositMoney(long bankId, double amount) {
        Bank bank = getBankById(bankId);

        if (bank == null) {
            System.out.println("Error: cannot deposit money to uninitialized bank");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Error: cannot deposit money - deposit amount must be positive");
            return false;
        }

        bank.setTotalMoney(bank.getTotalMoney() + amount);
        return true;
    }

    public boolean withdrawMoney(Bank bank, double amount) {
        if (bank == null) {
            System.out.println("Error: cannot withdraw money from uninitialized bank");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Error: cannot withdraw money - withdraw amount must be positive");
            return false;
        }

        if (bank.getTotalMoney() < amount) {
            System.out.println("Error: cannot withdraw money - bank does not have enough money. Please come back later.");
            return false;
        }

        bank.setTotalMoney(bank.getTotalMoney() - amount);
        return true;
    }

    public boolean approveCredit(Bank bank, CreditAccount account, Employee employee) throws CreditException {
        if (account != null && bank != null && employee != null) {
            double sum = account.getCreditAmount();

            if (bank.getTotalMoney() >= sum && employee.getIsCreditAvailable()) {
                double bankInterestRateMultiplier = 1 + (bank.getInterestRate() / 100);
                double sumMonthPay = sum * bankInterestRateMultiplier / account.getMonthCount();

                if (account.getUser().getMonthlyIncome() >= sumMonthPay) {
                    if (account.getUser().getCreditRating() < 5000 && bank.getRating() > 50) {
                        throw new CreditException("User credit rating is too low");
                    }

                    account.setEmployee(employee);
                    account.setMonthlyPayment(sumMonthPay);
                    account.setBank(bank);
                    account.setInterestRate(bank.getInterestRate());

                    LocalDate dateEnd = account.getDateStart();
                    dateEnd = dateEnd.plusMonths(account.getMonthCount());
                    account.setDateEnd(dateEnd);

                    return true;
                } else {
                    throw new CreditException("User income is too low");
                }
            }
        }

        return false;
    }

    public List<BankOffice> getBankOfficeSuitableInBank(Bank bank, double sum) throws NotFoundException {
        List<BankOffice> bankOfficesByBank = getAllOfficesByBankId(bank.getId());
        List<BankOffice> suitableBankOffice = new ArrayList<>();

        for (BankOffice bankOffice : bankOfficesByBank) {
            if (bankOfficeService.isSuitableBankOffice(bankOffice, sum)) {
                suitableBankOffice.add(bankOffice);
            }
        }

        return suitableBankOffice;
    }

    public boolean isBankSuitable(Bank bank, double sum) throws NotFoundException {
        List<BankOffice> bankOfficeSuitable = getBankOfficeSuitableInBank(bank, sum);
        return !bankOfficeSuitable.isEmpty();
    }

    public List<Bank> getBanksSuitable(double sum, int countMonth) throws NotFoundException, CreditException {
        List<Bank> banksSuitable = new ArrayList<>();
        for (Bank bank : banksTable.values()) {
            if (isBankSuitable(bank, sum)) {
                banksSuitable.add(bank);
            }
        }

        if (banksSuitable.isEmpty()) {
            throw new CreditException("No suitable bank found for credit with passed parameters.");
        }

        return banksSuitable;
    }

    public boolean transferClient(User user, long newBankId) {
        Bank currentBank = getBankById(user.getBank().getId());
        Bank newBank = getBankById(newBankId);

        if (currentBank != null && newBank != null) {
            List<User> users = usersByBankIdTable.get(user.getBank().getId());
            users.remove(user);
            currentBank.removeUser(user);


            user.setBank(newBank);
            users.add(user);
            newBank.addUser(user);
            return true;
        }

        return false;
    }
}
