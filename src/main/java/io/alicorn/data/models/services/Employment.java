package io.alicorn.data.models.services;

public class Employment implements Service {
    @Override
    public Type getType() {
        return Type.Employment;
    }
}
