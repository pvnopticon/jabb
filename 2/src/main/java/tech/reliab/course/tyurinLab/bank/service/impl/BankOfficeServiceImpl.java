package tech.reliab.course.tyurinLab.bank.service.impl;

import tech.reliab.course.tyurinLab.bank.entity.BankAtm;
import tech.reliab.course.tyurinLab.bank.entity.BankOffice;
import tech.reliab.course.tyurinLab.bank.entity.Employee;
import tech.reliab.course.tyurinLab.bank.service.BankOfficeService;
import tech.reliab.course.tyurinLab.bank.service.BankService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankOfficeServiceImpl implements BankOfficeService {
    private final Map<Integer, BankOffice> bankOfficesTable = new HashMap<>();
    private final Map<Integer, List<Employee>> employeesByOfficeIdTable = new HashMap<>();
    private final Map<Integer, List<BankAtm>> atmsByOfficeIdTable = new HashMap<>();
    private final BankService bankService;

    public BankOfficeServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    public BankOffice create(BankOffice bankOffice) {
        if (bankOffice == null) {
            return null;
        }

        if (bankOffice.getId() < 0) {
            System.out.println("Error: id must be non-negative");
            return null;
        }

        if (bankOffice.getTotalMoney() < 0) {
            System.out.println("Error: bankOffice total money must be non-negative");
            return null;
        }

        if (bankOffice.getBank() == null) {
            System.out.println("Error: bankOffice must have bank");
            return null;
        }

        if (bankOffice.getRentPrice() < 0) {
            System.out.println("Error: bankOffice rentPrice must be non-negative");
            return null;
        }

        bankOfficesTable.put(bankOffice.getId(), bankOffice);
        employeesByOfficeIdTable.put(bankOffice.getId(), new ArrayList<>());
        atmsByOfficeIdTable.put(bankOffice.getId(), new ArrayList<>());
        bankService.addOffice(bankOffice.getBank().getId(), bankOffice);

        return new BankOffice(bankOffice);
    }

    public void printBankOfficeData(int id) {
        BankOffice bankOffice = bankOfficesTable.get(id);

        if (bankOffice == null) {
            System.out.println("Bank office with id: " + id + " was not found.");
            return;
        }

        System.out.println(bankOffice);
        List<Employee> employees = employeesByOfficeIdTable.get(id);
        if (employees != null) {
            System.out.println("Employees:");
            employees.forEach((Employee employee) -> {
                System.out.println(employee);
            });
        }

        List<BankAtm> atms = atmsByOfficeIdTable.get(id);
        if (atms != null) {
            System.out.println("ATMs:");
            atms.forEach((BankAtm atm) -> {
                System.out.println(atm);
            });
        }
    }

    public BankOffice getBankOfficeById(int id) {
        BankOffice office = bankOfficesTable.get(id);

        if (office == null) {
            System.out.println("Bank office with id: " + id + " was not found.");
        }

        return office;
    }

    public List<BankOffice> getAllOffices() {
        return new ArrayList<BankOffice>(bankOfficesTable.values());
    }

    public List<Employee> getAllEmployeesByOfficeId(int officeId) {
        return employeesByOfficeIdTable.get(officeId);
    }

    public boolean installAtm(int bankOfficeId, BankAtm bankAtm) {
        BankOffice bankOffice = getBankOfficeById(bankOfficeId);

        if (bankOffice != null && bankAtm != null) {
            if (!bankOffice.getIsAtmPlaceable()) {
                System.out.println("Error: cannot install atm at office " + bankOffice.getId());
                return false;
            }

            bankAtm.setBankOffice(bankOffice);
            bankAtm.setAddress(bankOffice.getAddress());
            bankAtm.setBank(bankOffice.getBank());
            List<BankAtm> officeAtms = atmsByOfficeIdTable.get(bankOffice.getId());
            officeAtms.add(bankAtm);
            return true;
        }
        return false;
    }

    public boolean removeAtm(BankOffice bankOffice, BankAtm bankAtm) {
        if (bankOffice != null && bankAtm != null) {
            // Добавить поиск банкомата в офисе / банка и удаление оттуда
            // final int newAtmCountOffice = bankOffice.getAtmCount() - 1;
            // if (newAtmCountOffice < 0) {
            //   System.out.println("Error: cannot remove ATM, office has no ATMs");
            //   return false;
            // }

            return true;
        }
        return false;
    }

    public boolean depositMoney(BankOffice bankOffice, double amount) {
        if (bankOffice == null) {
            System.out.println("Error: cannot deposit money to not existing office");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Error: cannot deposit money - deposit amount must be positive");
            return false;
        }

        if (!bankOffice.getIsCashDepositAvailable()) {
            System.out.println("Error: cannot deposit money - office aint accepting deposits");
            return false;
        }

        bankOffice.setTotalMoney(bankOffice.getTotalMoney() + amount);
        // Добавить добавление денег в банк

        return true;
    }

    public boolean withdrawMoney(BankOffice bankOffice, double amount) {
        if (bankOffice == null) {
            System.out.println("Error: can't withdraw money from not existing office");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Error: can't withdraw money - withdraw amount must be positive");
            return false;
        }

        if (!bankOffice.getIsCashWithdrawalAvailable()) {
            System.out.println("Error: can't withdraw money - office aint giving withdrawals");
            return false;
        }

        if (bankOffice.getTotalMoney() < amount) {
            System.out.println("Error: can't withdraw money - office does not have enough money. Please come back later.");
            return false;
        }

        bankOffice.setTotalMoney(bankOffice.getTotalMoney() - amount);
        // Добавить вычитание денег из банка

        return true;
    }

    public boolean addEmployee(int bankOfficeId, Employee employee) {
        BankOffice bankOffice = getBankOfficeById(bankOfficeId);

        if (bankOffice != null && employee != null) {
            employee.setBankOffice(bankOffice);
            employee.setBank(bankOffice.getBank());
            List<Employee> officeEmployees = employeesByOfficeIdTable.get(bankOffice.getId());
            officeEmployees.add(employee);
            return true;
        }
        return false;
    }

    public boolean removeEmployee(BankOffice bankOffice, Employee employee) {
        if (bankOffice != null && employee != null) {
            // добавить поиск работника в офисе и его удаление
            // добавить поиск работника в банке и его удаление
            return true;
        }
        return false;
    }
}
