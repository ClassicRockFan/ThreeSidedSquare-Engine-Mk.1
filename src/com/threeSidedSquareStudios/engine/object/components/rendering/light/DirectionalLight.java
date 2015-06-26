package com.threeSidedSquareStudios.engine.object.components.rendering.light;

import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.shaders.forward.ForwardDirectional;

public class DirectionalLight extends BaseLight {

    public DirectionalLight(Vector3f color, float intensity) {
        super(color, intensity);
        setShader(ForwardDirectional.getInstance());
    }

    public Vector3f getDirection() {
        return getTransform().getTransformedRot().getForward();
    }
}
