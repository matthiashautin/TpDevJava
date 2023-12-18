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

   
   public Player(final String pNamePlayer, final Room pCurrentRoom , final int pPoidsMax, final int pVie) {
       this.aNamePlayer = pNamePlayer;
       this.aCurrentRoom = pCurrentRoom;
       this.aPoidsMax = pPoidsMax;
       this.aVie = pVie;
       this.aPreviousRoom = new Stack<Room>();
       this.aInventoryItems = new ItemList();
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
   
   public Item getItem(final String pItem) {
       return this.aInventoryItems.getItem(pItem);
   }
   
   /** setter
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
}
    
       /**
         * Poser un objet de l'inventaire du joueur dans la pièce actuelle.
         * @return true si l'objet a été posé avec succès, false sinon.
         */
       
    public void dropItem(final String pItemName) {
       this.aInventoryItems.removeItem(pItemName);
}
   
    
   
}
