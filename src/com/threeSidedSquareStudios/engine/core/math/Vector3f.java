package com.threeSidedSquareStudios.engine.core.math;

public class Vector3f {

    private float x;
    private float y;
    private float z;

    public Vector3f(float x, float y, float z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    public float maxVal() {
        return Math.max(x, Math.max(y, z));
    }

    public Vector3f getBigger(Vector3f other) {
        float length1 = this.length();
        float length2 = other.length();

        if (length1 > length2)
            return this;
        else
            return other;
    }

    public Vector3f max(Vector3f other) {
        Vector3f result = new Vector3f(0, 0, 0);

        if (this.getX() < other.getX())
            result.setX(x);
        else
            result.setX(other.getX());
        if (this.getY() < other.getY())
            result.setY(y);
        else
            result.setY(other.getY());
        if (this.getZ() < other.getZ())
            result.setZ(z);
        else
            result.setZ(other.getZ());

        return result;
    }

    public Vector3f lerp(Vector3f dest, float lerpFactor) {
        return dest.sub(this).mul(lerpFactor).add(this);
    }

    public boolean equals(Vector3f r) {
        return
                x == r.getX() &&
                        y == r.getY() &&
                        z == r.getX();
    }

    public Vector3f abs() {
        return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float dot(Vector3f r) {
        return x * r.getX() + y * r.getY() + z * r.getZ();
    }

    public Vector3f cross(Vector3f r) {
        float x_ = y * r.getZ() - z * r.getY();
        float y_ = z * r.getX() - x * r.getZ();
        float z_ = x * r.getY() - y * r.getX();

        return new Vector3f(x_, y_, z_);
    }


    public Vector3f normalized() {

        float length = length();

        float x_ = x;
        float y_ = y;
        float z_ = z;

        x_ /= length;
        y_ /= length;
        z_ /= length;

        return new Vector3f(x_, y_, z_);
    }

    public Vector3f rotate(Vector3f axis, float angle) {
        float sinHalfAngle = (float) Math.sin(Math.toRadians(angle / 2));
        float cosHalfAngle = (float) Math.cos(Math.toRadians(angle / 2));

        float rX = axis.getX() * sinHalfAngle;
        float rY = axis.getY() * sinHalfAngle;
        float rZ = axis.getZ() * sinHalfAngle;
        float rW = cosHalfAngle;

        Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
        Quaternion conjugate = rotation.conjugate();

        Quaternion w = rotation.mul(this).mul(conjugate);

        return new Vector3f(w.getX(), w.getY(), w.getZ());
    }

    public Vector3f rotate(Quaternion rotation) {
        Quaternion w = rotation.conjugate().mul(this).mul(rotation);

        return new Vector3f(w.getX(), w.getY(), w.getZ());
    }

    public Vector3f reflect(Vector3f normal) {
        return this.sub(normal.mul((this.dot(normal) * 2)));
    }

    public Vector3f print(String header) {
        System.out.println(header + ": " + toString());
        return this;
    }

    @Override
    public String toString() {
        return ("(" + getX() + ", " + getY() + ", " + getZ() + ")");
    }

    public Vector3f sqrt() {
        Vector3f result = new Vector3f(0, 0, 0);
        if (this.getX() < 0) {
            float value = this.getX() * -1;
            result.setX((float) (-1 * Math.sqrt(value)));
        } else {
            result.setX((float) Math.sqrt(this.getX()));
        }
        if (this.getY() < 0) {
            float value = this.getY() * -1;
            result.setY((float) (-1 * Math.sqrt(value)));
        } else {
            result.setY((float) Math.sqrt(this.getY()));
        }
        if (this.getZ() < 0) {
            float value = this.getZ() * -1;
            result.setZ((float) (-1 * Math.sqrt(value)));
        } else {
            result.setZ((float) Math.sqrt(this.getZ()));
        }
        return result;
    }

    public float innerProduct(Vector3f v) {

        return (x * v.getX() + y * v.getY() + z * v.getZ());
    }

    //Swizzling
    public Vector2f getXY() {
        return new Vector2f(x, y);
    }

    public Vector2f getYZ() {
        return new Vector2f(y, z);
    }

    public Vector2f getZX() {
        return new Vector2f(z, x);
    }

    public Vector2f getYX() {
        return new Vector2f(y, x);
    }

    public Vector2f getZY() {
        return new Vector2f(z, y);
    }

    public Vector2f getXZ() {
        return new Vector2f(x, z);
    }

    //Basic Math
    public Vector3f add(Vector3f r) {
        return new Vector3f(x + r.getX(), y + r.getY(), z + r.getZ());
    }

    public Vector3f add(float r) {
        return new Vector3f(x + r, y + r, z + r);
    }

    public Vector3f sub(Vector3f r) {
        return new Vector3f(x - r.getX(), y - r.getY(), z - r.getZ());
    }

    public Vector3f sub(float r) {
        return new Vector3f(x - r, y - r, z - r);
    }

    public Vector3f mul(Vector3f r) {
        return new Vector3f(x * r.getX(), y * r.getY(), z * r.getZ());
    }

    public Vector3f mul(float r) {
        return new Vector3f(x * r, y * r, z * r);
    }

    public Vector3f div(Vector3f r) {
        return new Vector3f(x / r.getX(), y / r.getY(), z / r.getZ());
    }

    public Vector3f div(float r) {
        return new Vector3f(x / r, y / r, z / r);
    }


    //Getters and Setters
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Vector3f set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector3f set(Vector3f r) {
        set(r.getX(), r.getY(), r.getZ());
        return this;
    }

}
