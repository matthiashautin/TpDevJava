 
/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author votre nom
 */
public class Room
{
    public Room aNorthExit;
    public Room aSouthExit;
    public Room aWestExit;
    public Room aEastExit;    
    
    private String aDescription;
    
    public Room(final String pDescription) {
        this.aDescription = pDescription;
    } // Room()
    
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
    
    public String getDescription() {
        return this.aDescription;
    } //getDescription()
    
} // Room
