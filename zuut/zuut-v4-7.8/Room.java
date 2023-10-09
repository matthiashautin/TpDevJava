import java.util.HashMap;

/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author votre nom
 */
public class Room
{
    private HashMap<String, Room> aExits;
    private String aDescription;
    
    public Room(final String pDescription) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
    } // Room()
    
    public String getDescription() {
        return this.aDescription;
    } //getDescription()
    
    /**
     * define the exits for the all rooms. All direction if its possible, if that rooms egal null no exits here 
     * @method setExits(Room pNorthExit,Room pSouthExit,Room pWestExit,Room pEastExit)
     */
    public void setExits(final String pDirection, final Room pNeighbor) {
        this.aExits.put(pDirection,pNeighbor);
    } //setExits()
    
    /**
     * return all attribute relative to the direction
     * @method getExit(String pDirection)
     */
    public Room getExit(final String pDirection) {
        return this.aExits.get(pDirection);
    } //getExit()


} // Room
