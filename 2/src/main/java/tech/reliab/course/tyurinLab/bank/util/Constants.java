package tech.reliab.course.tyurinLab.bank.util;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final byte MAX_BANK_RATING = 100;
    public static final double MAX_BANK_TOTAL_MONEY = 1000000;
    public static final double MAX_BANK_INTEREST_RATE = 20;
    public static final double MAX_USER_MONTHLY_INCOME = 10000;

    public static final List<String> PERSON_NAMES = new ArrayList<>();
    public static final List<String> BANK_ROLES = new ArrayList<>();
    public static final List<String> COMPANY_NAMES = new ArrayList<>();

    public Constants() {
        PERSON_NAMES.add("Ivan");
        PERSON_NAMES.add("Sergei");
        PERSON_NAMES.add("Egor");
        PERSON_NAMES.add("Denis");
        PERSON_NAMES.add("Anton");
        PERSON_NAMES.add("Roman");
        PERSON_NAMES.add("Olga");

        BANK_ROLES.add("Manager");
        BANK_ROLES.add("CEO");
        BANK_ROLES.add("Security");
        BANK_ROLES.add("Programmer");
        BANK_ROLES.add("Lawyer");

        COMPANY_NAMES.add("Yandex");
        COMPANY_NAMES.add("Google");
        COMPANY_NAMES.add("Huawei");
        COMPANY_NAMES.add("Microsoft");
        COMPANY_NAMES.add("Xiaomi");
        COMPANY_NAMES.add("Samsung");
    }
}
