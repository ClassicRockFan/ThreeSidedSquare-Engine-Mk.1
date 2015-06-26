package com.threeSidedSquareStudios.engine.rendering;

import com.threeSidedSquareStudios.engine.core.Util;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.meshLoading.model.IndexedModel;
import com.threeSidedSquareStudios.engine.rendering.meshLoading.model.OBJModel;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Mesh {

    private int vbo;
    private int ibo;
    private int size;

    public Mesh(String path) {
        initMeshData();
        loadMesh(path);
    }

    public Mesh(Vertex[] vertices, int[] indices) {
        this(vertices, indices, false);
    }

    public Mesh(Vertex[] vertices, int[] indices, boolean calcNormals) {
        initMeshData();
        addVertices(vertices, indices, calcNormals);
    }

    private void initMeshData() {
        this.vbo = glGenBuffers();
        this.ibo = glGenBuffers();
        this.size = 0;
    }

    private void addVertices(Vertex[] vertices, int[] indices) {
        addVertices(vertices, indices, false);
    }

    private void addVertices(Vertex[] vertices, int[] indices, boolean calcNormals) {
        if (calcNormals)
            calcNormals(vertices, indices);

        size = indices.length;

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
    }


    public void draw() {
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 12);
        glVertexAttribPointer(2, 3, GL_FLOAT, false, Vertex.SIZE * 4, 20);


        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
    }

    private void calcNormals(Vertex[] vertices, int[] indices) {
        for (int i = 0; i < indices.length; i += 3) {
            int i0 = indices[i];
            int i1 = indices[i + 1];
            int i2 = indices[i + 2];

            Vector3f v1 = vertices[i1].getPosition().sub(vertices[i0].getPosition());
            Vector3f v2 = vertices[i2].getPosition().sub(vertices[i0].getPosition());

            Vector3f normal = v1.cross(v2).normalized();

            vertices[i0].setNormal(vertices[i0].getNormal().add(normal));
            vertices[i1].setNormal(vertices[i1].getNormal().add(normal));
            vertices[i2].setNormal(vertices[i2].getNormal().add(normal));
        }

        for (int i = 0; i < vertices.length; i++)
            vertices[i].setNormal(vertices[i].getNormal().normalized());
    }

    private void loadMesh(String fileName) {
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length - 1];


        if (!ext.equals("obj")) {
            System.err.println("Error: File format not supported for mesh data: " + ext);
            new Exception().printStackTrace();
            System.exit(1);
        }

        OBJModel test = new OBJModel("./res/models/" + fileName);

        IndexedModel model = test.toIndexedModel();
        model.calcNormals();

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();

        for (int i = 0; i < model.getPositions().size(); i++) {
            vertices.add(new Vertex(model.getPositions().get(i),
                    model.getTextCoords().get(i),
                    model.getNormals().get(i)));
        }
        Vertex[] vertexData = new Vertex[vertices.size()];
        vertices.toArray(vertexData);

        Integer[] indexData = new Integer[model.getIndices().size()];
        model.getIndices().toArray(indexData);

        addVertices(vertexData, Util.toIntArray(indexData), false);

    }
}
