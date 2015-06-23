package com.threeSidedSquareStudios.engine.object.components.rendering.light;

import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.shaders.forward.ForwardPoint;

public class PointLight extends BaseLight{
    private static final int COLOR_DEPTH = 256;

    private Attenuation attenuation;
    private float range;

    public PointLight(float intensity, Vector3f color, Attenuation attenuation, float range) {
        super(intensity, color);
        this.attenuation = attenuation;

        if(Float.compare(range, 0.0f) <= 0) {
            float a = attenuation.getExponent();
            float b = attenuation.getLinear();
            float c = attenuation.getConstant() - COLOR_DEPTH * getIntensity() * getColor().maxVal();

            this.range = (float) ((-b + Math.sqrt(b * b - 4 * a * c)) / 2 * a);
        }else
            this.range = range;

        setShader(ForwardPoint.getInstance());
    }

    public static int getCOLOR_DEPTH() {
        return COLOR_DEPTH;
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
