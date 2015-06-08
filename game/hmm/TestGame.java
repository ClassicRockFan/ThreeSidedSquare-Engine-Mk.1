package hmm;

import com.threeSidedSquareStudios.engine.core.Game;
import com.threeSidedSquareStudios.engine.core.Transform;
import com.threeSidedSquareStudios.engine.core.administrative.Logging;
import com.threeSidedSquareStudios.engine.core.math.Quaternion;
import com.threeSidedSquareStudios.engine.core.math.Vector2f;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.*;
import com.threeSidedSquareStudios.engine.rendering.light.DirectionalLight;
import com.threeSidedSquareStudios.engine.rendering.shaders.PhongShader;
import com.threeSidedSquareStudios.engine.rendering.shaders.Shader;

public class TestGame extends Game{

    private Mesh mesh;
    private float temp = 0.0f;
    private Transform transform;
    private Camera camera;
    private Material material;
    private Shader shader;

    @Override
    public void init() {
        super.init();
        Logging.printLog("OpenGL version: " + RenderUtil.getOpenGLVersion());

        shader = PhongShader.getInstance();

        camera = new Camera();

        transform = new Transform();
        Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000f);
        Transform.setBullshit(camera);

        material = new Material("default.png", new Vector3f(1, 1, 1));


        PhongShader.setDirectionalLight(new DirectionalLight(new Vector3f(1, 1, 1), 0.8f, new Vector3f(1, 1, 1)));
        //PhongShader.setAmbientLight(new Vector3f(0.1f, 0.1f, 0.1f));

        Vertex[] vertices = new Vertex[] { new Vertex( new Vector3f( -1.0f, -1.0f, 0.5773f ), new Vector2f( 0.0f, 0.0f ) ),
                new Vertex( new Vector3f( 0.0f, -1.0f, -1.15475f ), new Vector2f( 0.5f, 0.0f ) ),
                new Vertex( new Vector3f( 1.0f, -1.0f, 0.5773f ),new Vector2f( 1.0f, 0 ) ),
                new Vertex( new Vector3f( 0.0f, 1.0f, 0.0f ), new Vector2f( 0.5f, 1.0f ) ) };
        int[] indices = new int[] { 0, 3, 1,
                1, 3, 2,
                2, 3, 0,
                1, 2, 0 };

        mesh = new Mesh();
        mesh.addVertices(vertices, indices, true);

        //mesh = ResourceLoader.loadMesh("cube2.obj");
        transform.setPosition(0, 0, 5);
//        shader.addUniform("sampler");
  //      shader.setUniformi("sampler", 0);
    }

    @Override
    public void input(float delta) {
        super.input(delta);

//        if(Input.getKeyDown(Keyboard.KEY_UP))
//            Logging.printLog("Up Key Pressed");
//        if(Input.getKeyDown(Keyboard.KEY_DOWN))
//            Logging.printLog("Down Key Pressed");
//        if(Input.getKeyDown(Keyboard.KEY_LEFT))
//            Logging.printLog("Left Key Pressed");
//        if(Input.getKeyDown(Keyboard.KEY_RIGHT))
//            Logging.printLog("Right Key Pressed");
//
//        if(Input.getMouseDown(0))
//            Logging.printLog("Mouse 1 pressed");
//        if(Input.getMouseDown(1))
//            Logging.printLog("Mouse 2 pressed");
//        if(Input.getMouseDown(2))
//            Logging.printLog("Mouse 3 pressed");
//        if(Input.getMouseDown(3))
//            Logging.printLog("Mouse 4 pressed");
//        if(Input.getMouseDown(4))
//            Logging.printLog("Mouse 5 pressed");
//
//        if(Input.getKeyUp(Keyboard.KEY_UP))
//            Logging.printLog("Up Key Released");
//        if(Input.getKeyUp(Keyboard.KEY_DOWN))
//            Logging.printLog("Up Key Released");
//        if(Input.getKeyUp(Keyboard.KEY_LEFT))
//            Logging.printLog("Left Key Released");
//        if(Input.getKeyUp(Keyboard.KEY_RIGHT))
//            Logging.printLog("Right Key Released");
//
//        if(Input.getMouseUp(0))
//            Logging.printLog("Mouse 1 Released");
//        if(Input.getMouseUp(1))
//            Logging.printLog("Mouse 2 Released");
//        if(Input.getMouseUp(2))
//            Logging.printLog("Mouse 3 Released");
//        if(Input.getMouseUp(3))
//            Logging.printLog("Mouse 4 Released");
//        if(Input.getMouseUp(4))
//            Logging.printLog("Mouse 5 Released");

        camera.input(delta);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        temp+= delta;
        float sinTemp = (float)Math.sin(temp);

        //Logging.printLog("sinTemp = " + sinTemp);

        //transform.setPosition(sinTemp, 0, 5);
        //transform.setRotationVec(new Vector3f(0, sinTemp, 0));
        transform.setRotation(new Quaternion(new Vector3f(0, 1, 0), sinTemp * 2));

        //transform.setRotation(new Quaternion(new Vector3f(0, 1, 0), Math.abs(Math.sin(temp))));
        //transform.setScale(new Vector3f(0.7f * (float) Math.sin(temp), (float) Math.sin(temp), (float) Math.sin(temp)));
    }

    @Override
    public void render() {
        super.render();
        shader.bind();
        shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
        mesh.draw();
    }
}
