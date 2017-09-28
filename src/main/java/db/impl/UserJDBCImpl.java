package db.impl;

import db.PermissionsJDBC;
import db.UserJDBC;
import db.jdbcadapter.JDBCAdapter;
import domain.Contact;
import domain.ContactType;
import domain.Permission;
import domain.User;
import org.joda.time.DateTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Nick on 04.03.2015.
 */
public class UserJDBCImpl extends JDBCAdapter implements UserJDBC {
    private Connection connection = null;

    @Override
    public User create(User u) {
        return null;
    }

    @Override
    public void createUser(User u) {
        if(u==null)
            return;

        try {

            connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (" +
                    "uid," +
                    "ugroup," +
                    "UserName," +
                    "passwd," +
                    "FirstName," +
                    "LastName," +
                    "RegDate)VALUES(" +
                    "default,?,?,?,?,?,?)");
            preparedStatement.setLong(1, u.getUgroup());
            preparedStatement.setString(2,u.getUserName());
            preparedStatement.setString(3,u.getPassword());
            preparedStatement.setString(4,u.getFirstName());
            preparedStatement.setString(5,u.getLastName());
            preparedStatement.setInt(6,(int)(u.getRegTime().getMillis()/1000));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        finally {
            closeConnection(connection);
        }
        
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(long userId) {

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE uid=? AND ugroup!=1 ");
            preparedStatement.setLong(1,userId);
            ResultSet r = preparedStatement.executeQuery();
            if(r.next())
            {
                User u = new User();
                initUser(u,r);
                return u;
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return null;
    }

    @Override
    public User getByUserName(String userName) {
        return null;
    }

    @Override
    public User getByPassAndUserName(String password, String userName) {
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.*,ug.* FROM users as u LEFT JOIN user_group as ug USING(ugroup) WHERE UserName=? AND passwd=? LIMIT 1");
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2, password);
            ResultSet res = preparedStatement.executeQuery();
            User u =new User();
            if(res.next())
            {
                this.initUser(u,res);
            }
            return u;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }

        return new User();
    }

    @Override
    public boolean delUser(User u) {
        return false;
    }

    @Override
    public boolean delById(long userId) {
        return false;
    }

    @Override
    public boolean update(User u) {
        return false;
    }

    @Override
    public User getUser(String UserName, String passed) {
        return null;
    }

    @Override
    public String getAvatar(Long userId) {

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT avatar FROM users WHERE uid=? AND def_avatar=0");
            preparedStatement.setLong(1,userId);
            ResultSet r = preparedStatement.executeQuery();
            if(r.next())
            {
                return r.getString("avatar");
            }

        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }

        return null;
    }

    @Override
    public void setAvatar(Long userId, String fileName) {
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET def_avatar=0, avatar=? WHERE uid=? ");
            preparedStatement.setString(1,fileName);
            preparedStatement.setLong(2,userId);
            preparedStatement.executeUpdate();
        }catch(SQLException e)
        {
         e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void removeAvatar(Long userId) {
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET def_avatar=1, avatar=NULL WHERE uid=? ");
            preparedStatement.setLong(1,userId);
            preparedStatement.executeUpdate();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Contact> getContacts(Long userId) {
        List<Contact> contacts = new ArrayList<>();
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT c.*, ct.TypeName FROM contacts as c LEFT JOIN ContactType as ct ON c.contactType=ct.id WHERE c.uid=? AND c.active=1");
            preparedStatement.setLong(1,userId);
            ResultSet r = preparedStatement.executeQuery();
            while(r.next())
            {
                Contact c = new Contact();
                initContact(c,r);
                contacts.add(c);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return contacts;
    }

    @Override
    public List<Contact> getContactsAccount(Long userId) {
        List<Contact> contacts = new ArrayList<>();
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT c.*, ct.TypeName FROM contacts as c LEFT JOIN ContactType as ct ON c.contactType=ct.id WHERE c.uid=?");
            preparedStatement.setLong(1,userId);
            ResultSet r = preparedStatement.executeQuery();
            while(r.next())
            {
                Contact c = new Contact();
                initContact(c,r);
                contacts.add(c);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return contacts;
    }

    @Override
    public void deleteContact(Long cid)
    {
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM contacts WHERE Id=?");
            preparedStatement.setLong(1,cid);
            preparedStatement.executeUpdate();
        }catch(SQLException e)
        {
            e.printStackTrace();

        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public boolean setContact(Contact c) {
        if(c==null)
        return false;

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contacts (Id,uid,contactType,value,pos,active)VALUES(default,?,?,?,?,?)");
            preparedStatement.setLong(1,c.getUid());
            preparedStatement.setLong(2,c.getContactType());
            preparedStatement.setString(3, c.getValue());
            preparedStatement.setLong(4,c.getPosition());
            preparedStatement.setBoolean(5,c.isActive());
            preparedStatement.executeUpdate();
            return true;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean OpenClosedContact(Long contactId, String openClose) {

        try{
            connection = getConnection();
            String sql;
            switch(openClose) {
                case "close":
                    sql = "UPDATE contacts SET active=0 WHERE Id=?";
                    break;
                case "open":
                    sql = "UPDATE contacts SET active=1 WHERE Id=?";
                    break;
                default:
                    return false;

            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,contactId);
            preparedStatement.executeUpdate();
            return true;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return false;
    }

    @Override
    public List<ContactType> getContactTypes() {
        List<ContactType> contactTypes = new ArrayList<>();
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ContactType WHERE active=1");
            ResultSet r = preparedStatement.executeQuery();
            while(r.next())
            {
                ContactType ct = new ContactType();
                initContactType(ct,r);
               contactTypes.add(ct);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return contactTypes;
    }

    @Override
    public Contact getContactById(Long contId) {
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * , ct.TypeName FROM contacts as c LEFT JOIN ContactType as ct ON c.contactType=ct.id WHERE c.Id=?");
            preparedStatement.setLong(1,contId);
            ResultSet r = preparedStatement.executeQuery();
            if(r.next())
            {
                Contact c = new Contact();
                initContact(c,r);
                return c;
            }

        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return null;
    }

    private void initUser(User u, ResultSet r) throws SQLException {
        u.setUid(r.getLong("uid"));
        u.setUgroup(r.getLong("ugroup"));
        u.setUserName(r.getString("UserName"));
        u.setPassword(r.getString("passwd"));
        u.setFirstName(r.getString("FirstName"));
        u.setLastName(r.getString("LastName"));
        u.setRegTime(new DateTime(r.getLong("RegDate")*1000));
        u.setDef_avatar(r.getBoolean("def_avatar"));
        u.setAvatar(r.getString("avatar"));
        u.setIsLoged(true);
    }

    private void initContact(Contact c, ResultSet r) throws SQLException {
        c.setId(r.getLong("Id"));
        c.setUid(r.getLong("uid"));
        c.setContactType(r.getLong("contactType"));
        c.setContactTypeName(r.getString("TypeName"));
        c.setValue(r.getString("value"));
        c.setPosition(r.getInt("pos"));
        c.setActive(r.getBoolean("active"));
    }
    private void initContactType(ContactType ct, ResultSet r) throws SQLException {
        ct.setId(r.getLong("id"));
        ct.setContactType(r.getString("TypeName"));
        ct.setActive(r.getBoolean("active"));
    }
}
