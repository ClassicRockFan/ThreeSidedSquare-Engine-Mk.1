package com.threeSidedSquareStudios.engine.object.components.rendering.light;

import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.light.*;
import com.threeSidedSquareStudios.engine.rendering.shaders.forward.ForwardDirectional;

public class DirectionalLight extends BaseLight{

    private Vector3f direction;

    public DirectionalLight(float intensity, Vector3f color, Vector3f direction) {
        super(intensity, color);
        this.direction = direction;
        setShader(ForwardDirectional.getInstance());
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }
}
