import java.util.Stack;

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
   
   public Player(final String pNamePlayer, final Room pCurrentRoom , final int pPoidsMax, final int pVie) {
       this.aNamePlayer = pNamePlayer;
       this.aCurrentRoom = pCurrentRoom;
       this.aPoidsMax = pPoidsMax;
       this.aVie = pVie;
       this.aPreviousRoom = new Stack<Room>();
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
   
}
