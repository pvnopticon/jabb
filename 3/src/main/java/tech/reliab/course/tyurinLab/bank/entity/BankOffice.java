package tech.reliab.course.tyurinLab.bank.entity;

public class BankOffice {
    private static long currentId = 0;
    private long id;
    private String name = "null";
    private String address = "null";
    private Bank bank = null;
    private boolean isWorking = false;
    private boolean isAtmPlaceable = false;
    private boolean isCreditAvailable = false;
    private boolean isCashWithdrawalAvailable = false;
    private boolean isCashDepositAvailable = false;
    private double totalMoney = 0;
    private double rentPrice = 0;

    private void initializeId() {
        id = currentId++;
    }

    public BankOffice() {
        initializeId();
    }

    public BankOffice(String name, String address) {
        initializeId();
        this.name = name;
        this.address = address;
    }

    public BankOffice(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public BankOffice(
            String name,
            String address,
            Bank bank,
            boolean isWorking,
            boolean isAtmPlaceable,
            boolean isCreditAvailable,
            boolean isCashWithdrawalAvailable,
            boolean isCashDepositAvailable,
            double totalMoney,
            double rentPrice
    ) {
        initializeId();
        this.name = name;
        this.address = address;
        this.bank = bank;
        this.isWorking = isWorking;
        this.isAtmPlaceable = isAtmPlaceable;
        this.isCreditAvailable = isCreditAvailable;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.rentPrice = rentPrice;
    }

    public BankOffice(BankOffice bankOffice) {
        this.id = bankOffice.getId();
        this.name = bankOffice.getName();
        this.address = bankOffice.getAddress();
        this.bank = bankOffice.getBank();
        this.isWorking = bankOffice.getIsWorking();
        this.isAtmPlaceable = bankOffice.getIsAtmPlaceable();
        this.isCreditAvailable = bankOffice.getIsCreditAvailable();
        this.isCashWithdrawalAvailable = bankOffice.getIsCashWithdrawalAvailable();
        this.isCashDepositAvailable = bankOffice.getIsCashDepositAvailable();
        this.totalMoney = bankOffice.getTotalMoney();
        this.rentPrice = bankOffice.getRentPrice();
    }

    @Override
    public String toString() {
        return "Bank Office {\n" +
                    "\tid: " + id + ",\n" +
                    "\tname: " + name + ",\n" +
                    "\taddress: " + address + ",\n" +
                    "\tbank: " + (bank == null ? "null" : bank.getName()) + ",\n" +
                    "\tisWorking: " + isWorking + ",\n" +
                    "\tisAtmPlaceable: " + isAtmPlaceable + ",\n" +
                    "\tisCreditAvailable: " + isCreditAvailable + ",\n" +
                    "\tisCashWithdrawalAvailable: " + isCashWithdrawalAvailable + ",\n" +
                    "\tisCashDepositAvailable: " + isCashDepositAvailable + ",\n" +
                    "\ttotalMoney: " + String.format("%.2f", totalMoney) + ",\n" +
                    "\trentPrice: " + String.format("%.2f", rentPrice) + ",\n" +
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

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    public boolean getIsWorking() {
        return isWorking;
    }

    public void setIsAtmPlaceable(boolean isAtmPlaceable) {
        this.isAtmPlaceable = isAtmPlaceable;
    }

    public boolean getIsAtmPlaceable() {
        return isAtmPlaceable;
    }

    public void setIsCreditAvailable(boolean isCreditAvailable) {
        this.isCreditAvailable = isCreditAvailable;
    }

    public boolean getIsCreditAvailable() {
        return isCreditAvailable;
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

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public double getRentPrice() {
        return rentPrice;
    }
}
