package io.alicorn.data.models.services;

public class Shelter extends Service {
    private int totalAvailability;
    private int currentAvailability;
    private String unitType;

    public Shelter() {
        super(ServiceType.Shelter);
    }

    public int getTotalAvailability() {
        return totalAvailability;
    }

    public void setTotalAvailability(int totalAvailability) {
        this.totalAvailability = totalAvailability;
    }

    public int getCurrentAvailability() {
        return currentAvailability;
    }

    public void setCurrentAvailability(int currentAvailability) {
        this.currentAvailability = currentAvailability;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
}
