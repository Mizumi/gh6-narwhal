package io.alicorn.data.models.services;

public interface Service {
    enum Type {
        Shelter,
        Food,
        Health,
        Education,
        Employment,
        Prevention
    }

    Type getType();
}
