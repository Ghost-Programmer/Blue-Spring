package com.blue.project.modules.organizations.models;

import com.blue.project.modules.meta.models.Country;
import com.blue.project.modules.meta.models.State;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Table(name = "organizations", schema = "organizations", catalog = "organizations")
@Entity
public class Organizations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "name")
    private String name;

    @Column( name = "abbreviation")
    private String abbreviation;

    @Column( name = "address_line_1")
    private String addressLine1;

    @Column( name = "address_line_2")
    private String addressLine2;

    @Column( name = "address_line_3")
    private String addressLine3;

    @Column( name = "city")
    private String city;

    @Column( name = "zip_code")
    private String zipCode;

    @Column( name = "phone_number")
    private String phoneNumber;

    @Column( name = "email_address")
    private String emailAddress;

    @ManyToOne
    @JoinColumn( name = "country", referencedColumnName = "id")
    private Country country;

    @ManyToOne
    @JoinColumn( name = "state", referencedColumnName = "id")
    private State state;


    @Lob
    @Column( name = "logo")
    private byte[] logo;

    @Column( name = "primary_color")
    private String primaryColor;

    @Column( name = "accent_color")
    private String accentColor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getAccentColor() {
        return accentColor;
    }

    public void setAccentColor(String accentColor) {
        this.accentColor = accentColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organizations that = (Organizations) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(abbreviation, that.abbreviation) && Objects.equals(addressLine1, that.addressLine1) && Objects.equals(addressLine2, that.addressLine2) && Objects.equals(addressLine3, that.addressLine3) && Objects.equals(city, that.city) && Objects.equals(zipCode, that.zipCode) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(emailAddress, that.emailAddress) && Objects.equals(country, that.country) && Objects.equals(state, that.state) && Arrays.equals(logo, that.logo) && Objects.equals(primaryColor, that.primaryColor) && Objects.equals(accentColor, that.accentColor);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, abbreviation, addressLine1, addressLine2, addressLine3, city, zipCode, phoneNumber, emailAddress, country, state, primaryColor, accentColor);
        result = 31 * result + Arrays.hashCode(logo);
        return result;
    }
}
