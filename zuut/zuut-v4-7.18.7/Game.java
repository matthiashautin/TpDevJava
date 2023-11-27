 
import java.util.Scanner;
//iosgoihbg
/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author Hautin Matthias
 */
public class Game
{
    private UserInterface aGui;
    private GameEngine aGameEngine;
    
    public Game(){
        this.aGameEngine = new GameEngine();
        this.aGui = new UserInterface(this.aGameEngine);
        this.aGameEngine.setGUI(this.aGui);
    }

} // Game
