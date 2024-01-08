/**
 * Décrivez votre classe png ici.
 *
 * @author Hautin Matthias 
 * @version 08/01/24
 */
public class Png
{
    private String       aNamePng;
    private Room         aCurrentRoom;
    private int          aVie;
    private int          aDegat;

    public Png(final String pNamePng, final Room pCurrentRoom, final int pVie, final int pDegat) {
        this.aNamePng = pNamePng;
        this.aCurrentRoom = pCurrentRoom;
        this.aVie = pVie;
        this.aDegat = pDegat;
    } //Png()

    public String getNamePng() {
        return this.aNamePng;
    }

    public int getVie() {
        return this.aVie;
    }

    public int getDegat() {
        return this.aDegat;
    }

    public void prendDegat(final int pVie) {
        this.aVie -= pVie;
    }


}
