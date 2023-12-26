package tech.reliab.course.tyurinLab.bank.util;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final byte MAX_BANK_RATING = 100;
    public static final double MAX_BANK_TOTAL_MONEY = 1000000;
    public static final double MAX_BANK_INTEREST_RATE = 20;
    public static final double MAX_USER_MONTHLY_INCOME = 10000;
    public static final String ASCII_RED_COLOR = "\u001B[31m";
    public static final String ASCII_GREEN_COLOR = "\u001B[32m";
    public static final String ASCII_BLUE_COLOR = "\u001B[34m";
    public static final String ASCII_PURPLE_COLOR = "\u001B[35m";
    public static final String ASCII_RESET = "\u001B[0m";

    public static final List<String> PERSON_NAMES = new ArrayList<>() {
        {
            add("Ivan");
            add("Sergei");
            add("Egor");
            add("Denis");
            add("Anton");
            add("Roman");
            add("Olga");
        }
    };
    public static final List<String> BANK_ROLES = new ArrayList<>() {
        {
            add("Manager");
            add("CEO");
            add("Security");
            add("Programmer");
            add("Lawyer");
        }
    };
    public static final List<String> COMPANY_NAMES = new ArrayList<>() {
        {
            add("Yandex");
            add("Google");
            add("Huawei");
            add("Microsoft");
            add("Xiaomi");
            add("Samsung");
        }
    };
}
