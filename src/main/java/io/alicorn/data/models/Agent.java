package io.alicorn.data.models;

public class Agent extends User {

    @Override
    public Kind getKind() {
        return Kind.AGENT;
    }
}
