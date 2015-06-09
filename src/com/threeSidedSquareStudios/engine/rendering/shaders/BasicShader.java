package com.threeSidedSquareStudios.engine.rendering.shaders;

import com.threeSidedSquareStudios.engine.core.math.Matrix4f;
import com.threeSidedSquareStudios.engine.rendering.Material;
import com.threeSidedSquareStudios.engine.rendering.RenderUtil;

public class BasicShader extends Shader{

    private static final BasicShader instance = new BasicShader();

    public static BasicShader getInstance() {
        return instance;
    }

    private BasicShader() {
        super();

        addFragmentShaderFromFile("basicShader.fs");
        addVertexShaderFromFile("basicShader.vs");
        compileShader();

        addUniform("transform");
        addUniform("color");
    }

    @Override
    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
        super.updateUniforms(worldMatrix, projectedMatrix, material);
        if(material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtil.unbindTexture();
        setUniform("transform", projectedMatrix);
        setUniform("color", material.getColor());
    }
}
