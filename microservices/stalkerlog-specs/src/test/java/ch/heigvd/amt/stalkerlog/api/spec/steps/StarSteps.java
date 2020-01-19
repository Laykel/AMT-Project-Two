package ch.heigvd.amt.stalkerlog.api.spec.steps;

import ch.heigvd.amt.stalkerlog.ApiException;
import ch.heigvd.amt.stalkerlog.api.StarsApi;
import ch.heigvd.amt.stalkerlog.api.dto.Star;
import ch.heigvd.amt.stalkerlog.api.spec.helpers.Environment;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class StarSteps {

    private Environment environment;
    private StarsApi starsApi;

    Star star;
    Integer starId = null;

    public StarSteps(Environment environment) {
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
        star.setName("Star" + System.currentTimeMillis());
        star.setPlatform("Test");
    }

    @When("^I POST it to the /stars endpoint$")
    public void i_POST_it_to_the_stars_endpoint() throws Throwable {
        try {
            starsApi.getApiClient().setApiKey(environment.getToken());
            environment.setLastApiResponse(starsApi.postStarWithHttpInfo(star));
            environment.setLastApiCallThrewException(false);
            environment.setLastApiException(null);
            environment.setLastStatusCode(environment.getLastApiResponse().getStatusCode());
        } catch (ApiException e) {
            environment.setLastApiCallThrewException(true);
            environment.setLastApiResponse(null);
            environment.setLastApiException(e);
            environment.setLastStatusCode(e.getCode());
        }
    }

    @When("^I GET page (\\d+) with (\\d+) items of the /stars endpoint$")
    public void i_GET_page_with_items_of_the_stars_endpoint(int page, int size) {
        try {
            starsApi.getApiClient().setApiKey(environment.getToken());
            environment.setLastApiResponse(starsApi.getStarsWithHttpInfo(page, size));
            environment.setLastApiCallThrewException(false);
            environment.setLastApiException(null);
            environment.setLastStatusCode(environment.getLastApiResponse().getStatusCode());
        } catch (ApiException e) {
            environment.setLastApiCallThrewException(true);
            environment.setLastApiResponse(null);
            environment.setLastApiException(e);
            environment.setLastStatusCode(e.getCode());
        }
    }

    @And("^I receive a list of <= (\\d+) stars$")
    public void i_receive_a_list_of_cities(int number) {
        List<Star> stars = (ArrayList<Star>)environment.getLastApiResponse().getData();
        assertNotNull(stars);
        assertFalse(stars.isEmpty());
        assertTrue(stars.size() <= number);
    }
}
