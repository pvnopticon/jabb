package tech.reliab.course.tyurinLab.bank.entity.common;

import java.time.LocalDate;

public class Person {
    private static long currentId = 0;
    protected long id;
    protected String name = "null";
    protected LocalDate birthDate = null;

    private void initializeId() {
        id = currentId++;
    }

    public Person() {
        initializeId();
    }

    public Person(String name, LocalDate birthDate) {
        initializeId();
        this.name = name;
        this.birthDate = birthDate;
    }

    public Person(long id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
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

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
