package io.alicorn.data.models.services;

public class Shelter extends Service {
    private int totalAvailability;
    private int currentAvailability;
    private String unitType;

    public Shelter() {
        super(ServiceType.Shelter);
    }
}
