package domain;

import org.joda.time.DateTime;

import java.util.Random;

/**
 * Created by Nick on 03.03.2015.
 */
public class User {
    private Long uid;
    private Long ugroup;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private DateTime regTime;
    private Boolean isLoged;
    private Boolean def_avatar = true;
    private String avatar;

    public User() {

        this.ugroup = 2L;
        this.userName = "Guest-" + DateTime.now().toString();
        this.isLoged = false;

    }

    public User(Long ugroup, String userName, String password, String firstName, String lastName, DateTime regTime, Boolean isLoged) {
        this.ugroup = ugroup;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.regTime = regTime;
        this.isLoged = isLoged;
    }

    public User(Long uid, Long ugroup, String userName, String password, String firstName, String lastName, DateTime regTime, Boolean isLoged) {
        this.uid = uid;
        this.ugroup = ugroup;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.regTime = regTime;
        this.isLoged = isLoged;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getUgroup() {
        return ugroup;
    }

    public void setUgroup(Long ugroup) {
        this.ugroup = ugroup;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(DateTime regTime) {
        this.regTime = regTime;
    }
    public int getRegTimeSeconds() {return (int)regTime.getMillis()/1000;}

    public Boolean getIsLoged() {
        return isLoged;
    }

    public void setIsLoged(Boolean isLoged) {
        this.isLoged = isLoged;
    }

    public Boolean getDef_avatar() {
        return def_avatar;
    }

    public void setDef_avatar(Boolean def_avatar) {
        this.def_avatar = def_avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!password.equals(user.password)) return false;
        if (ugroup != user.ugroup) return false;
        if (uid != user.uid) return false;
        if (!userName.equals(user.userName)) return false;
        if (isLoged != user.isLoged ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid.hashCode();
        result = 31 * result + ugroup.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + isLoged.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", ugroup=" + ugroup +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", regTime=" + regTime + '\''+
                ", isLoged=" + isLoged +
                '}';
    }
}
