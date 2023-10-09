 
import java.util.Scanner;
//iosgoihbg
/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author votre nom
 */
public class Game
{
    private Room aCurrentRoom;
    private Parser aParser;
    
    public Game() {
        createRooms();
        play();
    } //Game()

        /**
     * create all rooms for the game with calling class Room and create the all directions for each rooms
     * @method procedure createRooms()
     * @params aihfhi
     * 
     */
    private void createRooms() {
        //Main :
        Room vMainPilot = new Room("in main pilot room");
        Room vMainWing = new Room("in main wing room");
        Room vMainEngine = new Room("in main engine room");
        
        // Left and Right room
        Room vRightWing = new Room("in right wing ");
        Room vLeftWing = new Room("in left wing");
        Room vRightEngine = new Room("in right engine");
        Room vLeftEngine = new Room("in left engine");
        
        // Wing Corridor
        Room vCorridorRightWing = new Room("in corridor right wing");
        Room vCorridorLeftWing = new Room("in corridor left wing");
        
        // Corridor Wing Main to Engine Main
        Room vCorridorMainWingMainEngine = new Room("in corridor between main wing and main engine");
        
        //Engine Corridor
        Room vCorridorRightEngine = new Room("in corridor right engine");
        Room vCorridorLeftEngine = new Room("in corridor left engine");
        
        //Hidden Corridor
        Room vCorridorHiddenRight = new Room("in hidden corridor right between right wing and right engine");
        Room vCorridorHiddenLeft = new Room("in hidden corridor left between left wing and left engine");
        
        
        //setExits : String pDirection, Room pNeighbor
        // for vMainPilot:
        vMainPilot.setExits("south", vMainWing);
        
        //for vMainWing
        vMainWing.setExits("north", vMainPilot);
        vMainWing.setExits("south", vCorridorMainWingMainEngine);
        vMainWing.setExits("west", vCorridorLeftWing);
        vMainWing.setExits("east", vCorridorRightWing);
        
        //for vMainEngine
        vMainEngine.setExits("north", vCorridorMainWingMainEngine);
        vMainEngine.setExits("west", vCorridorLeftEngine);
        vMainEngine.setExits("east", vCorridorRightEngine);
        
        //for vRightWing
        vRightWing.setExits("south", vCorridorHiddenRight);
        vRightWing.setExits("west", vCorridorRightWing);
        
        //for vLeftWing
        vLeftWing.setExits("south", vCorridorHiddenLeft);
        vLeftWing.setExits("east", vCorridorLeftWing);
        
        //for vRightEngine
        vRightEngine.setExits("north", vCorridorHiddenRight);
        vRightEngine.setExits("west", vCorridorRightEngine);
        
        //for vLeftEngine
        vLeftEngine.setExits("north", vCorridorHiddenLeft);
        vLeftEngine.setExits("east", vCorridorLeftEngine);
        
        //vCorridorRightWing
        vCorridorRightWing.setExits("west", vMainWing);
        vCorridorRightWing.setExits("east", vRightWing);
        
        //vCorridorLeftWing
        vCorridorLeftWing.setExits("west", vLeftWing);
        vCorridorLeftWing.setExits("east", vMainWing);

        //vCorridorMainWingMainEngine
        vCorridorMainWingMainEngine.setExits("north", vMainWing);
        vCorridorMainWingMainEngine.setExits("south", vMainEngine);
        
        //vCorridorRightEngine
        vCorridorRightEngine.setExits("west", vMainEngine);
        vCorridorRightEngine.setExits("east", vRightEngine);
        
        //vCorridorLeftEngine
        vCorridorLeftEngine.setExits("west", vLeftEngine);
        vCorridorLeftEngine.setExits("east", vMainEngine);
        
        //vCorridorHiddenRight
        vCorridorHiddenRight.setExits("north", vRightWing);
        vCorridorHiddenRight.setExits("south", vRightEngine);
        
        //vCorridorHiddenLeft
        vCorridorHiddenLeft.setExits("north", vLeftWing);
        vCorridorHiddenLeft.setExits("south", vLeftEngine);

        this.aCurrentRoom = vMainPilot;

    } // createRooms()
    
    private void play() {
        printWelcome();
        boolean vFinished = false;
        this.aParser = new Parser(); // Créer l'objet Parser
        while(vFinished == false) {
            Command vTypeCommand = this.aParser.getCommand();
            vFinished = processCommand(vTypeCommand);
            
            if(vFinished == true) {
                System.out.println("Thank you for playing. Good bye.");
            }
        }
        
    } // play()

    public void goRoom(final Command pCommand) {

        if(! pCommand.hasSecondWord()) {
            System.out.println("Go Where ?");
            return;
        } 

        Room vNextRoom = null;
        String vDirection = pCommand.getSecondWord();
        
        vNextRoom = this.aCurrentRoom.getExit(vDirection);
        
        if (vNextRoom == null) {
            System.out.println("There is no door !");
            return;
        } else {
            this.aCurrentRoom = vNextRoom;
            printLocationInfo();
        }
    } // goRoom()
    
    /**
     * printWelcome to creating a new game
     * @method printWelcome()
     */
    private void printWelcome() {
        System.out.print("Welcome to the world of Zuut!\nWorld of Zuul is an incredibly boring new adventure game. \nType < help > if you need help.");
        System.out.println("\nYou are in the pilot room of the ship you can exit to: South");
        System.out.println();
        printLocationInfo();
    } //printWelcome()
    
    private void printHelp() {
        System.out.println("You are lost. You are alone.\nYou wander around at the ship. \nYour command words are: go quit help");
    } //printHelp()
    
    private boolean quit(final Command pSecondMot) {
        if(pSecondMot.hasSecondWord() == true ) {
            System.out.println("Quit what ?");
            return false;
            
        } else {
            return true;
        }
    } //quit()
    
    private boolean processCommand(final Command pAppelBonneMethod) {
        if(pAppelBonneMethod.isUnknown() == true) {
            System.out.println("I don't know what you mean...");
            return false;
        } 
        else if(pAppelBonneMethod.getCommandWord().equals("quit")) {
             return quit(pAppelBonneMethod);
        } 
        else if(pAppelBonneMethod.getCommandWord().equals("help")) {
            printHelp();
            return false;
        } 
        else if(pAppelBonneMethod.getCommandWord().equals("go")) {
            goRoom(pAppelBonneMethod);
            return false;
        } 
        else {
           System.out.println("Erreur du programmeur : commande non reconnue !");
           return false;
        }   
    } //processCommand()
    
    /**
     * give: the current room in which the user is located, the room description, its different exits
     * it was created to aviod code duplication between printWelcome() and goRoom()
     * @method procedure printLactionInfo()
     * 
     */
    private void printLocationInfo() {
        System.out.println("You are " + this.aCurrentRoom.getDescription());
        System.out.println(this.aCurrentRoom.getExitString());
        System.out.println("\n");
    } //printLocationInfo()k
    
} // Game
