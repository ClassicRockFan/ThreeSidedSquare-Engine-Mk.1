package com.threeSidedSquareStudios.engine.core;


import com.threeSidedSquareStudios.engine.core.administrative.ConsoleWindow;
import com.threeSidedSquareStudios.engine.core.administrative.Logging;
import com.threeSidedSquareStudios.engine.rendering.RenderingEngine;
import com.threeSidedSquareStudios.engine.rendering.Window;

public class CoreEngine {

    private boolean running = false;
    private Game game;
    private double frameTime;
    private int width;
    private int height;

    private RenderingEngine renderingEngine;

    public CoreEngine(int width, int heght, Game game, double frameCap) {
        this.game = game;
        this.frameTime = 1 / frameCap;
        this.renderingEngine = new RenderingEngine(this, false);
        this.width = width;
        this.height = heght;
    }

    public void createWindowInitGraphics(String title) {
        Window.createWindow(width, height, title);
        renderingEngine.finalizeSetup();
    }

    public boolean start() {
        if (running)
            return false;
        this.running = true;

        run();

        return true;
    }

    public boolean stop() {
        if (!running)
            return false;

        this.running = false;

        return true;
    }

    public void run() {
        ConsoleWindow.instantiate();

        Logging.printLog("Starting the CoreEngine");

        Logging.printLog("Creating Game Window");


        int frames = 0;
        double frameCounter = 0;

        game.init(this);

        double lastTime = Time.getTime();
        double unprocessedTime = 0;

        while (running) {
            boolean render = false;

            double startTime = Time.getTime();
            double passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime;
            frameCounter += passedTime;

            while (unprocessedTime > frameTime) {
                render = true;

                unprocessedTime -= frameTime;

                if (Window.isCloseRequested()) {
                    stop();
                }

                float deltaTime = (float) frameTime;

                game.input(deltaTime);
                game.update(deltaTime);

                Input.update();

                if (frameCounter >= 1.0) {
                    double totalTime = (1000.0 * frameCounter) / (double) frames;

                    ConsoleWindow.addConsoleText("");
                    System.out.println("");
                    Logging.printLog("Running at " + frames + " FPS");
                    Logging.printLog("Total Time: " + (totalTime) + " ms");

                    frames = 0;
                    frameCounter = 0;
                }
            }

            if (render) {
//                Window.bindAsRenderTarget();
//                RenderUtil.clearScreen();
//                game.render();
                game.render();
                Window.render();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        cleanUp();
    }


    private void cleanUp() {
        Logging.printLog("In Cleanup");
        Window.dispose();
        System.gc();
        System.exit(0);
    }

    public boolean isRunning() {
        return running;
    }

    public Game getGame() {
        return game;
    }

    public double getFrameTime() {
        return frameTime;
    }

    public RenderingEngine getRenderingEngine() {
        return renderingEngine;
    }
}
