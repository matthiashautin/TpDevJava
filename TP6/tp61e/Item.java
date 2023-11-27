/**
 * TP6
 * @author DB
 */
public class Item
{
    private String aNom;
    private int    aPrix;

    public Item( final String pNom, final int pPrix )
    {
        this.aNom  = pNom;
        this.aPrix = pPrix;
    } // Item(..)

    public String getNom()
    {
        return this.aNom;
    } // getNom()

    public int getPrix()
    {
        return this.aPrix;
    } // getPrix()

    @Override 
    public String toString() {
        return  this.aNom + "(" + this.aPrix + ")";
    }
    
} // Item
