import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collection;

/**
 * player permet la création de nouveaux joueur et aussi d'ennemi
 *
 * @author Matthias Hautin
 * @version 11/12/2023
 */
public class Player
{
    private String       aNamePlayer;
    private Room         aCurrentRoom;
    private Stack<Room>  aPreviousRoom;
    private int          aPoidsMax;
    private int          aVie;
    private ItemList     aInventoryItems;

    /**
     * initialize all attribute
     * @param pNamePlayer (String)
     * @param pCurrentRoom (Room)
     * @param pPoidsMax (int)
     * @param pVie (int)
     */
    public Player(final String pNamePlayer, final Room pCurrentRoom , final int pPoidsMax, final int pVie) {
        this.aNamePlayer = pNamePlayer;
        this.aCurrentRoom = pCurrentRoom;
        this.aPoidsMax = pPoidsMax;
        this.aVie = pVie;
        this.aPreviousRoom = new Stack<Room>();
        this.aInventoryItems = new ItemList();
    } //Player()

    /** 
     *  @return this.aNamePlayer
     */
    public String getNamePlayer() {
        return this.aNamePlayer;   
    } //getNamePlayer()

    /** 
     *  @return this.aCurrentRoom
     */
    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    } //getCurrentRoom()

    /** 
     *  @return this.aPoidsMax
     */
    public int getPoidsMax() {
        return this.aPoidsMax;
    } //getPoidsMax()

    /**
     *  @return this.aVie
     */
    public int getVie() {
        return this.aVie;
    } //getVie()

    /**
     * Renvoie une description détaillée de tous les objets dans l'inventaire du joueur.
     * Utilise la méthode getItemLongDescription de la classe ItemList pour obtenir
     * les descriptions longues de chaque objet présent dans l'inventaire.
     *
     * @return Une chaîne de caractères représentant la description détaillée des objets dans l'inventaire.
     */
    public String getInventoryItemList() {
        return this.aInventoryItems.getItemString();
    }

    /**
     * Obtient un objet spécifique de l'inventaire du joueur en fonction de son nom.
     *
     * @param pItemName Le nom de l'objet à récupérer.
     * @return L'objet correspondant au nom spécifié, ou null s'il n'est pas trouvé dans l'inventaire.
     */
    public Item getItemInInventory(final String pItemName) {
        return this.aInventoryItems.getItem(pItemName);
    }

    /**
     *  return this.aPreviousRoom
     */
    public Stack<Room> getPreviousRoom() {
        return this.aPreviousRoom;
    } //getPreviousRoom()

    public Item getItem(final String pItem) {
        return this.aInventoryItems.getItem(pItem);
    }

    /**
     * Calculer le poids total des objets dans l'inventaire du joueur.
     * @return Poids total des objets dans l'inventaire.
     */
    public boolean getTotalWeight(Item pItemtotal) {
        return this.aPoidsMax - pItemtotal.getPoids() > 0;
    }

    public void donneVie(final int pVie) {
        this.aVie += pVie;
    }

    /**
     *  @param pCurrentRoom 
     */
    public void setCurrentRoom(final Room pCurrentRoom) {
        this.aCurrentRoom = pCurrentRoom;
    } //setCurrentRoom()  

    /**
     * Prendre un objet de la pièce actuelle et l'ajouter à l'inventaire du joueur.
     * @param itemName Nom de l'objet à prendre.
     * @return true si l'objet a été pris avec succès, false sinon.
     */
    public void takeItem(final String pItemName, final Item pItem) {
        this.aInventoryItems.takeItem(pItemName,pItem);
        this.aPoidsMax = this.aPoidsMax - pItem.getPoids();
    }

    /**
     * Poser un objet de l'inventaire du joueur dans la pièce actuelle.
     * @return true si l'objet a été posé avec succès, false sinon.
     */

    public void dropItem(final String pItemName, final Item pItem) {
        this.aInventoryItems.removeItem(pItemName, pItem);
        this.aPoidsMax = this.aPoidsMax + pItem.getPoids();
    }

}
