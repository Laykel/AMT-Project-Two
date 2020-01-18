package ch.heigvd.amt.stalkerlog.api.spec.steps;

import ch.heigvd.amt.stalkerlog.ApiException;
import ch.heigvd.amt.stalkerlog.api.CountriesApi;
import ch.heigvd.amt.stalkerlog.api.dto.Country;
import ch.heigvd.amt.stalkerlog.api.spec.helpers.Environment;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CountrySteps {
    private Environment environment;
    private CountriesApi countriesApi;

    public CountrySteps(Environment environment) {
        this.environment = environment;
        this.countriesApi = environment.getCountriesApi();
    }

    @Given("^there is a Countries server$")
    public void there_is_a_Countries_server() throws Throwable {
        assertNotNull(countriesApi);
    }

    @When("^I GET the /countries endpoint$")
    public void i_GET_the_countries_endpoint() {
        try {
            environment.setLastApiResponse(countriesApi.getCountriesWithHttpInfo());
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

    @And("^I receive a list of countries$")
    public void i_receive_a_list_of_countries() {
        List<Country> countries = (ArrayList<Country>)environment.getLastApiResponse().getData();
        assertNotNull(countries);
        assertFalse(countries.isEmpty());
    }
}
