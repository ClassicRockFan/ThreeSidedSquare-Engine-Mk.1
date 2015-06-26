package com.threeSidedSquareStudios.engine.object.components.rendering.light;

import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.shaders.forward.ForwardSpot;

public class SpotLight extends PointLight {

    private float cutoff;

    public SpotLight(Vector3f color, float intensity, Attenuation attenuation, float cutoff) {
        super(color, intensity, attenuation);
        this.cutoff = cutoff;
        setShader(ForwardSpot.getInstance());
    }

    public Vector3f getDirection() {
        return getTransform().getTransformedRot().getForward();
    }


    public float getCutoff() {
        return cutoff;
    }

    public void setCutoff(float cutoff) {
        this.cutoff = cutoff;
    }
}
