package hmm;

import com.threeSidedSquareStudios.engine.core.CoreEngine;

public class Main {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "ThreeSidedSquare-Engine-Mk.1";

    public static final double FRAME_CAP = 60.0;

    public static void main(String[] args){
        CoreEngine engine = new CoreEngine(new TestGame(), FRAME_CAP);
        engine.start();
    }

}
