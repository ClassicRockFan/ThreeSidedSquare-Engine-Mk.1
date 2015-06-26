package com.threeSidedSquareStudios.engine.core;

import com.threeSidedSquareStudios.engine.core.administrative.Logging;
import com.threeSidedSquareStudios.engine.core.math.Matrix4f;
import com.threeSidedSquareStudios.engine.core.math.Quaternion;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;

public class Transform {
    private Transform parent;
    private Matrix4f parentMatrix;

    private Vector3f pos;
    private Quaternion rot;
    private Vector3f scale;

    private Vector3f oldPos;
    private Quaternion oldRot;
    private Vector3f oldScale;

    public Transform() {
        pos = new Vector3f(0, 0, 0);
        rot = new Quaternion(0, 0, 0, 1);
        scale = new Vector3f(1, 1, 1);

        parentMatrix = new Matrix4f().initIdentity();
    }

    public void update() {
        if (oldPos != null) {
            oldPos.set(pos);
            oldRot.set(rot);
            oldScale.set(scale);
        } else {
            oldPos = new Vector3f(0, 0, 0).set(pos).add(1.0f);
            oldRot = new Quaternion(0, 0, 0, 0).set(rot).mul(0.5f);
            oldScale = new Vector3f(0, 0, 0).set(scale).add(1.0f);
        }
    }

    public void rotate(Vector3f axis, float angle) {
        Quaternion q = new Quaternion(axis,angle);
        rot = q.mul(rot).normalized();
    }

    public void lookAt(Vector3f point, Vector3f up) {
        rot = getLookAtDirection(point, up);
    }

    public Quaternion getLookAtDirection(Vector3f point, Vector3f up) {
        return new Quaternion(new Matrix4f().initRotation(point.sub(pos).normalized(), up));
    }

    public boolean hasChanged() {
        if (parent != null && parent.hasChanged())
            return true;

        if (!pos.equals(oldPos))
            return true;

        if (!rot.equals(oldRot))
            return true;

        if (!scale.equals(oldScale))
            return true;

        return false;
    }

    public Matrix4f getTransformation() {
        Matrix4f translationMatrix = new Matrix4f().initTranslation(pos.getX(), pos.getY(), pos.getZ());
        Matrix4f rotationMatrix = rot.toRotationMatrix();
        Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());

        return getParentMatrix().mul(translationMatrix.mul(rotationMatrix.mul(scaleMatrix)));
    }

    private Matrix4f getParentMatrix() {
        if (parent != null && parent.hasChanged())
            parentMatrix = parent.getTransformation();

        return parentMatrix;
    }

    public void setParent(Transform parent) {
        this.parent = parent;
    }

    public Vector3f getTransformedPos() {
        return getParentMatrix().transform(pos);
    }

    public Quaternion getTransformedRot() {
        Quaternion parentRotation = new Quaternion(0, 0, 0, 1);

        if (parent != null)
            parentRotation = parent.getTransformedRot();

        return parentRotation.mul(rot);
    }

    public Vector3f getPosition() {
        return pos;
    }

    public void setPosition(Vector3f pos) {
        this.pos = pos;
    }
    public void setPosition(float x, float y, float z){this.pos = new Vector3f(x, y, z);}

    public Quaternion getRotation() {
        return rot;
    }

    public void setRotation(Quaternion rotation) {
        this.rot = rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = new Vector3f(scale, scale, scale);
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
}
