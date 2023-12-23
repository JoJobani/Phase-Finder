//Yehonatan Menashe 206478067
import game.Game;
/**
 * The game launcher.
 */
public class Ass3Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
