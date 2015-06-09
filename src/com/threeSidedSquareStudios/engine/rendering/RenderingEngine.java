package com.threeSidedSquareStudios.engine.rendering;


import com.threeSidedSquareStudios.engine.core.CoreEngine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

public class RenderingEngine {

    private CoreEngine engine;

    public RenderingEngine(CoreEngine engine) {
        this.engine = engine;
    }

    public void finalizeSetup() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);

        glEnable(GL_DEPTH_CLAMP);
    }


    public CoreEngine getEngine() {
        return engine;
    }
}
