package veref;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MethodsContent
{
//   public          MethodsContent( Class<?> pClass )
//   public          MethodsContent( String pName )
//   public Class<?> getTheClass()
//   public boolean  hasMethod( String pName )
//   public boolean  hasMethod( String pName, String pParams )
//   public boolean  hasMethodRT( String pName, String pRT )
//   public boolean  hasMethodNP( String pName, int pNP )
//   public boolean  hasMethodRTNP( String pName, String pRT, int pNP )
//   public String   toString()

  private Class<?> aClass;
  private List<MethodContent> aList;
  
  public MethodsContent( Class<?> pClass )
  {
      aClass = pClass;
      aList = new ArrayList<MethodContent>();
      for ( Method vMethod : pClass.getDeclaredMethods() )
//          if ( ! vMethod.isSynthetic() )
              aList.add( new MethodContent( vMethod ) );
  } // MethodsContent()
  
  public MethodsContent( String pName )
  {
      this( ClassContent.getClass( pName ) );
  } // MethodsContent()
  
  public Class<?> getTheClass()
  {
      return aClass;
  } // getTheClass()
  
  public int getNbMethods()
  {
      return this.aList.size();
  } // getNbMethods()
    
  public boolean hasMethod( String pName )
  {
      for ( MethodContent vM : aList ) {
          if ( vM.getName().equals( pName ) )
              return true;
      }
      return false;
  } // hasMethod()
  
  public boolean hasMethod( String pName, String pParams )
  {
      for ( MethodContent vM : aList ) {
          if ( vM.getName().equals( pName ) &&
               vM.getParameterString().equals( pParams ) )
              return true;
      }
      return false;
  } // hasMethod()
  
  public boolean hasMethodRT( String pName, String pRT )
  {
      for ( MethodContent vM : aList ) {
          if ( vM.getName().equals( pName ) && vM.getReturnType().equals( pRT ) )
              return true;
//               else System.out.println( "bad " + vM.getReturnType().toString() );
      }
      return false;
  } // hasMethodRT()
  
  public boolean hasMethodNP( String pName, int pNP )
  {
      for ( MethodContent vM : aList ) {
          if ( vM.getName().equals( pName ) && vM.getParameters().size() == pNP )
              return true;
      }
      return false;
  } // hasMethodNP()
  
  public boolean hasMethodRTNP( String pName, String pRT, int pNP )
  {
      for ( MethodContent vM : aList ) {
          if ( vM.getName().equals( pName )
               && vM.getReturnType().equals( pRT )
               && vM.getParameters().size() == pNP )
              return true;
      }
      return false;
  } // hasMethodRTNP()
  
  public boolean hasMethod( String pName, String pRT, String pProto )
  {
      for ( MethodContent vM : aList ) {
          if ( vM.getName().equals( pName )
               && vM.getReturnType().equals( pRT )
               && vM.getParameterString().equals( pProto ) )
              return true;
      }
      return false;
  } // hasMethod()
  
  public MethodContent getMethod( String pName, String pRT, String pProto )
  {
      for ( MethodContent vM : aList ) {
          if ( vM.getName().equals( pName )
               && vM.getReturnType().equals( pRT )
               && vM.getParameterString().equals( pProto ) )
              return vM;
      }
      return null;
  } // getMethod()
    
  public String toString()
  {
      String vS = "";
      for ( MethodContent vM : aList ) {
          vS += vM.toString() + ClassContent.LN;
      }
      return vS;
  } // toString()
} // MethodsContent
