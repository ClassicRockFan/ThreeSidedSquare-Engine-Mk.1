package com.threeSidedSquareStudios.engine.rendering;

import com.threeSidedSquareStudios.engine.core.Util;
import com.threeSidedSquareStudios.engine.core.administrative.Logging;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class ResourceLoader {

    public static Texture loadTexture(String fileName)
    {
        try
        {
            BufferedImage image = ImageIO.read(new File("./res/textures/" + fileName));
            int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());

            ByteBuffer buffer = Util.createByteBuffer(image.getHeight() * image.getWidth() * 4);
            boolean hasAlpha = image.getColorModel().hasAlpha();

            for(int y = 0; y < image.getHeight(); y++)
            {
                for(int x = 0; x < image.getWidth(); x++)
                {
                    int pixel = pixels[y * image.getWidth() + x];

                    buffer.put((byte)((pixel >> 16) & 0xFF));
                    buffer.put((byte)((pixel >> 8) & 0xFF));
                    buffer.put((byte)((pixel) & 0xFF));
                    if(hasAlpha)
                        buffer.put((byte)((pixel >> 24) & 0xFF));
                    else
                        buffer.put((byte)(0xFF));
                }
            }

            buffer.flip();

            Texture resource = new Texture();
            glBindTexture(GL_TEXTURE_2D, resource.getId());

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

            return resource;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    public static String loadShader(String fileName){
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader;

        try{
            shaderReader = new BufferedReader(new FileReader("./res/shaders/" + fileName));
            String line;
            while((line = shaderReader.readLine()) != null)
                shaderSource.append(line).append("\n");

            shaderReader.close();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }


        return shaderSource.toString();
    }

    public static Mesh loadMesh(String fileName){
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length - 1];

        if(!ext.equals("obj")){
            Logging.printError("Error: The file format " + ext + " is not supported for loading meshes");
            new Exception().printStackTrace();
            System.exit(-8);
        }

        ArrayList<Vertex> vertices = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();

        BufferedReader meshReader;

        try{
            meshReader = new BufferedReader(new FileReader("./res/models/" + fileName));
            String line;
            while((line = meshReader.readLine()) != null){
                String[] tokens = Util.removeEmptyStrings(line.split(" "));

                if(tokens.length == 0 || tokens[0].equals("#"))
                    continue;
                else if(tokens[0].equals("v"))
                    vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]), Float.valueOf(tokens[2]), Float.valueOf(tokens[3]))));
                else if(tokens[0].equals("f")) {
                    indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);

                    if(tokens.length > 4){
                        indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
                    }
                }

            }

            meshReader.close();

            Mesh result = new Mesh();

            Vertex[] vertexData = new Vertex[vertices.size()];
            vertices.toArray(vertexData);

            Integer[] indexData = new Integer[indices.size()];
            indices.toArray(indexData);

            result.addVertices(vertexData, Util.toIntArray(indexData));

            return result;
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

}
