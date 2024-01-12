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
    private Room            aRoom;
    private Png             aPng;

    private int             aMaxMoves;
    private int             aCurrentMoves;

    private Room            aRightEngine;
    private Room            aLeftEngine;

    private Item            aArmeLegere;
    private Item            aArmeLourde;

    private Png             aEnnemiNul;
    private Png             aMiniBoss;
    private Png             aBoss;

    //pour activer ou non l'audio
    private boolean aIsAudioEnabled;

    public GameEngine() {
        this.aParser = new Parser();
        this.createRoomsAndPlayers();
        this.aIsAudioEnabled = false; //l'audio est désactiver par défaut
        this.aMaxMoves = 14;
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
        String vLienAudios = "./NewAudios/";

        //Main Room:
        vMainPilot = new Room("in main pilot room", vLienImages + "mainpilot.jpg" , vLienAudios + "mainpilot.wav");

        Item vTW = new Item("Talkie-Walkie", 2, "Le talkie Walkie vous permetra de toujours être connecté avec Lara pour vous indiquer les étapes du jeu.", 0);
        vMainPilot.setItems(vTW);

        Item vMasse = new Item("Masse" , 80, "Une masse très Lourd", 0);
        vMainPilot.setItems(vMasse);

        Item vCleAnglaise = new Item("CleAnglaise", 2, "Une clé anglaise pour réparer les moteurs.", 0);
        vMainPilot.setItems(vCleAnglaise);

        this.aArmeLegere  = new Item("ArmeLegere" , 5, "Une Arme qui fait de petits dégâts aux ennemies", 10);
        vMainPilot.setItems(this.aArmeLegere);

        this.aArmeLourde  = new Item("ArmeLourde" , 10, "Une Arme qui fait de gros dégâts aux ennemies", 30);
        vMainPilot.setItems(this.aArmeLourde);

        vMainWing = new Room("in main wing room", vLienImages + "mainwing.jpg" , "");
        vMainEngine = new Room("in main engine room", vLienImages + "mainengine.jpg" , "");

        // Left and Right room
        vRightWing = new Room("in right wing ", vLienImages + "rightroomwing.jpg" , vLienAudios + "rightroomwing.wav");
        vLeftWing = new Room("in left wing", vLienImages + "leftroomwing.png" , vLienAudios + "leftroomwing.wav");
        this.aRightEngine = new Room("in right engine", vLienImages + "rightroomengine.jpg" , vLienAudios + "rightroomengine.wav");
        this.aLeftEngine = new Room("in left engine", vLienImages + "leftroomengine.jpg" , vLienAudios + "leftroomengine.wav");

        // Wing Corridor
        vCorridorRightWing = new Room("in corridor right wing", vLienImages + "corridorrightwing.jpg" , "");
        vCorridorLeftWing = new Room("in corridor left wing", vLienImages + "corridorleftwing.jpg" , "");

        // Corridor Wing Main to Engine Main
        vCorridorMainWingMainEngine = new Room("in corridor between main wing and main engine", vLienImages + "corridormainwingmainengine.jpg" , "");

        //Engine Corridor
        vCorridorRightEngine = new Room("in corridor right engine", vLienImages + "corridorengineright.png" , "");
        vCorridorLeftEngine = new Room("in corridor left engine", vLienImages + "corridorengineleft.png" , "");

        //Hidden Corridor
        vCorridorHiddenRight = new Room("in hidden corridor right between right wing and right engine", vLienImages + "corridorhiddenright.png" , "");
        vCorridorHiddenLeft = new Room("in hidden corridor left between left wing and left engine", vLienImages + "corridorhiddenleft.png" , "");

        vRightWing.setTrapDoor(true);
        vLeftWing.setTrapDoor(true);

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
        Item vCookieMagic = new Item("Cookie" , 1, "Un cookie magique qui ajoute de la vie", 0);
        vMainEngine.setItems(vCookieMagic);

        //for vRightWing
        vRightWing.setExits("south", vCorridorHiddenRight);
        vRightWing.setExits("west", vCorridorRightWing);

        //for vLeftWing
        vLeftWing.setExits("south", vCorridorHiddenLeft);
        vLeftWing.setExits("east", vCorridorLeftWing);

        //for vRightEngine
        this.aRightEngine.setExits("upnorth", vCorridorHiddenRight);
        this.aRightEngine.setExits("west", vCorridorRightEngine);

        //for vLeftEngine
        this.aLeftEngine.setExits("upnorth", vCorridorHiddenLeft);
        this.aLeftEngine.setExits("east", vCorridorLeftEngine);

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
        vCorridorRightEngine.setExits("east", this.aRightEngine);

        //vCorridorLeftEngine
        vCorridorLeftEngine.setExits("west", this.aLeftEngine);
        vCorridorLeftEngine.setExits("upeast", vMainEngine);

        //dans le futur on ne les verra pas 
        //vCorridorHiddenRight
        vCorridorHiddenRight.setExits("north", vRightWing);
        vCorridorHiddenRight.setExits("downsouth", this.aRightEngine);

        //vCorridorHiddenLeft
        vCorridorHiddenLeft.setExits("north", vLeftWing);
        vCorridorHiddenLeft.setExits("downsouth", this.aLeftEngine);
        Item vCookieMagic1 = new Item("MegaCookie" , 3, "Un cookie magique qui ajoute beaucoup de vie", 0);
        vCorridorHiddenLeft.setItems(vCookieMagic1);

        this.aPlayer = new Player("Mike", vMainPilot, 20, 100);//creation du joueur
        this.aEnnemiNul  = new Png("EnnemiNull",vRightWing, 20, 1, true);//petit ennemi
        this.aMiniBoss   = new Png("MiniBoss", vLeftWing, 50, 15, true);//mini boss
        this.aBoss    = new Png("Boss", this.aLeftEngine, 150, 30, true);//Boss

        vRightWing.setPng(this.aEnnemiNul);
        vLeftWing.setPng(this.aMiniBoss);
        this.aLeftEngine.setPng(this.aBoss);

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
            this.aCurrentMoves++; // Incrémente le compteur de mouvements à chaque commande entrée
            checkTimeLimit(); // Vérifie si le joueur a dépassé la limite de temps
            this.aPlayer.getCurrentRoom().stopAudio();//arrete l'audio de la piece actuelle avant de change de piece
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

        }else if (vCommandWord.equals("repare")) { //si la commande tapée est "repare"
            repareMotor(vCommand);
            verifRepare();
        }else if (vCommandWord.equals("attack")) { //si la commande tapée est "attack"
            attack(vCommand);
            verifAttack();
        }
        else { //si print "Erreur du programmeur : commande non reconnue !"
            this.aGui.println("Erreur du programmeur : commande non reconnue !");
        }   

    } //processCommand()

    /**
     * give: the current room in which the user is located, the room description, its different exits
     * it was created to aviod code duplication between printWelcome() and goRoom()
     */
    private void printLocationInfo() {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        Png vCurrentPng = this.aPlayer.getCurrentRoom().getPng();
        if(vCurrentPng != null) {
            this.aGui.println("Attention il y a un ennemi qui se nomme " + vCurrentPng.getNamePng() + ". Vous pouvez l'attaquer avec attaque a condition d'avoir une Arme");
        }

        if ( this.aPlayer.getCurrentRoom().getImageName() != null ) {
            this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName()); //l'image de chaque room
            //this.aGui.showImage("./Images/Map.png"); //map du jeu
        }

        if (this.aIsAudioEnabled == false) {
            this.aPlayer.getCurrentRoom().stopAudio();
        } else {
            this.aPlayer.getCurrentRoom().playAudio();
        }
    } //printLocationInfo()

    /**
     * print "I don't know how to look at something in particular yet." if there have a second word after look
     * else give: the current room in which the user is located, the room description, its different exits
     */
    private void look(final Command pSecondMot) {
        if(pSecondMot.hasSecondWord() == true ) {//si l'utilisateur tape un second mot apres "look" (exemple: "look room")
            this.aGui.println("I don't know how to look at something in particular yet.\n");
        } else {
            printLocationInfo();
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

            if(vItem != null && vItem.getNameItem().equals("Cookie") || vItem != null && vItem.getNameItem().equals("MegaCookie")) {
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
                }

                if (vItem != null && vItem.getNameItem().equals("MegaCookie")) {
                    // Check if the player has the item in the inventory
                    if (this.aPlayer.getItemInInventory("MegaCookie") != null) {
                        // Remove the Cookie from the player's inventory
                        this.aPlayer.dropItem("MegaCookie", vItem);
                        // Update player's health
                        this.aPlayer.donneVie(100);
                        this.aGui.println("You have eaten the Cookie. Your health is now " + this.aPlayer.getVie() + ".");
                    } else {
                        this.aGui.println("You don't have a " + vItemName + " in your inventory.");
                    }
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
    private void endGame() {
        this.aGui.println( "Thank you for playing.  Good bye." );
        this.aGui.enable( false ); 
        this.aPlayer.getCurrentRoom().stopAudio();//arreter l'audio aussi
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
            Item talkieWalkie = this.aPlayer.getItemInInventory("Talkie-Walkie");
            if (talkieWalkie != null) {
                this.aIsAudioEnabled = true;
                if (this.aIsAudioEnabled) {
                    this.aPlayer.getCurrentRoom().playAudio();
                } // Jouer le son lorsque l'audio est activé
            } else {
                this.aGui.println("Vous avez besoin du Talkie-Walkie pour activer l'audio.");
            }
        }
    }

    /**
     * Cette méthode permet à l'utilisateur de retourner à la pièce précédente s'ils saisissent simplement "back"
     * @param pSecondMot pour vérifier s'il y a un deuxième mot
     */
    private void goback(final Command pSecondMot) {
        if (pSecondMot.hasSecondWord() == true) {// Vérifie si un deuxième mot est saisi après "back" (exemple : "back south")
            this.aGui.println("Just back.\n");

        } else if (!this.aPlayer.getPreviousRoom().isEmpty()) {  // Si la pièce précédente n'est pas vide, appelle la méthode trapdoor()
            trapdoor();
        } else {
            this.aGui.println("You can't go back!");
        }
    }  // goback()

    /**
     * Cette méthode permet à l'utilisateur d'utiliser la trappe si la pièce actuelle en a une
     */
    private void trapdoor() {
        Room vPreviousRoom = this.aPlayer.getPreviousRoom().peek(); // Récupère la pièce précédente depuis la pile des pièces précédentes du joueur

        if (vPreviousRoom != null && !vPreviousRoom.getTrapDoor()) { // Vérifie si la pièce précédente existe et si elle a une trappe
            this.aPlayer.setCurrentRoom(this.aPlayer.getPreviousRoom().pop()); // Déplace le joueur vers la pièce précédente en retirant la pièce du dessus de la pile
            printLocationInfo();//affiche les info de le room
        } else {
            aGui.println("You can't go back through the trapdoor!");
        }
    }

    /**
     * test permet de tester automatiquement le jeu.
     * @param pFileName est une commande taper par le joueur.
     */
    private void test(final Command pFileName){
        if (!pFileName.hasSecondWord()){
            this.aGui.println( "tester quoi? (court, exploration, ideal, takedrop, eat, attaque)"); //si le joueur tape seulement test
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
                // Ajouter la logique de vérification pour les armes légères et lourdes
                if ((vItem.getNameItem().equals("ArmeLegere") && this.aPlayer.getItemInInventory("ArmeLourde") != null) ||
                (vItem.getNameItem().equals("ArmeLourde") && this.aPlayer.getItemInInventory("ArmeLegere") != null)) {
                    this.aGui.println("Vous ne pouvez pas prendre l'arme légère et l'arme lourde en même temps.\n");
                }else {
                    if(this.aPlayer.getTotalWeight(vItem)) {// Vérifie si le poids de l'objet peut être ajouté à l'inventaire du joueur avec getTotalWeight contenu dans Player
                        this.aPlayer.takeItem(vItemName, vItem);// ajoute l'item à l'inventaire du joueur 
                        this.aPlayer.getCurrentRoom().getItemList().removeItem(vItemName, vItem); // retire l'item de la pièce actuelle
                        this.aGui.println("You took the " + vItemName + "."); //print l'item 
                        this.aGui.println("Your Maximum possible weight now is " + this.aPlayer.getPoidsMax() + ".\n"); //print le poid Max que peut porter le Player après avoir add l'item
                    } else {
                        this.aGui.println("The item is too heavy."); //print si l'objet est supèrieur à ce que peut porter un Player
                    }
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
        if(this.aCurrentMoves == this.aMaxMoves - 1) {
            this.aGui.println("Il vous reste seulement un seul Mouvement. Attention vous avez atteint votre limite de temps");
        }

        if (this.aCurrentMoves == this.aMaxMoves) {
            System.out.println("Temps écoulé ! Vous avez dépassé le nombre maximal de mouvements. Fin du jeu.");
            this.endGame();
            return;
        }
    }   //checkTimeLimit()

    /**
     * Cette méthode permet au joueur de réparer un moteur spécifique dans la pièce actuelle
     * @param pCommand la commande utilisateur
     */
    private void repareMotor(final Command pCommand) {
        if (!pCommand.hasSecondWord()) { // Vérifie si aucun deuxième mot n'est saisi dans la commande
            this.aGui.println("Réparer quoi? 'motor1' ou 'motor2'");
            return;
        } else {
            String vString = pCommand.getSecondWord(); // Récupère le deuxième mot de la commande
            Room vRoom = this.aPlayer.getCurrentRoom(); // Récupère la pièce actuelle du joueur

            if (vString.equals("motor1") && this.aRightEngine == vRoom) { // Vérifie si le deuxième mot est "motor1" et si la pièce actuelle correspond au moteur droit
                if (this.aPlayer.getItemInInventory("CleAnglaise") != null) { // Vérifie si le joueur a la clé anglaise dans son inventaire
                    // Répare le moteur 1 et met à jour l'état du moteur dans la pièce
                    vRoom.setMotor1(true);
                    this.aGui.println("Le moteur 1 est réparé avec succès.");
                } else {
                    this.aGui.println("Vous avez besoin d'une clé anglaise pour réparer le moteur 1.\n");
                }
            } else if (vString.equals("motor2") && this.aLeftEngine == vRoom) { // Vérifie si le deuxième mot est "motor2" et si la pièce actuelle correspond au moteur gauche
                if (this.aPlayer.getItemInInventory("CleAnglaise") != null) { // Vérifie si le joueur a la clé anglaise dans son inventaire
                    // Répare le moteur 2 et met à jour l'état du moteur dans la pièce
                    vRoom.setMotor2(true);
                    this.aGui.println("Le moteur 2 est réparé avec succès.");
                } else {
                    this.aGui.println("Vous avez besoin d'une clé anglaise pour réparer le moteur 2.\n");
                }
            } else {
                // Si le deuxième mot n'est ni "motor1" ni "motor2", ou si la pièce actuelle ne correspond à aucun moteur, affiche un message d'erreur
                this.aGui.println("Vous ne pouvez pas réparer le moteur.");
            }
        }
    }

    /**
     * Vérifie l'état des moteurs et la progression du joueur dans le jeu
     * @return true si le joueur a réussi à réparer les moteurs et a vaincu tous les ennemis, false sinon
     */
    private boolean verifRepare() {
        Room vRoom = this.aPlayer.getCurrentRoom(); // Récupère la pièce actuelle du joueur
        if (this.aRightEngine.getMotor1()) { // Vérifie si le motor 1 est réparé, si oui dire que il faut réparer le motor2 
            this.aGui.println("Il vous reste le Motor2 à réparer.\n");
        } else if (this.aRightEngine.getMotor2()) { // sinon si le motor 2 est réparé dire que il reste le moeur1 à réparer 
            this.aGui.println("Il vous reste le Motor1 à réparer.\n");
        } else {
            return false;
        }

        // Vérifie si la pièce actuelle, le Motor1 du moteur droit, le Motor2 du moteur gauche, le boss, le mini-boss et l'ennemi nul sont tous dans un état particulier
        if (vRoom != null && this.aRightEngine.getMotor1() && this.aLeftEngine.getMotor2() && !this.aEnnemiNul.getEnVie() && !this.aMiniBoss.getEnVie() && !this.aBoss.getEnVie()) {
            // Affiche un message de victoire et termine le jeu
            System.out.println("Vous avez gagné le Jeu!");
            endGame();
            return true;
        }
        return false;
    }

    /**
     * Cette méthode permet au joueur d'attaquer le Png présent dans la pièce actuelle avec une arme spécifiée.
     * @param pCommand la commande utilisateur
     */
    private void attack(final Command pCommand) {
        if (!this.aPlayer.getCurrentRoom().hasPng()) { // Vérifie si la pièce actuelle contient un Png
            this.aGui.println("Il n'y a pas d'ennemi ici.");
            return;
        }

        Png vCurrentPng = this.aPlayer.getCurrentRoom().getPng(); // Récupère le Png actuel dans la pièce du joueur
        String vTypeArme = pCommand.getSecondWord(); // Récupère le type d'arme spécifié dans la commande

        if (!pCommand.hasSecondWord()) { // Vérifie s'il n'y a pas de second mot dans la commande
            this.aGui.println("Attaque avec quelle arme? Regardez dans votre inventaire avec 'infoplayer'.");
        } else if (this.aPlayer.getVie() <= vCurrentPng.getDegat()) { // Vérifie si le joueur n'a plus de vie
            System.out.println("Vous vous êtes fait tuer par " + vCurrentPng.getNamePng());
            endGame();
        } else {

            // Le joueur perd de la vie en fonction des dégâts du Png
            this.aPlayer.perdVie(vCurrentPng.getDegat());
            this.aGui.println("Il vous reste " + this.aPlayer.getVie() + " de vie");

            int vDegatArme = 0;
            
            if ("ArmeLegere".equals(vTypeArme) && this.aPlayer.getItemInInventory("ArmeLegere") != null) {// Vérifie le type d'arme et si le joueur possède cette arme dans son inventaire
                vDegatArme = this.aArmeLegere.getDegat();
            } else if ("ArmeLourde".equals(vTypeArme) && this.aPlayer.getItemInInventory("ArmeLourde") != null) {
                vDegatArme = this.aArmeLourde.getDegat();
            } else {
                // Si le type d'arme n'est pas valide ou le joueur ne possède pas cette arme, affiche un message et quitte la méthode
                this.aGui.println("Vous ne pouvez pas attaquer avec cette arme, elle ne se trouve pas dans votre inventaire.");
                return;
            }

            if (vCurrentPng.getVie() > vDegatArme) { // Vérifie si le Png a plus de vie que les dégâts de l'arme
                vCurrentPng.prendDegat(vDegatArme); // Faire les dégâts de l'arme au Png
                this.aGui.println("Il reste " + vCurrentPng.getVie() + " à " + vCurrentPng.getNamePng());
            } else {
                // Si le Png est vaincu, affiche un message et le supprime de la pièce
                vCurrentPng.setEnVie(false);
                this.aGui.println(vCurrentPng.getNamePng() + " est vaincu !");
                this.aPlayer.getCurrentRoom().removePng();
            }
        }
    } //attack()

    /**
     * Vérifie l'état des ennemis dans le jeu et informe le joueur sur les ennemis restants à vaincre.
     * @return true si tous les ennemis ont été vaincus, false sinon.
     */
    private boolean verifAttack() {
        if (!this.aEnnemiNul.getEnVie() && !this.aMiniBoss.getEnVie() && this.aBoss.getEnVie()) {
            this.aGui.println("Vous avez tué : " + this.aEnnemiNul.getNamePng() + " et " + this.aMiniBoss.getNamePng() + ". Il vous reste à tuer : " + this.aBoss.getNamePng() + "\n");
        } else if (!this.aMiniBoss.getEnVie() && !this.aBoss.getEnVie() && this.aEnnemiNul.getEnVie()) {
            this.aGui.println("Vous avez tué : " + this.aMiniBoss.getNamePng() + " et " + this.aBoss.getNamePng() + ". Il vous reste à tuer : " + this.aEnnemiNul.getNamePng() + "\n");
        } else if (!this.aBoss.getEnVie() && !this.aEnnemiNul.getEnVie() && this.aMiniBoss.getEnVie()) {
            this.aGui.println("Vous avez tué : " + this.aBoss.getNamePng() + " et " + this.aEnnemiNul.getNamePng() + ". Il vous reste à tuer : " + this.aMiniBoss.getNamePng() + "\n");
        } else if (!this.aEnnemiNul.getEnVie() && this.aMiniBoss.getEnVie() && !this.aBoss.getEnVie()) {
            this.aGui.println("Vous avez tué : " + this.aEnnemiNul.getNamePng() + ". Il vous reste à tuer : " + this.aMiniBoss.getNamePng() + " et " + this.aBoss.getNamePng() + "\n");
        } else if (!this.aMiniBoss.getEnVie() && this.aEnnemiNul.getEnVie() && !this.aBoss.getEnVie()) {
            this.aGui.println("Vous avez tué : " + this.aMiniBoss.getNamePng() + ". Il vous reste à tuer : " + this.aEnnemiNul.getNamePng() + " et " + this.aBoss.getNamePng() + "\n");
        } else if (!this.aBoss.getEnVie() && this.aMiniBoss.getEnVie() && !this.aEnnemiNul.getEnVie()) {
            this.aGui.println("Vous avez tué : " + this.aBoss.getNamePng() + ". Il vous reste à tuer : " + this.aMiniBoss.getNamePng() + " et " + this.aEnnemiNul.getNamePng() + "\n");
        } else if (this.aEnnemiNul.getEnVie() && this.aMiniBoss.getEnVie() && this.aBoss.getEnVie()) {
            this.aGui.println("Vous avez tué aucun ennemis !\n");
        } else if (!this.aEnnemiNul.getEnVie() && this.aMiniBoss.getEnVie() && this.aBoss.getEnVie()) {
            this.aGui.println("Vous avez tué : " + this.aEnnemiNul.getNamePng() + ". Il vous reste à tuer : " + this.aMiniBoss.getNamePng() + " et " + this.aBoss.getNamePng() + "\n");
        } else if (!this.aMiniBoss.getEnVie() && this.aEnnemiNul.getEnVie() && this.aBoss.getEnVie()) {
            this.aGui.println("Vous avez tué : " + this.aMiniBoss.getNamePng() + ". Il vous reste à tuer : " + this.aMiniBoss.getNamePng() + " et " + this.aBoss.getNamePng() + "\n");
        } else if (!this.aBoss.getEnVie() && this.aMiniBoss.getEnVie() && this.aEnnemiNul.getEnVie()) {
            this.aGui.println("Vous avez tué : " + this.aBoss.getNamePng() + ". Il vous reste à tuer : " + this.aMiniBoss.getNamePng() + " et " + this.aEnnemiNul.getNamePng() + "\n");
        } else {
            this.aGui.println("Vous avez tué tous les ennemis !\n");
            return true;
        }
        return false;
    } //verifAttack

}//GameEngine