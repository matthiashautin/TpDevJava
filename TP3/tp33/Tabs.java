
/**
 * Décrivez votre classe Tabs ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Tabs
{
  public static void affTab(final int[] pTab ) {
      for (int vIndice = 0; vIndice < pTab.length ; vIndice++) {
          System.out.println("tab["+vIndice+"] = " + pTab[vIndice]);
      }
  }
  
  public static void affTabInv(final int[] pTabInv) {
      for (int vIndice = pTabInv.length -1 ;  vIndice >= 0 ; vIndice--) {
          System.out.println("tab["+vIndice+"] = " + pTabInv[vIndice]);
      }
  }
  
  public static void initTab(final int[] pInitTab) {
      for (int vIndice = 0; vIndice < pInitTab.length ; vIndice++) {
          System.out.print("tab["+vIndice+"] = ");
          System.out.println(pInitTab[vIndice]= 2 * vIndice);
      }
  }
  
    public static void somme(final int[] pSommeTab) {
      int vSomme = 0;
      for (int vIndice = 0; vIndice < pSommeTab.length ; vIndice++) {
          vSomme = vSomme + pSommeTab[vIndice];
      }
      //return vSomme;
      System.out.println("Somme Tab = " + vSomme);
  }
  
  public static void essai(){
      int[] vTabInt = {50,6,5,4,5};
      
      System.out.println("afficher le tableau:");
      affTab(vTabInt);
      
      System.out.println("afficher le tableau inverse:");
      affTabInv(vTabInt);
      
      
      System.out.println("afficher la somme du tableau:");
      somme(vTabInt);
      
      System.out.println("afficher les valeurs indices *2:");
      initTab(vTabInt);
      

  }
  
  public static int test() {
      int[] vTabs  = new int[8];
      int vNombrDeCase = vTabs.length ;
      return vTabs.length;
  }
  
  public static double test1(final double plocal ) {
      
      return Math.sqrt(plocal);
 
      
  }

}