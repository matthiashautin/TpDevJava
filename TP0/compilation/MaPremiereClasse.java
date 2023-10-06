/**
 * MaPremiereClasse pour experimenter les erreurs de compilation.
 * 
 * @author Denis BUREAU 
 * @version 2010.02.1
 */
public class MaPremiereClasse
{

  // ### Attributs ###
  private int     aPremierAttribut;
  private boolean aDeuxiemeAttribut;
  private String  aTroisiemeAttribut;
  
  // ### Constructeurs ###
  
  /**
   * Construit toujours le meme objet : 2, true, "exemple"
   */
  public MaPremiereClasse()
  {
      this.aPremierAttribut   = 2;
      this.aDeuxiemeAttribut  = true;
      this.aTroisiemeAttribut = "exemple";
  } // MaPremiereClasse()
  
  /**
   * Constructeur naturel
   * @param pI entier  pour initialiser aPremierAttribut
   * @param pB booleen pour initialiser aDeuxiemeAttribut
   * @param pS chaine  pour initialiser aTroisiemeAttribut
   */
  public MaPremiereClasse( final int pI, final boolean pB, final String pS )
  {
      this.aPremierAttribut   = pI;
      this.aDeuxiemeAttribut  = pB;
      this.aTroisiemeAttribut = pS;
  } // MaPremiereClasse()
  
  // ### Accesseurs ###
  
  public int     getPremierAttribut()   { return this.aPremierAttribut;   }
  public boolean getDeuxiemeAttribut()  { return this.aDeuxiemeAttribut;  }
  public String  getTroisiemeAttribut() { return this.aTroisiemeAttribut; }
  
  // ### Modificateurs ###
  
  public void setPremierAttribut(   final int     pPremierAttribut )
      { this.aPremierAttribut   = pPremierAttribut; }
  public void setDeuxiemeAttribut(  final boolean pDeuxiemeAttribut )
      { this.aDeuxiemeAttribut  = pDeuxiemeAttribut; }
  public void setTroisiemeAttribut( final String  pTroisiemeAttribut )
      { this.aTroisiemeAttribut = pTroisiemeAttribut; }

  // ### Autres methodes ###
  
  /**
   * Un exemple de procedure qui provoque une erreur de compilation
   */
  public void procedure()
  {
    System.out.println( "La procedure s'est bien executee." );
    return ;
  } // procedure()

  /**
   * Un exemple de fonction booleenne qui provoque une erreur de compilation
   * @return toujours true
   */
  public boolean fonction()
  {
    return true;
  } // fonction()

} // MaPremiereClasse
