/**
 * This class allows you to create an item from any room 
 * 
 * @author Matthias Hautin  
 * @version 25/11/23
 * 
 */
public class Item
{
    private String aNameItem;
    private int aPoids;
    private String aLongDescription;
    
    //contructor
    public Item(final String pNameItem, final int pPoids, final String pLongDescription) {
        this.aNameItem = pNameItem;
        this.aPoids = pPoids;
        this.aLongDescription = pLongDescription;
    } //Item()
    
    /**
     * @return aNameItem
     */
    public String getNameItem() {
        return this.aNameItem;
    } //getImageName()
    
    /**
     * @return aPoids
     */
    public int getPoids() {
        return this.aPoids;
    } //getPoids()
    
    /**
     * @return aLongDescription
     */
    public String getLongDescrptionItem() {
        return this.aLongDescription;
    } //getLongDescription()
    
} //Item()
