package com.threeSidedSquareStudios.engine.rendering;


import com.threeSidedSquareStudios.engine.core.CoreEngine;
import com.threeSidedSquareStudios.engine.core.administrative.Logging;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.object.GameObject;
import com.threeSidedSquareStudios.engine.object.components.rendering.light.*;
import com.threeSidedSquareStudios.engine.rendering.shaders.Shader;
import com.threeSidedSquareStudios.engine.rendering.shaders.forward.ForwardAmbient;
import com.threeSidedSquareStudios.engine.rendering.shaders.forward.ForwardDirectional;
import com.threeSidedSquareStudios.engine.rendering.shaders.forward.ForwardPoint;
import com.threeSidedSquareStudios.engine.rendering.shaders.forward.ForwardSpot;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

public class RenderingEngine {

    private CoreEngine engine;
    private Camera mainCamera;
    private Vector3f ambientLight;
    private DirectionalLight directionalLight;
    private DirectionalLight directionalLight2;
    private PointLight pointLight;
    private PointLight[] pointLightList;
    private SpotLight spotLight;
    private BaseLight activeLight;

    private ArrayList<BaseLight> lights;

    public RenderingEngine(CoreEngine engine, boolean initGraphics) {
        this.engine = engine;
        if(initGraphics)
            finalizeSetup();
    }

    public void input(float delta){
        mainCamera.input(delta);
    }

    public void render(ArrayList<GameObject> objects){
        clearScreen();

        for(GameObject object : objects)
            object.render(ForwardAmbient.getInstance());

        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);
        glDepthMask(false);
        glDepthFunc(GL_EQUAL);

        for(BaseLight light : lights) {
            activeLight = light;
            for(GameObject object : objects)
                object.render(light.getShader());
        }

        glDepthFunc(GL_LESS);
        glDepthMask(true);
        glDisable(GL_BLEND);
    }

    public void finalizeSetup() {
        //OpenGL Stuff
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);

        glEnable(GL_DEPTH_CLAMP);

        glEnable(GL_TEXTURE_2D);

        //"Permanent" initialization
        this.mainCamera = new Camera((float)Math.toRadians(70f), 0.1f, 1000f);
        this.ambientLight = new Vector3f(0.1f, 0.1f, 0.1f);
        this.lights = new ArrayList<>();

        //Shader Stuff
        ForwardAmbient.getInstance().setRenderingEngine(this);
    }


    public CoreEngine getEngine() {
        return engine;
    }

    private void clearScreen() {
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

    public Vector3f getAmbientLight() {
        return ambientLight;
    }

    public void setAmbientLight(Vector3f ambientLight) {
        this.ambientLight = ambientLight;
    }

    public BaseLight addLight(BaseLight light){
        lights.add(light);
        light.getShader().setRenderingEngine(this);
        Logging.printLog("Added light number " +  lights.size() + " to the renderingEngine");
        return light;
    }

    public void removeLight(BaseLight light){
        lights.remove(light);
    }

    public ArrayList<BaseLight> getLights() {
        return lights;
    }

    public void setLights(ArrayList<BaseLight> lights) {
        this.lights = lights;
    }

    public BaseLight getActiveLight() {
        return activeLight;
    }
}


