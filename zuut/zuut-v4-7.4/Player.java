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
     * Renvoie tous les objets dans l'inventaire du joueur avec le poid associé
     * Utilise la méthode getItemString de la classe ItemList pour obtenir les Items de l'inventaire 
     * @return Une chaîne de caractères représentant la description détaillée des objets dans l'inventaire
     */
    public String getInventoryItemList() {
        return this.aInventoryItems.getItemString();
    } //getInventoryItemList()

    /**
     * Obtient un Item spécifique de l'inventaire du joueur en fonction de son nom
     * @param pItemName Le nom de l'item à récupérer
     * @return L'item correspondant au nom spécifié, ou null s'il n'est pas trouvé dans l'inventaire
     */
    public Item getItemInInventory(final String pItemName) {
        return this.aInventoryItems.getItem(pItemName);
    } //getItemInInventory()

    /**
     *  return this.aPreviousRoom
     */
    public Stack<Room> getPreviousRoom() {
        return this.aPreviousRoom;
    } //getPreviousRoom()

    /**
     * Obtient un Item spécifique de l'inventaire en fonction de son nom
     * @param pItem Le nom de l'item à récupérer
     * @return L'item correspondant au nom spécifié, ou null s'il n'est pas trouvé dans l'inventaire
     */
    public Item getItem(final String pItem) {
        return this.aInventoryItems.getItem(pItem);
    } //getItem()

    /**
     * Calculer le poids total des objets dans l'inventaire du joueur
     * @return Poids total des objets dans l'inventaire
     */
    public boolean getTotalWeight(Item pItemtotal) {
        return this.aPoidsMax - pItemtotal.getPoids() > 0;
    } //getTotalWeight()

    /**
     * Ajoute la quantité spécifiée de points de vie au joueur
     * @param pVie La quantité de points de vie à ajouter
     */
    public void donneVie(final int pVie) {
        this.aVie += pVie;
    } //donneVie()

    /**
     * Définit la pièce actuelle du joueur
     * @param pCurrentRoom La nouvelle pièce actuelle du joueur
     */
    public void setCurrentRoom(final Room pCurrentRoom) {
        this.aCurrentRoom = pCurrentRoom;
    } //setCurrentRoom()  

    /**
     * Prend un objet de la pièce actuelle et l'ajoute à l'inventaire du joueur
     * Réduit le poids maximal du joueur en fonction du poids de l'objet pris
     * @param pItemName Nom de l'objet à prendre
     * @param pItem Objet à prendre
     */
    public void takeItem(final String pItemName, final Item pItem) {
        this.aInventoryItems.takeItem(pItemName,pItem);
        this.aPoidsMax = this.aPoidsMax - pItem.getPoids();
    } //takeItem()

    /**
     * Pose un objet de l'inventaire du joueur dans la pièce actuelle
     * Retire l'objet de l'inventaire du joueur et ajuste le poids maximal en conséquence
     * @param pItemName Nom de l'objet à poser
     * @param pItem Objet à poser
     */
    public void dropItem(final String pItemName, final Item pItem) {
        this.aInventoryItems.removeItem(pItemName, pItem);
        this.aPoidsMax = this.aPoidsMax + pItem.getPoids();
    } //dropItem()

}
