package com.threeSidedSquareStudios.engine.object.components.rendering.light;

import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.shaders.forward.ForwardSpot;

public class SpotLight extends PointLight{

    private Vector3f direction;
    private float cutoff;

    public SpotLight(float intensity, Vector3f color, Attenuation attenuation, float range, Vector3f direction, float cutoff) {
        super(intensity, color, attenuation, range);
        this.direction = direction;
        this.cutoff = cutoff;
        setShader(ForwardSpot.getInstance());
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }

    public float getCutoff() {
        return cutoff;
    }

    public void setCutoff(float cutoff) {
        this.cutoff = cutoff;
    }
}
