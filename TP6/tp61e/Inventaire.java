import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Inventaire
{
    private List<Item> aListe;
    private int aPrixTotal;
    
    public Inventaire(){
        this.aListe = new ArrayList<Item>();
        this.aPrixTotal = 0;
        
    }
    
    /**
     * pour tous les items de la liste 
     * return l'item si le paramètre créé(pItem) est égale à un des items de la liste créé
     */
    public Item getItem(final String pItem) {
        for(Item vItem : this.aListe) {
            if(pItem.equals(vItem.getNom())) {
                return vItem;
            } 
        } 
        return null;
    } //getItem()
    
    public boolean contientItem(final String pItem) {
        if (getItem(pItem)!=null) {
            return true;            
        } else {
            return false;
        }
    } //contientItem()
    
    public void ajouteItem(final String pNom, final int pPrix) {
        if(contientItem(pNom) == false) {
            Item vItem = new Item(pNom,pPrix);
            this.aListe.add(vItem);
            this.aPrixTotal += pPrix;
        } else {
            return;
        }        
    }
    
    public void enleveItem(final String pNom) {
        Iterator<Item> vIt = this.aListe.iterator();
        while ( vIt.hasNext() ) {
          Item vItem = vIt.next();
          if(vItem.getNom() == pNom) {
            vIt.remove();
            this.aPrixTotal -= vItem.getPrix();
          }
        } 
    }
    
    @Override
    public String toString() {
        return this.aListe.toString() + " : " + this.aPrixTotal;
    } //toString()

} // Inventaire
