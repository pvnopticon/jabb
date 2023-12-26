package tech.reliab.course.tyurinLab.bank.exception;

public class PaymentException extends RuntimeException {
    public PaymentException(String msg) {
        super("Error: can not " + msg + " - not enough balance.");
    }
}