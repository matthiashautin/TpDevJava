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
    
    private boolean aIsExit;

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
    } // Room()

    public boolean getTrapDoor() {
        return this.aIsExit;
    }
    
    public void setTrapDoor(final boolean vExit) {
        this.aIsExit = vExit;
    }
    
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

    public void playAudio() {
        try {
            File vAudioFile = new File(this.aAudioName);
            AudioInputStream vAudioStream = AudioSystem.getAudioInputStream(vAudioFile);

            this.aAudioClip = AudioSystem.getClip();
            this.aAudioClip.open(vAudioStream);
            this.aAudioClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
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

} // Room
