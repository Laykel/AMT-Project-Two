package ch.heigvd.amt.stalkerlog.api.spec.steps;

import ch.heigvd.amt.stalkerlog.ApiException;
import ch.heigvd.amt.stalkerlog.api.CitiesApi;
import ch.heigvd.amt.stalkerlog.api.dto.City;
import ch.heigvd.amt.stalkerlog.api.spec.helpers.Environment;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class CitySteps {
    private Environment environment;
    private CitiesApi citiesApi;

    public CitySteps(Environment environment) {
        this.environment = environment;
        citiesApi = environment.getCitiesApi();
    }

    @Given("^there is a Cities server$")
    public void there_is_a_Cities_server() throws Throwable {
        assertNotNull(citiesApi);
    }

    @When("^I GET the /cities endpoint$")
    public void i_GET_the_cities_endpoint() {
        try {
            environment.setLastApiResponse(citiesApi.getCitiesWithHttpInfo());
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

    @And("^I receive a list of cities$")
    public void i_receive_a_list_of_cities() {
        List<City> cities = (ArrayList<City>)environment.getLastApiResponse().getData();
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }
}
