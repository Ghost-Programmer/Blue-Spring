package com.blue.project.modules.organizations.models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Table(name = "organizations", schema = "organizations", catalog = "organizations")
@Entity
public class Organizations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String abbreviation;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String zipCode;
    private String phoneNumber;
    private String emailAddress;
    private byte[] logo;
    private String primaryColor;
    private String accentColor;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "abbreviation", nullable = true, length = 20)
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Basic
    @Column(name = "address_line_1", nullable = true, length = 100)
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Basic
    @Column(name = "address_line_2", nullable = true, length = 100)
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Basic
    @Column(name = "address_line_3", nullable = true, length = 100)
    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 80)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "zip_code", nullable = true, length = 10)
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Basic
    @Column(name = "phone_number", nullable = true, length = 15)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "email_address", nullable = true, length = 80)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Basic
    @Column(name = "logo", nullable = true)
    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "primary_color", nullable = true, length = 6)
    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    @Basic
    @Column(name = "accent_color", nullable = true, length = 6)
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
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(abbreviation, that.abbreviation) && Objects.equals(addressLine1, that.addressLine1) && Objects.equals(addressLine2, that.addressLine2) && Objects.equals(addressLine3, that.addressLine3) && Objects.equals(city, that.city) && Objects.equals(zipCode, that.zipCode) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(emailAddress, that.emailAddress) && Arrays.equals(logo, that.logo) && Objects.equals(primaryColor, that.primaryColor) && Objects.equals(accentColor, that.accentColor);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, abbreviation, addressLine1, addressLine2, addressLine3, city, zipCode, phoneNumber, emailAddress, primaryColor, accentColor);
        result = 31 * result + Arrays.hashCode(logo);
        return result;
    }
}
