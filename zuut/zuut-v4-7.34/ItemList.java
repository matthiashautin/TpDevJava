import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

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

    /**
     * @return aItemList attribute, String, getter
     * @param pItem String
     */
    public Item getItem(final String pItem) {
        return this.aItemList.get(pItem);
    }

    /**
     * Ajoute un objet à la liste d'objets de l'inventaire.
     * Utilise le nom de l'objet comme clé dans la HashMap.
     *
     * @param pItem L'objet de type Item à ajouter à la liste.
     */
    public void setItemList(final Item pItem) {
        this.aItemList.put(pItem.getNameItem(), pItem);
    } //setItem()

    /**
     * Ajoute un objet à la liste d'objets de l'inventaire en spécifiant son nom.
     * Utilisé lorsque le joueur prend un objet dans une pièce et l'ajoute à son inventaire.
     *
     * @param pItemName Le nom de l'objet à ajouter à la liste d'objets.
     * @param pItem L'objet de type Item à ajouter à la liste.
     */
    public void takeItem(final String pItemName, final Item pItem) {
        this.aItemList.put(pItemName, pItem);
    } //takeItem()

    /**
     * Supprimer un objet de la pièce en fonction de son nom.
     * @param itemName Nom de l'objet à supprimer.
     * @return L'objet supprimé, ou null si l'objet n'est pas trouvé.
     */
    public void removeItem(final String pItemName, final Item pItem) {
        this.aItemList.remove(pItemName, pItem);
    } //romeveItem

    /**
     * @return "Object : " + this.aItem.getNameItem()+ " (Weight: " + this.aItem.getPoids() + " )" if exist an Item 
     */
    public String getItemString() {
        if(!this.aItemList.isEmpty()){
            StringBuilder vItemsString = new StringBuilder("Items: ");
            for (Item vItem : this.aItemList.values()) {
                vItemsString.append(vItem.getNameItem()).append(" (Weight: ").append(vItem.getPoids()).append("), ");
            }
            return vItemsString.substring(0, vItemsString.length() - 2); // Pour enlever la virgule finale
        } else {
            return "No objects in this room.";
        }
    } //getItemString()

    /**
     * @return "Item " + this.aItem.getNameItem() + " description : "  + this.aItem.getLongDescrptionItem(); if exist an Item 
     */
    public String getItemLongDescription() {
        StringBuilder vItemsDescription = new StringBuilder("Item descriptions:\n");
        if (!this.aItemList.isEmpty()) {
            for (Item vItem : this.aItemList.values()) {
                vItemsDescription.append(vItem.getNameItem()).append(": ").append(vItem.getLongDescrptionItem()).append("\n");
            }
        } else {
            return "";
        }
        return vItemsDescription.toString();
    } //getItemLongDescription 

}
