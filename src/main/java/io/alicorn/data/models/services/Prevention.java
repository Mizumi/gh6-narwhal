package io.alicorn.data.models.services;

public class Prevention implements Service {
    @Override
    public Type getType() {
        return Type.Prevention;
    }
}
