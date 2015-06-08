package com.threeSidedSquareStudios.engine.rendering.shaders;

import com.threeSidedSquareStudios.engine.core.Transform;
import com.threeSidedSquareStudios.engine.core.math.Matrix4f;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.Material;
import com.threeSidedSquareStudios.engine.rendering.RenderUtil;
import com.threeSidedSquareStudios.engine.rendering.ResourceLoader;
import com.threeSidedSquareStudios.engine.rendering.light.DirectionalLight;

public class PhongShader extends Shader{

    private static final PhongShader instance = new PhongShader();

    private static Vector3f ambientLight = new Vector3f(0.1f, 0.1f, 0.1f);
    private static DirectionalLight directionalLight = new DirectionalLight(new Vector3f(1, 1, 1), 0.0f, new Vector3f(0, 0, 0));

    public static PhongShader getInstance() {
        return instance;
    }

    private PhongShader() {
        super();

        addFragmentShader(ResourceLoader.loadShader("phongShader.fs"));
        addVertexShader(ResourceLoader.loadShader("phongShader.vs"));
        compileShader();

        addUniform("transform");
        addUniform("transformProjected");
        addUniform("eyePos");
        addUniform("baseColor");
        addUniform("ambientLight");
        addUniform("directionalLight.base.color");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
        addUniform("specularIntensity");
        addUniform("specularPower");
    }

    @Override
    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
        super.updateUniforms(worldMatrix, projectedMatrix, material);
        if(material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtil.unbindTexture();
        setUniform("transform", worldMatrix);
        setUniform("transformProjected", projectedMatrix);
        setUniform("baseColor", material.getColor());
        setUniform("ambientLight", ambientLight);
        setUniformDirectionalLight("directionalLight", directionalLight);
        setUniformf("specularPower", material.getSpecularExponent());
        setUniformf("specularIntensity", material.getSpecularIntesity());
        setUniform("eyePos", Transform.getBullshit().getPos());
    }

    public static Vector3f getAmbientLight() {
        return ambientLight;
    }

    public static void setAmbientLight(Vector3f ambientLight) {
        PhongShader.ambientLight = ambientLight;
    }

    public static DirectionalLight getDirectionalLight() {
        return directionalLight;
    }

    public static void setDirectionalLight(DirectionalLight directionalLight) {
        PhongShader.directionalLight = directionalLight;
    }
}