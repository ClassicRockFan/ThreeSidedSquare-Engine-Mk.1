package com.threeSidedSquareStudios.engine.object.components.rendering;

import com.threeSidedSquareStudios.engine.core.Input;
import com.threeSidedSquareStudios.engine.core.math.Matrix4f;
import com.threeSidedSquareStudios.engine.core.math.Quaternion;
import com.threeSidedSquareStudios.engine.core.math.Vector2f;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.object.components.GameComponent;
import com.threeSidedSquareStudios.engine.rendering.Window;
import org.lwjgl.input.Keyboard;

public class Camera extends GameComponent{
    public static final Vector3f yAxis = new Vector3f(0,1,0);

    private Matrix4f projectionMatrix;
    private boolean mouseLocked;
    private float sensitivity;

    public Camera(float fov, float zNear, float zFar){
        this(fov, (float) Window.getWidth()/(float)Window.getHeight(), zNear, zFar);
    }

    public Camera(float fov, float aspect, float zNear, float zFar) {
        this.projectionMatrix = new Matrix4f().initPerspective(fov, aspect, zNear, zFar);
        this.mouseLocked = false;
        this.sensitivity = 3.0f;
    }

    public void input(float delta) {
        float movAmt = (10 * delta);

        Vector2f centerPos = Window.getCenter();
        if (Input.getMouseDown(0)) {
            Input.setMousePosition(centerPos);
            if (!mouseLocked) {
                mouseLocked = true;
                Input.setCursor(false);
            } else {
                mouseLocked = false;
                Input.setCursor(true);
            }
        }

        if (mouseLocked) {
            Vector2f deltaPos = Input.getMousePosition().sub(centerPos);

            boolean rotY = deltaPos.getX() != 0;
            boolean rotX = deltaPos.getY() != 0;

            if (rotY) {
                getTransform().setRotation(getTransform().getRotation().mul(new Quaternion(yAxis, Math.toRadians((deltaPos.getX() * -sensitivity))).normalized()));
            }
            if (rotX) {
                getTransform().setRotation(getTransform().getRotation().mul(new Quaternion(getTransform().getRotation().getRight(), Math.toRadians((deltaPos.getY() * sensitivity))).normalized()));
            }

            if (rotY || rotX) {
                Input.setMousePosition(centerPos);
            }
        }

        if(Input.getKey(Keyboard.KEY_W))
            move(getTransform().getRotation().getForward(), movAmt);
        if(Input.getKey(Keyboard.KEY_S))
            move(getTransform().getRotation().getForward().mul(-1), movAmt);
        if(Input.getKey(Keyboard.KEY_A))
            move(getTransform().getRotation().getLeft(), movAmt);
        if(Input.getKey(Keyboard.KEY_D))
            move(getTransform().getRotation().getRight(), movAmt);

    }

    public void move(Vector3f dir, float amt) {
        getTransform().setPosition(getTransform().getPosition().add(dir.normalized().mul(amt)));
    }

    public Matrix4f getProjectionMatrix() {
        Matrix4f cameraRotation = getTransform().getRotation().toRotationMatrix();
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(-getTransform().getPosition().getX(), -getTransform().getPosition().getY(), -getTransform().getPosition().getZ());

        return projectionMatrix.mul(cameraRotation.mul(cameraTranslation));
    }
}
