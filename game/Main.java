
import com.threeSidedSquareStudios.engine.core.Game;

public class Main {



    public static void main(String[] args){
        Game game = new Game();
        if(!game.stop()){
            game.start();
        }
    }

}
