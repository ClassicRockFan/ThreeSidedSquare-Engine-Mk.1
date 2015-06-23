package com.threeSidedSquareStudios.engine.object.components.rendering.light;

import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.object.components.GameComponent;
import com.threeSidedSquareStudios.engine.rendering.shaders.Shader;

public class BaseLight extends GameComponent{

    private float intensity;
    private Vector3f color;
    private Shader shader;

    public BaseLight(float intensity, Vector3f color) {
        this.intensity = intensity;
        this.color = color;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }
}
