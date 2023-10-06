package tp;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PointColoreTest
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
        sClassName = "PointColore";
        veref.ClassContent.setRefPkg( sPkg );
        sFil = sPkg + "/" + sClassName + ".java";
        sCla = veref.V.getVClaFName( sClassName );
        // il faut tester le extends
    } // test1_classe()

    @Test
    public void test2_attributs()
    {
        test1_classe();
        sAttName = "aCouleur";
        sAttType = "String";
        sAtt = veref.V.getV1AttFTN( sCla, sAttType, sAttName );

        veref.V.verifModAttribut( sAtt, "private", "static final" );
        veref.V.verifAttThis( sFil, sAttType, sAttName );
    } // test2_attributs()

    @Test
    public void test0_constructeurNaturel()
    {
        test2_attributs();

        sProtoC = "( int p1, int p2, String p3 )";
        //         veref.V.verifDefCon( vCla, "F" );
        //         veref.V.verifConNbP( vCla, 1, "T" );
        //         veref.ConstructorContent vCon = veref.V.getConFProto( vCla, vProtoC, "T" );
        //         veref.V.verifModCon( vCon, "public", "static final" );
        sCon = veref.V.getVConFProto( sCla, sProtoC );
        sMetName = sClassName;
        for ( int vI=1; vI<=3; vI++ ) {
            String vRet = veref.V.verifFinalN( sFil, sMetName, vI );
            veref.V.vrai( vRet.equals( "OK" ), vRet );
            veref.V.failIfNot();        
        } // for

        Object vIns = null;
        try {
            vIns = sCon.newInstance( new Object[]{11,23,"W"} );
            // Comment acceder aux attributs de la super-classe ?
            // sAttType = "int";
            // sAttName = "aX";
            // veref.FieldContent vAtt1 = veref.V.selAttFTN( sCla, sAttType, sAttName );
            // veref.V.vrai( vAtt1.fieldValue( vIns ).equals("11"), sAttName+" incorrect !?" );
            // veref.V.failIfNot();   
            
            // sAttName = "aY";
            // veref.FieldContent vAtt2 = veref.V.selAttFTN( sCla, sAttType, sAttName );
            // veref.V.vrai( vAtt2.fieldValue( vIns ).equals("23"), sAttName+" incorrect !?" );
            // veref.V.failIfNot();        
            
            sAttType = "String";
            sAttName = "aCouleur";
            veref.FieldContent vAtt3 = veref.V.selAttFTN( sCla, sAttType, sAttName );
            veref.V.vrai( vAtt3.fieldValue( vIns ).equals("W"), sAttName+" incorrecte !?" );
            veref.V.failIfNot();   
        } catch( final Exception pE ) {
            pE.printStackTrace();
            fail( "le constructeur " + sCon.getName() + sCon.getParameterString() + " genere une exception !" );
        } // t/c

        veref.V.vrai( veref.V.nbCon( sCla ) == 1 , "Test0 : Juste le constructeur naturel pour l'instant !" );
        veref.V.mesIfNot();        
    } // test0_constructeurNaturel()

    @Test
    public void test3_constructeurs()
    {
        test2_attributs();

        sProtoC = "( int p1, int p2, String p3 )";
        //         veref.V.verifDefCon( vCla, "F" );
        //         veref.V.verifConNbP( vCla, 1, "T" );
        //         veref.ConstructorContent vCon = veref.V.getConFProto( vCla, vProtoC, "T" );
        //         veref.V.verifModCon( vCon, "public", "static final" );
        sCon = veref.V.getVConFProto( sCla, sProtoC );
        sMetName = sClassName;
        for ( int vI=1; vI<=3; vI++ ) {
            String vRet = veref.V.verifFinalN( sFil, sMetName, vI );
            veref.V.vrai( vRet.equals( "OK" ), vRet );
            veref.V.failIfNot();        
        } // for

        sProtoC = "( int p1, int p2 )";
        sCon = veref.V.getVConFProto( sCla, sProtoC );
        for ( int vI=1; vI<=2; vI++ ) {
            String vRet = veref.V.verifFinalN( sFil, sMetName, vI );
            veref.V.vrai( vRet.equals( "OK" ), vRet );
            veref.V.failIfNot();        
        } // for
        // verif valeur par defaut a faire

        veref.V.verifDefCon( sCla, "T" );
        sProtoC = "()";
        sCon = veref.V.getVConFProto( sCla, sProtoC );

        Object vIns = null;
        try {
            vIns = sCon.newInstance( new Object[]{} );
            // Comment acceder aux attributs de la super-classe ?
            // sAttType = "int";
            // sAttName = "aX";
            // veref.FieldContent vAtt1 = veref.V.selAttFTN( sCla, sAttType, sAttName );
            // veref.V.vrai( vAtt1.fieldValue( vIns ).equals("10"), sAttName+" incorrect !?" );
            // veref.V.failIfNot();   
            
            // sAttName = "aY";
            // veref.FieldContent vAtt2 = veref.V.selAttFTN( sCla, sAttType, sAttName );
            // veref.V.vrai( vAtt2.fieldValue( vIns ).equals("10"), sAttName+" incorrect !?" );
            // veref.V.failIfNot();        
            
            sAttType = "String";
            sAttName = "aCouleur";
            veref.FieldContent vAtt3 = veref.V.selAttFTN( sCla, sAttType, sAttName );
            veref.V.vrai( vAtt3.fieldValue( vIns ).equals("N"), sAttName+" incorrecte !?" );
            veref.V.failIfNot();   
        } catch( final Exception pE ) {
            pE.printStackTrace();
            fail( "le constructeur " + sCon.getName() + sCon.getParameterString() + " genere une exception !" );
        } // t/c

        veref.V.vrai( veref.V.nbCon( sCla ) <= 3 , "Il y a au moins un constructeur de trop ..." );
        veref.V.mesIfNot();        

        veref.V.verifConThisParDebug( sFil, 2, false );
        // reste a verifier que c'est dans le constructeur par defaut
        // et qu'il n'y a pas d'initialisation des attributs apres
        veref.V.verifFileContains( sFil, "super\\(", "super(", true, false );
        veref.V.verifFileContains( sFil, "super\\s+\\(", "un espace entre super et (", false, false );

    } // test3_constructeurs()

    @Test
    public void test4_toString()
    {
        test2_attributs();
        sMetName = "toString";
        sMetRT   = "String";
        sProtoM  = "()";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM );
        veref.V.mesIfNot();        
        // verif public ?
        veref.V.verifMetOverDebug( sFil, "public", sMetRT, sMetName, false );

        sMetName = sClassName;
        sProtoC = "( int p1, int p2, String p3 )";
        sCon = veref.V.getVConFProto( sCla, sProtoC );
        Object vIns = null;
        try {
            vIns = sCon.newInstance( new Object[]{10,20,"R"} );
            try {
                sMetName = "toString";
                String vRes = (String)sMet.invoke( vIns, new Object[]{} );
                veref.V.vrai( vRes.contains("(10,20)"), vRes+" La partie Point n'est pas correcte !?" );
                veref.V.failIfNot();        
                veref.V.vrai( vRes.equals("R:(10,20)"), vRes+" n'est pas exactement ce que l'on souhaite retourner !" );
                veref.V.failIfNot();        
            } catch( final Exception pE ) {
                fail( "l'appel de la methode " + sMet.getName() + sMet.getParameterString() + " genere une exception !" );
            } // t/c
        } catch( final Exception pE ) {
            fail( "le constructeur " + sCon.getName() + sCon.getParameterString() + " genere une exception !" );
        } // t/c
        veref.V.verifFileContains( sFil, "return\\s*\\(", "des parentheses apres return", false, false );
    } // test4_toString()

    @Test
    public void test5_equals()
    {
        test2_attributs();
        sMetName = "equals";
        sMetRT   = "boolean";
        sProtoM  = "( Object p1 )";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM );
        veref.V.mesIfNot();        
        for ( int vI=1; vI<=1; vI++ ) {
            String vRet = veref.V.verifFinalN( sFil, sMetName, vI );
            veref.V.vrai( vRet.equals( "OK" ), vRet );
            veref.V.failIfNot();        
        } // for
        // verif public ?
        veref.V.verifMetOverDebug( sFil, "public", sMetRT, sMetName, false );
    } // test5_equals()

} // PointColoreTest
