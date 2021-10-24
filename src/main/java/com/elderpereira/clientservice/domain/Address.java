package com.elderpereira.clientservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_address")
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false, length = 100)
    private String street;

    @Column(nullable = false, length = 100)
    private String district;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false, length = 100)
    private String complement;

    @Column(name = "postal_code", nullable = false, length = 15)
    private String postalCode;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String state;

    @JsonIgnore
    @OneToOne
    @MapsId
    private Client client;

    public Address(){ }

    public Address(String country, String street, String district, Integer number, String complement, String postalCode, String city, String state) {

        this.country = country;
        this.street = street;
        this.district = district;
        this.number = number;
        this.complement = complement;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(country, address.country) && Objects.equals(street, address.street) && Objects.equals(district, address.district) && Objects.equals(number, address.number) && Objects.equals(complement, address.complement) && Objects.equals(postalCode, address.postalCode) && Objects.equals(city, address.city) && Objects.equals(state, address.state) && Objects.equals(client, address.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, street, district, number, complement, postalCode, city, state, client);
    }

    @Override
    public String toString() {
        return "Address{" +

                ", country='" + country + '\'' +
                ", street='" + street + '\'' +
                ", district='" + district + '\'' +
                ", number=" + number +
                ", complement='" + complement + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
