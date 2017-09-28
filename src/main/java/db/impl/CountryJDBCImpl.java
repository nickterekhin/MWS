package db.impl;

import db.CountryJDBC;
import db.jdbcadapter.JDBCAdapter;
import domain.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 01.04.2015.
 */
public class CountryJDBCImpl extends JDBCAdapter implements CountryJDBC{
    private Connection connection = null;
    @Override
    public String getCountryNameById(Long id) {
       return getCountryById(id).getCountryName();
    }

    public List<Country> getCountries(){
            List<Country> countries = new ArrayList<>();
            try{
                connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM country WHERE isActive=1");
                ResultSet res = preparedStatement.executeQuery();
                while(res.next())
                {
                    Country c = new Country();
                    initCountry(res,c);
                    countries.add(c);
                }
                return countries;
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        finally {
                closeConnection(connection);
            }
        return null;
    }
    @Override
    public Country getCountryById(Long id) {
        if(id==null)
            return null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM country WHERE id=? AND isActive=1");
            preparedStatement.setLong(1,id);
            ResultSet res = preparedStatement.executeQuery();
            Country country = null;
            if(res.next())
            {
                country = new Country();
                initCountry(res,country);
            }
            return country;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return null;

    }

    public void initCountry(ResultSet r, Country c) throws SQLException {
        c.setCountryId(r.getLong("Id"));
        c.setCountryCode(r.getString("Code"));
        c.setCountryName(r.getString("Country"));
        c.setIsActive(r.getBoolean("isActive"));
    }
}
