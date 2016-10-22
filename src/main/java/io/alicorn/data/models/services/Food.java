package io.alicorn.data.models.services;

public class Food implements Service {
    @Override
    public Type getType() {
        return Type.Food;
    }
}
