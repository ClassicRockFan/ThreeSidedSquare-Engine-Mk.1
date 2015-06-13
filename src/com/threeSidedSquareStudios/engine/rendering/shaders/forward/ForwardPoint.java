package com.threeSidedSquareStudios.engine.rendering.shaders.forward;


import com.threeSidedSquareStudios.engine.core.Transform;
import com.threeSidedSquareStudios.engine.core.math.Matrix4f;
import com.threeSidedSquareStudios.engine.rendering.Material;
import com.threeSidedSquareStudios.engine.rendering.shaders.Shader;

public class ForwardPoint extends Shader{

    private static final ForwardPoint instance = new ForwardPoint();

    public static ForwardPoint getInstance() {
        return instance;
    }

    private ForwardPoint() {
        super();

        addFragmentShaderFromFile("forward-point.fs");
        addVertexShaderFromFile("forward-point.vs");

        setAttribLocation("position", 0);
        setAttribLocation("texCoord", 1);
        setAttribLocation("normal", 2);

        compileShader();

        addUniform("transform");
        addUniform("transformProjected");

        addUniform("specularIntensity");
        addUniform("specularPower");
        addUniform("eyePos");

        addUniform("pointLight.base.color");
        addUniform("pointLight.base.intensity");
        addUniform("pointLight.position");
        addUniform("pointLight.attenuation.linear");
        addUniform("pointLight.attenuation.constant");
        addUniform("pointLight.attenuation.exponent");
        addUniform("pointLight.range");
    }

    @Override
    public void updateUniforms(Transform transform, Material material) {
        Matrix4f worldMatrix = transform.getTransformation();
        Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getProjectionMatrix().mul(worldMatrix);

        super.updateUniforms(transform, material);
        material.getTexture().bind();

        setUniform("transformProjected", projectedMatrix);
        setUniform("transform", worldMatrix);

        setUniformf("specularPower", material.getSpecularExponent());
        setUniformf("specularIntensity", material.getSpecularIntesity());
        setUniform("eyePos", getRenderingEngine().getMainCamera().getPos());

        setUniformPointLight("pointLight", getRenderingEngine().getPointLight());
    }

}
