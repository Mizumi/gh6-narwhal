package io.alicorn.data.models.services;

import io.alicorn.data.models.ContactInfo;

import java.util.List;
import java.util.UUID;

public class Service {
    enum ServiceType {
        Shelter,
        Food,
        Health,
        Education,
        Employment,
        Prevention
    }

    private String uuid;
    private ServiceType serviceType;
    private ContactInfo contactInfo;
    private List<String> availability;

    Service(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Service() { }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getUuid() {
        if (uuid == null || uuid.isEmpty()) {
            uuid = serviceType.toString() + UUID.randomUUID().toString();
        }
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ContactInfo getContactInfo() {
        return this.contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<String> getAvailability() {
        return availability;
    }

    public void setAvailability(List<String> availability) {
        this.availability = availability;
    }
}
