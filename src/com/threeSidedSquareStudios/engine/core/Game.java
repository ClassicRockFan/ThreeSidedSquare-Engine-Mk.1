package com.threeSidedSquareStudios.engine.core;

import com.threeSidedSquareStudios.engine.object.GameObject;
import com.threeSidedSquareStudios.engine.object.components.rendering.light.BaseLight;

import java.util.ArrayList;

public abstract class Game {

    private CoreEngine engine;
    private ArrayList<GameObject> loadedObjects;

    public Game() {
        loadedObjects = new ArrayList<>();
    }

    public void init(CoreEngine engine) {
        this.engine = engine;
    }

    public void input(float delta) {
        for (GameObject object : loadedObjects)
            object.input(delta);
    }

    public void update(float delta) {
        for (GameObject object : loadedObjects)
            object.update(delta);
    }

    public void render() {
        engine.getRenderingEngine().render(getLoadedObjects());
    }

    public GameObject addObject(GameObject object) {
        loadedObjects.add(object);
        return object;
    }

    public GameObject addLight(BaseLight base) {
        getEngine().getRenderingEngine().addLight(base);
        return addObject(new GameObject().addComponent(base));
    }

    public GameObject removeObject(GameObject object) {
        loadedObjects.remove(object);
        return object;
    }

    public CoreEngine getEngine() {
        return engine;
    }

    public ArrayList<GameObject> getLoadedObjects() {
        return loadedObjects;
    }
}
