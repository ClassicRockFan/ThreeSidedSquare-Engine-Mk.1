package com.threeSidedSquareStudios.engine.rendering;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

    private int id;

    public Texture() {
        this.id = glGenTextures();
    }

    public void bind(){
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public int getId() {
        return id;
    }
}
