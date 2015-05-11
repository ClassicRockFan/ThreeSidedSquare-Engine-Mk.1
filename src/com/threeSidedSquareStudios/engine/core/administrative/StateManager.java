package com.threeSidedSquareStudios.engine.core.administrative;

/**
 * Created by Tyler on 3/19/2015.
 */
public class StateManager {

    public static final int STATE_INIT = 0;
    public static final int STATE_MENU = 1;
    public static final int STATE_RUNNING = 2;

    private int currentState;

    public StateManager(int currentStateIn) {
        currentState = currentStateIn;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }
}
