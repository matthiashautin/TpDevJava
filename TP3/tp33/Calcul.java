  
/**
 * Décrivez votre classe Calcul ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Calcul
{
   public static void expressions() {
       System.out.println("(2 + 3) / 4 = " + ((2 + 3) / 4));
       System.out.println("(2.0 + 3) / 4 = " + ((2.0 + 3) / 4));
       System.out.println("(2 + 30E-1) / 4 = " + ((2 + 30E-1) / 4));
       System.out.println("11 % 4 = " + (11 % 4));
       System.out.println("Math.cos(Math.PI/4)== (Math.sin(Math.PI/4))");
       System.out.println(Math.cos(Math.PI/4)== (Math.sin(Math.PI/4)));
       
   } // expressions()
   
   public static double recNeg(final double pX) {
       if(pX >= 0.0 ) 
           return Math.sqrt(pX);
           return -Math.sqrt(-pX);
       
   } //recNeg()
   
   public static void aficheMoities(final int pN) {
       int vN = pN;
       while(vN >= 1) {
           System.out.println(vN);
           vN = vN / 2;
       }
   } // aficheMoities
   
   public static boolean sontProches(final double pPremier, final double pDeuxieme ) {
       return Math.abs(pPremier - pDeuxieme ) < 1E-9;
   }
   
}
