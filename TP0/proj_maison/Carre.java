import java.awt.Rectangle;

/**
 * Un carre qui peut etre manipule et qui se dessine sur une toile (canvas).
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author  mod.by Denis BUREAU
 * @author  Matthias Hautin
 * @version 2006.03.30/2014.09.08/2023.09.15
 */

public class Carre
{
    private int     aCote;
    private int     aXPosition;
    private int     aYPosition;
    private String  aCouleur;
    private boolean aEstVisible;

    /**
     * Cree un nouveau carre avec les position et couleur par defaut.
     */
    public Carre()
    {
        this.aCote=      30;
        this.aXPosition= 60;
        this.aYPosition= 50;
        this.aCouleur=   "red";
        this.aEstVisible= true;
        this.dessine();
    } // Carre()

    /**
     * Rend ce carre visible.
     */
    public void rendVisible()
    {
        this.aEstVisible= true;
        this.dessine();
    } // rendVisible()
    
    /**
     * Rend ce carre invisible.
     */
    public void rendInvisible()
    {
        this.efface();
        this.aEstVisible= false;
    } // rendInvisible()
    
    /**
     * Deplace le carre de quelques pixels vers la droite.
     */
    public void vaDroite()
    {
        this.depHorizontal( 20 );
    } // vaDroite()

    /**
     * Deplace le carre de quelques pixels vers la gauche.
     */
    public void vaGauche()
    {
        this.depHorizontal( -20 );
    } // vaGauche()

    /**
     * Deplace le carre de quelques pixels vers le haut.
     */
    public void vaHaut()
    {
        this.depVertical( -20 );
    } // vaHaut()

    /**
     * Deplace le carre de quelques pixels vers le bas.
     */
    public void vaBas()
    {
        this.depVertical( 20 );
    } // vaBas()

    /**
     * Deplace le carre horizontalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers la droite
     */
    public void depHorizontal( final int pDistance )
    {
        this.efface();
        this.aXPosition= this.aXPosition + pDistance;
        this.dessine();
    } // depHorizontal()

    /**
     * Deplace le carre verticalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers le bas
     */
    public void depVertical( final int pDistance )
    {
        this.efface();
        this.aYPosition= this.aYPosition + pDistance;
        this.dessine();
    } // depVertical()

    /**
     * Deplace lentement le carre horizontalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers la droite
     * @param vDelta variable locale permet de déterminer la direction du déplacement du carré, positive(1) déplacement vers le bas et une valeur négative(-1) déplacement vers le haut.
     */
    public void depLentHorizontal( final int pDistance )
    {
        //Deplacement récursif vertical vers le bas et le haut de l'objet carré    Exercice 3.2.1 .2 
        int vDelta; // le pas unitaire de deplacement
    
        if (pDistance < 0) {
            vDelta = pDistance + 1;
            this.depHorizontal(-1);
            this.dessine();
        } else {
            vDelta = pDistance - 1;
            this.depHorizontal(1);
            this.dessine();
        }
        
        if (vDelta != 0) {
            this.depLentHorizontal(vDelta);
        }
        //this.depHorizontal( pDistance );  // deplacement rapide pour l'instant
    } // depLentHorizontal()

    /**
     * Deplace lentement le carre verticalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers le bas
     * @param vDelta variable locale permet de déterminer la direction du déplacement du carré, positive(1) déplacement vers le bas et une valeur négative(-1) déplacement vers le haut.
     */
    public void depLentVertical( final int pDistance )
    {
        //Deplacement récursif vertical vers le bas de l'objet carré    Exercice 3.2.1 .1 a)
         //if ( pDistance > 0)
         //{
         //    this.depVertical(1);
         //    this.dessine();
         //    this.depLentVertical(pDistance -1);
         //}           
         
        //Deplacement récursif vertical vers le bas et le haut de l'objet carré    Exercice 3.2.1 .1 b)
        int vDelta; // le pas unitaire de deplacement
    
        if (pDistance < 0) {
            vDelta = pDistance + 1;
            this.depVertical(-1);
            this.dessine();
        } else {
            vDelta = pDistance - 1;
            this.depVertical(1);
            this.dessine();
        }
        
        if (vDelta != 0) {
            this.depLentVertical(vDelta);
        }

        // ecrire ici le deplacement recursif :
    //this.depVertical( pDistance );  // deplacement rapide pour l'instant
    } // depLentVertical()

    /**
     * Remplace l'ancienne taille par la nouvelle (en pixels).
     * La taille doit etre >= 0.
     * @param pNouvCote taille du cote souhaitee pour ce carre
     */
    public void changeTaille( final int pNouvCote )
    {
        this.efface();
        this.aCote= pNouvCote;
        this.dessine();
    } // changeTaille()


    /**
     * Change la couleur. Les couleurs valides sont
     * "red", "yellow", "blue", "green", "magenta" and "black".
     * @param pNewColor couleur souhaitee pour ce carre
     */
    public void changeCouleur( final String pNouvCouleur )
    {
        this.aCouleur= pNouvCouleur;
        this.dessine();
    } // changeCouleur()

    /**
     * Dessine sur l'ecran le carre avec ses specifications courantes
     */
    private void dessine()
    {
        if ( this.aEstVisible ) {
            Canvas vCanvas; // classe compliquee a ne pas regarder pour le moment
            vCanvas= Canvas.getCanvas();
            vCanvas.draw(this, this.aCouleur,
                         new Rectangle(this.aXPosition, this.aYPosition,
                                       this.aCote, this.aCote));
            vCanvas.waitALittle();
        } // if
    } // dessine()

    /**
     * Efface le carre sur l'ecran.
     */
    private void efface()
    {
        if ( this.aEstVisible ) {
            Canvas vCanvas;
            vCanvas= Canvas.getCanvas();
            vCanvas.erase( this );
        } // if
    } // efface()

} // Carre
