package com.threeSidedSquareStudios.engine.rendering.shaders;

import com.threeSidedSquareStudios.engine.core.Util;
import com.threeSidedSquareStudios.engine.core.administrative.Logging;
import com.threeSidedSquareStudios.engine.core.math.Matrix4f;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.Material;
import com.threeSidedSquareStudios.engine.rendering.light.BaseLight;
import com.threeSidedSquareStudios.engine.rendering.light.DirectionalLight;
import com.threeSidedSquareStudios.engine.rendering.light.PointLight;

import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

public class Shader {

    private int program;
    private HashMap<String, Integer> uniforms;

    public Shader(){
        this.program = glCreateProgram();
        this.uniforms = new HashMap<String, Integer>();

        if(program == 0){
            Logging.printError("Shader creation failed: could not find valid memory location");
            new Exception().printStackTrace();
            System.exit(-2);
        }
    }

    public void addUniform(String uniformName){
        int uniformLocation = glGetUniformLocation(program, uniformName);

        if(uniformLocation == 0xFFFFFFFF){
            Logging.printError("Error: Couldnt find uniform: " + uniformName);
            new Exception().printStackTrace();
            System.exit(-7);
        }

        uniforms.put(uniformName, uniformLocation);
    }

    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material){

    }

    public void addVertexShader(String text){
        addProgram(text, GL_VERTEX_SHADER);
    }

    public void addGeometryShader(String text){
        addProgram(text, GL_GEOMETRY_SHADER);
    }

    public void addFragmentShader(String text){
        addProgram(text, GL_FRAGMENT_SHADER);
    }

    private void addProgram(String text, int type){
        int shader = glCreateShader(type);

        if(program == 0){
            Logging.printError("Shader creation failed: could not find valid memory location when adding shader");
            new Exception().printStackTrace();
            System.exit(-3);
        }

        glShaderSource(shader, text);
        glCompileShader(shader);

        if(glGetShader(shader, GL_COMPILE_STATUS) == 0){
            Logging.printError(glGetShaderInfoLog(shader, 1024));
            System.exit(-4);
        }

        glAttachShader(program, shader);
    }

    public void compileShader(){
        glLinkProgram(program);

        if(glGetProgram(program, GL_LINK_STATUS) == 0){
            Logging.printError(glGetShaderInfoLog(program, 1024));
            new Exception().printStackTrace();
            System.exit(-5);
        }

        glValidateProgram(program);

        if(glGetProgram(program, GL_VALIDATE_STATUS) == 0){
            Logging.printError(glGetShaderInfoLog(program, 1024));
            new Exception().printStackTrace();
            System.exit(-6);
        }
    }

    public void bind(){
        glUseProgram(program);
    }

    public void setUniformi(String uniformName, int value){
        glUniform1i(uniforms.get(uniformName), value);
    }

    public void setUniformf(String uniformName, float value){
        glUniform1f(uniforms.get(uniformName), value);
    }

    public void setUniform(String uniformName, Vector3f value){
        glUniform3f(uniforms.get(uniformName), value.getX(), value.getY(), value.getZ());
    }

    public void setUniform(String uniformName, Matrix4f value){
        glUniformMatrix4(uniforms.get(uniformName), true, Util.createFlippedBuffer(value));
    }

    public void setUniformBaseLight(String uniformName, BaseLight base){
        setUniform(uniformName + ".color", base.getColor());
        setUniformf(uniformName + ".intensity", base.getIntensity());
    }

    public void setUniformDirectionalLight(String uniformName, DirectionalLight light){
        setUniformBaseLight(uniformName + ".base", light.getBase());
        setUniform(uniformName + ".direction", light.getDirection());
    }

    public void setUniformPointLight(String uniformName, PointLight light){
        setUniformBaseLight(uniformName + ".base", light.getBase());
        setUniform(uniformName + ".position", light.getPosition());
        setUniformf(uniformName + ".range", light.getRange());
        setUniformf(uniformName + ".attenuation.constant", light.getAttenuation().getConstant());
        setUniformf(uniformName + ".attenuation.linear", light.getAttenuation().getLinear());
        setUniformf(uniformName + ".attenuation.exponent", light.getAttenuation().getExponent());
    }
}
