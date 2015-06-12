package com.threeSidedSquareStudios.engine.rendering.meshLoading.index;

public class OBJIndex {
    public int vertexIndex;
    public int textCoordIndex;
    public int normalIndex;

    @Override
    public boolean equals(Object obj) {
        OBJIndex index = (OBJIndex) obj;

        return vertexIndex == index.vertexIndex &&
                textCoordIndex == index.textCoordIndex &&
                normalIndex == index.normalIndex;
    }

    @Override
    public int hashCode() {
        final int BASE = 17;
        final int MULTIPLIER = 31;

        int result = BASE;

        result = MULTIPLIER * result + vertexIndex;
        result = MULTIPLIER * result + textCoordIndex;
        result = MULTIPLIER * result + normalIndex;

        return result;
    }

    public void setVertexIndex(int index){this.vertexIndex = index;}

    public int getVertexIndex() {
        return vertexIndex;
    }

    public int getTextCoordIndex() {
        return textCoordIndex;
    }

    public void setTextCoordIndex(int textCoordIndex) {
        this.textCoordIndex = textCoordIndex;
    }

    public int getNormalIndex() {
        return normalIndex;
    }

    public void setNormalIndex(int normalIndex) {
        this.normalIndex = normalIndex;
    }
}
