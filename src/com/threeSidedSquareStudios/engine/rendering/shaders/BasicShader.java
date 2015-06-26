package com.threeSidedSquareStudios.engine.rendering.shaders;

public class BasicShader extends Shader {

//    private static final BasicShader instance = new BasicShader();
//
//    public static BasicShader getInstance() {
//        return instance;
//    }
//
//    private BasicShader() {
//        super();
//
//        addFragmentShaderFromFile("basicShader.fs");
//        addVertexShaderFromFile("basicShader.vs");
//        compileShader();
//
//        addUniform("transform");
//        addUniform("baseColor");
//    }
//
//    @Override
//    public void updateUniforms(Transform transform, Material material) {
//        Matrix4f worldMatrix = transform.getTransformation();
//        Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getProjectionMatrix().mul(worldMatrix);
//
//        super.updateUniforms(transform, material);
//        material.getTexture().bind();
//        setUniform("transform", projectedMatrix);
//        setUniform("baseColor", material.getColor());
//    }
}
