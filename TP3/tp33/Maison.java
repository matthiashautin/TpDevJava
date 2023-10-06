
/**
 * Décrivez votre classe Oiseaux ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Maison
{
    private int aNombrePiece;
    private int aSuperficie;
    private boolean aSalleDeSport;

        public Maison() {
            this(5,150,false);
        }
        
        public Maison(final int pNbP, final int pSup, final boolean pSalSpt) {
            this.aNombrePiece = pNbP;
            this.aSuperficie = pSup;
            this.aSalleDeSport = pSalSpt;
        }
        
        
        public void setaNombrePiece(final int pNbp) {
            this.aNombrePiece = pNbp;
        }
        

}
