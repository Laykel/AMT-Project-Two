package ch.heigvd.amt.stalkerlog.api.spec.steps;

import ch.heigvd.amt.stalkerlog.api.spec.helpers.Environment;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;

public class UtilSteps {
    private Environment environment;

    public UtilSteps(Environment environment) {
        this.environment = environment;
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, environment.getLastStatusCode());
    }
}
