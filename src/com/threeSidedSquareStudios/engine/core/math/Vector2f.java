package com.threeSidedSquareStudios.engine.core.math;

public class Vector2f {

    private float x;
    private float y;

    public Vector2f(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    public float max() {
        return Math.max(x, y);
    }


    public Vector2f lerp(Vector2f dest, float lerpFactor) {
        return dest.sub(this).mult(lerpFactor).add(this);
    }

    public boolean equals(Vector2f other) {
        return x == other.getX() && y == other.getY();
    }

    public float cross(Vector2f r) {
        return x * r.getY() - y * r.getX();
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float dot(Vector2f r) {
        return x * r.getX() + y * r.getY();
    }

    public Vector2f abs() {
        return new Vector2f(Math.abs(x), Math.abs(y));
    }

    public Vector2f normalize() {
        float length = length();

        x /= length;
        y /= length;

        return this;
    }

    public Vector2f add(Vector2f r) {
        return new Vector2f(x + r.getX(), y + r.getY());
    }

    public Vector2f add(float r) {
        return new Vector2f(x + r, y + r);
    }

    public Vector2f sub(Vector2f r) {
        return new Vector2f(x - r.getX(), y - r.getY());
    }

    public Vector2f sub(float r) {
        return new Vector2f(x - r, y - r);
    }

    public Vector2f mult(Vector2f r) {
        return new Vector2f(x * r.getX(), y * r.getY());
    }

    public Vector2f mult(float r) {
        return new Vector2f(x * r, y * r);
    }

    public Vector2f div(Vector2f r) {
        return new Vector2f(x / r.getX(), y / r.getY());
    }

    public Vector2f div(float r) {
        return new Vector2f(x / r, y / r);
    }

    public Vector2f rotate(float angle) {
        double rad = Math.toRadians(angle);
        double sin = Math.sin(rad);
        double cos = Math.cos(rad);

        return new Vector2f((float) (x * cos - y * sin), (float) (x * sin + y * cos));
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector2f set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2f set(Vector2f r) {
        set(r.getX(), r.getY());
        return this;
    }


}
