 
import java.util.Scanner;
//iosgoihbg
/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author Hautin Matthias
 */
public class Game
{
    private Room aCurrentRoom;
    private Parser aParser;
    
    /**
     * call the createRooms() procedure to create all the rooms and the exits of each room
     * call play() procedure to start the game
     * @method Game()
     */
    public Game() {
        createRooms();
        play();
    } //Game()

    /**
     * create all rooms for the game with calling class Room and create the all directions for each rooms
     * @method procedure createRooms()
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
        vMainPilot.setExits("down", vMainWing);
        
        //for vMainWing
        vMainWing.setExits("up", vMainPilot);
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
    
    /**
     * call printWelcome() method 
     * allows you to analyze the different commands entered by the player
     * at each loop then the getCommand() method of the parser is called to obtain the next command entered by the player
     * Then, the processCommand() method is called with the command obtained as a parameter. (example: "go", "quit", "help") and performs appropriate actions.
     * If the quit command is entered then the game ends and displays "Thank you for playing. Good bye."
     * @method play()
     * 
     */
    private void play() {
        printWelcome();//appel de la methode printWelcome()
        boolean vFinished = false; //création d'une variable boolean instantiée à false
        this.aParser = new Parser(); // Créer l'objet Parser pour gérer l'analyse des commandes entrées par le joueur
        while(vFinished == false) {
            //si le jeu n'est pas fini
            Command vTypeCommand = this.aParser.getCommand();// Appel de la méthode getCommand() sur l'objet Parser que je viens de créer
            vFinished = processCommand(vTypeCommand);//Appel de processCommand() avec le paramètre obtenue
            
            if(vFinished == true) {
                //si le jeu est fini
                System.out.println("Thank you for playing. Good bye.");
            }
        }
    } // play()
    
    /**
     * allows you to change room if this room exist c_a_d if the direction of the next room is not equal to null
     * @method goRoom()
     * @param pCommand type Command allows the player to retrieve the print command
     */
    public void goRoom(final Command pCommand) {

        if(! pCommand.hasSecondWord()) {//si le second mot n'existe pas 
            System.out.println("Go Where ?");
            return;
        }

        Room vNextRoom = null;//initialise une varible de type Room à nulle
        String vDirection = pCommand.getSecondWord();//initialise une variable de type String qui est le second mot de notre parametre
        vNextRoom = this.aCurrentRoom.getExit(vDirection);//donne à la variable vNextRoom la valeur de toutes les sorties qui existe pour la room actuelle
        
        if (vNextRoom == null) {//si la room suivante n'a pas de direction associée
            System.out.println("There is no door !");
            return;
        } else {//si on change de room on donne la valeur de aCurrentRoom a notre nouvelle room
            this.aCurrentRoom = vNextRoom;
            printLocationInfo();
        }
    } // goRoom()
    
    /**
     * printWelcome to print information for start game
     * call printLocationInfo() to get the description of the first aCurrentRooms now (vMainPilot)
     * @method printWelcome()
     */
    private void printWelcome() {
        System.out.print("Welcome to the world of Zuut!\nWorld of Zuul is an incredibly boring new adventure game. \nType < help > if you need help.");
        System.out.println("\nYou are in the pilot room of the ship you can exit to: South");
        System.out.println();
        printLocationInfo();
    } //printWelcome()
    
    /**
     * printHelp to print if you write "help"
     * @method printHelp()
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone.\nYou wander around at the ship. \nYour command words are: go quit help");
    } //printHelp()
    
    /**
     * quit return true if you write "quit"
     * @method quit()
     * @param pSecondMot type Command check secondWord and if there is a second word print "Quit what?"
     */
    private boolean quit(final Command pSecondMot) {
        if(pSecondMot.hasSecondWord() == true ) {//si l'utilisateur tap un second mot aprés "quit" (exemple: "quit program")
            System.out.println("Quit what ?");
            return false;
            
        } else {//sinon on quit 
            return true;
        }
    } //quit()
    
    /**
     * analyse entered command and determines what action must be taken based on this commad and manages the different actions
     * @method processCommand()
     * @param pAppelBonneMethod type Command represent the command entered by the player
     * 
     */
    private boolean processCommand(final Command pAppelBonneMethod) {
        if(pAppelBonneMethod.isUnknown() == true) { //si la Commande tapée est inconnu
            System.out.println("I don't know what you mean...");
            return false;
        } 
        else if(pAppelBonneMethod.getCommandWord().equals("quit")) { //si la commande tapée est "quit"
             return quit(pAppelBonneMethod);
        } 
        else if(pAppelBonneMethod.getCommandWord().equals("help")) { //si la commande tapée est "help"
            printHelp();
            return false;
        } 
        else if(pAppelBonneMethod.getCommandWord().equals("go")) { //si la commande tapée est "go"
            goRoom(pAppelBonneMethod);
            return false;
        } 
        else { //si print "Erreur du programmeur : commande non reconnue !"
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
