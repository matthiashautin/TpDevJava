/**
 * Décrivez votre classe Item ici.
 *
 * @author Matthias Hautin  
 * @version 25/11/23
 * this class allows you to create an item from any room 
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
     * @method getNameItem()
     */
    public String getNameItem() {
        return this.aNameItem;
    } //getImageName()
    
    /**
     * @return aPoids
     * @method getPoids()
     */
    public int getPoids() {
        return this.aPoids;
    } //getPoids()
    
    /**
     * @return aLongDescription
     * @method getLongDescrptionItem()
     */
    public String getLongDescrptionItem() {
        return this.aLongDescription;
    } //getLongDescription()
    
} //Item()
