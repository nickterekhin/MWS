package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nick on 01.04.2015.
 */
public class Country {
    private Long countryId;
    private String countryCode;
    private String countryName;
    private Boolean isActive = false;

    public Country() {
    }

    public Country(String countryCode, String countryName, Boolean isActive) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.isActive = isActive;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        if (!countryCode.equals(country.countryCode)) return false;
        if (!countryId.equals(country.countryId)) return false;
        if (!countryName.equals(country.countryName)) return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = countryId.hashCode();
        result = 31 * result + countryCode.hashCode();
        result = 31 * result + countryName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
