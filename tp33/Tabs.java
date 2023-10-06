
/**
 * Utilisation de procedure pour des tableaux
 *
 * @author Mr.Hautin Matthias
 * @version 29/09/2023
 */
public class Tabs
{
    /** @method affTab()
     *  afficher un tableau
     */     
    public static void affTab(final int[] pTab ) {
        for (int vIndice = 0; vIndice < pTab.length ; vIndice++) {
            System.out.println("tab["+vIndice+"] = " + pTab[vIndice]);
        }
    } //affTab()
    
    /** @method affTabInv()
     *  afficher un tableau inverse
     */ 
    public static void affTabInv(final int[] pTabInv) {
        for (int vIndice = pTabInv.length -1 ;  vIndice >= 0 ; vIndice--) {
            System.out.println("tab["+vIndice+"] = " + pTabInv[vIndice]);
        }
    } //affTabInv()

    /** @method initTab()
     *  afficher l'indice de chaque valeur en le multipliant par 2
     */    
    public static void initTab(final int[] pInitTab) {
        for (int vIndice = 0; vIndice < pInitTab.length ; vIndice++) {
            System.out.print("tab["+vIndice+"] = ");
            System.out.println(pInitTab[vIndice]= 2 * vIndice);
        }
    }
    
    /** @method somme()
     *  afficher la somme d'un Tableau
     */
    public static void somme(final int[] pSommeTab) {
        int vSomme = 0;
        for (int vIndice = 0; vIndice < pSommeTab.length ; vIndice++) {
            vSomme = vSomme + pSommeTab[vIndice];
        }
        //return vSomme;
        System.out.println("Somme Tab = " + vSomme);
    } //somme()

    /** @method valeurMinimale()
     *  afficher la valeur minimale d'un tableau
     */
    public static void valeurMinimale(final double[] pValeurTab) {   
        double vValeurMinimale = pValeurTab[0];
        for (int vIndice = 0; vIndice < pValeurTab.length ; vIndice++) {

            if(pValeurTab[vIndice] < vValeurMinimale) {
                vValeurMinimale = pValeurTab[vIndice];
            } 

        }
        System.out.println("la valeur minimale du tableau: " + vValeurMinimale);
    } //valeurMinimale()

    /** @method indiceDuMinimuInt()
     * Pour un Int, afficher l'indice de la valeur minimale, avec le plus petit indice 
     */
    static void indiceDuMinimuInt(final int[] pIndMinTab) {
        int vIndiceMinimum = pIndMinTab[0];

        for (int vIndice = 0; vIndice < pIndMinTab.length ; vIndice++) {
            if(pIndMinTab[vIndice] < pIndMinTab[vIndiceMinimum]) {
                vIndiceMinimum = vIndice;
            }

        } 
        System.out.println("l'indice de la valeur minimale du tableau: "+ vIndiceMinimum);
    }//indiceDuMinimuInt()

    /** @method indiceDuMinimumDouble()
     * Pour un Double, afficher l'indice de la valeur minimale, avec le plus petit indice 
     */

    static void indiceDuMinimumDouble(final double[] pIndMinTab) {
        double vIndiceMinimum = 0;
        double vValeurMinimum = pIndMinTab[0];

        for (int vIndice = 0; vIndice < pIndMinTab.length ; vIndice++) {
            if(pIndMinTab[vIndice] < vValeurMinimum) {
                vValeurMinimum = pIndMinTab[vIndice];
                vIndiceMinimum = vIndice;
            }

        } 
        System.out.println("l'indice de la valeur minimale du tableau: "+ vIndiceMinimum);
    } //indiceDuMinimumDouble()

    public static void essai(){
        int[] vTabInt = {50,6,5,4,5};
        double[] vTabDouble = {33,32,37,35,38,34,29,36,37,28,29,30,36,37,31,29,28,30,28,29,28,37,31,30,33};

        System.out.println("afficher le tableau:");
        affTab(vTabInt);
        System.out.println("//~~~~~~~~~~~~~~~~~~~~~//");

        System.out.println("afficher le tableau inverse:");
        affTabInv(vTabInt);
        System.out.println("//~~~~~~~~~~~~~~~~~~~~~//");

        System.out.println("afficher la somme du tableau:");
        somme(vTabInt);
        System.out.println("//~~~~~~~~~~~~~~~~~~~~~//");

        System.out.println("afficher les valeurs indices *2:");
        initTab(vTabInt);
        System.out.println("//~~~~~~~~~~~~~~~~~~~~~//");

        System.out.println("afficher la valeur minimale du tabelau:");
        valeurMinimale(vTabDouble);
        System.out.println("//~~~~~~~~~~~~~~~~~~~~~//"); 

        System.out.println("afficher la plus petit indice de la valeur minimale:");
        indiceDuMinimumDouble(vTabDouble);

    } //essai()

} //Tabs()
