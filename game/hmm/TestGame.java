package hmm;

import com.threeSidedSquareStudios.engine.core.Game;
import com.threeSidedSquareStudios.engine.core.Input;
import com.threeSidedSquareStudios.engine.core.Transform;
import com.threeSidedSquareStudios.engine.core.administrative.Logging;
import com.threeSidedSquareStudios.engine.core.math.Quaternion;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.*;
import org.lwjgl.input.Keyboard;

public class TestGame extends Game{

    private Mesh mesh;
    private Shader shader;
    private float temp = 0.0f;
    private Transform transform;

    @Override
    public void init() {
        super.init();
        Logging.printLog("OpenGL version: " + RenderUtil.getOpenGLVersion());
        mesh = new Mesh();
        shader = new Shader();

        transform = new Transform();

        Vertex[] data = new Vertex[]{new Vertex(new Vector3f(0, 1.7f, 0)),
                new Vertex(new Vector3f(1, 0, -1)),
                new Vertex(new Vector3f(1, 0, 1)),
                new Vertex(new Vector3f(-1, 0, 1)),
                new Vertex(new Vector3f(-1, 0, -1))};

        int[] indices = new int[]{0, 2, 1,
                0, 3, 2,
                0, 4, 3,
                0, 1, 4,
                1, 2, 3,
                1, 3, 4
        };

        mesh.addVertices(data, indices);

        shader.addVertexShader(ResourceLoader.loadShader("basicShader.vs"));
        shader.addFragmentShader(ResourceLoader.loadShader("basicShader.fs"));
        shader.compileShader();

        shader.addUniform("transform");
    }

    @Override
    public void input(float delta) {
        super.input(delta);

        if(Input.getKeyDown(Keyboard.KEY_UP))
            Logging.printLog("Up Key Pressed");
        if(Input.getKeyDown(Keyboard.KEY_DOWN))
            Logging.printLog("Down Key Pressed");
        if(Input.getKeyDown(Keyboard.KEY_LEFT))
            Logging.printLog("Left Key Pressed");
        if(Input.getKeyDown(Keyboard.KEY_RIGHT))
            Logging.printLog("Right Key Pressed");

        if(Input.getMouseDown(0))
            Logging.printLog("Mouse 1 pressed");
        if(Input.getMouseDown(1))
            Logging.printLog("Mouse 2 pressed");
        if(Input.getMouseDown(2))
            Logging.printLog("Mouse 3 pressed");
        if(Input.getMouseDown(3))
            Logging.printLog("Mouse 4 pressed");
        if(Input.getMouseDown(4))
            Logging.printLog("Mouse 5 pressed");

        if(Input.getKeyUp(Keyboard.KEY_UP))
            Logging.printLog("Up Key Released");
        if(Input.getKeyUp(Keyboard.KEY_DOWN))
            Logging.printLog("Up Key Released");
        if(Input.getKeyUp(Keyboard.KEY_LEFT))
            Logging.printLog("Left Key Released");
        if(Input.getKeyUp(Keyboard.KEY_RIGHT))
            Logging.printLog("Right Key Released");

        if(Input.getMouseUp(0))
            Logging.printLog("Mouse 1 Released");
        if(Input.getMouseUp(1))
            Logging.printLog("Mouse 2 Released");
        if(Input.getMouseUp(2))
            Logging.printLog("Mouse 3 Released");
        if(Input.getMouseUp(3))
            Logging.printLog("Mouse 4 Released");
        if(Input.getMouseUp(4))
            Logging.printLog("Mouse 5 Released");

    }

    @Override
    public void update(float delta) {
        super.update(delta);
        temp += delta;

        transform.setPosition((float) Math.sin(temp), 0, 0);
        transform.setRotation(new Quaternion(new Vector3f(0, 1, 0), Math.abs(Math.sin(temp) * 10)));
        //transform.setScale(new Vector3f((float) Math.tan(temp), (float) Math.tan(temp), (float) Math.tan(temp)));
    }

    @Override
    public void render() {
        super.render();
        shader.bind();
        shader.setUniform("transform", transform.getTransformation());
        mesh.draw();
    }
}
