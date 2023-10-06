package v1;

import java.util.Scanner;

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

    private void createRooms() {
        Room vOutside = new Room("outside the main entrance of the iniveersity");
        Room vTheatre = new Room("in a lecture theatre");
        Room vPub = new Room("in the campus pub");
        Room vLab = new Room("in a computing lab");
        Room vOffice = new Room("in the computing admin office");

        vOutside.setExits(null, vLab, vPub, vTheatre);  
        vTheatre.setExits(null , null ,vOutside, null);
        vPub.setExits(null, null, null, vOutside);
        vLab.setExits(vOutside, null, null, vOffice);
        vOffice.setExits(null, null, vLab, null);

        this.aCurrentRoom = vOutside;

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

        if(vDirection.equals("N")) {
            vNextRoom = this.aCurrentRoom.aNorthExit;

        } else if( vDirection.equals("S")) {
            vNextRoom = this.aCurrentRoom.aSouthExit;

        } else if( vDirection.equals("W")) {
            vNextRoom = this.aCurrentRoom.aWestExit;

        } else if( vDirection.equals("E")) {
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
            System.out.println(this.aCurrentRoom.getDescription());
            System.out.print("Exits: ");
            
            if(this.aCurrentRoom.aNorthExit != null) {
                System.out.print("North ");
            }
            
            if(this.aCurrentRoom.aSouthExit != null) {
                System.out.print("South "); 
            }
            
            if(this.aCurrentRoom.aWestExit != null) {
                System.out.print("West ");
            }
            
            if(this.aCurrentRoom.aEastExit != null) {
                System.out.print("East ");
            }
            
        }
    } // goRoom()

    private void printWelcome() {
        System.out.println("Welcome to the World of Zuul!\nWorld of Zuul is a new, incredibly boring adventure game. \nType 'help' if you need help. \nYou are outside the main entrance of the university Exits: east south west");
        
    } //printWelcome()
    
    private void printHelp() {
        System.out.println("You are lost. You are alone.\nYou wander around at the university. \nYour command words are: go quit help");
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
    
} // Game
