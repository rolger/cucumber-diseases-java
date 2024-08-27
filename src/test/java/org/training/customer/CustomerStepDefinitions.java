package org.training.customer;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class CustomerStepDefinitions {

    private static final LocalDate DEFAULT_BIRTHDAY = LocalDate.of(1995, 1, 1);

    private final CustomerService customerService;
    private Exception error;
    private int count;

    public CustomerStepDefinitions(CustomerService customerService) {
        this.customerService = customerService;
    }

    @When("the customer {} {} is created")
    @When("the second customer {} {} is created")
    @When("an invalid customer {} {} is created")
    public void aCustomerIsCreated(String firstName, String lastName) {
        try {
            customerService.addCustomer(firstName, lastName, DEFAULT_BIRTHDAY);
        } catch (IllegalArgumentException e) {
            error = e;
        }
    }

    @Then("the customer creation should be successful")
    public void theCustomerCreationShouldBeSuccessful() {
        Assertions.assertThat(error).isNull();
    }

    @Then("the customer creation should fail")
    public void theCustomerCreationShouldFail() {
        Assertions.assertThat(error)
                .isNotNull()
                .hasMessage("Mandatory name parameter is missing");
    }

    @Then("the second customer creation should fail")
    public void theSecondCustomerCreationShouldFail() {
        Assertions.assertThat(error)
                .isNotNull()
                .hasMessage("Customer already exists");
    }

    @Given("there are no customers")
    public void thereAreNoCustomers() {
    }

    @Given("there is a customer")
    @Given("there are some customers")
    public void thereAreSomeCustomers(DataTable customerTable) {
        List<Map<String, String>> rows = customerTable.asMaps(String.class, String.class);

        for (Map<String, String> col : rows) {
            customerService.addCustomer(col.get("firstname"), col.get("lastname"), DEFAULT_BIRTHDAY);
        }
    }

    @When("all customers are searched")
    public void allCustomersAreSearched() {
        count = customerService.searchCustomers().size();
    }

    @When("the customer Sabine Mustermann is searched")
    public void theCustomerSabineMustermannIsSearched() {
        count = customerService.searchCustomers("Sabine", "Mustermann").size();
    }

    @Then("the customer {} {} can be found")
    public void theCustomerCanBeFound(String firstName, String lastName) {
        var customer = customerService.searchCustomer(firstName, lastName);

        Assertions.assertThat(customer.firstName).isEqualTo(firstName);
        Assertions.assertThat(customer.lastName).isEqualTo(lastName);
    }

    @Then("the number of customers found is {int}")
    public void theNumberOfCustomersFoundIs(int expectedCount) {
        Assertions.assertThat(count).isEqualTo(expectedCount);
    }
}
