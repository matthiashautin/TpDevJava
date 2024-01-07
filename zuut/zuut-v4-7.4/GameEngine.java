import java.util.Stack;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.List;

/**
 * Allows create all rooms and command management 
 *
 * @author Matthias Hautin
 * @version 08/12/2023
 */
public class GameEngine
{
    private Parser          aParser;
    private UserInterface   aGui;
    private Item            aItem;
    private Player          aPlayer;
    
    private int             aMaxMoves;
    private int             aCurrentMoves;
    
    //pour activer ou non l'audio
    private boolean aIsAudioEnabled;

    public GameEngine() {
        this.aParser = new Parser();
        this.createRoomsAndPlayers();
        this.aIsAudioEnabled = false; //l'audio est désactiver par défaut
        this.aMaxMoves = 3;
        this.aCurrentMoves = 0;
    }

    /**
     * Définit l'interface utilisateur du jeu. La méthode initialise l'interface utilisateur
     * et affiche le message de bienvenue.
     * @param pUserInterface L'interface utilisateur à définir.
     */
    public void setGUI(final UserInterface pUserInterface ) {
        this.aGui = pUserInterface;
        this.printWelcome();
    }

    /**
     * create all rooms for the game with calling class Room and create the all directions for each rooms
     */
    private void createRoomsAndPlayers() {
        Room vMainPilot, vMainWing, vMainEngine, vRightWing, vLeftWing, vRightEngine, vLeftEngine, vCorridorRightWing, vCorridorLeftWing, vCorridorMainWingMainEngine, vCorridorRightEngine, vCorridorLeftEngine, vCorridorHiddenRight, vCorridorHiddenLeft;
        String vLienImages = "./Images/";
        String vLienAudios = "./Audios/";

        //Main :
        vMainPilot = new Room("in main pilot room", vLienImages + "mainpilot.jpg" , vLienAudios + "mainpilot.wav");
        Item vTW = new Item("Talkie-Walkie", 2, "Le talkie Walkie vous permetra de toujours être connecté avec Lara pour vous indiquer les étapes du jeu. Ces donc un outil important même obligatoire dans le jeu.");
        Item vTest = new Item("Test", 15, "un item en plus"); 
        Item vMasse = new Item("Masse" , 80, "Une masse Lourd");
        Item vCookieMagic = new Item("Cookie" , 1, "Un gros  cookie Magic de 1kils batard, qui te met bien ");
        vMainPilot.setItems(vTest);
        vMainPilot.setItems(vTW);
        vMainPilot.setItems(vMasse);
        vMainPilot.setItems(vCookieMagic);

        vMainWing = new Room("in main wing room", vLienImages + "mainwing.jpg" , vLienAudios + "mainwing.wav");
        vMainEngine = new Room("in main engine room", vLienImages + "mainengine.jpg" ,vLienAudios + "mainengine.wav");

        // Left and Right room
        vRightWing = new Room("in right wing ", vLienImages + "rightroomwing.jpg" , vLienAudios + "rightroomwing.wav");
        vLeftWing = new Room("in left wing", vLienImages + "leftroomwing.png" , vLienAudios + "leftroomwing.wav");
        vRightEngine = new Room("in right engine", vLienImages + "rightroomengine.jpg" , vLienAudios + "rightroomengine.wav");
        vLeftEngine = new Room("in left engine", vLienImages + "leftroomengine.jpg" , vLienAudios + "leftroomengine.wav");

        // Wing Corridor
        vCorridorRightWing = new Room("in corridor right wing", vLienImages + "corridorrightwing.jpg" , vLienAudios + "corridorrightwing.wav");
        vCorridorLeftWing = new Room("in corridor left wing", vLienImages + "corridorleftwing.jpg" , vLienAudios + "corridorleftwing.wav");

        // Corridor Wing Main to Engine Main
        vCorridorMainWingMainEngine = new Room("in corridor between main wing and main engine", vLienImages + "corridormainwingmainengine.jpg" , vLienAudios + "corridormainwingmainengine.wav");

        //Engine Corridor
        vCorridorRightEngine = new Room("in corridor right engine", vLienImages + "corridorengineright.png" , vLienAudios + "corridorengineright.wav");
        vCorridorLeftEngine = new Room("in corridor left engine", vLienImages + "corridorengineleft.png" , vLienAudios + "corridorengineleft.wav");

        //Hidden Corridor
        vCorridorHiddenRight = new Room("in hidden corridor right between right wing and right engine", vLienImages + "corridorhiddenright.png" , vLienAudios + "corridorhiddenright.wav");
        vCorridorHiddenLeft = new Room("in hidden corridor left between left wing and left engine", vLienImages + "corridorhiddenleft.png" , vLienAudios + "corridorhiddenleft.wav");

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
        vMainEngine.setExits("downwest", vCorridorLeftEngine);
        vMainEngine.setExits("downeast", vCorridorRightEngine);

        //for vRightWing
        vRightWing.setExits("south", vCorridorHiddenRight);
        vRightWing.setExits("west", vCorridorRightWing);

        //for vLeftWing
        vLeftWing.setExits("south", vCorridorHiddenLeft);
        vLeftWing.setExits("east", vCorridorLeftWing);

        //for vRightEngine
        vRightEngine.setExits("upnorth", vCorridorHiddenRight);
        vRightEngine.setExits("west", vCorridorRightEngine);

        //for vLeftEngine
        vLeftEngine.setExits("upnorth", vCorridorHiddenLeft);
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
        vCorridorRightEngine.setExits("upwest", vMainEngine);
        vCorridorRightEngine.setExits("east", vRightEngine);

        //vCorridorLeftEngine
        vCorridorLeftEngine.setExits("west", vLeftEngine);
        vCorridorLeftEngine.setExits("upeast", vMainEngine);

        //dans le futur on ne les verra pas 
        //vCorridorHiddenRight
        vCorridorHiddenRight.setExits("north", vRightWing);
        vCorridorHiddenRight.setExits("downsouth", vRightEngine);

        //vCorridorHiddenLeft
        vCorridorHiddenLeft.setExits("north", vLeftWing);
        vCorridorHiddenLeft.setExits("downsouth", vLeftEngine);

        this.aPlayer = new Player("Mike", vMainPilot, 20, 100);//creation du premier joueur

        this.aPlayer.setCurrentRoom(vMainPilot); // start 
    } // createRooms()

    /**
     * allows you to change room if this room exist c_a_d if the direction of the next room is not equal to null
     * @param pCommand type Command allows the player to retrieve the print command
     */
    public void goRoom(final Command pCommand) {

        if(! pCommand.hasSecondWord()) {//si le second mot n'existe pas 
            this.aGui.println("Go Where ?");
            return;
        }

        String vDirection = pCommand.getSecondWord();//initialise une variable de type String qui est le second mot de notre parametre
        Room vNextRoom = this.aPlayer.getCurrentRoom().getExit(vDirection);//donne à la variable vNextRoom la valeur de toutes les sorties qui existe pour la room actuelle

        if (vNextRoom == null) {//si la room suivante n'a pas de direction associée
            this.aGui.println("There is no door !");
            return;
        } else {//si on change de room on donne la valeur de aCurrentRoom a notre nouvelle room
            this.aPlayer.getPreviousRoom().push(this.aPlayer.getCurrentRoom()); // mettre à jour la pièce précédente 
            this.aPlayer.setCurrentRoom(vNextRoom);
            printLocationInfo();
        }
    } // goRoom()

    /**
     * printWelcome to print information for start game
     * call printLocationInfo() to get the description of the first aCurrentRooms now (vMainPilot)
     */
    private void printWelcome() {
        this.aGui.print("Welcome to the world of Zuut!\nWorld of Zuul is an incredibly boring new adventure game. \nType < help > if you need help.");
        this.aGui.println("\nYou are in the pilot room of the ship you can exit to: down");
        this.aGui.print("\n");
        this.aGui.println("------------ Your name : " + this.aPlayer.getNamePlayer() + " ------------");
        this.aGui.println("------------ Your life : " + this.aPlayer.getVie()+ " ------------" );
        this.aGui.println("------------ Your Maximum possible weight : " + this.aPlayer.getPoidsMax() + " ------------");
        printLocationInfo();
    } //printWelcome()

    /**
     * printHelp to print if you write "help"
     * call showCommands() for give you all possible commmands
     */
    private void printHelp() {
        this.aGui.println("You are lost. You are alone.\nYou wander around at the ship. \n");
        this.aGui.println("Your command words are: | " + this.aParser.getShowCommands());
    } //printHelp()

    /**
     * analyse entered command and determines what action must be taken based on this commad and manages the different actions
     * @param pAppelBonneMethod type Command represent the command entered by the player
     **/
    public void interpretCommand(final String pAppelBonneMethod) {
        this.aGui.println( "> " + pAppelBonneMethod );
        Command vCommand = this.aParser.getCommand( pAppelBonneMethod );
    
        if(vCommand.isUnknown() ) { //si la Commande tapée est inconnu
            this.aGui.println("I don't know what you mean...");
            return;
        } 

        String vCommandWord = vCommand.getCommandWord();        

        if(vCommandWord.equals("quit")) { //si la commande tapée est "quit"
            if(vCommand.hasSecondWord() == true) {//si l'utilisateur tap un second mot aprés "quit" (exemple: "quit program")
                this.aGui.println("Quit what ?");
            } else {//sinon on quit 
                this.endGame();
            } 
        }
        else if(vCommandWord.equals("help")) { //si la commande tapée est "help"
            this.printHelp();
        } 
        else if(vCommandWord.equals("go")) { //si la commande tapée est "go"
            goRoom(vCommand);
        }
        else if(vCommandWord.equals("look")) { //si la commande tapée est "look"
            look(vCommand);
        }
        else if(vCommandWord.equals("eat")) { //si la commande tapée est "eat"
            eat(vCommand);
        }
        else if(vCommandWord.equals("audio")) { //si la commande tapée est "audio"
            this.toggleAudio();

        }else if(vCommandWord.equals("back")) { //si la commande tapée est "audio"
            goback(vCommand);

        }else if (vCommandWord.equals("test")) { //si la commande tapée est "test"
            test(vCommand);

        }else if (vCommandWord.equals("take")) { //si la commande tapée est "take"
            take(vCommand);

        }else if (vCommandWord.equals("drop")) { //si la commande tapée est "drop"
            drop(vCommand);

        }else if (vCommandWord.equals("infoplayer")) { //si la commande tapée est "infoplayer"
            printPlayer();
        }
        else { //si print "Erreur du programmeur : commande non reconnue !"
            this.aGui.println("Erreur du programmeur : commande non reconnue !");
        }   
        
        this.aCurrentMoves++; // Incrémente le compteur de mouvements à chaque commande entrée
        checkTimeLimit(); // Vérifie si le joueur a dépassé la limite de temps
        
    } //processCommand()

    /**
     * give: the current room in which the user is located, the room description, its different exits
     * it was created to aviod code duplication between printWelcome() and goRoom()
     */
    private void printLocationInfo() {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());

        if ( this.aPlayer.getCurrentRoom().getImageName() != null ) 
            this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());   

        if (this.aIsAudioEnabled) {
            this.aPlayer.getCurrentRoom().playAudio();
        } else {
            this.aPlayer.getCurrentRoom().stopAudio();
        }
    } //printLocationInfo()

    /**
     * print "I don't know how to look at something in particular yet." if there have a second word after look
     * else give: the current room in which the user is located, the room description, its different exits
     */
    private void look(final Command pSecondMot) {
        if(pSecondMot.hasSecondWord() == true ) {//si l'utilisateur tape un second mot apres "quit" (exemple: "quit program")
            this.aGui.println("I don't know how to look at something in particular yet.\n");

        } else {
            this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        }
    } //look()

    /**
     * Gère la commande "eat" permettant au joueur de manger un objet spécifique
     * @param pSecondMot Commande contenant le deuxième mot saisi par le joueur
     */
    private void eat(final Command pSecondMot) {
        if (pSecondMot.hasSecondWord()) {
            String vItemName = pSecondMot.getSecondWord();
            Item vItem = this.aPlayer.getItemInInventory(vItemName);

            // Check if the item is in the player's inventory
            if (vItem != null && vItem.getNameItem().equals("Cookie")) {
                // Check if the player has the item in the inventory
                if (this.aPlayer.getItemInInventory("Cookie") != null) {
                    // Remove the Cookie from the player's inventory
                    this.aPlayer.dropItem("Cookie", vItem);
                    // Update player's health
                    this.aPlayer.donneVie(20);
                    this.aGui.println("You have eaten the Cookie. Your health is now " + this.aPlayer.getVie() + ".");
                } else {
                    this.aGui.println("You don't have a " + vItemName + " in your inventory.");
                }
            } else {
                this.aGui.println("You can't eat that!");
            }
        } else {
            this.aGui.println("Just one thing at a time.");
        }
    } // eat()

    /**
     * print "Thank you for playing.  Good bye." if you print "quit" or click on the "quit" button 
     * this command allows you to quit and close the frame
     */
    private void endGame()
    {
        this.aGui.println( "Thank you for playing.  Good bye." );
        this.aGui.enable( false ); 
        this.aGui.closeFrame();
    } //endGame()

    /**
     * Active ou désactive l'audio du jeu. Si l'audio est activé, le son de la pièce actuelle est joué,
     * sinon, le son est arrêté. La méthode met à jour l'état de l'audio et effectue les actions nécessaires
     */
    public void toggleAudio() {
        if (this.aIsAudioEnabled) {
            this.aIsAudioEnabled = false;
            this.aPlayer.getCurrentRoom().stopAudio(); // Arrêter le son lorsque l'audio est désactivé
        } else {
            this.aIsAudioEnabled = true;
            if (this.aIsAudioEnabled) {
                this.aPlayer.getCurrentRoom().playAudio();
            } // Jouer le son lorsque l'audio est activé
        }
    } //toggleAudio()

    /**
     * This method allows the user to go to the previous room if they enter just "back"
     * @param pSecondMot to check if there is a second word
     */
    private void goback(final Command pSecondMot) {
        if(pSecondMot.hasSecondWord() == true ) {//si l'utilisateur tape un second mot apres "back" (exemple: "back south")
            this.aGui.println("Just back.\n");

        } else if (!this.aPlayer.getPreviousRoom().isEmpty()) {
            this.aPlayer.setCurrentRoom(this.aPlayer.getPreviousRoom().pop()); //retire la room au dessus de la pile avec pop et donne la valaeur de cette room à aCurrentRoom 
            printLocationInfo();
        } else {
            this.aGui.println("You can't go back!");
        }
    }  //goback()

    /**
     * test permet de tester automatiquement le jeu.
     * @param pFileName est une commande taper par le joueur.
     */
    private void test(final Command pFileName){
        if (!pFileName.hasSecondWord()){
            this.aGui.println( "tester quoi? (court, exploration, ideal, takedrop, eat)"); //si le joueur tape seulement test
            return; //sortir directement de la fonction
        }

        try { //faire toutes les commandes même si erreur
            File vTest = new File("./Commands/"+pFileName.getSecondWord()+".txt"); //récupération du fichier pour le test
            Scanner vScanner = new Scanner(vTest); //scanner le fichier

            while(vScanner.hasNextLine()){ //tant qu'on détecte une ligne passer à la suivante
                interpretCommand(vScanner.nextLine()); //appele de interpretCommand()
            }
            vScanner.close();
        }
        catch(final java.io.FileNotFoundException pE){ //si le fichier demandé n'existe pas 
            this.aGui.println("il n'y a pas le fichier demandé.");
        }
    }   //test()

    /** Exécute la commande "take" pour que le joueur prenne un item de la pièce actuelle
     * Si la commande n'a pas de deuxième mot (nom de l'item à prendre), affiche un message
     * Sinon, vérifie si l'item existe dans la pièce actuelle, s'il peut être pris en fonction du poids,
     * et ajoute l'objet à l'inventaire du joueur tout en le retirant de la pièce
     * @param pCommand L'Item Command représentant la commande utilisateur
     */
    private void take(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Take what?"); //si la commande est incomplète sans second mot
            return;
        }else {
            String vItemName = pCommand.getSecondWord(); // Obtient le nom de l'objet à partir de la commande
            Item vItem = this.aPlayer.getCurrentRoom().getItem(vItemName); //Récupere l'objet de la pièce actuelle

            if(vItem != null ) {
                if(this.aPlayer.getTotalWeight(vItem)) {// Vérifie si le poids de l'objet peut être ajouté à l'inventaire du joueur avec getTotalWeight contenu dans Player
                    this.aPlayer.takeItem(vItemName, vItem);// ajoute l'item à l'inventaire du joueur 
                    this.aPlayer.getCurrentRoom().getItemList().removeItem(vItemName, vItem); // retire l'item de la pièce actuelle
                    this.aGui.println("You took the " + vItemName + "."); //print l'item 
                    this.aGui.println("Your Maximum possible weight now is " + this.aPlayer.getPoidsMax() + ".\n"); //print le poid Max que peut porter le Player après avoir add l'item
                } else {
                    this.aGui.println("The item is too heavy."); //print si l'objet est supèrieur à ce que peut porter un Player
                }
            } else {
                this.aGui.println("Unable to take the " + vItemName + ".\n"); //print si l'item n'existe pas
            }
        }
    } //takeItem()

    /**
     * Exécute la commande "drop" pour que le joueur pose un Item de son inventaire dans la pièce actuelle
     * Si la commande n'a pas de deuxième mot (nom de l'item à poser), affiche un message
     * Sinon, vérifie si l'item existe dans l'inventaire du joueur, le retire de l'inventaire,
     * et le place dans la pièce actuelle
     * @param pCommand L'objet Command représentant la commande utilisateur
     */
    private void drop(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Drop what?");
            return;
        } else {
            String vItemName = pCommand.getSecondWord();
            Item vItem = this.aPlayer.getItem(vItemName);

            if (vItem != null) {
                this.aPlayer.getCurrentRoom().getItemList().takeItem(vItemName, vItem);
                this.aPlayer.dropItem(vItemName, vItem);
                this.aGui.println("You dropped the " + vItemName + ".");
                this.aGui.println("Your Maximum possible weight now is " + this.aPlayer.getPoidsMax() + ".\n");
            } else {
                this.aGui.println("Error: The " + vItemName + " is not in your inventory.\n");
            }
        }
    } //drop()

    /**
     * Affiche les informations du joueur, y compris son nom, sa vie, son poids maximal possible,
     * et la liste des objets dans son inventaire.
     */
    private void printPlayer() {
        this.aGui.println("Your name : " + this.aPlayer.getNamePlayer());
        this.aGui.println("Your life : " + this.aPlayer.getVie());
        this.aGui.println("Your maximum possible weight now : " + this.aPlayer.getPoidsMax());
        this.aGui.println(this.aPlayer.getInventoryItemList());
    } //printPlayer()

    
     /**
     * Vérifie si le joueur a dépassé le nombre maximal de mouvements.
     * Si oui, met fin au jeu.
     */
    private void checkTimeLimit() {
        if(this.aCurrentMoves > this.aMaxMoves - 1) {
            this.aGui.println("Il vous reste seulement un Seulement Mouvement. Attention vous avez atteint votre limite de temps");
        }
        
        if (this.aCurrentMoves > this.aMaxMoves) {
            this.aGui.println("Temps écoulé ! Vous avez dépassé le nombre maximal de mouvements. Fin du jeu.");
            this.endGame();
        }
    }
    
    

} //GameEngine