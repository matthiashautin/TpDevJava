import java.util.HashMap;
import java.util.Set;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author votre nom
 */
public class Room
{
    private HashMap<String, Room> aExits;
    private String aDescription;
    private String aImageName;
    private String aAudioName;
    private Clip aAudioClip;
    private Item aItem;
    
    /**
     * initialize all attribute
     * @Constructor Room() use for create new room
     * @param String pDescription intialize Room descrioption 
     */
    public Room(final String pDescription, final String pImage, final String pAudio) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aImageName = pImage;
        this.aAudioName = pAudio;
    } // Room()
    
    /**
     * @return aDescription attribute, String, getter
     * @method getDescription()
     */
    public String getDescription() {
        return this.aDescription;
    } //getDescription()
    
    /**
     * define the exits for the all rooms. All direction if its possible, if that rooms egal null no exits here, procedure, setter
     * @method setExits()
     * @param pDirection Exit direction 
     * @param pNeighbor the given direction room
     */
    public void setExits(final String pDirection, final Room pNeighbor) {
        this.aExits.put(pDirection,pNeighbor);
    } //setExits()
    
    /**
     * @return all directions relating to room , Room, getter
     * @method getExit()
     * @params String pDirection given all direction for next room else null if direction is null
     */
    public Room getExit(final String pDirection) {
        return this.aExits.get(pDirection);
    } //getExit()
    
    /**
     * return all directions that exist for all exits that exist in the room
     * @method getExitString()
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
     * @return getDescription() and getExitString() to avoid calling its 2 getters in printLocationInfo()
     * print for example:
     * You are in main pilot room.
     * Exits:  | down | 
     * @method getLongDescription()
     */
    public String getLongDescription() {
        return "You are " + getDescription() + ".\n" + getExitString() + "\n" + getItemString() + "\n";
    } //getLongDescription
    
    /**
     * @return "You have eaten now and you are not hungry any more.\n"
     * @method getEat()
     */
    public String getEat() {
        return "You have eaten now and you are not hungry anymore.\n";
    } //getEat()
    
    //Image Audio
    public String getImageName() {
        return this.aImageName;
    }
    
    public String getAudioName() {
        return this.aAudioName;
    }
    
    public void playAudio() {
        try {
            File audioFile = new File(this.aAudioName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            this.aAudioClip = AudioSystem.getClip();
            this.aAudioClip.open(audioStream);
            this.aAudioClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        
    }

    public void stopAudio() {
        if (this.aAudioClip != null && this.aAudioClip.isRunning()) {
            this.aAudioClip.stop();
        }
    }
    
    //Item
    public void setItem(Item pItem) {
        this.aItem = pItem;
    }
    
    public Item getItem() {
        return this.aItem;
    }
    
    public String getItemString() {
        if(this.aItem != null) {
            return "Object : " + this.aItem.getNameItem()+ " (Weight: " + this.aItem.getPoids() + " )";   
        }else {
            return "No object in this room.";
        }
    } //getExitString 
    
    
} // Room
