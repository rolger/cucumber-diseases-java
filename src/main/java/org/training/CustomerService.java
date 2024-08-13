package org.training;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CustomerService {

    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(String firstName, String lastName, LocalDate birthday) {

        if (firstName.isEmpty() || lastName.isEmpty()) {
            throw new IllegalArgumentException("Mandatory name parameter is missing");
        }

        if (customerExits(firstName, lastName)) {
            throw new IllegalArgumentException("Customer already exists");
        }

        customers.add(new Customer(firstName, lastName, birthday));
    }

    private boolean customerExits(String firstName, String lastName) {
        if (customers.isEmpty())
            return false;

        return customers.stream().anyMatch(customer -> hasSameName(customer, firstName, lastName));
    }

    public void removeCustomer(String firstName, String lastName, LocalDate birthday) {
        customers.removeIf(customer -> hasSameName(customer, firstName, lastName) && customer.birthday.equals(birthday));
    }

    public Customer searchCustomer(String firstName, String lastName) {
        return searchCustomers(firstName, lastName).stream().findFirst().orElse(null);
    }

    public List<Customer> searchCustomers() {
        return searchCustomers(customer -> true);
    }

    public List<Customer> searchCustomers(String firstName, String lastName) {
        return searchCustomers(customer -> hasSameName(customer, firstName, lastName));
    }

    private List<Customer> searchCustomers(Predicate<Customer> match) {
        return customers.stream().filter(match).toList();
    }

    private static boolean hasSameName(Customer customer, String firstName, String lastName) {
        return customer.firstName.equals(firstName) && customer.lastName.equals(lastName);
    }

}
