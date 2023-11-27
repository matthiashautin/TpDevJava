package veref;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;

public class ConstructorsContent
{
//   public                    ConstructorsContent( Class<?> pClass )
//   public                    ConstructorsContent( String pName )
//   public Class<?>           getTheClass()
//   public int                getNbConstructors()
//   public boolean            hasConstructor()
//   public boolean            hasConstructor( String pParams )
//   public boolean            hasConstructorNP( int pNP )
//   public ConstructorContent firstConstructor()
//   public String             toString()

  private Class<?> aClass;
  private List<ConstructorContent> aList;
  
  public ConstructorsContent( Class<?> pClass )
  {
      aClass = pClass;
      aList = new ArrayList<ConstructorContent>();
      for ( Constructor vConstructor : pClass.getDeclaredConstructors() )
          if ( ! vConstructor.isSynthetic() )
              aList.add( new ConstructorContent( vConstructor ) );
      if ( getNbConstructors()!=1 || aList.get(0).getParameters().size()>0 )
          return;
      // verif not synthetized constructor :
      System.err.println("constructeur par defaut non verifie !");
      // exam source : constructeur existe ?
  } // ConstructorsContent()
  
  public ConstructorsContent( String pName )
  {
      this( ClassContent.getClass( pName ) );
  } // ConstructorsContent()
  
  public Class<?> getTheClass()
  {
      return aClass;
  } // getTheClass()
    
  public int getNbConstructors()
  {
      return aList.size();
  } // getNbConstructors()

  public boolean hasConstructor()
  {
      return aList.size() > 0;
  } // hasConstructor()
  
  public boolean hasConstructor( String pParams )
  {
      for ( ConstructorContent vC : aList ) {
          if ( vC.getParameterString().equals( pParams ) )
              return true;
      }
      return false;
  } // hasConstructor()
  
  public ConstructorContent getConstructor( String pParams )
  {
      for ( ConstructorContent vC : aList ) {
          if ( vC.getParameterString().equals( pParams ) )
              return vC;
      }
      return null;
  } // getConstructor()
  
  public boolean hasConstructorNP( int pNP )
  {
      for ( ConstructorContent vC : aList ) {
          if ( vC.getParameters().size() == pNP )
              return true;
      }
      return false;
  } // hasConstructorNP(.)
  
  public ConstructorContent firstConstructor()
  {
      return aList.get(0);
  } // firstConstructor()

  public String toString()
  {
      String vS = "";
      for ( ConstructorContent vC : aList ) {
          vS += vC.toString() + ClassContent.LN;
      }
      return vS;
  } // toString()
} // ConstructorsContent
