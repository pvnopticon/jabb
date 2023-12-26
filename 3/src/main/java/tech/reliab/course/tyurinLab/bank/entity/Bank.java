package tech.reliab.course.tyurinLab.bank.entity;


public class Bank {
    private static long currentId = 0;
    private long id;
    private String name = "null";
    private int employeeCount = 0;
    private int userCount = 0;
    private byte rating;
    private double totalMoney;
    private double interestRate;

    private void initializeId() {
        id = currentId++;
    }

    public Bank() {
        initializeId();
    }

    public Bank(String name) {
        initializeId();
        this.name = name;
    }

    public Bank(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bank {\n" +
                    "\tid: " + id + ",\n" +
                    "\tname: " + name + ",\n" +
                    "\temployeeCount: " + employeeCount + ",\n" +
                    "\tuserCount: " + userCount + ",\n" +
                    "\trating: " + rating + ",\n" +
                    "\ttotalMoney: " + String.format("%.2f", totalMoney) + ",\n" +
                    "\tinterestRate: " + String.format("%.2f", interestRate) + "\n" +
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

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public byte getRating() {
        return rating;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
