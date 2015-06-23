package com.threeSidedSquareStudios.engine.object.components;


import com.threeSidedSquareStudios.engine.core.Transform;
import com.threeSidedSquareStudios.engine.object.GameObject;
import com.threeSidedSquareStudios.engine.object.components.rendering.light.BaseLight;
import com.threeSidedSquareStudios.engine.rendering.shaders.Shader;

public abstract class GameComponent {

    private GameObject parent;

    public void input(float delta){

    }

    public void update(float delta){

    }

    public void render(Shader shader){

    }

    public GameObject getParent() {
        return parent;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }

    public Transform getTransform(){
        return getParent().getTransform();
    }
}
