package org.rewards.system.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Customer extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    Long birthDate;

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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }

    public void update(Customer updateCustomer) {
        this.setName(updateCustomer.getName());
        this.setAddress1(updateCustomer.getAddress1());
        this.setAddress2(updateCustomer.getAddress2());
        this.setCity(updateCustomer.getCity());
        this.setState(updateCustomer.getState());
        this.setZip(updateCustomer.getZip());
        this.setBirthDate(updateCustomer.getBirthDate());
    }
}
