package com.threeSidedSquareStudios.engine.rendering;

import com.threeSidedSquareStudios.engine.core.math.Vector2f;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;

public class Vertex {

    public static final int SIZE = 5;

    private Vector3f position;
    private Vector2f textCoord;

    public Vertex(Vector3f position) {
        this(position, new Vector2f(0,0));
    }

    public Vertex(Vector3f position, Vector2f textCoord) {
        this.position = position;
        this.textCoord = textCoord;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector2f getTextCoord() {
        return textCoord;
    }

    public void setTextCoord(Vector2f textCoord) {
        this.textCoord = textCoord;
    }
}
