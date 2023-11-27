package veref;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodContent
{
//     public                  MethodContent( Method pM )
//     public String           getParameterString()
//     public String           getExceptionString()
//     public String           getAnnotationString()
//     public ModifiersContent getModifiers()
//     public String           getName()
//     public String           getReturnType()
//     public List<String>     getParameters()
//     public List<String>     getExceptions()
//     public String           genParName()
//     public String           toString()

    private int               aParCount;
    private Method            aMethod;
    private Class<?>          aClass;
    private Annotation[]      aAnnotations;
    private ModifiersContent  aModifiers;
    private List<String>      aParameters;
    private List<String>      aExceptions;
    
    public MethodContent( Method pM )
    {
        aMethod      = pM;
        aClass       = pM.getDeclaringClass();
        int vM       = pM.getModifiers();
        if ( aClass.isInterface() )   vM= -vM;
        aModifiers   = new ModifiersContent( vM );
        aAnnotations = aMethod.getAnnotations();
//         System.out.println("<<"+aMethod.getAnnotations().length+">>");
//         System.out.println("<<"+aMethod.getDeclaredAnnotations().length+">>");
        aParameters  = new ArrayList<String>();
        for ( Class<?> vCla : pM.getParameterTypes() )
            aParameters.add( ClassContent.getClassName(vCla) );
        aExceptions  = new ArrayList<String>();
        for ( Class<?> vCla : pM.getExceptionTypes() )
            aExceptions.add( ClassContent.getClassName(vCla) );
    } // MethodContent(.)

    public Object invoke( final Object pObj, final Object[] pTArgs )
    {
        try {
            if ( aModifiers.hasModifier( "private" ) )
                aMethod.setAccessible( true );
            return aMethod.invoke( pObj, pTArgs );
        }
        catch ( final Exception pE ) {
            System.err.println( this.getName() + " ==> " + pE );
        }
        return null;
    } // invoke(..)

    public String getParameterString()
    { // for (:) if (notFirst) ", "
        if ( aParameters.isEmpty() )  return "()";
        aParCount = 0;
        String vP = "( " + aParameters.get(0).toString() + " " + genParName();
        for ( int i=1; i<aParameters.size(); i++ )
            vP += ", " + aParameters.get(i).toString() + " " + genParName();
        return vP + " )";
    } // getParameterString()

    public String getExceptionString()
    {
        if ( aExceptions.isEmpty() )  return "";
        String vE = " throws " + aExceptions.get(0).toString();
        for ( int i=1; i<aExceptions.size(); i++ )
            vE += ", " + aExceptions.get(i).toString();
        return vE;
    } // getExceptionString()
    
    public String getAnnotationString()
    {
        if ( aAnnotations.length == 0 )  return "";
        String vA = ClassContent.INDENT + "@" + aAnnotations[0].toString();
        for ( int i=1; i<aAnnotations.length; i++ )
            vA += ", @" + aAnnotations[i].toString();
        return vA + ClassContent.LN;
    } // getAnnotationString()

    public ModifiersContent getModifiers()
    {
        return aModifiers;
    } // getModifiers()

    public String getName()
    {
        return aMethod.getName();
    } // getName()

    public String getReturnType()
    {
        // return aMethod.getReturnType().toString();
        return ClassContent.getClassName( aMethod.getReturnType() );
    } // getReturnType()

    public List<String> getParameters()
    {
        return aParameters;
    } // getParameters()

    public List<String> getExceptions()
    {
        return aExceptions;
    } // getExceptions()
    
    public String genParName()
    {
        return "p" + ++aParCount;
    } // genParName()
    
    public String toString()
    {
        String vS = ClassContent.LN + getAnnotationString();
        vS += ClassContent.INDENT + aModifiers.toString();
        vS += ClassContent.getClassName( aMethod.getReturnType() ) + " " + aMethod.getName();
        vS += getParameterString() + getExceptionString();
        if ( this.aModifiers.hasModifier( "abstract" ) )
            vS += ";";
        else
            vS += " { /* instructions */ }";
        return vS;
    } // toString()
} // MethodContent
