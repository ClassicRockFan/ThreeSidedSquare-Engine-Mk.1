package com.threeSidedSquareStudios.engine.object.components.rendering;

import com.threeSidedSquareStudios.engine.object.components.GameComponent;
import com.threeSidedSquareStudios.engine.rendering.Material;
import com.threeSidedSquareStudios.engine.rendering.Mesh;
import com.threeSidedSquareStudios.engine.rendering.shaders.Shader;

public class MeshRender extends GameComponent {

    private Material material;
    private Mesh mesh;

    public MeshRender(Mesh mesh, Material material) {
        this.mesh = mesh;
        this.material = material;
    }

    @Override
    public void render(Shader shader) {
        super.render(shader);

        shader.bind();
        shader.updateUniforms(getTransform(), material);
        mesh.draw();
    }
}
