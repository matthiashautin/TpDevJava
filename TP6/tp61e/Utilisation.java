/**
 * TP6
 * @author DB
 */
public abstract class Utilisation
{
    public static void essai() {
        Inventaire vInv = new Inventaire();
        System.out.println("Voici l'inventaire : " + vInv.toString());
        
        vInv.ajouteItem("X", 1);
        System.out.println("Voici l'inventaire après l'ajout d'un Item: " + vInv.toString());
        
        vInv.ajouteItem("X", 4);
        System.out.println("Voici l'inventaire après l'ajout du même Item: " + vInv.toString());
        
        vInv.ajouteItem("Y", 10);
        System.out.println("Voici l'inventaire après l'ajout d'un autre Item: " + vInv.toString());
        
        vInv.enleveItem("A");
        System.out.println("Voici l'inventaire après avoir enlever un Item non existant: " + vInv.toString());
        
        vInv.enleveItem("X");
        System.out.println("Voici l'inventaire après avoir enlever un Item X: " + vInv.toString());
        
        vInv.enleveItem("Y");
        System.out.println("Voici l'inventaire après avoir enlever un Item Y: " + vInv.toString());
        
        vInv.enleveItem("Y");
        System.out.println("Voici l'inventaire après avoir enlever un Item Y une deuxièmre fois: " + vInv.toString());
    }
    
    
} // Utilisation
