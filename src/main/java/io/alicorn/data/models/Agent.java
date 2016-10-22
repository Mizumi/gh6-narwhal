package io.alicorn.data.models;

public class Agent extends User {
    private String email;
    private String key;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public Kind getKind() {
        return Kind.AGENT;
    }
}
