package io.alicorn.data.models;


import io.alicorn.data.models.services.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Continuum {
    public enum Specialty {
        SingleMen, SingleWomen, Veteran, WomenWithChildren, Families, Youth
    }

    private String uuid;
    private Set<Specialty> specialties;
    private List<Service> services;
    private ContactInfo contactInfo;

    public String getUuid() {
        if (uuid == null || uuid.isEmpty()) {
            uuid = "CONTINUUM" + UUID.randomUUID().toString();
        }
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    public void addSpecialty(Specialty specialty) {
        this.specialties.add(specialty);
    }

    public void removeSpecialty(Specialty specialty) {
        this.specialties.remove(specialty);
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void addService(Service service) {
        this.services.add(service);
    }

    public void removeService(Service service) {
        this.services.removeIf(s -> s.getUuid().equals(service.getUuid()));
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
