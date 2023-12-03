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
    
    public Item(final String pNameItem, final int pPoids) {
        this.aNameItem = pNameItem;
        this.aPoids = pPoids;
    }
    
    public String getNameItem() {
        return this.aNameItem;
    }
    
    public int getPoids() {
        return this.aPoids;
    }
}
