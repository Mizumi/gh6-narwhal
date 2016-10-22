package io.alicorn.data.models.services.externalized.inner.classes.to.make.brandon.happy;

import io.alicorn.data.models.ContactInfo;

import java.util.List;
import java.util.UUID;

public class Employer {
    private String uuid;
    private ContactInfo contactInfo;
    private List<Opportunity> opportunities;

    public String getUuid() {
        if (uuid == null || uuid.isEmpty()) {
            uuid = "EMPLOYER" + UUID.randomUUID().toString();
        }
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Opportunity> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<Opportunity> opportunities) {
        this.opportunities = opportunities;
    }
}
