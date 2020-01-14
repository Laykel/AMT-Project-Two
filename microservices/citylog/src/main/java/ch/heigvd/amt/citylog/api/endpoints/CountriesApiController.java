package ch.heigvd.amt.citylog.api.endpoints;

import ch.heigvd.amt.citylog.api.CountriesApi;
import ch.heigvd.amt.citylog.entities.CountryEntity;
import ch.heigvd.amt.citylog.api.model.Country;
import ch.heigvd.amt.citylog.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class CountriesApiController implements CountriesApi {
    @Autowired
    CountryRepository countryRepository;

    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countries = new ArrayList<>();
        for (CountryEntity countryEntity : countryRepository.findAll()) {
            countries.add(toCountry(countryEntity));
        }
        return ResponseEntity.ok(countries);
    }

    private CountryEntity toCountryEntity(Country country) {
        CountryEntity entity = new CountryEntity();
        entity.setName(country.getName());
        entity.setCountryCode(country.getCountryCode());
        return entity;
    }

    private Country toCountry(CountryEntity entity) {
        Country country = new Country();
        country.setName(entity.getName());
        country.setCountryCode(entity.getCountryCode());
        return country;
    }
}
