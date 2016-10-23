/*
    Alicorn Systems GH6 Project "Narwhal"

    Copyright (c) Brandon Sanders [brandon@alicorn.io], Joshua Gagen [joshuagagen@outlook.com],
                  Edwin Munguia [daiokaio@gmail.com] and Justin Stone

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
package io.alicorn.data.models.services;

import io.alicorn.data.models.ContactInfo;

import java.util.List;
import java.util.UUID;

public class Service {
    public enum ServiceType {
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
