package com.threeSidedSquareStudios.engine.rendering.shaders.forward;

import com.threeSidedSquareStudios.engine.core.Transform;
import com.threeSidedSquareStudios.engine.core.math.Matrix4f;
import com.threeSidedSquareStudios.engine.rendering.Material;
import com.threeSidedSquareStudios.engine.rendering.shaders.Shader;

public class ForwardAmbient extends Shader {

    private static final ForwardAmbient instance = new ForwardAmbient();

    private ForwardAmbient() {
        super();

        addFragmentShaderFromFile("forward-ambient.fs");
        addVertexShaderFromFile("forward-ambient.vs");

        setAttribLocation("position", 0);
        setAttribLocation("texCoord", 1);

        compileShader();

        addUniform("MvP");
        addUniform("ambientIntensity");
    }

    public static ForwardAmbient getInstance() {
        return instance;
    }

    @Override
    public void updateUniforms(Transform transform, Material material) {
        Matrix4f worldMatrix = transform.getTransformation();
        Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getProjectionMatrix().mul(worldMatrix);

        super.updateUniforms(transform, material);
        material.getTexture().bind();
        setUniform("MvP", projectedMatrix);
        setUniform("ambientIntensity", getRenderingEngine().getAmbientLight());
    }

}
