package com.threeSidedSquareStudios.engine.core;

import com.threeSidedSquareStudios.engine.core.math.Matrix4f;
import com.threeSidedSquareStudios.engine.core.math.Quaternion;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.Camera;

public class Transform {

    private Camera bullshit;

    private Vector3f position;
    private Vector3f scale;
    private Quaternion rotation;

    private Vector3f rotationVec;

    private Transform parent;
    private Matrix4f parentMatrix;

    private Vector3f oldPos;
    private Quaternion oldRot;
    private Vector3f oldScale;

    public Transform() {
        this.position = new Vector3f(0,0,0);
        this.scale = new Vector3f(1,1,1);
        this.rotation = new Quaternion(0,0,0,1);
        this.rotationVec = new Vector3f(0, 0, 0);
        this.parent = null;
        this.parentMatrix = new Matrix4f().initIdentity();
    }

//    public Matrix4f getTransformation()
//    {
//        Matrix4f translationMatrix = new Matrix4f().initTranslation(position.getX(), position.getY(), position.getZ());
//        //Matrix4f rotationMatrix = new Matrix4f().initRotation(rotationVec.getX(), rotationVec.getY(), rotationVec.getZ());
//        Matrix4f rotationMatrix = rotation.toRotationMatrix();
//
//        Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());
//
//        return translationMatrix.mul(rotationMatrix.mul(scaleMatrix));
//    }

    public void rotate(Vector3f axis, float angle) {
        rotation = new Quaternion(axis, angle).mul(rotation).normalized();
    }

    public void lookAt(Vector3f point, Vector3f up) {
        rotation = getLookAtDirection(point, up);
    }

    public Quaternion getLookAtDirection(Vector3f point, Vector3f up) {
        return new Quaternion(new Matrix4f().initRotation(point.sub(position).normalized(), up));
    }

    public boolean hasChanged() {
        if (parent != null && parent.hasChanged())
            return true;

        if (!position.equals(oldPos))
            return true;

        if (!rotation.equals(oldRot))
            return true;

        if (!scale.equals(oldScale))
            return true;

        return false;
    }

    public Matrix4f getTransformation() {
        Matrix4f translationMatrix = new Matrix4f().initTranslation(position.getX(), position.getY(), position.getZ());
        Matrix4f rotationMatrix = rotation.toRotationMatrix();
        Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());

        return translationMatrix.mul(rotationMatrix.mul(scaleMatrix));
    }

    private Matrix4f getParentMatrix() {
        if (parent != null && parent.hasChanged())
            parentMatrix = parent.getTransformation();

        return parentMatrix;
    }


    public Transform getHighest(){
        if(parent != null)
            return parent.getHighest();
        return new Transform();
    }

    public Vector3f getTransformedPos() {
        return parentMatrix.transform(position);
    }

    public Quaternion getTransformedRot() {
        Quaternion parentRotation = new Quaternion(0, 0, 0, 1);

        if (parent != null)
            parentRotation = parent.getTransformedRot();

        return parentRotation.mul(rotation);
    }

    public Transform getParent() {
        return parent;
    }

    public void setParent(Transform parent) {
        this.parent = parent;
    }

    public Vector3f getPosition() {

        return position;
    }

    public void setPosition(float x, float y, float z){setPosition(new Vector3f(x, y, z));}

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
    public void setScale(float scale){
        setScale(new Vector3f(scale, scale, scale));
    }

    public Quaternion getRotation() {
        return rotation;
    }

    public void setRotation(Quaternion rotation) {
        this.rotation = rotation;
    }

    public Vector3f getRotationVec() {
        return rotationVec;
    }

    public void setRotationVec(Vector3f rotationVec) {
        this.rotationVec = rotationVec;
    }

}
