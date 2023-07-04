import Constants.Category;

import java.util.concurrent.atomic.AtomicInteger;

public class Person extends IOData {
    private int id;
    private Category category;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

    private static AtomicInteger counter = new AtomicInteger(0);

    public static int nextId() {
        return counter.incrementAndGet();
    }

    public Person(String name, String surname, String phoneNumber, String email, Category category) {
        this.id = nextId();
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.category = category;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Person{" +
               "id=" + id +
               ", category='" + category + '\'' +
               ", name='" + name + '\'' +
               ", surname='" + surname + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}