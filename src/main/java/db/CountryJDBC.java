package db;

import domain.Country;

import java.util.List;

/**
 * Created by Nick on 01.04.2015.
 */
public interface CountryJDBC {
    public String getCountryNameById(Long id);
    public Country getCountryById(Long id);
    public List<Country> getCountries();

}
