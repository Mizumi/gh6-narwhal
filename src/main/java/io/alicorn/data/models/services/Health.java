package io.alicorn.data.models.services;

public class Health implements Service {
    @Override
    public Type getType() {
        return Type.Health;
    }
}
