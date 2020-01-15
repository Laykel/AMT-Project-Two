package ch.heigvd.amt.stalkerlog.api.spec.helpers;

import ch.heigvd.amt.stalkerlog.api.CitiesApi;
import ch.heigvd.amt.stalkerlog.api.StarsApi;
import ch.heigvd.amt.stalkerlog.api.VisitsApi;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private CitiesApi citiesApi = new CitiesApi();
    private StarsApi starsApi = new StarsApi();
    private VisitsApi visitsApi = new VisitsApi();

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heigvd.amt.stalkerlog.server.url");
        citiesApi.getApiClient().setBasePath(url);
        starsApi.getApiClient().setBasePath(url);
        visitsApi.getApiClient().setBasePath(url);

    }

    public CitiesApi getCitiesApi() {
        return citiesApi;
    }

    public StarsApi getStarsApi() {
        return starsApi;
    }

    public VisitsApi getVisitsApi() {
        return visitsApi;
    }

}
