package domain;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Nick on 01.04.2015.
 */
public class Flat {
    private Long flatId;
    private Long uid;
    private Long countryId;
    private String cityName;
    private String districtName;
    private String street;
    private String flatnumber;
    private Integer roomsQty;
    private String shortDescription;
    private Double flatSquare;
    private Integer level;
    private Boolean isPrivate = false;
    private DateTime buildDate;
    private String fullDescription;
    private DateTime regDate;
    private Long viewCount;
    private Long contactPerson;
    private Boolean default_contact;
    private Boolean isActive = false;
    private Boolean def_foto=false;
    private String photo;
    private String countryName;
    private Double priceForSale;
    private Double priceForRent;
    private String rentRate;
    private Boolean isRent;
    private List<Photo> gallery = new ArrayList<>();

    public Flat() {
    }

    public List<Photo> getGallery() {
        return gallery;
    }

    public void setGallery(List<Photo> gallery) {
        this.gallery = gallery;
    }

    public void setPhotoToGallery(Photo photo)
    {
        gallery.add(photo);
    }

    public Long getFlatId() {
        return flatId;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFlatnumber() {
        return flatnumber;
    }

    public void setFlatnumber(String flatnumber) {
        this.flatnumber = flatnumber;
    }

    public Integer getRoomsQty() {
        return roomsQty;
    }

    public void setRoomsQty(Integer roomsQty) {
        this.roomsQty = roomsQty;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Double getFlatSquare() {
        return flatSquare;
    }

    public void setFlatSquare(Double flatSquare) {
        this.flatSquare = flatSquare;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public DateTime getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(DateTime buildDate) {
        this.buildDate = buildDate;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public DateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(DateTime regDate) {
        this.regDate = regDate;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(Long contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Boolean getDefault_contact() {
        return default_contact;
    }

    public void setDefault_contact(Boolean default_contact) {
        this.default_contact = default_contact;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getDef_foto() {
        return def_foto;
    }

    public void setDef_foto(Boolean def_foto) {
        this.def_foto = def_foto;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Double getPriceForSale() {
        return priceForSale;
    }

    public void setPriceForSale(Double priceForSale) {
        this.priceForSale = priceForSale;
    }

    public Double getPriceForRent() {
        return priceForRent;
    }

    public void setPriceForRent(Double priceForRent) {
        this.priceForRent = priceForRent;
    }

    public String getRentRate() {
        return rentRate;
    }

    public void setRentRate(String rentRate) {
        this.rentRate = rentRate;
    }

    public Boolean getIsRent() {
        return isRent;
    }

    public void setIsRent(Boolean isRent) {
        this.isRent = isRent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flat)) return false;

        Flat flat = (Flat) o;

        if (!buildDate.equals(flat.buildDate)) return false;
        if (!cityName.equals(flat.cityName)) return false;
        if (!countryName.equals(flat.countryName)) return false;
        if (!contactPerson.equals(flat.contactPerson)) return false;
        if (!countryId.equals(flat.countryId)) return false;
        if (districtName != null ? !districtName.equals(flat.districtName) : flat.districtName != null) return false;
        if (!flatId.equals(flat.flatId)) return false;
        if (!flatSquare.equals(flat.flatSquare)) return false;
        if (flatnumber != null ? !flatnumber.equals(flat.flatnumber) : flat.flatnumber != null) return false;
        if (!isPrivate.equals(flat.isPrivate)) return false;
        if (!level.equals(flat.level)) return false;
        if (!regDate.equals(flat.regDate)) return false;
        if (roomsQty != flat.roomsQty) return false;
        if (street != null ? !street.equals(flat.street) : flat.street != null) return false;
        if (!uid.equals(flat.uid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = flatId.hashCode();
        result = 31 * result + uid.hashCode();
        result = 31 * result + countryId.hashCode();
        result = 31 * result + cityName.hashCode();
        result = 31 * result + countryName.hashCode();
        result = 31 * result + (districtName != null ? districtName.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (flatnumber != null ? flatnumber.hashCode() : 0);
        result = 31 * result + roomsQty.hashCode();
        result = 31 * result + flatSquare.hashCode();
        result = 31 * result + level.hashCode();
        result = 31 * result + isPrivate.hashCode();
        result = 31 * result + buildDate.hashCode();
        result = 31 * result + regDate.hashCode();
        result = 31 * result + contactPerson.hashCode();
        return result;
    }
}
