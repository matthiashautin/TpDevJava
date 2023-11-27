package veref;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class ConstructorContent
{
//     public                  ConstructorContent( Constructor pC )
//     public String           getParameterString()
//     public String           getExceptionString()
//     public ModifiersContent getModifiers()
//     public List<String>     getParameters()
//     public List<String>     getExceptions()
//     public String           genParName()
//     public String           toString()

    private int               aParCount;
    private Constructor       aConstructor;
    private Class<?>          aClass;
    private ModifiersContent  aModifiers;
    private List<String>      aParameters;
    private List<String>      aExceptions;
    
    public ConstructorContent( Constructor pC )
    {
        aParCount    = 0;
        aConstructor = pC;
        aClass       = pC.getDeclaringClass();
        aModifiers   = new ModifiersContent(  pC.getModifiers() );
        aParameters  = new ArrayList<String>();
        for ( Class<?> vCla : pC.getParameterTypes() )
            aParameters.add( vCla.getSimpleName() );
        aExceptions  = new ArrayList<String>();
        for ( Class<?> vCla : pC.getExceptionTypes() )
            aExceptions.add( vCla.getSimpleName() );
    } // ConstructorContent(.)

    public Object newInstance( Object[] pTArgs ) throws Exception
    {
        return aClass.cast( aConstructor.newInstance( pTArgs ) );
    } // newinstance(.)
  
  public String getName()
  {
      return aClass.getSimpleName();
  } // getName()
    
    public String getParameterString()
    { // for (:) if (notFirst) ", "
        aParCount    = 0;
        if ( aParameters.isEmpty() )  return "()";
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

    public ModifiersContent getModifiers()
    {
        return aModifiers;
    } // getModifiers()

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
        String vS = ClassContent.LN + ClassContent.INDENT;
        vS += aModifiers.toString() + aClass.getSimpleName();
        vS += getParameterString() + getExceptionString();
        return vS + " { /* instructions */ }";
    } // toString()
} // ConstructorContent
