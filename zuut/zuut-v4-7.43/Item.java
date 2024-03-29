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
    private int aDegat;
    
    /**
     * Initialise un objet de type Item avec un nom, un poids et une description détaillée
     * @param pNameItem        Le nom de l'Item
     * @param pPoids           Le poids de l'Item
     * @param pLongDescription La description détaillée de l'Item
     */
    public Item(final String pNameItem, final int pPoids, final String pLongDescription, final int pDegat) {
        this.aNameItem = pNameItem;
        this.aPoids = pPoids;
        this.aLongDescription = pLongDescription;
        this.aDegat = pDegat;
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
    
    /**
     * @return aDegat
     */
    public int getDegat() {
        return this.aDegat;
    }

} //Item
