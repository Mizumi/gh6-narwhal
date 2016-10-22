package io.alicorn.data.models.services;

public class Shelter implements Service {
    @Override
    public Type getType() {
        return Type.Shelter;
    }
}
