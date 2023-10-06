package tp;


/**
 * Décrivez votre classe Utilisation ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Utilisation extends Point
{
    public static void essai() {
        Point vPoint = new Point(15,15);
        System.out.println(vPoint);
        
        PointColore vPointColoreRed = new PointColore(23,12,"R");
        vPointColoreRed.affiche(); 
        
        vPointColoreRed.deplace(10,10);
        vPointColoreRed.affiche();
        
        Point vPointEqual = new Point(15,15);
        if(vPoint.equals(vPointEqual)) {
            System.out.println("ca fonctionne");
            
        } else {
            System.out.println("non");
        }
        
        PointColore vPointEqualColor = new PointColore(23,12,"R");
        if(vPointColoreRed.equals(vPointEqualColor)) {
            System.out.println("ca fonctionne");
            
        } else {
            System.out.println("non");
        }
         
    }
}
