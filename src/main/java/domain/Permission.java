package domain;

/**
 * Created by Nick on 03.03.2015.
 */
public class Permission {
    private long ugroup;
    private String permString;

    public Permission() {
    }

    public Permission(long ugroup, String permString) {
        this.ugroup = ugroup;
        this.permString = permString;
    }

    public long getUgroup() {
        return ugroup;
    }

    public void setUgroup(long ugroup) {
        this.ugroup = ugroup;
    }

    public String getPermString() {
        return permString;
    }

    public void setPermString(String permString) {
        this.permString = permString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permission)) return false;

        Permission that = (Permission) o;

        if (ugroup != that.ugroup) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (ugroup ^ (ugroup >>> 32));
    }

    @Override
    public String toString() {
        return "Permission{" +
                "ugroup=" + ugroup +
                ", permString='" + permString + '\'' +
                '}';
    }
}
