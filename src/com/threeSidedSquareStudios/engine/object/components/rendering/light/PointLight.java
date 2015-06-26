package com.threeSidedSquareStudios.engine.object.components.rendering.light;

import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.shaders.forward.ForwardPoint;

public class PointLight extends BaseLight {
    private static final int COLOR_DEPTH = 256;

    private Attenuation attenuation;
    private float range;

    public PointLight(Vector3f color, float intensity, Attenuation attenuation) {
        super(color, intensity);
        this.attenuation = attenuation;

        float a = attenuation.getExponent();
        float b = attenuation.getLinear();
        float c = attenuation.getConstant() - COLOR_DEPTH * intensity * color.maxVal();

        this.range = (float) ((-b + Math.sqrt((b * b) - (4 * a * c))) / (2 * a));


        setShader(ForwardPoint.getInstance());
    }

    public Attenuation getAttenuation() {
        return attenuation;
    }

    public void setAttenuation(Attenuation attenuation) {
        this.attenuation = attenuation;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }
}
