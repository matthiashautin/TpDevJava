
/**
 * D�crivez votre classe Vide ici.
 *
 * @author (votre nom)
 * @version (un num�ro de version ou une date)
 */
public class Vide
{
    // variables d'instance - remplacez l'exemple qui suit par le v�tre
    private int x;

    /**
     * Constructeur d'objets de classe Vide
     */
    public Vide()
    {
        // initialisation des variables d'instance
        x = 0;
    }

    /**
     * Un exemple de m�thode - remplacez ce commentaire par le v�tre
     *
     * @param  y   le param�tre de la m�thode
     * @return     la somme de x et de y
     */
    public int sampleMethod(int y)
    {
        // Ins�rez votre code ici
        return x + y;
    }
          
      public long factoriel(final int pfactoriel) 
      {
        long vTotale = 1;
        if (pfactoriel != 0) {
            vTotale = pfactoriel* factoriel(pfactoriel -1);
        }
        return vTotale;
      }
    
    
    
}
