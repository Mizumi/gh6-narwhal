package io.alicorn.data.models;


import io.alicorn.data.models.services.Service;

import java.util.List;
import java.util.Set;

public class Continuum {
    private enum Specialty {
        SingleMen, SingleWomen, Veteran, WomenWithChildren, Families, Youth
    }

    private String name;
    private String address;
    private String phoneNumber;
    private String url;
    private Set<Specialty> specialties;
    private List<Service> services;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
