/**
 * Cette classe represente un simple dessin. Vous pouvez l'afficher en appelant
 * la methode dessine(). Mais il y a mieux : comme c'est un dessin electronique,
 * vous pouvez facilement le modifier. Par exemple, le passer en noir et blanc,
 * puis le remettre en couleurs (seulement apres l'avoir dessine, bien sur).
 *
 * (ecrite pour servir d'exemple au debut de l'apprentissage de Java avec BlueJ)
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author  mod.by Denis BUREAU
 * @author  Matthias Hautin
 * @version 2006.03.30/2012.02.06/2023.09.15
 */
public class Maison
{
    private Carre    aMur;
    private Carre    aFenetre;
    private Triangle aToit;
    private Cercle   aSoleil;
    private Cercle   aSoleil1;
    private boolean  aDejaPlace;

    /**
     * Constructor for objects of class Picture
     */
    public Maison()
    {
        this.aDejaPlace= false; // place() is needed
        
        this.aMur= new Carre();
        this.aMur.changeTaille( 100 );
        
        this.aFenetre= new Carre();
        this.aFenetre.changeCouleur( "black" );

        this.aToit= new Triangle();  
        this.aToit.changeTaille( 140, 50 );

        this.aSoleil= new Cercle();
        this.aSoleil.changeCouleur( "yellow" );
        this.aSoleil.changeTaille( 60 );
        
        this.aSoleil1= new Cercle();
        this.aSoleil1.changeCouleur( "green" );
        this.aSoleil1.changeTaille( 60 );
        
        this.dessine();
        this.place();
    } // Picture()
    

    /**
     * Draw this picture.
     */
    public void dessine()
    {
        this.aMur.rendVisible();
        this.aFenetre.rendVisible();
        this.aToit.rendVisible();
        this.aSoleil.rendVisible();
    } // dessine()

    /**
     * Slowly move the elements to their place.
     */
    public void place()
    {
        if ( ! aDejaPlace ) {
            this.aMur.depLentVertical(80);
            this.aFenetre.depLentHorizontal(20);
            this.aFenetre.depLentVertical(100);
            this.aToit.depLentHorizontal(60);
            this.aToit.depLentVertical(80);
            this.aSoleil.depLentHorizontal(180);
            this.aSoleil.depLentVertical(-10);
            this.aSoleil1.depLentHorizontal(20);
            this.aSoleil1.depLentVertical(-30);
            this.aDejaPlace= true;
        }
    } // place()

    /**
     * Erase the house from this picture.
     */
    public void effaceMaison()
    {
        this.aMur.rendInvisible();
        this.aFenetre.rendInvisible();
        this.aToit.rendInvisible();
    } // effaceMaison()

    /**
     * Change this picture to black/white display
     */
    public void metNoirEtBlanc()
    {
        if (this.aMur != null) { // only if it's painted already...
            this.aMur.changeCouleur(  "black");
            this.aFenetre.changeCouleur("white");
            this.aToit.changeCouleur(  "black");
            this.aSoleil.changeCouleur(   "black");
            this.aSoleil1.changeCouleur(   "black");
        } // if
        else {}
    } // metNoirEtBlanc()

    /**
     * Change this picture to use color display
     */
    public void metCouleurs()
    {
        if (this.aMur != null) { // only if it's painted already...
            this.aMur.changeCouleur(  "red"   );
            this.aFenetre.changeCouleur("black" );
            this.aToit.changeCouleur(  "green" );
            this.aSoleil.changeCouleur(   "yellow");
            this.aSoleil1.changeCouleur(   "green");
        } // if
        else {}
    } // metCouleurs()
    
    /**
     * Change this picture to use color display
     * @param variables locales vPositionSoleil et vPositionSoleil1 permettent de récuperer la position X et Y des deux soleils et les stockes en mémoire
     * @return donne la position X et Y des deux soleils
     */
    /* Exercice 3.1.5a
    private String getPositionSoleil(final Cercle pCercle)
    {
        int vXCercle = pCercle.getPosition() / 1000;
        int vYCercle = pCercle.getPosition() % 1000;
        return "x=" +vXCercle + "y=" +vYCercle; 
    } //getPositionSoleil
    */ 
    
    /**
     * @param vXCercle et vYCercle stocke en mémoire le quotient et le reste de l'objet Cercle
     * @return pNomSoleil donne la valeur de X et Y selon vXCerlce et VYCercle
     */
    // Exercice 3.1.5.b   
    private String getPositionSoleil(final Cercle pCercle, final String pNomSoleil)
    {
        int vXCercle = pCercle.getPosition() / 1000;
        int vYCercle = pCercle.getPosition() % 1000;
              
        return pNomSoleil + "x=" +vXCercle + " y=" +vYCercle; 
    } //getPositionSoleil
    
    /**
     * @param variables locales vPositionSoleil et vPositionSoleil1 permettent de récuperer la position X et Y des deux soleils et les stockes en mémoire
     * @return donne la position X et Y des deux soleils
     */
    
    public String getPositionsDeuxSoleils() 
    {   
        String vPositionSoleil = getPositionSoleil(this.aSoleil, " Soleil ");
        String vPsoitionSoleil1 = getPositionSoleil(this.aSoleil1, " Soleil1 ");
        
        return vPositionSoleil + " | " + vPsoitionSoleil1;
        /*
        int vXSoleil = aSoleil.getPosition() / 1000; // CercleX // permet de renvoyer le quotient de la division, Calcule = this.aXPosition * 1000 + this.aYPosition / 1000 + aSoleilX; 
        int vYSoleil = aSoleil.getPosition() % 1000; // CercleY // permet de renvoyer le reste de la division, Calcule = this.aXPosition * 1000 + this.aYPosition % 1000 + aSoleilY;
        
        int vXSoleil1 = aSoleil1.getPosition() / 1000; // Cercle1 
        int vYSoleil1 = aSoleil1.getPosition() % 1000; // Cercle1 
        
        return "x=" + vXSoleil + ", y=" + vYSoleil + "  |  " + "x=" + vXSoleil1 + ", y=" + vYSoleil1;
        */
    } // getPositionsDeuxSoleils
    
} // Maison
