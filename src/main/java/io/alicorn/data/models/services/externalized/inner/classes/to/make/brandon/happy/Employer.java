package io.alicorn.data.models.services.externalized.inner.classes.to.make.brandon.happy;

import io.alicorn.data.models.ContactInfo;

import java.util.List;

public class Employer {
    private ContactInfo contactInfo;
    private List<Opportunity> opportunities;

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
