package tech.reliab.course.tyurinLab.bank.service;

import tech.reliab.course.tyurinLab.bank.entity.User;

public interface UserService {
    User create(User user);
    int calculateCreditRating(User user);
}
