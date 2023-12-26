package tech.reliab.course.tyurinLab.bank.entity;

import tech.reliab.course.tyurinLab.bank.entity.common.Account;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreditAccount extends Account {
    private LocalDate dateStart = null;
    private LocalDate dateEnd = null;
    private int monthCount = 0;
    private double creditAmount = 0;
    private double remainingCreditAmount = 0;
    private double monthlyPayment = 0;
    private double interestRate = 0;
    private Employee employee = null;
    private PaymentAccount paymentAccount = null;

    public CreditAccount() {
        super();
    }

    public CreditAccount(LocalDate dateStart, LocalDate dateEnd, int monthCount, double creditAmount, double remainingCreditAmount, double monthlyPayment, double interestRate, Employee employee, PaymentAccount paymentAccount) {
        super();
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.monthCount = monthCount;
        this.creditAmount = creditAmount;
        this.remainingCreditAmount = remainingCreditAmount;
        this.monthlyPayment = monthlyPayment;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    public CreditAccount(User user, Bank bank, LocalDate dateStart, LocalDate dateEnd, int monthCount, double creditAmount, double remainingCreditAmount, double monthlyPayment, double interestRate, Employee employee, PaymentAccount paymentAccount) {
        super(user, bank);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.monthCount = monthCount;
        this.creditAmount = creditAmount;
        this.remainingCreditAmount = remainingCreditAmount;
        this.monthlyPayment = monthlyPayment;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    public CreditAccount(CreditAccount creditAccount) {
        super(creditAccount.getId(), creditAccount.getUser(), creditAccount.getBank());
        this.dateStart = creditAccount.getDateStart();
        this.dateEnd = creditAccount.getDateEnd();
        this.monthCount = creditAccount.getMonthCount();
        this.creditAmount = creditAccount.getCreditAmount();
        this.remainingCreditAmount = creditAccount.getRemainingCreditAmount();
        this.monthlyPayment = creditAccount.getMonthlyPayment();
        this.interestRate = creditAccount.getInterestRate();
        this.employee = creditAccount.getEmployee();
        this.paymentAccount = creditAccount.getPaymentAccount();
    }

    @Override
    public String toString() {
        return "Credit Account {\n" +
                    "\tid: " + id + ",\n" +
                    "\tuser: " + (user == null ? "null" : user.getName()) + ",\n" +
                    "\tbank: " + (bank == null ? "null" : bank.getName()) + ",\n" +
                    "\tdateStart: " + (dateStart == null ? "null" : dateStart.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))) + ",\n" +
                    "\tdateEnd: " + (dateEnd == null ? "null" : dateEnd.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))) + ",\n" +
                    "\tmonthCount: " + monthCount + ",\n" +
                    "\tcreditAmount: " + String.format("%.2f", creditAmount) + ",\n" +
                    "\tmonthlyPayment: " + String.format("%.2f", monthlyPayment) + ",\n" +
                    "\tinterestRate: " + String.format("%.2f", interestRate) + ",\n" +
                    "\temployee: " + (employee == null ? "null" : employee.getName()) + ",\n" +
                    "\tpaymentAccount: " + (paymentAccount == null ? "null" : paymentAccount.getId()) + ",\n" +
                "}\n";
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }

    public int getMonthCount() {
        return monthCount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setRemainingCreditAmount(double remainingCreditAmount) {
        this.remainingCreditAmount = remainingCreditAmount;
    }

    public double getRemainingCreditAmount() {
        return remainingCreditAmount;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }
}
