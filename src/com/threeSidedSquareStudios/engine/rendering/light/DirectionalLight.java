package com.threeSidedSquareStudios.engine.rendering.light;

import com.threeSidedSquareStudios.engine.core.math.Vector3f;

public class DirectionalLight{

    private BaseLight base;
    private Vector3f direction;

    public DirectionalLight(Vector3f color, float intensity, Vector3f direction) {
        this(new BaseLight(color, intensity), direction);
    }

    public DirectionalLight(BaseLight base, Vector3f direction) {
        this.base = base;
        this.direction = direction.normalized();
    }

    public BaseLight getBase() {
        return base;
    }

    public void setBase(BaseLight base) {
        this.base = base;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }
}
