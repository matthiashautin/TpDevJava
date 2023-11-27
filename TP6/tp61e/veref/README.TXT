Nouvelle API :

verif/get/calc Exi From Cla Ini Met Att Par Fil Nam Typ Pro Mod Nb Op

Classcontent getClaFromNam( String pNam ) => verifClaExiFromNam( pNam )
verifClaExiFromNam( String pNam ) = verifClaExiFromNamYN( pNam, true )    non !
verifClaExiFromNamYN( String pNam, boolean pExi ) => okClaExiFromNamYN( pNam, pExi )  non !
okClaExiFromNamYN( String pNam, boolean pExi )      non !

FieldContent getAttFromTypNam( ClassContent pCla, String pTyp, String pNam ) => verifAttExiFromTypNam( pCla, pTyp, pNam )
verifAttExiFromTypNam( ClassContent pCla, String pTyp, String pNam ) = verifAttExiFromTypNamOp( pCla, pTyp, pNam, ">", 0 )
verifAttExiFromTypNamOp( ClassContent pCla, String pTyp, String pNam, String pOp, int pNb ) => okAttExiFromTypNamOp( pCla, pTyp, pNam, pOp, pNb )
okAttExiFromTypNamOp( ClassContent pCla, String pTyp, String pNam, String pOp, int pNb ) => okNbAttOp( pCla, pOp, pNb )
okNbAttOp( ClassContent pCla, String pOp, int pNb ) => getNbAtt( pCla )
getNbAtt( ClassContent pCla )

verifNamFromAtt( FieldContent pAtt ) => okNamFromAtt( pAtt )
okNamFromAtt( FieldContent pAtt )
verifModFromAtt( FieldContent pAtt, String pYes, String pNo ) => okModFromAtt( pAtt, pYes, pNo )
okModFromAtt( FieldContent pAtt )
