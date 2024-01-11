import java.util.HashMap;
import java.util.Set;
import java.util.Collection;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author Matthias Hautin
 * @version 25/11/23
 * 
 */
public class Room
{
    private HashMap<String, Room> aExits;
    private String aDescription;
    private String aImageName;
    private String aAudioName;
    private Clip aAudioClip;
    private ItemList aItemsList;
    private Png aPng;
    private Player aPlayer;

    private boolean aIsExit;
    private boolean aMotor1;
    private boolean aMotor2;

    /**
     * initialize all attribute
     * @param pDescription (String) intialize Room descrioption 
     * @param pImage (String)
     * @param pAudio (String)
     */
    public Room(final String pDescription, final String pImage, final String pAudio) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aImageName = pImage;
        this.aAudioName = pAudio;
        this.aItemsList = new ItemList();
        this.aIsExit = false;
        this.aPng = null; // La pièce n'a pas de PNG par défaut
    } // Room()

    /**
     * @return aDescription attribute, String, getter
     */
    public String getDescription() {
        return this.aDescription;
    } //getDescription()

    /**
     * define the exits for the all rooms. All direction if its possible, if that rooms egal null no exits here, procedure, setter
     * @param pDirection (String) Exit direction 
     * @param pNeighbor (Room) the given direction room
     */
    public void setExits(final String pDirection, final Room pNeighbor) {
        this.aExits.put(pDirection,pNeighbor);
    } //setExits()

    /**
     * @return all directions relating to room , Room, getter
     * @param pDirection (String) given all direction for next room else null if direction is null
     */
    public Room getExit(final String pDirection) {
        return this.aExits.get(pDirection);
    } //getExit()

    /**
     * @return all directions that exist for all exits that exist in the room
     */
    public String getExitString() {
        String vReturnString = "Exits: ";
        Set<String> vKeys = this.aExits.keySet();
        for(String vExit : vKeys) {
            vReturnString += " | " + vExit + " | ";
        }
        return vReturnString;
    } //getExitString 

    /**
     * @return getDescription() and getExitString() and getItemString and getItemLongDescription to avoid calling its 4 getters in printLocationInfo()
     * print for example:
     * You are in main pilot room.
     *   Exits:  | down | 
     *   Object : Talkie Walkie (Weight: 2 )
     *   Item Talkie Walkie description : Le talkie Walkie vous permetra de toujours être connecté avec Lara ...
     */
    public String getLongDescription() {
        return "You are " + getDescription() + ".\n" + getExitString() + "\n" + this.aItemsList.getItemString() + "\n" + this.aItemsList.getItemLongDescription();
    } //getLongDescription

    /**
     * @return "You have eaten now and you are not hungry any more.\n"
     */
    public String getEat() {
        return "You have eaten now and you are not hungry anymore.\n";
    } //getEat()

    /**
     * @return aImageName String
     */
    public String getImageName() {
        return this.aImageName;
    } //getImageName()

    /**
     * @return aAudioName String
     */
    public String getAudioName() {
        return this.aAudioName;
    } //getAudioName()

    /**
     * Joue le fichier audio associé à cette instance de la classe.
     * Si le fichier audio n'existe pas, la méthode ne fait rien.
     * En cas d'erreur lors de la lecture du fichier audio, une trace
     * de la pile est affichée.
     * 
     */
    public void playAudio() {
        try {
            // Création d'un objet File à partir du nom de fichier audio
            File vAudioFile = new File(this.aAudioName);

            if (!vAudioFile.exists()) { // Vérifie si le fichier audio existe
                return; // Si le fichier n'existe pas, la méthode se termine
            } else {
                AudioInputStream vAudioStream = AudioSystem.getAudioInputStream(vAudioFile);// Obtient un flux audio à partir du fichier audio
                this.aAudioClip = AudioSystem.getClip();// Création d'un objet Clip pour la lecture du fichier audio
                this.aAudioClip.open(vAudioStream);// Ouverture du Clip avec le flux audio
                this.aAudioClip.start(); // Démarre la lecture du fichier audio
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // En cas d'erreur, affiche la trace de la pile
            e.printStackTrace();
        }
    } //playAudio()

    public void stopAudio() {
        if (this.aAudioClip != null && this.aAudioClip.isRunning()) {
            this.aAudioClip.stop();
        }
    } //stopAudio()

    /**
     * define the items that are given to a room
     * @param pItem (string) 
     */
    public void setItems(final Item pItem) {
        this.aItemsList.setItemList(pItem);
    } //setItem()

    /**
     * Obtient l'Item correspondant au nom spécifié de la liste d'items de la pièce
     * @param pItem Le nom de l'item à récupérer
     * @return L'item correspondant au nom spécifié, ou null s'il n'est pas trouvé
     */
    public Item getItem(final String pItem) {
        return this.aItemsList.getItem(pItem);
    } //getItem()

    /**
     * @return La liste d'Items de la pièce.
     */
    public ItemList getItemList() {
        return this.aItemsList;
    } //getItemList()

    /**
     * @return true si la pièce a une trappe de sortie, sinon false.
     */
    public boolean getTrapDoor() {
        return this.aIsExit;
    } //getTrapDoor()

    /**
     * Définit si la pièce a une trappe de sortie.
     * 
     * @param vExit true si la pièce a une trappe de sortie, sinon false.
     */
    public void setTrapDoor(final boolean vExit) {
        this.aIsExit = vExit;
    } //setTrapDoor()

    /**
     * @return true si le moteur 1 est activé, sinon false.
     */
    public boolean getMotor1() {
        return this.aMotor1;
    } //getMotor1

    /**
     * @return true si le moteur 2 est activé, sinon false.
     */
    public boolean getMotor2() {
        return this.aMotor2;
    } //getMotor2()

    /**
     * Active ou désactive le moteur 1.
     * 
     * @param vMotor true pour activer, false pour désactiver.
     */
    public void setMotor1(final boolean vMotor) {
        this.aMotor1 = vMotor;
    } //setMotor1()

    /**
     * Active ou désactive le moteur 2.
     * 
     * @param vMotor true pour activer, false pour désactiver.
     */
    public void setMotor2(final boolean vMotor) {
        this.aMotor2 = vMotor;
    } //setMotor2()

    /**
     * @return true si la pièce a un personnage non-joueur (PNG), sinon false.
     */
    public boolean hasPng() {
        return this.aPng != null;
    } //hasPng()

    /**
     * @return Le PNG de la pièce, null si la pièce n'a pas de PNG.
     */
    public Png getPng() {
        return this.aPng;
    } //getPng()

    /**
     * Définit le PNG de la pièce.
     * 
     * @param pPng Le PNG à définir dans la pièce.
     */
    public void setPng(Png pPng) {
        this.aPng = pPng;
    } //setPng()

    /**
     * Retire le personnage non-joueur (PNG) de la pièce.
     */
    public void removePng() {
        this.aPng = null;
    } //removePng()

} // Room
