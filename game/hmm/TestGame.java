package hmm;

import com.threeSidedSquareStudios.engine.core.CoreEngine;
import com.threeSidedSquareStudios.engine.core.Game;
import com.threeSidedSquareStudios.engine.core.Transform;
import com.threeSidedSquareStudios.engine.core.administrative.Logging;
import com.threeSidedSquareStudios.engine.core.math.Vector2f;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.object.GameObject;
import com.threeSidedSquareStudios.engine.object.components.rendering.MeshRender;
import com.threeSidedSquareStudios.engine.rendering.*;

public class TestGame extends Game{

//    private Mesh mesh;
//    private float temp = 0.0f;
    private Transform transform;
    //private Camera camera;
//    private Material material;
//
//    PointLight pLight1 = new PointLight(new BaseLight(new Vector3f(1,0.5f,0), 0.8f), new Attenuation(0,0,1), new Vector3f(-2,0,5f), 0.0f);
//    PointLight pLight2 = new PointLight(new BaseLight(new Vector3f(0,0.5f,1), 0.8f), new Attenuation(0,0,1), new Vector3f(2,0,7f), 0.0f);
//    SpotLight sLight1 = new SpotLight(new PointLight(new BaseLight(new Vector3f(1f,1f,1f), 0.001f), new Attenuation(0,0,0.01f), new Vector3f(-2,0,5f), 60f), new Vector3f(1,1,1), 0.7f);

    @Override
    public void init(CoreEngine engine) {
        super.init(engine);

        engine.createWindowInitGraphics(Main.TITLE);
        Logging.printLog("OpenGL version: " + engine.getRenderingEngine().getOpenGLVersion());

//        camera = new Camera(70f, 0.1f, 1000);
//        camera.setPos(new Vector3f(0, 1, 0));

        transform = new Transform();
        //Plane
        float fieldDepth = 10.0f;
        float fieldWidth = 10.0f;

        Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
                new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};

        int indices[] = { 0, 1, 2,
                2, 1, 3};

        //mesh = new Mesh(vertices, indices, true);

        GameObject test = new GameObject();
        test.getTransform().setPosition(new Vector3f(0, -1, 0));
        test.addComponent(new MeshRender(new Material("bricks.jpg", new Vector3f(1, 1, 1), 1, 8), new Mesh(vertices, indices, true)));

        GameObject cameraObject = new GameObject();



        addObject(cameraObject);
        addObject(test);
        //mesh = new Mesh("cube2.obj");
    }

    @Override
    public void input(float delta) {
        super.input(delta);
        //camera.input(delta);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void render() {
        super.render();
    }
}
