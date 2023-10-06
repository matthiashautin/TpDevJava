package tp;

/**
 * Décrivez votre classe PointColore ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PointColore extends Point
{
     private String aCouleur;
     
     public PointColore(final int pX, final int pY, final String pC){
         super(pX,pY);
         this.aCouleur = pC;
     }
     
     public PointColore(final int pX, final int pY) {
         this(pX,pY,"N");
     }
     
     public PointColore() {
         this(10,10,"N");
     }
     
     @Override
     public String toString() {
         return this.aCouleur + ":" + super.toString();
     }
     
     public void affichageDePoint() {
        super.affiche();
     }
     
     
     
}
