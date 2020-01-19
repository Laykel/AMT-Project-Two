package ch.heigvd.amt.stalkerlog.api.spec.helpers;

import ch.heigvd.amt.stalkerlog.ApiException;
import ch.heigvd.amt.stalkerlog.ApiResponse;
import ch.heigvd.amt.stalkerlog.api.CitiesApi;
import ch.heigvd.amt.stalkerlog.api.StarsApi;
import ch.heigvd.amt.stalkerlog.api.VisitsApi;

import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import java.util.Properties;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {
    private static String KEY_STRING = "+UekP+1B79335qjAe56sWOMZpI+ynYMhK3Dp8R+wS70=";

    private CitiesApi citiesApi = new CitiesApi();
    private StarsApi starsApi = new StarsApi();
    private VisitsApi visitsApi = new VisitsApi();

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    private int lastEntityId;
    private String token;

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

    public ApiResponse getLastApiResponse() {
        return lastApiResponse;
    }

    public void setLastApiResponse(ApiResponse lastApiResponse) {
        this.lastApiResponse = lastApiResponse;
    }

    public ApiException getLastApiException() {
        return lastApiException;
    }

    public void setLastApiException(ApiException lastApiException) {
        this.lastApiException = lastApiException;
    }

    public boolean isLastApiCallThrewException() {
        return lastApiCallThrewException;
    }

    public void setLastApiCallThrewException(boolean lastApiCallThrewException) {
        this.lastApiCallThrewException = lastApiCallThrewException;
    }

    public int getLastStatusCode() {
        return lastStatusCode;
    }

    public void setLastStatusCode(int lastStatusCode) {
        this.lastStatusCode = lastStatusCode;
    }

    public int getLastEntityId() {
        return lastEntityId;
    }

    public void setLastEntityId(int lastEntityId) {
        this.lastEntityId = lastEntityId;
    }

    public static String createJWTString(Long userId, boolean role) {
        // Generate signed JWT containing the user's id and role
        return Jwts.builder()
                .claim("userId", userId)
                .claim("role", role)
                .signWith(decodeKey(KEY_STRING))
                .compact();
    }

    private static Key decodeKey(String keyString) {
        byte[] keyBytes = Base64.getDecoder().decode(keyString);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
