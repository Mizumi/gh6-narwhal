package io.alicorn.data.models.services;

import java.util.List;

public class Health extends Service {
    private List<String> careTypes;

    public Health(){
        super(ServiceType.Health);
    }

    public List<String> getCareTypes() {
        return careTypes;
    }

    public void setCareTypes(List<String> careTypes) {
        this.careTypes = careTypes;
    }
}
