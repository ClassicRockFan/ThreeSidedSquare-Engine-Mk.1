package com.threeSidedSquareStudios.engine.rendering;


import com.threeSidedSquareStudios.engine.core.CoreEngine;
import com.threeSidedSquareStudios.engine.object.GameObject;
import com.threeSidedSquareStudios.engine.rendering.shaders.BasicShader;
import com.threeSidedSquareStudios.engine.rendering.shaders.Shader;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

public class RenderingEngine {

    private CoreEngine engine;
    private Camera mainCamera;

    public RenderingEngine(CoreEngine engine) {
        this.engine = engine;
    }

    public void input(float delta){
        mainCamera.input(delta);
    }

    public void render(ArrayList<GameObject> objects){
        Window.bindAsRenderTarget();
        clearScreen();

        Shader shader = BasicShader.getInstance();
        shader.setRenderingEngine(this);

        for(GameObject object : objects)
            object.render(shader);
    }

    public void finalizeSetup() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);

        glEnable(GL_DEPTH_CLAMP);

        glEnable(GL_TEXTURE_2D);

        mainCamera = new Camera((float)Math.toRadians(70f), 0.1f, 1000f);
    }


    public CoreEngine getEngine() {
        return engine;
    }

    private void clearScreen()
    {
        //TODO: Stencil Buffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }


    private static void unbindTexture(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public static void setClearColor(float r, float g, float b, float a){
        glClearColor(r, g, b, a);
    }

    public static String getOpenGLVersion()
    {
        return glGetString(GL_VERSION);
    }

    public Camera getMainCamera() {
        return mainCamera;
    }

    public void setMainCamera(Camera mainCamera) {
        this.mainCamera = mainCamera;
    }
}
