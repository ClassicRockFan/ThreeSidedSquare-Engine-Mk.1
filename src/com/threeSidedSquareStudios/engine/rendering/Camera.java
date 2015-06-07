package com.threeSidedSquareStudios.engine.rendering;

import com.threeSidedSquareStudios.engine.core.Input;
import com.threeSidedSquareStudios.engine.core.math.Vector2f;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import org.lwjgl.input.Keyboard;

public class Camera {

    public static final Vector3f yAxis = new Vector3f(0,1,0);

    private Vector3f pos;
    private Vector3f forward;
    private Vector3f up;
    private boolean mouseLocked;
    private float sensitivity;

    public Camera()
    {
        this(new Vector3f(0,0,0), new Vector3f(0,0,1), new Vector3f(0,1,0));
    }

    public Camera(Vector3f pos, Vector3f forward, Vector3f up)
    {
        this.pos = pos;
        this.forward = forward.normalized();
        this.up = up.normalized();
        this.mouseLocked = false;
        this.sensitivity = 50.0f;
    }

    public void input(float delta)
    {
        float movAmt = (10 * delta);
        float rotAmt = (100 * delta);

        float rotateAmount = sensitivity;

        if (Input.getMouseDown(0)) {
            Input.setMousePosition(Window.getCenter());
            if (!mouseLocked) {
                mouseLocked = true;
                Input.setCursor(false);
            } else {
                mouseLocked = false;
                Input.setCursor(true);
            }
        }

        if (mouseLocked) {
            Vector2f deltaPos = Input.getMousePosition().sub(Window.getCenter());

            boolean rotY = deltaPos.getX() != 0;
            boolean rotX = deltaPos.getY() != 0;

            if (rotY) {
                rotateY((float) Math.toRadians(deltaPos.getX() * rotateAmount));
            }
            if (rotX) {
                rotateX((float) Math.toRadians(-deltaPos.getY() * rotateAmount));
            }

            if (rotY || rotX) {
                Input.setMousePosition(Window.getCenter());
            }
        }


        if(Input.getKey(Keyboard.KEY_W))
            move(getForward(), movAmt);
        if(Input.getKey(Keyboard.KEY_S))
            move(getForward(), -movAmt);
        if(Input.getKey(Keyboard.KEY_A))
            move(getLeft(), movAmt);
        if(Input.getKey(Keyboard.KEY_D))
            move(getRight(), movAmt);

        if(Input.getKey(Keyboard.KEY_I))
            rotateX(-rotAmt);
        if(Input.getKey(Keyboard.KEY_K))
            rotateX(rotAmt);
        if(Input.getKey(Keyboard.KEY_J))
            rotateY(-rotAmt);
        if(Input.getKey(Keyboard.KEY_L))
            rotateY(rotAmt);
    }

    public void move(Vector3f dir, float amt)
    {
        setPos(pos.add(dir.mul(amt)));
    }

    public void rotateY(float angle)
    {
        Vector3f Haxis = yAxis.cross(forward).normalized();

        forward.rotate(angle, yAxis);

        setUp(forward.cross(Haxis).normalized());
    }

    public void rotateX(float angle) {
        Vector3f Haxis = yAxis.cross(forward).normalized();

        forward.rotate(angle, Haxis).normalized();

        setUp(forward.cross(Haxis).normalized());
    }

    public Vector3f getLeft()
    {
        Vector3f left = forward.cross(up).normalized();
        return left;
    }

    public Vector3f getRight()
    {
        Vector3f right = up.cross(forward).normalized();
        return right;
    }

    public Vector3f getPos()
    {
        return pos;
    }

    public void setPos(Vector3f pos)
    {
        this.pos = pos;
    }

    public Vector3f getForward()
    {
        return forward;
    }

    public void setForward(Vector3f forward)
    {
        this.forward = forward;
    }

    public Vector3f getUp()
    {
        return up;
    }

    public void setUp(Vector3f up)
    {
        this.up = up;
    }
}
