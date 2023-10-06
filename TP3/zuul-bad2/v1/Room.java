package v1;
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
    
    public String getDescription() {
        return this.aDescription;
    } //getDescription()
    
    public void setExits(final Room pNorthExit, final Room pSouthExit, final Room pWestExit, final Room pEastExit) {
        this.aNorthExit = pNorthExit;
        this.aSouthExit = pSouthExit;
        this.aWestExit = pWestExit;
        this.aEastExit = pEastExit;
    }
    
} // Room
