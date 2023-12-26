package tech.reliab.course.tyurinLab.bank.service.impl;

import tech.reliab.course.tyurinLab.bank.entity.Bank;
import tech.reliab.course.tyurinLab.bank.entity.BankOffice;
import tech.reliab.course.tyurinLab.bank.entity.CreditAccount;
import tech.reliab.course.tyurinLab.bank.entity.Employee;
import tech.reliab.course.tyurinLab.bank.entity.User;
import tech.reliab.course.tyurinLab.bank.service.BankOfficeService;
import tech.reliab.course.tyurinLab.bank.service.BankService;
import tech.reliab.course.tyurinLab.bank.service.UserService;
import tech.reliab.course.tyurinLab.bank.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BankServiceImpl implements BankService {
    private final Map<Integer, Bank> banksTable = new HashMap<>();
    private final Map<Integer, List<BankOffice>> officesByBankIdTable = new HashMap<>();
    private final Map<Integer, List<User>> usersByBankIdTable = new HashMap<>();
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

            final double centralBankInterestRate = random.nextDouble() * Constants.MAX_BANK_INTEREST_RATE;
            final double maxBankInterestRateMargin = Constants.MAX_BANK_INTEREST_RATE - centralBankInterestRate;
            final double bankInterestRateMargin = (random.nextDouble() * maxBankInterestRateMargin) * ((110 - rating) / 100);
            final double interestRate = centralBankInterestRate + bankInterestRateMargin;

            bank.setInterestRate(interestRate);
            return interestRate;
        }
        return 0;
    }

    public Bank create(Bank bank) {
        if (bank == null) {
            return null;
        }

        if (bank.getId() < 0) {
            System.out.println("Error: id must be non-negative");
            return null;
        }

        Bank createdBank = new Bank(bank.getId(), bank.getName());

        final Random random = new Random();

        createdBank.setRating((byte)random.nextInt(Constants.MAX_BANK_RATING + 1));
        createdBank.setTotalMoney(random.nextDouble() * Constants.MAX_BANK_TOTAL_MONEY);
        calculateInterestRate(createdBank);

        if (createdBank != null) {
            banksTable.put(createdBank.getId(), createdBank);
            officesByBankIdTable.put(createdBank.getId(), new ArrayList<>());
            usersByBankIdTable.put(createdBank.getId(), new ArrayList<>());
        }

        return createdBank;
    }

    public void printBankData(int id) {
        Bank bank = banksTable.get(id);

        if (bank == null) {
            System.out.println("Bank with id: " + id + " was not found.");
            return;
        }

        System.out.println("=========================");
        System.out.println(bank);
        List<BankOffice> offices = officesByBankIdTable.get(id);
        if (offices != null) {
            System.out.println("Bank offices:");
            offices.forEach((BankOffice office) -> {
                bankOfficeService.printBankOfficeData(office.getId());
                // System.out.println(office);
            });
        }

        List<User> users = usersByBankIdTable.get(id);
        if (users != null) {
            System.out.println("Users:");
            users.forEach((User user) -> {
                userService.printUserData(user.getId(), false);
            });
        }
        System.out.println("=========================");
    }

    public Bank getBankById(int id) {
        Bank bank = banksTable.get(id);

        if (bank == null) {
            System.out.println("Bank with id: " + id + " was not found.");
        }

        return bank;
    }

    public List<Bank> getAllBanks() {
        return new ArrayList<Bank>(banksTable.values());
    }

    public boolean addOffice(int bankId, BankOffice bankOffice) {
        Bank bank = getBankById(bankId);

        if (bank != null && bankOffice != null) {
            depositMoney(bankId, bankOffice.getTotalMoney());
            List<BankOffice> bankOffices = officesByBankIdTable.get(bankId);
            bankOffices.add(bankOffice);
            return true;
        }
        return false;
    }

    public boolean removeOffice(int bankId, BankOffice bankOffice) {
        Bank bank = getBankById(bankId);
        int officeIndex = officesByBankIdTable.get(bankId).indexOf(bankOffice);

        if (bank != null && officeIndex >= 0) {
            // final short newOfficeCount = (short)(bank.getOfficeCount() - 1);

            // if (newOfficeCount < 0) {
            //   System.out.println("Error: cannot remove office, bank has no offices");
            //   return false;
            // }

            officesByBankIdTable.get(bankId).remove(officeIndex);

            return true;
        }
        return false;
    }

    public List<BankOffice> getAllOfficesByBankId(int bankId) {
        Bank bank = getBankById(bankId);

        if (bank != null) {
            List<BankOffice> bankOffices = officesByBankIdTable.get(bankId);

            return bankOffices;
        }
        return new ArrayList<>();
    }

    public boolean addEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            employee.setBank(bank);
            bank.setEmployeeCount(bank.getEmployeeCount() + 1);
            return true;
        }
        return false;
    }

    public boolean removeEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            final int newEmployeeCount = bank.getEmployeeCount() - 1;

            if (newEmployeeCount < 0) {
                System.out.println("Error: cannot remove employee, bank has no employees");
                return false;
            }

            bank.setEmployeeCount(newEmployeeCount);

            return true;
        }
        return false;
    }

    public boolean addClient(int bankId, User user) {
        Bank bank = getBankById(bankId);

        if (bank != null && user != null) {
            user.setBank(bank);
            bank.setUserCount(bank.getUserCount() + 1);
            List<User> users = usersByBankIdTable.get(bankId);
            users.add(user);
            return true;
        }
        return false;
    }

    public boolean removeClient(Bank bank, User user) {
        if (bank != null && user != null) {
            int newUserCount = bank.getUserCount() - 1;

            if (newUserCount < 0) {
                System.out.println("Error: cannot remove user, bank has no users");
                return false;
            }

            bank.setUserCount(newUserCount);
            return true;
        }
        return false;
    }

    public boolean depositMoney(int bankId, double amount) {
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

    public boolean approveCredit(Bank bank, CreditAccount account, Employee employee) {
        return false; // дописать когда станет понятнее логика кредитов
    }
}
