

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Method;

/**
 * Classe-test MaPremiereClasseTest.
 *
 * @author  (votre nom)
 * @version (un numéro de version ou une date)
 *
 * Les classes-test sont documentées ici :
 * http://junit.sourceforge.net/javadoc/junit/framework/TestCase.html
 * et sont basées sur le document Š 2002 Robert A. Ballance intitulé
 * "JUnit: Unit Testing Framework".
 *
 * Les objets Test (et TestSuite) sont associés aux classes à tester
 * par la simple relation yyyTest (e.g. qu'un Test de la classe Name.java
 * se nommera NameTest.java); les deux se retrouvent dans le m?me paquetage.
 * Les "engagements" (anglais : "fixture") forment un ensemble de conditions
 * qui sont vraies pour chaque méthode Test à exécuter.  Il peut y avoir
 * plus d'une méthode Test dans une classe Test; leur ensemble forme un
 * objet TestSuite.
 * BlueJ découvrira automatiquement (par introspection) les méthodes
 * Test de votre classe Test et générera la TestSuite conséquente.
 * Chaque appel d'une méthode Test sera précédé d'un appel de setUp(),
 * qui réalise les engagements, et suivi d'un appel à tearDown(), qui les
 * détruit.
 */
public class MaPremiereClasseTest
{
    // Définissez ici les variables d'instance nécessaires à vos engagements;
    // Vous pouvez également les saisir automatiquement du présentoir
    // à l'aide du menu contextuel "Présentoir --> Engagements".
    // Notez cependant que ce dernier ne peut saisir les objets primitifs
    // du présentoir (les objets sans constructeur, comme int, float, etc.).
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
     * Méthode appelée avant chaque appel de méthode de test.
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
     * Méthode appelée après chaque appel de méthode de test.
     */
    @After
    public void tearDown() // throws java.lang.Exception
    {
        //Libérez ici les ressources engagées par setUp()
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


