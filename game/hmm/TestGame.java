package hmm;

import com.threeSidedSquareStudios.engine.core.CoreEngine;
import com.threeSidedSquareStudios.engine.core.Game;
import com.threeSidedSquareStudios.engine.core.Transform;
import com.threeSidedSquareStudios.engine.core.administrative.Logging;
import com.threeSidedSquareStudios.engine.core.math.Quaternion;
import com.threeSidedSquareStudios.engine.core.math.Vector2f;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.object.GameObject;
import com.threeSidedSquareStudios.engine.object.components.rendering.Camera;
import com.threeSidedSquareStudios.engine.object.components.rendering.MeshRender;
import com.threeSidedSquareStudios.engine.object.components.rendering.light.*;
import com.threeSidedSquareStudios.engine.rendering.*;
import com.threeSidedSquareStudios.engine.object.components.rendering.light.Attenuation;


public class TestGame extends Game{

    @Override
    public void init(CoreEngine engine) {
        super.init(engine);

        engine.createWindowInitGraphics(Main.TITLE);
        Logging.printLog("OpenGL version: " + engine.getRenderingEngine().getOpenGLVersion());


        //Plane
        float fieldDepth = 10.0f;
        float fieldWidth = 10.0f;

        Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
                new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};

        int indices[] = { 0, 1, 2,
                2, 1, 3};

        GameObject test = new GameObject();
        test.getTransform().setPosition(new Vector3f(0, -1, 0));
        test.addComponent(new MeshRender(new Material("default.png", new Vector3f(1, 1, 1), 1, 8), new Mesh(vertices, indices, true)));

        GameObject cameraObject = new GameObject();

        DirectionalLight testLight = new DirectionalLight(new Vector3f(1, 1, 1), 0.2f);
        GameObject directionalLightObject = new GameObject();
        directionalLightObject.addComponent(testLight);

        int lightFieldWidth = 5;
        int lightFieldDepth = 5;

        float lightFieldStartX = 0;
        float lightFieldStartY = 0;
        float lightFieldStepX = 7;
        float lightFieldStepY = 7;


        for(int i = 0; i < lightFieldWidth; i++) {
            for(int j = 0; j < lightFieldDepth; j++) {
                PointLight light = new PointLight(new Vector3f(0,1,0), 0.4f,
                        new Attenuation(0,0,1));

                GameObject lightObject = new GameObject();
                lightObject.getTransform().setPosition(new Vector3f(lightFieldStartX + lightFieldStepX * i,0,lightFieldStartY + lightFieldStepY * j));
                lightObject.addComponent(light);


                engine.getRenderingEngine().addLight(light);
                addObject(lightObject);
            }
        }


        addObject(cameraObject);
        addObject(test);

        //addLight(testLight);

//        GameObject huh = addLight(new DirectionalLight(new Vector3f(0, 0, 1), 0.4f));
//        huh.getTransform().setRotation(new Quaternion(new Vector3f(1, 1, 1), 1));

        //addLight(new DirectionalLight(new Vector3f(1, 0, 0), 0.4f, new Vector3f(-1, 1, -1)));
        GameObject eh = addLight(new SpotLight(new Vector3f(0, 1, 1), 0.4f, new Attenuation(0, 0, 0.1f), 0.7f));
        eh.getTransform().setPosition(5, 0, 5);
        eh.getTransform().setRotation(new Quaternion(new Vector3f(0, 1, 0), Math.toRadians(-90)));

        Camera camera = new Camera(70f, 0.1f, 1000f);
        getEngine().getRenderingEngine().setMainCamera(camera);
        addObject(new GameObject().addComponent(camera));
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
