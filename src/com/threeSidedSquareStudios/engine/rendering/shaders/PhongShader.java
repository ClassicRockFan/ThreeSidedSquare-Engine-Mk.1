package com.threeSidedSquareStudios.engine.rendering.shaders;

import com.threeSidedSquareStudios.engine.core.Transform;
import com.threeSidedSquareStudios.engine.core.administrative.Logging;
import com.threeSidedSquareStudios.engine.core.math.Matrix4f;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.Material;
import com.threeSidedSquareStudios.engine.rendering.RenderUtil;
import com.threeSidedSquareStudios.engine.rendering.ResourceLoader;
import com.threeSidedSquareStudios.engine.rendering.light.DirectionalLight;
import com.threeSidedSquareStudios.engine.rendering.light.PointLight;

import java.util.ArrayList;

public class PhongShader extends Shader{

    private static final PhongShader instance = new PhongShader();

    private static final int MAX_POINT_LIGHTS = 4;

    private static Vector3f ambientLight = new Vector3f(0.1f, 0.1f, 0.1f);
    private static DirectionalLight directionalLight = new DirectionalLight(new Vector3f(1, 1, 1), 0.0f, new Vector3f(0, 0, 0));
    private static ArrayList<PointLight> pointLights = new ArrayList<>();

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

        for(int i = 0; i < MAX_POINT_LIGHTS; i++) {
            addUniform("pointLights[" + i + "].base.color");
            addUniform("pointLights[" + i + "].base.intensity");
            addUniform("pointLights[" + i + "].position");
            addUniform("pointLights[" + i + "].attenuation.linear");
            addUniform("pointLights[" + i + "].attenuation.constant");
            addUniform("pointLights[" + i + "].attenuation.exponent");
            addUniform("pointLights[" + i + "].range");
        }
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

        //if(pointLights[0] != null)
            for (int i = 0; i < pointLights.size(); i++){
                setUniformPointLight("pointLights[" + i + "]", pointLights.get(i));
            }
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

    public static void addPointLight(PointLight light){
        int location = pointLights.size();
        Logging.printLog("location = " + location);
        if(location == 0)
            location += 1;

        Logging.printLog("location = " + location);

        if(location < MAX_POINT_LIGHTS)
            pointLights.add(light);
        else{
            Logging.printError("You are already at the maximum number of point lights!  Trying to add the " + pointLights.size() + "th PointLight");
            //new Exception().printStackTrace();
        }
    }
}