import java.util.Stack;

/**
 * Décrivez votre classe Player ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Player
{
   private String   aNamePlayer;
   private Room     aCurrentRoom;
   private Stack<Room>  aPreviousRoom;
   private int      aPoidsMax;
   private int      aVie;
   
   public Player(final String pNamePlayer, final Room pCurrentRoom , final int pPoidsMax, final int pVie) {
       this.aNamePlayer = pNamePlayer;
       this.aCurrentRoom = pCurrentRoom;
       this.aPoidsMax = pPoidsMax;
       this.aVie = pVie;
       this.aPreviousRoom = new Stack<Room>();
   } //Player()
   
   public String getNamePlayer() {
       return this.aNamePlayer;   
   } //getNamePlayer()
   
   public Room getCurrentRoom() {
       return this.aCurrentRoom;
   } //getCurrentRoom()
   
   public int getPoidsMax() {
       return this.aPoidsMax;
   } //getPoidsMax()
   
   public int getVie() {
       return this.aVie;
   }
   
    public void setCurrentRoom(final Room pCurrentRoom) {
       this.aCurrentRoom = pCurrentRoom;
   }
   
   
}
