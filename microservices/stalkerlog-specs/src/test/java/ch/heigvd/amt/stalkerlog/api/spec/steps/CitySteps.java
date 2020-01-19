package ch.heigvd.amt.stalkerlog.api.spec.steps;

import ch.heigvd.amt.stalkerlog.ApiException;
import ch.heigvd.amt.stalkerlog.api.CitiesApi;
import ch.heigvd.amt.stalkerlog.api.dto.City;
import ch.heigvd.amt.stalkerlog.api.spec.helpers.Environment;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CitySteps {
    private Environment environment;
    private CitiesApi citiesApi;

    public CitySteps(Environment environment) {
        this.environment = environment;
        citiesApi = environment.getCitiesApi();
    }

    @When("^I GET page (\\d+) with (\\d+) items of the /cities endpoint$")
    public void i_GET_the_cities_endpoint(int page, int size) {
        try {
            environment.setLastApiResponse(citiesApi.getCitiesWithHttpInfo(page, size));
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
        assertTrue(cities.size() <= 10);
    }

    @And("^I receive 2 pagination headers$")
    public void i_receive_2_pagination_headers() {
        Map headers = environment.getLastApiResponse().getHeaders();
        assertTrue(headers.containsKey("pagination-next"));
        assertTrue(headers.containsKey("pagination-numberofitems"));
    }
}
