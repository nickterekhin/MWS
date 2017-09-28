package domain;

/**
 * Created by Nick on 28.06.2015.
 */
public class Contact {
    private Long Id;
    private Long uid;
    private Long contactType;
    private String contactTypeName;
    private String value;
    private int position;
    private boolean active;

    public Contact() {
    }

    public Contact(Long uid, Long contactType, String value, int position, boolean active) {
        this.uid = uid;
        this.contactType = contactType;
        this.value = value;
        this.position = position;
        this.active = active;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getContactType() {
        return contactType;
    }

    public void setContactType(Long contactType) {
        this.contactType = contactType;
    }

    public String getContactTypeName() {
        return contactTypeName;
    }

    public void setContactTypeName(String contactTypeName) {
        this.contactTypeName = contactTypeName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (!Id.equals(contact.Id)) return false;
        if (!uid.equals(contact.uid)) return false;
        if (!contactType.equals(contact.contactType)) return false;
        return value.equals(contact.value);

    }

    @Override
    public int hashCode() {
        int result = Id.hashCode();
        result = 31 * result + uid.hashCode();
        result = 31 * result + contactType.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
