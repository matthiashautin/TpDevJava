package veref;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class DependanciesContent
{
    private Class<?> aSuper;
    private Class<?>[] aInterfaces;
    
    public DependanciesContent( Class<?> pC )
    {
        aSuper      = pC.getSuperclass();
        aInterfaces = pC.getInterfaces();
    } // DependanciesContent(.)
    
    public String toString()
    {
        String vS = "";
        if ( aSuper != null )
            vS += " extends " + aSuper.getSimpleName();
        if ( aInterfaces.length > 0 ) {
            vS += " implements " + aInterfaces[0].getSimpleName();
            for ( int i=1; i<aInterfaces.length; i++ )
                vS += ", " + aInterfaces[i].getSimpleName();
        } // if
        return vS;
    } // toString()
} // DependanciesContent
