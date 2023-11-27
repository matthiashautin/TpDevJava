import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class InventaireTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class InventaireTest
{
    private static String                   sClassName;
    private static String                   sPkg;
    private static String                   sFil;
    private static veref.ClassContent       sCla;
    private static boolean                  sAbstract;
    private static String                   sAttName;
    private static String                   sAttType;
    private static veref.FieldContent       sAtt;
    private static String                   sProtoC;
    private static veref.ConstructorContent sCon;
    private static String                   sMetName;
    private static String                   sMetRT;
    private static String                   sProtoM;
    private static veref.MethodContent      sMet;
    private static veref.MethodContent      sGetter;

    //x v1 : exactement les 2 attributs
    //x si<2, Vous ne devez supprimer aucun attribut de la classe Item !
    //x si>2, Vous ne devez ajouter aucun attribut dans la classe Item !
    //x si=2mais!=, Vous ne devez modifier aucun attribut dans la classe Item !
    //x v1 : constructeur à 2 paramètres
    //x si 0, Vous ne devez pas supprimer le constructeur de la classe Item !
    //x si 1 ou >2, Vous ne devez pas modifier le constructeur de la classe Item !
    //x v1 : getNom() et getPrix()
    //x si manque, Vous ne devez supprimer aucune méthode de la classe Item !
    //x si param!=, Vous ne devez modifier aucune méthode de la classe Item !
    //x v2 : v1 + toString()
    // manque, mauvaise signature, Override, mauvaise valeur
    // v3 : v2 + compareTo()
    // manque, mauvaise signature, Override, mauvaise valeur sur 3 cas
    /**
     * Default constructor for test class ItemTest
     */
    public InventaireTest()
    {
    }

    @Test
    public void test_attributs_2()
    {
        sCla = veref.V.getVClaFName( sClassName );
        assertFalse( "Vous n'avez pas dû terminer de déclarer les attributs...",
            veref.V.nbAtt(sCla) < 2 );
        assertFalse( "Trop d'attributs !", veref.V.nbAtt(sCla) > 2 );
        veref.FieldContent vAtt1 = sAtt = veref.V.getAttFTN( sCla, "int", "aPrixTotal" );
        veref.V.failIfNot(); 
        sAtt = veref.V.getAttFTN( sCla, "ArrayList", "aListe" );
        veref.V.mesIf( "ArrayList n'est pas conseillé ici ; relisez le A.4.1 !" ); 
        sAtt = veref.V.getAttFTN( sCla, "List", "aListe" );
        veref.V.failIfNot(); 
        veref.V.verifGTAttribut( sAtt, "List<Item>" );
        veref.V.verifModAttribut( vAtt1, "private", "static" );
        veref.V.verifModAttribut( sAtt, "private", "static" );
    }

    // @Test
    // public void test_constructeur_3()
    // {
        // sCla = veref.V.getVClaFName( sClassName );
        // sCon = veref.V.getConFProto( sCla, "()", "T" );
        // veref.V.failIfNot(); 
        // // verifier aPrixTotal == 0
        // // verifier aListe != null
        // // verifier aListe est ArrayList
        // // verifier aListe est ArrayList<Item>
    // }

    @Test
    public void test_getItem_4()
    {
        sCla = veref.V.getVClaFName( sClassName );
        sMetName = "getItem";
        sMetRT   = "Item";
        sProtoM  = "( String p1 )";
        sGetter = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM );
        veref.V.failIfNot(); 
    }

    @Test
    public void test_contientItem_5()
    {
        sCla = veref.V.getVClaFName( sClassName );
        sMetName = "contientItem";
        sMetRT   = "boolean";
        sProtoM  = "( String p1 )";
        sGetter = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM );
        veref.V.failIfNot(); 
    }

    @Test
    public void test_ajouteItem_6()
    {
        sCla = veref.V.getVClaFName( sClassName );
        sMetName = "ajouteItem";
        sMetRT   = "void";
        sProtoM  = "( String p1, int p2 )";
        sGetter = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM );
        veref.V.failIfNot(); 
    }

    @Test
    public void test_enleveItem_7()
    {
        sCla = veref.V.getVClaFName( sClassName );
        sMetName = "enleveItem";
        sMetRT   = "void";
        sProtoM  = "( String p1 )";
        sGetter = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM );
        veref.V.failIfNot(); 
    }

    // @Test
    // public void test_new()
    // {
    // Item vItem1 = new Item("clef", 42);
    // assertEquals("clef", vItem1.getNom());
    // assertEquals(42, vItem1.getPrix());
    // }

    @Test
    public void test_toString_8()
    {
        sCla = veref.V.getVClaFName( sClassName );
        sMetName = "toString";
        sMetRT   = "String";
        sProtoM  = "()";
        sGetter = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM );
        veref.V.verifOverride( sFil, sMetRT, sMetName );
        // à décommenter quand toString a été redéfinie :
        // Item vItem1 = new Item("clef", 42);
        // assertEquals("clef", vItem1.getNom());
        // assertEquals(42, vItem1.getPrix());
        // assertEquals("clef(42)", vItem1.toString());
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        sPkg       = "";
        sClassName = "Inventaire";
        sFil = sClassName + ".java";
        if ( sPkg != "" ) {
            veref.ClassContent.setRefPkg( sPkg );
            sFil = sPkg + "/" + sFil;
        }
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}