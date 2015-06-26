package hmm;

import com.threeSidedSquareStudios.engine.core.CoreEngine;
import com.threeSidedSquareStudios.engine.core.Game;
import com.threeSidedSquareStudios.engine.core.administrative.Logging;
import com.threeSidedSquareStudios.engine.core.math.Quaternion;
import com.threeSidedSquareStudios.engine.core.math.Vector2f;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.object.GameObject;
import com.threeSidedSquareStudios.engine.object.components.input.FreeLook;
import com.threeSidedSquareStudios.engine.object.components.input.FreeMove;
import com.threeSidedSquareStudios.engine.object.components.rendering.Camera;
import com.threeSidedSquareStudios.engine.object.components.rendering.MeshRender;
import com.threeSidedSquareStudios.engine.object.components.rendering.light.Attenuation;
import com.threeSidedSquareStudios.engine.object.components.rendering.light.DirectionalLight;
import com.threeSidedSquareStudios.engine.object.components.rendering.light.PointLight;
import com.threeSidedSquareStudios.engine.object.components.rendering.light.SpotLight;
import com.threeSidedSquareStudios.engine.rendering.Material;
import com.threeSidedSquareStudios.engine.rendering.Mesh;
import com.threeSidedSquareStudios.engine.rendering.Vertex;


public class TestGame extends Game {

    private static final boolean LIGHT_FIELD = false;

    @Override
    public void init(CoreEngine engine) {
        super.init(engine);

        engine.createWindowInitGraphics(Main.TITLE);
        Logging.printLog("OpenGL version: " + engine.getRenderingEngine().getOpenGLVersion());


        //Plane
        float fieldDepth = 10.0f;
        float fieldWidth = 10.0f;

        Vertex[] vertices = new Vertex[]{new Vertex(new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
                new Vertex(new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
                new Vertex(new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
                new Vertex(new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};

        int indices[] = {0, 1, 2,
                2, 1, 3};

        Vertex[] vertices2 = new Vertex[]{new Vertex(new Vector3f(-fieldWidth / 10, 0.0f, -fieldDepth / 10), new Vector2f(0.0f, 0.0f)),
                new Vertex(new Vector3f(-fieldWidth / 10, 0.0f, fieldDepth / 10 * 3), new Vector2f(0.0f, 1.0f)),
                new Vertex(new Vector3f(fieldWidth / 10 * 3, 0.0f, -fieldDepth / 10), new Vector2f(1.0f, 0.0f)),
                new Vertex(new Vector3f(fieldWidth / 10 * 3, 0.0f, fieldDepth / 10 * 3), new Vector2f(1.0f, 1.0f))};

        int indices2[] = {0, 1, 2,
                2, 1, 3};

        Mesh mesh2 = new Mesh(vertices2, indices2, true);
        Mesh mesh = new Mesh(vertices, indices, true);

        Material material = new Material("default.png", new Vector3f(1, 1, 1), 1, 8);

        GameObject test = new GameObject();
        test.getTransform().setPosition(new Vector3f(0, -1, 0));
        test.addComponent(new MeshRender(mesh, material));
        addObject(test);

//        DirectionalLight testLight = new DirectionalLight(new Vector3f(1, 1, 1), 0.2f);
//        GameObject directionalLightObject = new GameObject();
//        directionalLightObject.addComponent(testLight);
//        //addLight(testLight);

        if (LIGHT_FIELD) {
            int lightFieldWidth = 5;
            int lightFieldDepth = 5;

            float lightFieldStartX = 0;
            float lightFieldStartY = 0;
            float lightFieldStepX = 7;
            float lightFieldStepY = 7;


            for (int i = 0; i < lightFieldWidth; i++) {
                for (int j = 0; j < lightFieldDepth; j++) {
                    PointLight light = new PointLight(new Vector3f(0, 1, 0), 0.4f, new Attenuation(0, 0, 1));

                    GameObject lightObject = addLight(light);
                    lightObject.getTransform().setPosition(new Vector3f(lightFieldStartX + lightFieldStepX * i, 0, lightFieldStartY + lightFieldStepY * j));
                }
            }
        }


//        GameObject huh = addLight(new DirectionalLight(new Vector3f(0, 0, 1), 0.4f));
//        huh.getTransform().setRotation(new Quaternion(new Vector3f(1, 1, 1), 1));

        GameObject directionalLight = addLight(new DirectionalLight(new Vector3f(0, 0, 1), 0.4f));
        directionalLight.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), Math.toRadians(-45)));


        GameObject eh = addLight(new SpotLight(new Vector3f(0, 1, 1), 0.4f, new Attenuation(0, 0, 0.1f), 0.7f));
        eh.getTransform().setPosition(5, 0, 5);
        eh.getTransform().setRotation(new Quaternion(new Vector3f(0, 1, 0), Math.toRadians(90)));


        GameObject testMesh1 = new GameObject().addComponent(new MeshRender(mesh2, material));
        GameObject testMesh2 = new GameObject().addComponent(new MeshRender(mesh2, material));

        testMesh1.getTransform().getPosition().set(0, 2, 0);
        testMesh1.getTransform().setRotation(new Quaternion(new Vector3f(0, 1, 0), 0.4f));

        testMesh2.getTransform().getPosition().set(0, 0, 5);

        testMesh1.addChild(testMesh2);


        Camera camera = new Camera(70f, 0.1f, 1000f);
        getEngine().getRenderingEngine().setMainCamera(camera);

        GameObject cameraObject = new GameObject().addComponent(camera).addComponent(new FreeLook(1f)).addComponent(new FreeMove(10f));
        testMesh1.addChild(cameraObject);
        //addObject(cameraObject);

        addObject(testMesh1);

//        testMesh2.getTransform().getTransformedPos().print("testMesh2's Position");
//        cameraObject.getTransform().getTransformedPos().print("cameraObject's position");
    }

    @Override
    public void input(float delta) {
        super.input(delta);
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
