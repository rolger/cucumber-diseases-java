package org.training;

import java.time.LocalDate;

public class Customer {
    public String firstName;
    public String lastName;
    public LocalDate birthday;

    public Customer(String firstName, String lastName, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public String fullName() {
        return firstName.toLowerCase() + " " + lastName;
    }

    public String email() {
        return firstName.toLowerCase() + "." + lastName.toLowerCase() + "@mybank.com";
    }

}