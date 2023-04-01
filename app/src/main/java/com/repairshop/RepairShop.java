package com.repairshop;

public class RepairShop {
    private long id;
    private String name;
    private String ownerFirstName;
    private String ownerLastName;
    private String address;
    private String phone;
    private String email;
    private String description;

    public RepairShop() {}

    public RepairShop(long id, String name, String ownerFirstName, String ownerLastName, String address, String phone, String email, String description) {
        this.id = id;
        this.name = name;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
