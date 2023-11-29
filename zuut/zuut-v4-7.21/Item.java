//reg
/**
 * This class adds an Item in the room you want
 *
 * @author Mr Hautin Matthias
 * @version 28/11/2023
 */
public class Item
{
    //attributes
    private String aNameItem;
    private int aPoids;
    private String aLongDescription;
    
    /**
     * @constructor Item()
     * @params pNameItem pPoids pLongDescription
     */
    public Item(final String pNameItem, final int pPoids, final String pLongDescription) {
        this.aNameItem = pNameItem;
        this.aPoids = pPoids;
        this.aLongDescription = pLongDescription;
    } //Item()
    
    /**
     * @getters 
     * return NameItem
     */
    public String getNameItem() {
        return this.aNameItem;
    } //getNAmeItem()
    
    /**
     * @getters 
     * return Poids
     */
    public int getPoids() {
        return this.aPoids;
    } //getPoids()
    
    /**
     * @getters
     * return longDescription
     */
    public String getLongDescrptionItem() {
        return this.aLongDescription;
    } //getLongDescription()
    
} //Item()
