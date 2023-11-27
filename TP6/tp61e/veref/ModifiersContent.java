package veref;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ModifiersContent
{
    private static String[] aKeyWords
        = { "public", "protected", "private", "/*package*/", "abstract", "static", "final",
            "transient", "volatile", "synchronized", "native", "strictfp", "interface" };
    private List<String> aModifierList;
    private boolean aIsInterfaceMethod;
    
    public ModifiersContent( int pM )
    {
        aIsInterfaceMethod= false;
        if ( pM < 0 ) {
            pM= -pM;
            aIsInterfaceMethod= true;
        } // if
        aModifierList = new ArrayList<String>();
        Collections.addAll( aModifierList, aKeyWords );
        decodeModifiers( pM );
    } // FieldContent(.)
    
    private void decodeModifiers( int pMod )
    {
        // ordre =
        //  public protected private "package" abstract static final
        //  transient volatile synchronized native strictfp interface
        if ( ! Modifier.isPublic( pMod ) )
            aModifierList.remove( "public" );
        if ( ! Modifier.isProtected( pMod ) )
            aModifierList.remove( "protected" );
        if ( ! Modifier.isPrivate( pMod ) )
            aModifierList.remove( "private" );
        if ( Modifier.isPrivate( pMod ) || Modifier.isProtected( pMod ) || Modifier.isPublic( pMod ) )
            aModifierList.remove( "/*package*/" );
        if ( ! Modifier.isAbstract( pMod ) )
            aModifierList.remove( "abstract" );
        if ( ! Modifier.isStatic( pMod ) )
            aModifierList.remove( "static" );
        if ( ! Modifier.isFinal( pMod ) )
            aModifierList.remove( "final" );
        if ( ! Modifier.isTransient( pMod ) )
            aModifierList.remove( "transient" );
        if ( ! Modifier.isVolatile( pMod ) )
            aModifierList.remove( "volatile" );
        if ( ! Modifier.isSynchronized( pMod ) )
            aModifierList.remove( "synchronized" );
        if ( ! Modifier.isNative( pMod ) )
            aModifierList.remove( "native" );
        if ( ! Modifier.isStrict( pMod ) )
            aModifierList.remove( "strictfp" );
        if ( ! Modifier.isInterface( pMod ) )
            aModifierList.remove( "interface" );
    }
    
    public List<String> listModifiers()
    {
        return aModifierList;
    } // listModifiers()

    public boolean hasModifier( String pM )
    {
        return aModifierList.contains( pM );
    } // hasModifier(.)
    
    public String accessString()
    {
        for ( int i=0; i<3; i++ )
            if ( this.hasModifier( aKeyWords[i] ) )   return aKeyWords[i];
        return aKeyWords[3];
    } // accessString()
    
    public String toString()
    {
        String vS = "";
        for ( String vM : aModifierList )
            if ( ! aIsInterfaceMethod ||
                 ( ! vM.equals("public" ) && ! vM.equals("abstract") ) )
                vS += vM + " ";
        return vS;
    } // toString()
} // ModifiersContent

