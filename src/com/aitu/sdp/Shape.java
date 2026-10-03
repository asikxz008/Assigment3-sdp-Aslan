package com.aitu.sdp;

import java.util.Objects;

public abstract class Shape {
    private final String id;
    private Renderer renderer;

    protected Shape(String id, Renderer renderer) {
        this.id = Objects.requireNonNull(id);
        setImplementation(renderer);
    }

    public final String getId() {
        return id;
    }

    protected final Renderer getRenderer() {
        return renderer;
    }

    public final void setImplementation(Renderer renderer) {
        this.renderer = Objects.requireNonNull(renderer);
    }

    public abstract String execute();
}
