

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Method;

/**
 * Classe-test MaPremiereClasseTest.
 *
 * @author  (votre nom)
 * @version (un num�ro de version ou une date)
 *
 * Les classes-test sont document�es ici :
 * http://junit.sourceforge.net/javadoc/junit/framework/TestCase.html
 * et sont bas�es sur le document � 2002 Robert A. Ballance intitul�
 * "JUnit: Unit Testing Framework".
 *
 * Les objets Test (et TestSuite) sont associ�s aux classes � tester
 * par la simple relation yyyTest (e.g. qu'un Test de la classe Name.java
 * se nommera NameTest.java); les deux se retrouvent dans le m?me paquetage.
 * Les "engagements" (anglais : "fixture") forment un ensemble de conditions
 * qui sont vraies pour chaque m�thode Test � ex�cuter.  Il peut y avoir
 * plus d'une m�thode Test dans une classe Test; leur ensemble forme un
 * objet TestSuite.
 * BlueJ d�couvrira automatiquement (par introspection) les m�thodes
 * Test de votre classe Test et g�n�rera la TestSuite cons�quente.
 * Chaque appel d'une m�thode Test sera pr�c�d� d'un appel de setUp(),
 * qui r�alise les engagements, et suivi d'un appel � tearDown(), qui les
 * d�truit.
 */
public class MaPremiereClasseTest
{
    // D�finissez ici les variables d'instance n�cessaires � vos engagements;
    // Vous pouvez �galement les saisir automatiquement du pr�sentoir
    // � l'aide du menu contextuel "Pr�sentoir --> Engagements".
    // Notez cependant que ce dernier ne peut saisir les objets primitifs
    // du pr�sentoir (les objets sans constructeur, comme int, float, etc.).
    protected double fValeur1;
    protected double fValeur2;
    protected boolean bValeur;
    protected String sValeur1;
    protected String sValeur2;

    /**
     * Constructeur de la classe-test MaPremiereClasseTest
     */
    public MaPremiereClasseTest()
    {
    }

    /**
     * Met en place les engagements.
     *
     * M�thode appel�e avant chaque appel de m�thode de test.
     */
    @Before
    public void setUp() // throws java.lang.Exception
    {
        // Initialisez ici vos engagements
        fValeur1= 2.0;
        fValeur2= 3.0;
        bValeur = fValeur1 != fValeur2;
        sValeur1 = "voir";
        sValeur2 = "piocaduia";
    }

    /**
     * Supprime les engagements
     *
     * M�thode appel�e apr�s chaque appel de m�thode de test.
     */
    @After
    public void tearDown() // throws java.lang.Exception
    {
        //Lib�rez ici les ressources engag�es par setUp()
    }

    @Test
    public void m1_procedure()
    {
        MaPremiereClasse maPrem = new MaPremiereClasse();
        maPrem.procedure();
        try {
          Class<?> vMyClass = Class.forName("MaPremiereClasse");
          Method[] vMethods = vMyClass.getDeclaredMethods();
          for ( Method vMethod : vMethods )
            if ( vMethod.getName().equals( sValeur2.replace("i","r").replace("a","e") ) )
              assertEquals( sValeur1.replace("r","d"), vMethod.getReturnType().getName() );
        }
        catch( final Exception pE ) {
            System.err.println( pE );
        }
    }

    @Test
    public void m2_fonction()
    {
        MaPremiereClasse maPrem = new MaPremiereClasse();
        assertEquals(bValeur, maPrem.fonction());
    }
}


