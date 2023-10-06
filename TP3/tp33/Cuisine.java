
/**
 * Décrivez votre classe Cuisine ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Cuisine extends Maison 
{
        private int aNombresRangements;
        private String aNomDeLaCuisine;
        
        public Cuisine(final int pNbP, final int pSup, final boolean pSalSpt, final int pNBR, final String pNDLC) {
            super(pNbP,pSup,pSalSpt);
            this.aNombresRangements = pNBR;
            this.aNomDeLaCuisine = pNDLC;
        }
        
    
        public void essai() {
            Maison vr = new Cuisine(18, 300, true, 5,"Fifi");
            
            System.out.println("Voici les caractéristiques de la cuisine: " + this.aNombresRangements );
        }
}
