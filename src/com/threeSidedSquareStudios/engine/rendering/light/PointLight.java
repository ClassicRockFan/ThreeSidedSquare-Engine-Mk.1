package com.threeSidedSquareStudios.engine.rendering.light;

import com.threeSidedSquareStudios.engine.core.math.Vector3f;

public class PointLight {

    private static final int COLOR_DEPTH = 256;

    private Attenuation attenuation;
    private BaseLight base;
    private Vector3f position;
    private float range;

    public PointLight(Vector3f color, float intensity, Attenuation attenuation, Vector3f position){
        this(new BaseLight(color, intensity), attenuation, position);
    }

    public PointLight(BaseLight base, Attenuation attenuation, Vector3f position) {
        this.attenuation = attenuation;
        this.base = base;
        this.position = position;

        float a = attenuation.getExponent();
        float b = attenuation.getLinear();
        float c = attenuation.getConstant() - COLOR_DEPTH * base.getIntensity() * base.getColor().maxVal();

        this.range = (float) ((-b + Math.sqrt(b * b - 4 * a * c)) / 2 * a);
    }

    public Attenuation getAttenuation() {
        return attenuation;
    }

    public void setAttenuation(Attenuation attenuation) {
        this.attenuation = attenuation;
    }

    public BaseLight getBase() {
        return base;
    }

    public void setBase(BaseLight base) {
        this.base = base;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public static int getCOLOR_DEPTH() {
        return COLOR_DEPTH;
    }
}
