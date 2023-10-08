 
import java.util.Scanner;

/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author Hautin Matthias 
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
        
        
        //Direction : pNorthExit, pSouthExit, pWestExit, pEastExit
        vMainPilot.setExits(null, vMainWing, null, null);
        vMainWing.setExits(vMainPilot, vCorridorMainWingMainEngine, vCorridorLeftWing, vCorridorRightWing);
        vMainEngine.setExits(vCorridorMainWingMainEngine ,null, vCorridorLeftEngine, vCorridorRightEngine);
        
        vRightWing.setExits(null, vCorridorHiddenRight, vCorridorRightWing, null);
        vLeftWing.setExits(null, vCorridorHiddenLeft, null ,vCorridorLeftWing);
        vRightEngine.setExits(vCorridorHiddenRight, null, vCorridorRightEngine, null);
        vLeftEngine.setExits(vCorridorHiddenLeft, null, null, vCorridorLeftEngine);
        
        vCorridorRightWing.setExits(null, null, vMainWing, vRightWing);
        vCorridorLeftWing.setExits(null, null, vLeftWing, vMainWing);
        
        vCorridorMainWingMainEngine.setExits(vMainWing, vMainEngine, null, null);
        
        vCorridorRightEngine.setExits(null, null, vMainEngine, vRightEngine);
        vCorridorLeftEngine.setExits(null, null, vLeftEngine, vMainEngine);
        
        vCorridorHiddenRight.setExits(vRightWing, vRightEngine, null, null);
        vCorridorHiddenLeft.setExits(vLeftWing, vLeftEngine, null, null);

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

        if(vDirection.equals("north")) {
            vNextRoom = this.aCurrentRoom.aNorthExit;

        } else if( vDirection.equals("south")) {
            vNextRoom = this.aCurrentRoom.aSouthExit;

        } else if( vDirection.equals("west")) {
            vNextRoom = this.aCurrentRoom.aWestExit;

        } else if( vDirection.equals("east")) {
            vNextRoom = this.aCurrentRoom.aEastExit;

        } else {
            System.out.println("Unknown Direction !");
            return;
        }
        
        if (vNextRoom == null) {
            System.out.println("There is no door !");
            return;
        } else {
            this.aCurrentRoom = vNextRoom;
            printLocationInfo();
        }
    } // goRoom()

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
        System.out.print("Exits: ");
            
            if(this.aCurrentRoom.aNorthExit != null) {
                System.out.print("north ");
            }
            
            if(this.aCurrentRoom.aSouthExit != null) {
                System.out.print("south "); 
            }
            
            if(this.aCurrentRoom.aWestExit != null) {
                System.out.print("west ");
            }
            
            if(this.aCurrentRoom.aEastExit != null) {
                System.out.print("east ");
            }
            System.out.println();
    } //printLocationInfo()
    
} // Game
