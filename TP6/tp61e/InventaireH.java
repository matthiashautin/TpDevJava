import java.util.HashMap;
import java.util.Iterator;

public class InventaireH
{

    private HashMap<String,Item> aMap;
    private int aPrixTotal;
    
    public InventaireH(){
        this.aMap = new HashMap<String,Item>();
        this.aPrixTotal = 0;
        
    }
    
    /**
     * pour tous les items de la liste 
     * return l'item si le paramètre créé(pItem) est égale à un des items de la liste créé
     */
    public Item getItem(final String pItem) {
        return this.aMap.get(pItem);
    } //getItem()
    
    public boolean contientItem(final String pItem) {
      return this.getItem(pItem) != null;
    } //contientItem()
    
    public void ajouteItem(final String pNom, final int pPrix) {
        if(contientItem(pNom) == false) {
            Item vItem = new Item(pNom,pPrix);
            this.aMap.put(pNom, vItem);
            this.aPrixTotal += pPrix;
        } else {
            return;
        }        
    }
    
    public void enleveItem(final String pNom) {
        Item vItem = this.aMap.remove(pNom);
        this.aPrixTotal = this.aPrixTotal - vItem.getPrix();
    }
    
    @Override
    public String toString() {
        return this.aMap.toString() + " : " + this.aPrixTotal;
    }
    
    //return this.aListe.toString() + " : " + this.aPrixTotal;
    //toString()

} // InventaireH
