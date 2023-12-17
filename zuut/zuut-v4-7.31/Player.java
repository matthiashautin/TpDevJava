import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

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
   private List<Item>   aInventoryItems;
   
   public Player(final String pNamePlayer, final Room pCurrentRoom , final int pPoidsMax, final int pVie) {
       this.aNamePlayer = pNamePlayer;
       this.aCurrentRoom = pCurrentRoom;
       this.aPoidsMax = pPoidsMax;
       this.aVie = pVie;
       this.aPreviousRoom = new Stack<Room>();
       this.aInventoryItems = new ArrayList<Item>();
   } //Player()
   
   /** getter
    *  return this.aNamePlayer
    */
   public String getNamePlayer() {
       return this.aNamePlayer;   
   } //getNamePlayer()
   
   /** getter
    *  return this.aCurrentRoom
    */
   public Room getCurrentRoom() {
       return this.aCurrentRoom;
   } //getCurrentRoom()
   
   /** getter
    *  return this.aPoidsMax
    */
   public int getPoidsMax() {
       return this.aPoidsMax;
   } //getPoidsMax()
   
   /** getter
    *  return this.aVie
    */
   public int getVie() {
       return this.aVie;
   } //getVie()
   
   /** getter
    *  return this.aPreviousRoom
    */
   public Stack<Room> getPreviousRoom() {
       return this.aPreviousRoom;
   } //getPreviousRoom()
    
   /** setter
    *  @param pCurrentRoom 
    */
   public void setCurrentRoom(final Room pCurrentRoom) {
       this.aCurrentRoom = pCurrentRoom;
   } //setCurrentRoom() 
   
   
   
   public List<Item> getInventoryItems() {
       return this.aInventoryItems;
   } //getInventoryItems()
   
   /**
     * Prendre un objet de la pièce actuelle et l'ajouter à l'inventaire du joueur.
     * @param itemName Nom de l'objet à prendre.
     * @return true si l'objet a été pris avec succès, false sinon.
     */
   public boolean take(final String pItemName) {
       Item vItem = this.aCurrentRoom.removeItem(pItemName);

       if (vItem != null && (getPoidsMax() - vItem.getPoids())>= 0) {
           if (getTotalWeight() + vItem.getPoids() <= getPoidsMax()) {
                this.aInventoryItems.add(vItem);
                this.aPoidsMax = this.aPoidsMax - vItem.getPoids();
                return true;
           } else {
                // L'objet est trop lourd pour être porté.
                this.aCurrentRoom.setItems(pItemName, vItem); // Remettre l'objet dans la pièce.
           }
       }
       return false; // L'objet n'a pas été trouvé dans la pièce.
   }
    
   /**
     * Poser un objet de l'inventaire du joueur dans la pièce actuelle.
     * @return true si l'objet a été posé avec succès, false sinon.
     */
   public boolean drop(String pItemName) {
       if (this.aItem != null && this.aItem.getNameItem().equalsIgnoreCase(pItemName)) {
           this.aCurrentRoom.setItems(pItemName, this.aItem);
           this.aPoidsMax = this.aPoidsMax + this.aItem.getPoids();
           aItem = null;
           return true;
       }

       return false; // L'objet n'a pas été trouvé dans l'inventaire du joueur.
   }
   
   /**
     * Calculer le poids total des objets dans l'inventaire du joueur.
     * @return Poids total des objets dans l'inventaire.
     */
   private int getTotalWeight() {
       if (aItem != null) {
           return aItem.getPoids();
       } else {
           return 0;
       }
   }
    
}
