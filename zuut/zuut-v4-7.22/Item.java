//reg
/**
 * Décrivez votre classe Item ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Item
{
    private String aNameItem;
    private int aPoids;
    private String aLongDescription;
    
    public Item(final String pNameItem, final int pPoids, final String pLongDescription) {
        this.aNameItem = pNameItem;
        this.aPoids = pPoids;
        this.aLongDescription = pLongDescription;
    }
    
    public String getNameItem() {
        return this.aNameItem;
    }
    
    public int getPoids() {
        return this.aPoids;
    }
    
    public String getLongDescrptionItem() {
        return this.aLongDescription;
    }
}
