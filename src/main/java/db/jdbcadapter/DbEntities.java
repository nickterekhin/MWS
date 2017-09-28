package db.jdbcadapter;

import db.*;
import db.impl.*;

/**
 * Created by Nick on 04.03.2015.
 */
public class DbEntities {
    private UserJDBC users = new UserJDBCImpl();
    private PermissionsJDBC perms = new PermissionsJDBCImpl();
    private GroupJDBC groups = new GroupJDBCImpl(perms);
    private CountryJDBC countryJDBC = new CountryJDBCImpl();
    private FlatJDBC flatJDBC = new FlatJDBCImpl(countryJDBC);

    public FlatJDBC getFlatJDBC() {
        return flatJDBC;
    }

    public UserJDBC getUsers() {
        return users;
    }

    public PermissionsJDBC getPerms() {
        return perms;
    }

    public GroupJDBC getGroups() {
        return groups;
    }

    public CountryJDBC getCountryJDBC() {
        return countryJDBC;
    }
}
