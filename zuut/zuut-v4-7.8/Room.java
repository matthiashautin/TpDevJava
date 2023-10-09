import java.util.HashMap;

/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author Hautin Matthias
 */
public class Room
{
    private HashMap<String, Room> aExits;
    private String aDescription;
    
    /**
     * initialize all attribute
     * @Constructor Room() use for create new room
     * @param String pDescription intialize Room descrioption 
     */
    public Room(final String pDescription) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
    } // Room()
    
    /**
     * return aDescription attribute, String, getter
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
     * return all directions relating to room , Room, getter
     * @method getExit()
     * @params String pDirection given all direction for next room else null if direction is null
     */
    public Room getExit(final String pDirection) {
        return this.aExits.get(pDirection);
    } //getExit()

} // Room
