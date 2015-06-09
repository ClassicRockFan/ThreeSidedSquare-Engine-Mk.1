package com.threeSidedSquareStudios.engine.rendering.shaders;

import com.threeSidedSquareStudios.engine.core.Transform;
import com.threeSidedSquareStudios.engine.core.administrative.Logging;
import com.threeSidedSquareStudios.engine.core.math.Matrix4f;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.Material;
import com.threeSidedSquareStudios.engine.rendering.RenderUtil;
import com.threeSidedSquareStudios.engine.rendering.light.DirectionalLight;
import com.threeSidedSquareStudios.engine.rendering.light.PointLight;
import com.threeSidedSquareStudios.engine.rendering.light.SpotLight;

import java.util.ArrayList;

public class PhongShader extends Shader{

    private static final PhongShader instance = new PhongShader();

    private static final int MAX_POINT_LIGHTS = 4;
    private static final int MAX_SPOT_LIGHTS = 4;

    private static Vector3f ambientLight = new Vector3f(0.1f, 0.1f, 0.1f);
    private static DirectionalLight directionalLight = new DirectionalLight(new Vector3f(1, 1, 1), 0.0f, new Vector3f(0, 0, 0));
    private static ArrayList<PointLight> pointLights = new ArrayList<>();
    private static ArrayList<SpotLight> spotLights = new ArrayList<>();


    public static PhongShader getInstance() {
        return instance;
    }

    private PhongShader() {
        super();

        addFragmentShaderFromFile("phongShader.fs");
        addVertexShaderFromFile("phongShader.vs");
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

        for(int i = 0; i < MAX_SPOT_LIGHTS; i++) {
            addUniform("spotLights[" + i + "].pointLight.base.color");
            addUniform("spotLights[" + i + "].pointLight.base.intensity");
            addUniform("spotLights[" + i + "].pointLight.position");
            addUniform("spotLights[" + i + "].pointLight.attenuation.linear");
            addUniform("spotLights[" + i + "].pointLight.attenuation.constant");
            addUniform("spotLights[" + i + "].pointLight.attenuation.exponent");
            addUniform("spotLights[" + i + "].pointLight.range");
            addUniform("spotLights[" + i + "].direction");
            addUniform("spotLights[" + i + "].cutoff");
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

        if(pointLights.size() > 0)
            for (int i = 0; i < pointLights.size(); i++)
                setUniformPointLight("pointLights[" + i + "]", pointLights.get(i));

        if(spotLights.size() > 0)
            for (int i = 0; i < spotLights.size(); i++)
                setUniformSpotLight("spotLights[" + i + "]", spotLights.get(i));

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

        if(location < MAX_POINT_LIGHTS)
            pointLights.add(light);
        else{
            Logging.printError("You are already at the maximum number of point lights!  The maximum allowed is " + MAX_POINT_LIGHTS + ".     ");
//            new Exception().printStackTrace();
//            System.exit(-10);
        }

        Logging.printLog("New size of pointLights = " + pointLights.size());
    }
    public static void addSpotLight(SpotLight light){
        int location = spotLights.size();

        if(location < MAX_SPOT_LIGHTS)
            spotLights.add(light);
        else{
            Logging.printError("You are already at the maximum number of spot lights!  The maximum allowed is " + MAX_SPOT_LIGHTS + ".     ");
//            new Exception().printStackTrace();
//            System.exit(-11);
        }

        Logging.printLog("New size of spotLights = " + spotLights.size());
    }
}