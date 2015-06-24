package com.threeSidedSquareStudios.engine.rendering.shaders.forward;


import com.threeSidedSquareStudios.engine.core.Transform;
import com.threeSidedSquareStudios.engine.core.math.Matrix4f;
import com.threeSidedSquareStudios.engine.object.components.rendering.light.BaseLight;
import com.threeSidedSquareStudios.engine.object.components.rendering.light.SpotLight;
import com.threeSidedSquareStudios.engine.rendering.Material;
import com.threeSidedSquareStudios.engine.rendering.shaders.Shader;

public class ForwardSpot extends Shader{

    private static final ForwardSpot instance = new ForwardSpot();

    public static ForwardSpot getInstance() {
        return instance;
    }

    private ForwardSpot() {
        super();

        addFragmentShaderFromFile("forward-spot.fs");
        addVertexShaderFromFile("forward-spot.vs");

        setAttribLocation("position", 0);
        setAttribLocation("texCoord", 1);
        setAttribLocation("normal", 2);

        compileShader();

        addUniform("transform");
        addUniform("transformProjected");

        addUniform("specularIntensity");
        addUniform("specularPower");
        addUniform("eyePos");

        addUniform("spotLight.pointLight.base.color");
        addUniform("spotLight.pointLight.base.intensity");
        addUniform("spotLight.pointLight.position");
        addUniform("spotLight.pointLight.attenuation.linear");
        addUniform("spotLight.pointLight.attenuation.constant");
        addUniform("spotLight.pointLight.attenuation.exponent");
        addUniform("spotLight.pointLight.range");
        addUniform("spotLight.direction");
        addUniform("spotLight.cutoff");
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
        setUniform("eyePos", getRenderingEngine().getMainCamera().getTransform().getPosition());

        setUniformSpotLight("spotLight", (SpotLight) getRenderingEngine().getActiveLight());
    }

}
