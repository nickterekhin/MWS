package domain;

/**
 * Created by Nick on 28.06.2015.
 */
public class ContactType {
    private Long Id;
    private String contactType;
    private boolean active;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ContactType() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactType)) return false;

        ContactType that = (ContactType) o;

        if (!Id.equals(that.Id)) return false;
        return contactType.equals(that.contactType);

    }

    @Override
    public int hashCode() {
        int result = Id.hashCode();
        result = 31 * result + contactType.hashCode();
        return result;
    }
}
