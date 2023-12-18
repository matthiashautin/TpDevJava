import java.util.HashMap;

/**
 * Décrivez votre classe ItemList ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class ItemList
{
    private HashMap<String, Item> aItemList;
    
    public ItemList() {
        this.aItemList = new  HashMap<String, Item>();
    } //ItemList()
    
    public void setItemsList(final String pItemName, final Item pItem) {
        this.aItemList.put(pItemName, pItem);
    } //setItem()
    
    /**
     * @return "Object : " + this.aItem.getNameItem()+ " (Weight: " + this.aItem.getPoids() + " )" if exist an Item 
     */
    public String getItemString() {
       if(!this.aItemsList.isEmpty()){
           StringBuilder vItemsString = new StringBuilder("Items: ");
                for (Item vItem : this.aItemsList.values()) {
                vItemsString.append(vItem.getNameItem()).append(" (Weight: ").append(vItem.getPoids()).append("), ");
                }
                return vItemsString.substring(0, vItemsString.length() - 2); // Pour enlever la virgule finale
           } else {
           return "No objects in this room.";
       }
    } //getItemString()
    
}
