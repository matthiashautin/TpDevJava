package tp;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UtilisationTest
{
    private static String                   sClassName;
    private static String                   sPkg;
    private static String                   sFil;
    private static veref.ClassContent       sCla;
    private static String                   sAttName;
    private static String                   sAttType;
    private static veref.FieldContent       sAtt;
    private static String                   sProtoC;
    private static veref.ConstructorContent sCon;
    private static String                   sMetName;
    private static String                   sMetRT;
    private static String                   sProtoM;
    private static veref.MethodContent      sMet;

    @Before public void setUp() {}
    @After public void tearDown() {}

    @Test
    public void test1_classe()
    {
        sPkg       = "tp";
        sClassName = "Utilisation";
        veref.ClassContent.setRefPkg( sPkg );
        sFil = sPkg + "/" + sClassName + ".java";
        sCla = veref.V.getVClaFName( sClassName );
    } // test1_classe()

    @Test
    public void test2_attributs()
    {
        test1_classe();
        veref.V.verifNbAttOp( sCla, "==", 0 );
    } // test2_attributs()

    @Test
    public void test3_constructeurs()
    {
        test2_attributs();
        veref.V.nbCo( sCla, "==", 0 );
    } // test3_constructeurs()
    
    @Test
    public void test4_essai()
    {
        test3_constructeurs();
        sMetName = "essai";
        sMetRT   = "void";
        sProtoM  = "()";
        sMet = veref.V.getVMetFProtoMod( sCla, sMetName, sMetRT, sProtoM, "public static", "final" );
        veref.V.mesIfNot();        
// manque test static

        veref.V.verifFileContains( sFil, "new\\s+Point\\s*\\(", "new Point(", true, false );
        // veref.V.verifFileContains( sFil, "new\\s+Point\\s*\\(\\s*\\)", "new Point()", false );
        // veref.V.verifFileContains( sFil, "new\\s+Point\\s*\\(.*,.*\\)", "new Point(,)", false );
        veref.V.verifFileContains( sFil, "new\\s+PointColore\\s*\\(", "new PointColore(", true, false );
        // veref.V.verifFileContains( sFil, "new\\s+PointColore\\s*\\(\\s*\\)", "new PointColore()", false );
        // veref.V.verifFileContains( sFil, "new\\s+PointColore\\s*\\(.*,.*\\)", "new PointColore(,)", false );
        veref.V.verifFileContains( sFil, "affiche\\s*\\(\\s*\\)", "affiche(", true, false );
        veref.V.verifFileContains( sFil, "deplace\\s*\\(.*\\)", "deplace(", true, false );
    } // test4_essai()

} // UtilisationTest
