package com.threeSidedSquareStudios.engine.core;

public class Game {

    private boolean running = false;

    public boolean start(){
        if(running)
            return false;
        this.running = true;

        run();

        return true;
    }

    public boolean stop(){
        if(!running)
            return false;

        this.running = false;

        return true;
    }

    public void run(){
        int i = 0;
        while(running){
            System.out.println(i);
            i++;
            if(i > 1000)
                stop();
        }
    }
}
