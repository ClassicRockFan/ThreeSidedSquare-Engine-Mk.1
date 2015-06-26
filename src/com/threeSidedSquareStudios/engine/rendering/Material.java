package com.threeSidedSquareStudios.engine.rendering;

import com.threeSidedSquareStudios.engine.core.math.Vector3f;

public class Material {

    private Texture texture;
    private Vector3f color;
    private float specularIntesity;
    private float specularExponent;

    public Material(String path, Vector3f color) {
        this(new Texture(path), color);
    }

    public Material(Texture texture, Vector3f color) {
        this(texture, color, 1f, 8f);
    }

    public Material(String path, Vector3f color, float specularIntesity, float specularExponent) {
        this(new Texture(path), color, specularIntesity, specularExponent);
    }

    public Material(Texture texture, Vector3f color, float specularIntesity, float specularExponent) {
        this.texture = texture;
        this.color = color;
        this.specularIntesity = specularIntesity;
        this.specularExponent = specularExponent;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public float getSpecularIntesity() {
        return specularIntesity;
    }

    public void setSpecularIntesity(float specularIntesity) {
        this.specularIntesity = specularIntesity;
    }

    public float getSpecularExponent() {
        return specularExponent;
    }

    public void setSpecularExponent(float specularExponent) {
        this.specularExponent = specularExponent;
    }
}
