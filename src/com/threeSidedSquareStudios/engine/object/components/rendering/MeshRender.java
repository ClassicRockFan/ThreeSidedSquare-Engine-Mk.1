package com.threeSidedSquareStudios.engine.object.components.rendering;

import com.threeSidedSquareStudios.engine.object.components.GameComponent;
import com.threeSidedSquareStudios.engine.object.components.rendering.light.BaseLight;
import com.threeSidedSquareStudios.engine.rendering.Material;
import com.threeSidedSquareStudios.engine.rendering.Mesh;
import com.threeSidedSquareStudios.engine.rendering.shaders.Shader;

public class MeshRender extends GameComponent{

    private Material material;
    private Mesh mesh;

    public MeshRender(Material material, Mesh mesh) {
        this.material = material;
        this.mesh = mesh;
    }

    @Override
    public void render(Shader shader) {
        super.render(shader);

        shader.bind();
        shader.updateUniforms(getTransform(), material);
        mesh.draw();
    }
}
