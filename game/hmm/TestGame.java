package hmm;

import com.threeSidedSquareStudios.engine.core.CoreEngine;
import com.threeSidedSquareStudios.engine.core.Game;
import com.threeSidedSquareStudios.engine.core.Transform;
import com.threeSidedSquareStudios.engine.core.administrative.Logging;
import com.threeSidedSquareStudios.engine.core.math.Vector2f;
import com.threeSidedSquareStudios.engine.core.math.Vector3f;
import com.threeSidedSquareStudios.engine.rendering.*;
import com.threeSidedSquareStudios.engine.rendering.light.*;
import com.threeSidedSquareStudios.engine.rendering.shaders.PhongShader;
import com.threeSidedSquareStudios.engine.rendering.shaders.Shader;

public class TestGame extends Game{

    private Mesh mesh;
    private float temp = 0.0f;
    private Transform transform;
    private Camera camera;
    private Material material;
    private Shader shader;

    PointLight pLight1 = new PointLight(new BaseLight(new Vector3f(1,0.5f,0), 0.8f), new Attenuation(0,0,1), new Vector3f(-2,0,5f), 0.0f);
    PointLight pLight2 = new PointLight(new BaseLight(new Vector3f(0,0.5f,1), 0.8f), new Attenuation(0,0,1), new Vector3f(2,0,7f), 0.0f);
    SpotLight sLight1 = new SpotLight(new PointLight(new BaseLight(new Vector3f(1f,1f,1f), 0.001f), new Attenuation(0,0,0.01f), new Vector3f(-2,0,5f), 60f), new Vector3f(1,1,1), 0.7f);

    @Override
    public void init(CoreEngine engine) {
        super.init(engine);

        engine.createWindowInitGraphics(Main.TITLE);

        Logging.printLog("OpenGL version: " + RenderUtil.getOpenGLVersion());

        shader = PhongShader.getInstance();

        camera = new Camera();
        //camera.setPos(new Vector3f(0, 1, 0));

        transform = new Transform();
        Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000f);
        Transform.setBullshit(camera);
        transform.setPosition(0, -1, 5);



        material = new Material("default.png", new Vector3f(1, 1, 1), 1, 8);


        //PhongShader.setDirectionalLight(new DirectionalLight(new Vector3f(1, 1, 1), 0.8f, new Vector3f(1, 1, 1)));
        PhongShader.addPointLight(new PointLight(new Vector3f(1, 0.5f, 0), 0.8f, new Attenuation(0f, 0f, 1f), new Vector3f(-2, 0.5f, 5f), 0.0f));
        PhongShader.addPointLight(new PointLight(new Vector3f(0, 0.5f, 1), 0.8f, new Attenuation(0f, 0f, 1f), new Vector3f(2, 0.5f, 7), 0.0f));

        PhongShader.addPointLight(pLight1);
        PhongShader.addPointLight(pLight2);

        //PhongShader.addSpotLight(sLight1);
        //PhongShader.setAmbientLight(new Vector3f(0.1f, 0.1f, 0.1f));

//        //Pyramid
//        Vertex[] vertices = new Vertex[] { new Vertex( new Vector3f( -1.0f, -1.0f, 0.5773f ), new Vector2f( 0.0f, 0.0f ) ),
//                new Vertex( new Vector3f( 0.0f, -1.0f, -1.15475f ), new Vector2f( 0.5f, 0.0f ) ),
//                new Vertex( new Vector3f( 1.0f, -1.0f, 0.5773f ),new Vector2f( 1.0f, 0 ) ),
//                new Vertex( new Vector3f( 0.0f, 1.0f, 0.0f ), new Vector2f( 0.5f, 1.0f ) ) };
//        int[] indices = new int[] { 0, 3, 1,
//                1, 3, 2,
//                2, 3, 0,
//                1, 2, 0 };

        //Plane
        float fieldDepth = 10.0f;
        float fieldWidth = 10.0f;

        Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
                new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};

        int indices[] = { 0, 1, 2,
                2, 1, 3};

        mesh = new Mesh(vertices, indices, true);

        //mesh = new Mesh("cube2.obj");
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

        pLight1.setPosition(new Vector3f(3, 0, 8.0f * (float) (Math.sin(temp) + 1.0 / 2.0) + 10));
        pLight2.setPosition(new Vector3f(7,0,8.0f * (float)(Math.cos(temp) + 1.0/2.0) + 10));

        //Logging.printLog("sinTemp = " + sinTemp);

        //transform.setPosition(sinTemp, 0, 5);
        //transform.setRotationVec(new Vector3f(0, sinTemp, 0));
        //transform.setRotation(new Quaternion(new Vector3f(0, 1, 0), sinTemp * 2));

        //transform.setRotation(new Quaternion(new Vector3f(0, 1, 0), Math.abs(Math.sin(temp))));
        //transform.setScale(new Vector3f(0.7f * (float) Math.sin(temp), (float) Math.sin(temp), (float) Math.sin(temp)));

        sLight1.getPointLight().setPosition(camera.getPos());
        sLight1.setDirection(camera.getForward());
    }

    @Override
    public void render() {
        super.render();
        shader.bind();
        shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
        mesh.draw();
    }
}
