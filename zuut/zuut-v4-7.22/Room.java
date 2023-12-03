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
    private HashMap<String, Item> aItems;
    
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
        this.aItems = new HashMap<String, Item>() ;
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
     * @return getDescription() and getExitString() and getItemString and getItemLongDescription to avoid calling its 4 getters in printLocationInfo()
     * print for example:
     * You are in main pilot room.
     *   Exits:  | down | 
     *   Object : Talkie Walkie (Weight: 2 )
     *   Item Talkie Walkie description : Le talkie Walkie vous permetra de toujours être connecté avec Lara ...
     * @method getLongDescription()
     */
    public String getLongDescription() {
        return "You are " + getDescription() + ".\n" + getExitString() + "\n" + getItemString() + "\n" + getItemLongDescription() + "\n";
    } //getLongDescription
    
    /**
     * @return "You have eaten now and you are not hungry any more.\n"
     * @method getEat()
     */
    public String getEat() {
        return "You have eaten now and you are not hungry anymore.\n";
    } //getEat()
    
    /**
     * @return aImageName
     * @method getImageName()
     */
    public String getImageName() {
        return this.aImageName;
    } //getImageName()
    
    /**
     * @return aAudioName
     * @method getAudioName()
     */
    public String getAudioName() {
        return this.aAudioName;
    } //getAudioName()
    
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
    
    

    //ITEMSsss
    /**
     * define the items that are given to a room.
     * @method setItem()
     * @param pItem (string, int, string) of Item 
     */
    public void setItem(final String pItemName, final Item pItem) {
        this.aItems.put(pItemName, pItem);
    } //setItem()
    
    // Récupérer un objet de la pièce
    public Item getItem(String pItemName) {
        return this.aItems.get(pItemName);
    }

    // Autres méthodes pour obtenir des informations sur les objets dans la pièce
    public String getAllItemNames() {
        Set<String> vItemName = this.aItems.keySet();
        return String.join(", ", vItemName);
    }
    
    /**
     * @return "Object : " + this.aItem.getNameItem()+ " (Weight: " + this.aItem.getPoids() + " )" if exist an Item 
     * @method getItemString()
     */
    public String getItemString() {
       if(this.aItems != null){
        StringBuilder vItemsString = new StringBuilder("Objects: ");
        for (Item vItem : this.aItems.values()) {
            vItemsString.append(vItem.getNameItem()).append(" (Weight: ").append(vItem.getPoids()).append("), ");
        }
        return vItemsString.substring(0, vItemsString.length() - 2); // Pour enlever la virgule finale
    } else {
        return "No objects in this room.";
    }

    } //getExitString 

    /**
     * @return "Item " + this.aItem.getNameItem() + " description : "  + this.aItem.getLongDescrptionItem(); 
     * @method getItemLongDescription()
     */
    public String getItemLongDescription() {
        StringBuilder vItemsDescription = new StringBuilder("Item descriptions:\n");
        if (this.aItems != null) {
            for (Item vItem : this.aItems.values()) {
                vItemsDescription.append(vItem.getNameItem()).append(": ").append(vItem.getLongDescrptionItem()).append("\n");
            }
        } else {
            vItemsDescription.append("No objects in this room.");
        }
    
        return vItemsDescription.toString();
    } //getItemLongDescription 
    
    
} // Room
