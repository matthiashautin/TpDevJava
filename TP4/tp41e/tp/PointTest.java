package tp;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PointTest
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
        sClassName = "Point";
        veref.ClassContent.setRefPkg( sPkg );
        sFil = sPkg + "/" + sClassName + ".java";
        sCla = veref.V.getVClaFName( sClassName );
    } // test1_classe()

    @Test
    public void test2_attributs()
    {
        test1_classe();
        veref.V.verifNbAttOp( sCla, "==", 2 );
        veref.FieldContent vAtt;
        sAttType = "int";

        sAttName = "aX";
        vAtt = veref.V.selAttFTN( sCla, sAttType, sAttName );
        veref.V.failIfNot();
        veref.V.verifModAttribut( vAtt, "private", "static final" );
        veref.V.verifAttThis( sFil, sAttType, sAttName );

        sAttName = "aY";
        vAtt = veref.V.selAttFTN( sCla, sAttType, sAttName );
        veref.V.failIfNot();
        veref.V.verifModAttribut( vAtt, "private", "static final" );
        veref.V.verifAttThis( sFil, sAttType, sAttName );

        //         veref.V.verifNomAttribut( vAtt );
        //         veref.V.vrai( sAttName.equals( vAtt.fieldName() ), "$n n'est pas le nom attendu pour l'attribut" );
        //         veref.V.mesIfNot();
        //         sAtt = veref.V.getV1AttFTN( sCla, sAttType, sAttName );
    } // test2_attributs()

    @Test
    public void test3_constructeurs()
    {
        test2_attributs();
        sMetName = sClassName;

        sProtoC = "( int p1, int p2 )";
        //         veref.V.verifDefCon( vCla, "F" );
        //         veref.V.verifConNbP( vCla, 1, "T" );
        //         veref.ConstructorContent vCon = veref.V.getConFProto( vCla, vProtoC, "T" );
        //         veref.V.verifModCon( vCon, "public", "static final" );
        sCon = veref.V.getVConFProto( sCla, sProtoC );
        for ( int vI=1; vI<=2; vI++ ) {
            String vRet = veref.V.verifFinalN( sFil, sMetName, vI );
            veref.V.vrai( vRet.equals( "OK" ), vRet );
            veref.V.failIfNot();        
        } // for

        veref.V.verifDefCon( sCla, "T" );
        sProtoC = "()";
        sCon = veref.V.getVConFProto( sCla, sProtoC );
        sAttType = "int";
        String vValue1 = "10";
        veref.FieldContent vAtt1 = veref.V.selAttFTN( sCla, sAttType, "aX" );
        veref.FieldContent vAtt2 = veref.V.selAttFTN( sCla, sAttType, "aY" );
        veref.V.getAndVerifInsDef2( sCon, vAtt1, vValue1, vAtt2, vValue1 ); //object discarded

        veref.V.vrai( veref.V.nbCon( sCla ) <= 2 , "Il y a au moins un constructeur de trop ..." );
        veref.V.mesIfNot();    

        veref.V.verifConThisParDebug( sFil, 1, false );
        // reste a verifier que c'est dans le constructeur par defaut
        // et qu'il n'y a pas d'initialisation des attributs apres
    } // test3_constructeurs()

    @Test
    public void test4_deplace()
    {
        test2_attributs();
        sMetName = "deplace";
        sMetRT   = "void";
        sProtoM  = "( int p1, int p2 )";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM );
        veref.V.mesIfNot();        
        for ( int vI=1; vI<=2; vI++ ) {
            String vRet = veref.V.verifFinalN( sFil, sMetName, vI );
            veref.V.vrai( vRet.equals( "OK" ), vRet );
            veref.V.failIfNot();        
        } // for

        sMetName = sClassName;
        sProtoC = "( int p1, int p2 )";
        sCon = veref.V.getVConFProto( sCla, sProtoC );
        Object vIns = null;
        try {
            vIns = sCon.newInstance( new Object[]{12,24} );
            try {
                sMetName = "deplace";
                sMet.invoke( vIns, new Object[]{5,-5} );
                sAttType = "int";
                sAttName = "aX";
                veref.FieldContent vAtt1 = veref.V.selAttFTN( sCla, sAttType, sAttName );
                veref.V.vrai( vAtt1.fieldValue( vIns ).equals("17"), sAttName+" incorrect apres deplace !" );
                veref.V.failIfNot();        
                sAttName = "aY";
                veref.FieldContent vAtt2 = veref.V.selAttFTN( sCla, sAttType, sAttName );
                veref.V.vrai( vAtt2.fieldValue( vIns ).equals("19"), sAttName+" incorrect apres deplace !" );
                veref.V.failIfNot();        
            } catch( final Exception pE ) {
                fail( "l'appel de la methode " + sMet.getName() + sMet.getParameterString() + " genere une exception !" );
            } // t/c
        } catch( final Exception pE ) {
            fail( "le constructeur " + sCon.getName() + sCon.getParameterString() + " genere une exception !" );
        } // t/c
    } // test4_deplace()

    @Test
    public void test5_toString()
    {
        test2_attributs();
        sMetName = "toString";
        sMetRT   = "String";
        sProtoM  = "()";
        //         veref.V.verifMet( vCla, vMetName, "T" );
        //         veref.V.verifMetRT( vCla, vMetName, vMetRT, "T" );
        //         veref.V.verifMetRTNP( vCla, vMetName, vMetRT, 0, "T" );
        //         veref.MethodContent vMet = veref.V.getMetFProto( vCla, vMetName, vMetRT, vProtoM, "T" );
        //         veref.V.verifModMet( vMet, "public", "static final" );
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM );
        //         veref.V.verifFinal( sFil, vMetName, vProtoM ); // inutile pour 0 param
        //         veref.V.vrai( veref.V.nbMet( sCla ) <= 1 , "Il y a au moins une methode de trop ..." );
        veref.V.mesIfNot();        
        //         String vValue2 = "Room de l'attribut";
        //         Object vObj2 = veref.V.getAndVerifIns1( sCon, sAtt, vValue2 );
        //         veref.V.verifGetter( vObj2, sMet, vValue2 );
        // verif public ?
        veref.V.verifMetOverDebug( sFil, "public", sMetRT, sMetName, false );
        
        sMetName = sClassName;
        sProtoC = "( int p1, int p2 )";
        sCon = veref.V.getVConFProto( sCla, sProtoC );
        Object vIns = null;
        try {
            vIns = sCon.newInstance( new Object[]{12,24} );
            try {
                sMetName = "toString";
                String vRes = (String)sMet.invoke( vIns, new Object[]{} );
                veref.V.vrai( vRes.contains("12,24"), vRes+" ne sont pas les coordonnees du point (12,24) !?" );
                veref.V.failIfNot();        
                veref.V.vrai( vRes.equals("(12,24)"), vRes+" n'est pas exactement ce que l'on souhaite retourner !" );
                veref.V.failIfNot();        
            } catch( final Exception pE ) {
                fail( "l'appel de la methode " + sMet.getName() + sMet.getParameterString() + " genere une exception !" );
            } // t/c
        } catch( final Exception pE ) {
            fail( "le constructeur " + sCon.getName() + sCon.getParameterString() + " genere une exception !" );
        } // t/c

        veref.V.verifFileContains( sFil, "return\\s*\\(", "des parentheses apres return", false, false );
        veref.V.verifFileContains( sFil, "return\"", "pas d'espace apres return", false, false );
    } // test5_toString()

    @Test
    public void test6_affiche()
    {
        test2_attributs();
        sMetName = "affiche";
        sMetRT   = "void";
        sProtoM  = "()";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM );
        veref.V.mesIfNot(); 
        
        veref.V.verifFileContains( sFil, "println\\s*\\(.*this\\.aX.*\\)", "un acces a l'attribut aX", false, false );
        // tester la fonctionnalite
    } // test6_affiche()

    @Test
    public void test7_equals()
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
    } // test7_equals()

} // PointTest
