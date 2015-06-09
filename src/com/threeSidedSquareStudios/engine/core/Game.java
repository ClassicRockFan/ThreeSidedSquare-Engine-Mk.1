package com.threeSidedSquareStudios.engine.core;

public abstract class Game {

    private CoreEngine engine;

    public Game() {
    }

    public void init(CoreEngine engine){
        this.engine = engine;
    }

    public void input(float delta){
    }

    public void update(float delta){
    }
    public void render(){
    }

    public CoreEngine getEngine() {
        return engine;
    }
}
