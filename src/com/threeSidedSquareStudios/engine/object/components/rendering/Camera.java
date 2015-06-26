package com.threeSidedSquareStudios.engine.object.components.rendering;

import com.threeSidedSquareStudios.engine.core.Input;
import com.threeSidedSquareStudios.engine.core.math.Matrix4f;
import com.threeSidedSquareStudios.engine.core.math.Quaternion;
import com.threeSidedSquareStudios.engine.core.math.Vector2f;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.object.components.GameComponent;
import com.threeSidedSquareStudios.engine.rendering.Window;
import org.lwjgl.input.Keyboard;

public class Camera extends GameComponent {
    public static final Vector3f yAxis = new Vector3f(0, 1, 0);

    private Matrix4f projectionMatrix;
    private boolean mouseLocked;
    private float sensitivity;

    public Camera(){
        this(70f, 0.1f, 1000f);
    }

    public Camera(float fov, float zNear, float zFar) {
        this(fov, (float) Window.getWidth() / (float) Window.getHeight(), zNear, zFar);
    }

    public Camera(float fov, float aspect, float zNear, float zFar) {
        this.projectionMatrix = new Matrix4f().initPerspective(fov, aspect, zNear, zFar);
        this.mouseLocked = false;
        this.sensitivity = 3.0f;
    }

    public Matrix4f getProjectionMatrix() {
        Matrix4f cameraRotation = getTransform().getTransformedRot().conjugate().toRotationMatrix();
        Vector3f cameraPos = getTransform().getTransformedPos().mul(-1);
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(cameraPos.getX(), cameraPos.getY(), cameraPos.getZ());

        return projectionMatrix.mul(cameraRotation.mul(cameraTranslation));
    }
}
