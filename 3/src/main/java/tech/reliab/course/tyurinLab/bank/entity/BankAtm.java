package tech.reliab.course.tyurinLab.bank.entity;

import tech.reliab.course.tyurinLab.bank.util.BankAtmStatus;

public class BankAtm {
    private static long currentId = 0;
    private long id;
    private String name;
    private String address;
    private BankAtmStatus status = BankAtmStatus.CLOSED;
    private Bank bank = null;
    private BankOffice bankOffice = null;
    private Employee employee = null;
    private boolean isCashWithdrawalAvailable = false;
    private boolean isCashDepositAvailable = false;
    private double totalMoney = 0;
    private double maintenanceCost = 0;

    private void initializeId() {
        id = currentId++;
    }

    public BankAtm() {
        initializeId();
    }

    public BankAtm(String name, String address, BankAtmStatus status, Bank bank, BankOffice bankOffice, Employee employee, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable, double totalMoney, double maintenanceCost) {
        initializeId();
        this.name = name;
        this.address = address;
        this.status = status;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.employee = employee;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.maintenanceCost = maintenanceCost;
    }

    public BankAtm(BankAtm bankAtm) {
        this.id = bankAtm.getId();
        this.name = bankAtm.getName();
        this.address = bankAtm.getAddress();
        this.status = bankAtm.getStatus();
        this.bank = bankAtm.getBank();
        this.bankOffice = bankAtm.getBankOffice();
        this.employee = bankAtm.getEmployee();
        this.isCashWithdrawalAvailable = bankAtm.getIsCashWithdrawalAvailable();
        this.isCashDepositAvailable = bankAtm.getIsCashDepositAvailable();
        this.totalMoney = bankAtm.getTotalMoney();
        this.maintenanceCost = bankAtm.getMaintenanceCost();
    }

    @Override
    public String toString() {
        return "Bank ATM {\n" +
                    "\tid: " + id + ",\n" +
                    "\tname: " + name + ",\n" +
                    "\taddress: " + address + ",\n" +
                    "\tstatus: " + status.name() + ",\n" +
                    "\tbank: " + (bank == null ? "null" : bank.getName()) + ",\n" +
                    "\tbankOffice: " + (bankOffice == null ? "null" : bankOffice.getName()) + ",\n" +
                    "\temployee: " + (employee == null ? "null" : employee.getName()) + ",\n" +
                    "\tisCashWithdrawalAvailable: " + isCashWithdrawalAvailable + ",\n" +
                    "\tisCashDepositAvailable: " + isCashDepositAvailable + ",\n" +
                    "\ttotalMoney: " + String.format("%.2f", totalMoney) + ",\n" +
                    "\tmaintenanceCost: " + String.format("%.2f", maintenanceCost) + ",\n" +
                "}\n";
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setStatus(BankAtmStatus status) {
        this.status = status;
    }

    public BankAtmStatus getStatus() {
        return status;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBankOffice(BankOffice bankOffice) {
        this.bankOffice = bankOffice;
    }

    public BankOffice getBankOffice() {
        return bankOffice;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setIsCashWithdrawalAvailable(boolean isCashWithdrawalAvailable) {
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
    }

    public boolean getIsCashWithdrawalAvailable() {
        return isCashWithdrawalAvailable;
    }

    public void setIsCashDepositAvailable(boolean isCashDepositAvailable) {
        this.isCashDepositAvailable = isCashDepositAvailable;
    }

    public boolean getIsCashDepositAvailable() {
        return isCashDepositAvailable;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setMaintenanceCost(double maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public double getMaintenanceCost() {
        return maintenanceCost;
    }
}
