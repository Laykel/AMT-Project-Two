package ch.heigvd.amt.stalkerlog.api.spec.steps;

import ch.heigvd.amt.stalkerlog.ApiException;
import ch.heigvd.amt.stalkerlog.ApiResponse;
import ch.heigvd.amt.stalkerlog.api.CitiesApi;
import ch.heigvd.amt.stalkerlog.api.StarsApi;
import ch.heigvd.amt.stalkerlog.api.dto.Star;
import ch.heigvd.amt.stalkerlog.api.spec.helpers.Environment;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class CreationSteps {

    private Environment environment;
    private StarsApi starsApi;

    Star star;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public CreationSteps(Environment environment) {
        this.environment = environment;
        this.starsApi = environment.getStarsApi();
    }

    @Given("^there is a Stars server$")
    public void there_is_a_Stars_server() throws Throwable {
        assertNotNull(starsApi);
    }

    @Given("^I have a star payload$")
    public void i_have_a_star_payload() throws Throwable {
        star = new ch.heigvd.amt.stalkerlog.api.dto.Star();
    }

    @When("^I POST it to the /stars endpoint$")
    public void i_POST_it_to_the_stars_endpoint() throws Throwable {
        try {
            lastApiResponse = starsApi.postStarWithHttpInfo(star);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }

    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(201, lastStatusCode);
    }

}
