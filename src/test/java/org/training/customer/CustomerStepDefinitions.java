package org.training.customer;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.catchThrowable;

public class CustomerStepDefinitions {

    private static final LocalDate DEFAULT_BIRTHDAY = LocalDate.of(1995, 1, 1);

    private final CustomerService customerService;
    private LocalDate birthday;
    private String firstName;
    private String lastName;
    private String secondLastName;
    private String secondFirstName;
    private Exception error;
    private int count;

    public CustomerStepDefinitions(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Given("the customer first name is {string}")
    public void theCustomerFirstNameIs(String firstName) {
        this.firstName = firstName;
    }

    @Given("the customer last name is {string}")
    public void theCustomerLastNameIs(String lastName) {
        this.lastName = lastName;
    }

    @Given("the customer's birthday is {}")
    public void theCustomersBirthdayIs(String date) {
        this.birthday = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    @Given("the customer name is {} {}")
    public void theCustomerNameIs(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Given("the second customer is {} {}")
    public void theSecondCustomerIs(String firstName, String lastName) {
        this.secondFirstName = firstName;
        this.secondLastName = lastName;
    }

    @When("the customer is created")
    public void theCustomerIsCreated() {
        try {
            customerService.addCustomer(firstName, lastName, DEFAULT_BIRTHDAY);
        } catch (IllegalArgumentException e) {
            error = e;
        }
    }

    @When("an invalid customer is created")
    public void anInvalidCustomerIsCreated() {
        try {
            customerService.addCustomer(firstName, lastName, DEFAULT_BIRTHDAY);
        } catch (IllegalArgumentException e) {
            error = e;
        }
    }

    @When("the second customer is created")
    public void theSecondCustomerIsCreated() {
    }

    @Then("the customer creation should be successful")
    public void theCustomerCreationShouldBeSuccessful() {
        Assertions.assertThat(error).isNull();
    }

    @Then("the customer creation should fail")
    public void theCustomerCreationShouldFail() {
        Assertions.assertThat(error).isNotNull();
        Assertions.assertThat(error).hasMessage("Mandatory name parameter is missing");
    }

    @Then("the second customer creation should fail")
    public void theSecondCustomerCreationShouldFail() {
        Throwable error = catchThrowable(()->customerService.addCustomer(secondFirstName, secondLastName, DEFAULT_BIRTHDAY));

        Assertions.assertThat(error).isNotNull().hasMessage("Customer already exists");
    }

    @Given("there are no customers")
    public void thereAreNoCustomers() {
    }

    @Given("no customers exist")
    public void noCustomersExist() {
    }

    @Given("there is a customer")
    public void thereIsACustomer(DataTable customerTable) {
        List<List<String>> row = customerTable.asLists(String.class);

        customerService.addCustomer(row.get(0).get(0), row.get(0).get(1), DEFAULT_BIRTHDAY);
    }

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

    @When("the customer Rose Smith is searched")
    public void theCustomerRoseSmithIsSearched() {
    }

    @Then("the customer can be found")
    public void theCustomerCanBeFound() {
        var customer = customerService.searchCustomer(firstName, lastName);

        Assertions.assertThat(customer).isNotNull();
    }

    @Then("the customer can not be found")
    public void theCustomerCanNotBeFound() {
        var customer = customerService.searchCustomer(firstName, lastName);

        Assertions.assertThat(customer).isNull();
    }

    @Then("the customer Sabine Mustermann can be found")
    public void theCustomerSabineMustermannCanBeFound() {
        var customer = customerService.searchCustomer("Sabine", "Mustermann");

        Assertions.assertThat(customer.firstName).isEqualTo("Sabine");
        Assertions.assertThat(customer.lastName).isEqualTo("Mustermann");
    }

    @Then("the second customer can be found")
    public void theSecondCustomerCanBeFound() {
        customerService.addCustomer(secondFirstName, secondLastName, DEFAULT_BIRTHDAY);
        var customer = customerService.searchCustomers(secondFirstName, secondLastName);

        Assertions.assertThat(customer).isNotNull();
    }

    @Then("the number of customers found is {int}")
    public void theNumberOfCustomersFoundIs(int expectedCount) {
        Assertions.assertThat(count).isEqualTo(expectedCount);
    }
}
