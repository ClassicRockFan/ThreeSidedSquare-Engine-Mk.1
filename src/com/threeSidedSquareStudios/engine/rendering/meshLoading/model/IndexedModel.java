package com.threeSidedSquareStudios.engine.rendering.meshLoading.model;


import com.threeSidedSquareStudios.engine.core.math.Vector2f;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;

import java.util.ArrayList;

public class IndexedModel {
    private ArrayList<Vector3f> positions;
    private ArrayList<Vector2f> textCoords;
    private ArrayList<Vector3f> normals;
    private ArrayList<Vector3f> tangents;
    private ArrayList<Integer> indices;

    public IndexedModel() {
        positions = new ArrayList<Vector3f>();
        textCoords = new ArrayList<Vector2f>();
        normals = new ArrayList<Vector3f>();
        tangents = new ArrayList<Vector3f>();
        indices = new ArrayList<Integer>();
    }

    public void calcNormals() {
        for (int i = 0; i < indices.size(); i += 3) {
            int i0 = indices.get(i);
            int i1 = indices.get(i + 1);
            int i2 = indices.get(i + 2);

            Vector3f v1 = positions.get(i1).sub(positions.get(i0));
            Vector3f v2 = positions.get(i2).sub(positions.get(i0));

            Vector3f normal = v1.cross(v2).normalized();

            normals.get(i0).set(normals.get(i0).add(normal));
            normals.get(i1).set(normals.get(i1).add(normal));
            normals.get(i2).set(normals.get(i2).add(normal));

        }
        for (int i = 0; i < normals.size(); i++)
            normals.get(i).set(normals.get(i).normalized());
    }

    public void calcTangents() {
        for (int i = 0; i < indices.size(); i += 3) {
            int i0 = indices.get(i);
            int i1 = indices.get(i + 1);
            int i2 = indices.get(i + 2);

            Vector3f edge1 = positions.get(i1).sub(positions.get(i0));
            Vector3f edge2 = positions.get(i2).sub(positions.get(i0));

            float deltaU1 = textCoords.get(i1).getX() - textCoords.get(i0).getX();
            float deltaU2 = textCoords.get(i2).getX() - textCoords.get(i0).getX();
            float deltaV1 = textCoords.get(i1).getY() - textCoords.get(i0).getY();
            float deltaV2 = textCoords.get(i2).getY() - textCoords.get(i0).getY();

            float dividend = (deltaU1 * deltaV2 - deltaU2 * deltaV1);
            float f = dividend == 0 ? 0.0f : 1.0f / dividend;

            Vector3f tangent = new Vector3f(0, 0, 0);
            tangent.setX(f * (edge1.getX() * deltaV2 - edge2.getX() * deltaV1));
            tangent.setY(f * (edge1.getY() * deltaV2 - edge2.getY() * deltaV1));
            tangent.setZ(f * (edge1.getZ() * deltaV2 - edge2.getZ() * deltaV1));

            tangents.get(i0).set(tangents.get(i0).add(tangent));
            tangents.get(i1).set(tangents.get(i1).add(tangent));
            tangents.get(i2).set(tangents.get(i2).add(tangent));
        }
        for (int i = 0; i < tangents.size(); i++)
            tangents.get(i).set(tangents.get(i).normalized());
    }

    public ArrayList<Vector3f> getPositions() {
        return positions;
    }

    public ArrayList<Vector2f> getTextCoords() {
        return textCoords;
    }

    public ArrayList<Vector3f> getNormals() {
        return normals;
    }

    public ArrayList<Vector3f> getTangents() {
        return tangents;
    }

    public ArrayList<Integer> getIndices() {
        return indices;
    }

}
