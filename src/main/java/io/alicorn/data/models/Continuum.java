package io.alicorn.data.models;

import io.alicorn.data.models.resources.Resource;

import java.util.List;

public class Continuum {
    private String address;
    private String phoneNumber;
    private List<Resource> resources;

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

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
