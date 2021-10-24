package com.elderpereira.customerservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Table(name = "tb_customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, length = 15)
    private String cpf;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = false, length = 100)
    private String email;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Address address;

    public Customer() {
    }

    public Customer(Long id, String name, LocalDate birthDate, String cpf, String phone, String email) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
    }

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public Integer getAge(){
        if(this.birthDate != null){
            Period period = Period.between(this.birthDate, LocalDate.now());
            return period.getYears();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(birthDate, customer.birthDate) && Objects.equals(cpf, customer.cpf) && Objects.equals(phone, customer.phone) && Objects.equals(email, customer.email) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, cpf, phone, email, address);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", cpf='" + cpf + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
