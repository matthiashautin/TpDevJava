 
/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author votre nom
 */
public class Room
{
    private Room aNorthExit;
    private Room aSouthExit;
    private Room aWestExit;
    private Room aEastExit;    
    
    private String aDescription;
    
    public Room(final String pDescription) {
        this.aDescription = pDescription;
    } // Room()
    
    public String getDescription() {
        return this.aDescription;
    } //getDescription()
    
    /**
     * define the exits for the all rooms. All direction if its possible, if that rooms egal null no exits here 
     * @method setExits(Room pNorthExit,Room pSouthExit,Room pWestExit,Room pEastExit)
     */
    public void setExits(final Room pNorthExit, final Room pSouthExit, final Room pWestExit, final Room pEastExit) {
        if(pNorthExit != null) {
            this.aNorthExit = pNorthExit;
        }
        
        if(pSouthExit != null) {
            this.aSouthExit = pSouthExit;
        }
        
        if(pWestExit != null) {
            this.aWestExit = pWestExit;
        }
        
        if(pEastExit != null) {
           this.aEastExit = pEastExit;
        }
    } //setExits()
    
    /**
     * return all attribute relative to the direction
     * @method getExit(String pDirection)
     */
    public Room getExit(final String pDirection) {
        if(pDirection.equals("north")) {
            return this.aNorthExit;
        }
        
        if(pDirection.equals("south")) {
            return this.aSouthExit;
        }
        
        if(pDirection.equals("west")) {
            return this.aWestExit;
        }
        
        if(pDirection.equals("east")) {
            return this.aEastExit;
        }
        
        return null;
    } //getExit()
    
    /**
     * print different output calls in printLocationInfo() in Game class if output is not null
     * @method 
     */
    public String getExitString() {
        if(this.aNorthExit != null) {
            System.out.print("north ");
        }
        
        if(this.aSouthExit != null) {
            System.out.print("south ");
        }
        
        if(this.aWestExit != null) {
            System.out.print("west ");
        }
        
        if(this.aEastExit != null) {
            System.out.print("east ");
        }

        return null;
    } //getExitString 

} // Room
