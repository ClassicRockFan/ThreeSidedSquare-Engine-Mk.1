package com.threeSidedSquareStudios.engine.rendering.shaders;

import com.threeSidedSquareStudios.engine.core.math.Matrix4f;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.Material;
import com.threeSidedSquareStudios.engine.rendering.RenderUtil;
import com.threeSidedSquareStudios.engine.rendering.ResourceLoader;

public class PhongShader extends Shader{

    private static final PhongShader instance = new PhongShader();

    private static Vector3f ambientLight = new Vector3f(0.1f, 0.1f, 0.1f);

    public static PhongShader getInstance() {
        return instance;
    }

    private PhongShader() {
        super();

        addFragmentShader(ResourceLoader.loadShader("phongShader.fs"));
        addVertexShader(ResourceLoader.loadShader("phongShader.vs"));
        compileShader();

        addUniform("transform");
        addUniform("baseColor");
        addUniform("ambientLight");
    }

    @Override
    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
        super.updateUniforms(worldMatrix, projectedMatrix, material);
        if(material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtil.unbindTexture();
        setUniform("transform", projectedMatrix);
        setUniform("baseColor", material.getColor());
        setUniform("ambientLight", ambientLight);
    }

    public static Vector3f getAmbientLight() {
        return ambientLight;
    }

    public static void setAmbientLight(Vector3f ambientLight) {
        PhongShader.ambientLight = ambientLight;
    }
}