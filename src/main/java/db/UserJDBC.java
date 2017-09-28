package db;

import domain.Contact;
import domain.ContactType;
import domain.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Nick on 03.03.2015.
 */
public interface UserJDBC {
    public User create(User u);
    public void createUser(User u);
    public List<User> getAll();
    public User getById(long userId);
    public User getByUserName(String userName);
    public User getByPassAndUserName(String password, String userName);
    public boolean delUser(User u);
    public boolean delById(long userId);
    public boolean update(User u);
    public User getUser(String UserName, String passed);
    public String getAvatar(Long userId);
    public void setAvatar(Long userId, String fileName);
    public List<Contact> getContacts(Long userId);
    public List<Contact> getContactsAccount(Long userId);
    public boolean setContact(Contact c);
    public boolean OpenClosedContact(Long contactId,String openClose);
    public void removeAvatar(Long userId);
    public List<ContactType> getContactTypes();
    public Contact getContactById(Long contId);
    public void deleteContact(Long cid);

}