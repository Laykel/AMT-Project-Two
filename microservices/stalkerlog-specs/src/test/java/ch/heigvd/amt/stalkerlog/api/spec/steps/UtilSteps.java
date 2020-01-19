package ch.heigvd.amt.stalkerlog.api.spec.steps;

import ch.heigvd.amt.stalkerlog.api.spec.helpers.Environment;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UtilSteps {
    private Environment environment;

    public UtilSteps(Environment environment) {
        this.environment = environment;
    }

    @Given("^there is a Cities server$")
    public void there_is_a_Countries_server() throws Throwable {
        assertNotNull(environment.getCitiesApi());
    }

    @Given("^I am authenticated$")
    public void i_am_authenticated() {
        String token = Environment.createJWTString(1000L, false);
        environment.setToken(token);
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, environment.getLastStatusCode());
    }
}
