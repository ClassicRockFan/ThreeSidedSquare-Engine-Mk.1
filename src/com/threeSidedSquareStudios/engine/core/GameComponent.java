package com.threeSidedSquareStudios.engine.core;


public abstract class GameComponent {

    private GameObject parent;

    public void input(float delta){

    }

    public void update(float delta){

    }

    public void render(CoreEngine engine){

    }

    public GameObject getParent() {
        return parent;
    }

    public Transform getTransform(){
        return getParent().getTransform();
    }
}
