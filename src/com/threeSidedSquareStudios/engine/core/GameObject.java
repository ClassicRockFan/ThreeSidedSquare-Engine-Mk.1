package com.threeSidedSquareStudios.engine.core;


import java.util.ArrayList;

public class GameObject {

    private ArrayList<GameObject> children;
    private ArrayList<GameComponent> components;
    private Transform transform;

    public GameObject() {
        this.children = new ArrayList<>();
        this.components = new ArrayList<>();
        this.transform = new Transform();
    }

    public GameObject addChild(GameObject object){
        children.add(object);
        object.getTransform().setParent(getTransform());
        return this;
    }

    public GameObject addComponent(GameComponent component){
        components.add(component);
        return this;
    }

    public void input(float delta){
        for(GameObject child : children)
            child.input(delta);
        for(GameComponent component : components)
            component.input(delta);
    }

    public void update(float delta){
        for(GameObject child : children)
            child.update(delta);
        for(GameComponent component : components)
            component.update(delta);
    }

    public void render(CoreEngine engine){
        for(GameObject child : children)
            child.render(engine);
        for(GameComponent component : components)
            component.render(engine);
    }

    public ArrayList<GameObject> getChildren() {
        return children;
    }

    public ArrayList<GameComponent> getComponents() {
        return components;
    }

    public Transform getTransform() {
        return transform;
    }
}
