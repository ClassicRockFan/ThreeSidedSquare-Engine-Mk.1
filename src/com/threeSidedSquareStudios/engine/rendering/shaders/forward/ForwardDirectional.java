package com.threeSidedSquareStudios.engine.rendering.shaders.forward;

import com.threeSidedSquareStudios.engine.core.Transform;
import com.threeSidedSquareStudios.engine.core.math.Matrix4f;
import com.threeSidedSquareStudios.engine.object.components.rendering.light.BaseLight;
import com.threeSidedSquareStudios.engine.object.components.rendering.light.DirectionalLight;
import com.threeSidedSquareStudios.engine.rendering.Material;
import com.threeSidedSquareStudios.engine.rendering.shaders.Shader;

public class ForwardDirectional extends Shader{

    private static final ForwardDirectional instance = new ForwardDirectional();

    public static ForwardDirectional getInstance() {
        return instance;
    }

    private ForwardDirectional() {
        super();

        addFragmentShaderFromFile("forward-directional.fs");
        addVertexShaderFromFile("forward-directional.vs");

        setAttribLocation("position", 0);
        setAttribLocation("texCoord", 1);
        setAttribLocation("normal", 2);

        compileShader();

        addUniform("transform");
        addUniform("transformProjected");

        addUniform("specularIntensity");
        addUniform("specularPower");
        addUniform("eyePos");

        addUniform("directionalLight.base.color");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
    }

    @Override
    public void updateUniforms(Transform transform, Material material) {
        Matrix4f worldMatrix = transform.getTransformation();
        Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getProjectionMatrix().mul(worldMatrix);

        super.updateUniforms(transform, material);
        material.getTexture().bind();

        setUniform("transformProjected", projectedMatrix);
        setUniform("transform", worldMatrix);

        setUniformDirectionalLight("directionalLight", (DirectionalLight) getRenderingEngine().getActiveLight());
        setUniformf("specularPower", material.getSpecularExponent());
        setUniformf("specularIntensity", material.getSpecularIntesity());
        setUniform("eyePos", getRenderingEngine().getMainCamera().getPos());
    }

}
