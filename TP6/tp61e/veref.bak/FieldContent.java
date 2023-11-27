package veref;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldContent
{
//     public                         FieldContent( Field pF )
//     public                         FieldContent( String pCName, String pFName )
//     public static Field            getField( String pCName, String pFName )
//     public        String           fieldName()
//     public        String           fieldType()
//     public        boolean          isConstant()
//     public        boolean          isPrivate()
//     public        String           fieldValue()
//     public        Class<?>         getTheClass()
//     public        ModifiersContent getModifiers()
//     public        String           toString()

    private Field aField;
    private Class<?> aClass;
    private ModifiersContent aModifiers;
    
    public FieldContent( Field pF )
    {
        aField = pF;
        aClass = pF.getDeclaringClass();
        aModifiers = new ModifiersContent( pF.getModifiers() );
    } // FieldContent(.)
    
    public FieldContent( String pCName, String pFName )
    {
        this( FieldContent.getField( pCName, pFName ) );
    } // FieldContent(.,.)
    
    public static Field getField( String pCName, String pFName )
    {
        try {
            return Class.forName( pCName ).getDeclaredField( pFName );
        } catch ( ClassNotFoundException e ) {
            return null;
        } catch ( NoSuchFieldException e ) {
            return null;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    } // getField(.,.)

    public String fieldName()
    {
        return aField.getName();
    } // fieldName()

    public String fieldType()
    {
        return aField.getType().getSimpleName();
    } // fieldType()

    public boolean isConstant()
    {
//         if ( ! aModifiers.hasModifier( "public" ) ) return false;
        if ( ! aModifiers.hasModifier( "static" ) ) return false;
        if ( ! aModifiers.hasModifier( "final"  ) ) return false;
        return true;
    } // isConstant()

    public boolean isPrivate()
    {
        if ( ! aModifiers.hasModifier( "private" ) ) return false;
        return true;
    } // isPrivate()

    public String fieldValue( final Object pObj )
    {
        if ( aModifiers.hasModifier( "private" ) )
            aField.setAccessible( true );
        try {
            return "" + aField.get( pObj );
        } catch ( Exception e ) {
                e.printStackTrace();
        }
        return "cannot_access_value";
    } // fieldValue()

    public String fieldValue()
    {
        String vS ="";
        if ( aModifiers.hasModifier( "private" ) )
            aField.setAccessible( true );
        if ( aModifiers.hasModifier( "static" ) )
            try {
                vS= ""+aField.get( null );
            } catch ( IllegalAccessException iae ) {
                vS= "/*value*/";
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        else if ( aModifiers.hasModifier( "final" ) )
            try {
                vS= "" + aField.get( aClass.newInstance() );
            } catch ( InstantiationException ie ) {
                vS= "/*value*/";
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        return vS;
    } // fieldValue()

    public Class<?> getTheClass()
    {
        return aClass;
    } // getTheClasss()

    public ModifiersContent getModifiers()
    {
        return aModifiers;
    } // getModifiers()
    
    public String toString()
    {
        String vS = ClassContent.INDENT + aModifiers.toString();
        vS += ClassContent.getClassName(aField.getType()) + " ";
        vS += aField.getName();
        String vG = "";
        if ( aField.getType().getSimpleName().equals( "String" ) )
          vG = "\"";
        String vValue = fieldValue();
        if ( ! vValue.equals( "" ) )
            vS += " = " + vG + vValue.replace("\n","\\n") + vG;
        return vS + ";";
    } // toString()
} // FieldContent
