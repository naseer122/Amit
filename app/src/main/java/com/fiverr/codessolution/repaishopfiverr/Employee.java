package com.fiverr.codessolution.repaishopfiverr;

public class Employee {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private long positionId;
    private long repairShopId;

    public Employee() {}

    public Employee(long id, String firstName, String lastName, String email, String phone, long positionId, long repairShopId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.positionId = positionId;
        this.repairShopId = repairShopId;
    }

    // Getter and setter methods

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public long getRepairShopId() {
        return repairShopId;
    }

    public void setRepairShopId(long repairShopId) {
        this.repairShopId = repairShopId;
    }
}