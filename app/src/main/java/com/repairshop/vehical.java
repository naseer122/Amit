package com.repairshop;

public class vehical {
    private long id;
    private String name;
    private String vehicleTypeName;
    private String makeName;
    private String repairShopName;

    public vehical() {
    }

    public vehical(long id, String name, String vehicleTypeName, String makeName, String repairShopName) {
        this.id = id;
        this.name = name;
        this.vehicleTypeName = vehicleTypeName;
        this.makeName = makeName;
        this.repairShopName = repairShopName;
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

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public String getRepairShopName() {
        return repairShopName;
    }

    public void setRepairShopName(String repairShopName) {
        this.repairShopName = repairShopName;
    }
}
