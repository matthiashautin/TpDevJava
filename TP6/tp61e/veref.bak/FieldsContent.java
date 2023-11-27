package veref;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Field;

public class FieldsContent
{
  private Class<?> aClass;
  private List<FieldContent> fieldList;
  
  public FieldsContent( Class<?> pClass )
  {
      aClass = pClass;
      fieldList = new ArrayList<FieldContent>();
      for ( Field vField : pClass.getDeclaredFields() )
          fieldList.add( new FieldContent( vField ) );
  } // FieldsContent()
  
  public FieldsContent( ClassContent pCC )
  {
      this( pCC.getTheClass() );
  } // FieldsContent()
  
  public FieldsContent( String pName )
  {
      this( ClassContent.getClass( pName ) );
  } // FieldsContent()
  
  public Class<?> getTheClass()
  {
      return aClass;
  } // getTheClass()
  
  public int getNbFields()
  {
      return fieldList.size();
  } // getNbFields()
  
  public FieldContent firstField()
  {
      return fieldList.get(0);
  } // firstField()
  
  public FieldContent findField( String pName )
  {
      for ( FieldContent vF : fieldList ) {
          if ( vF.fieldName().equals( pName ) )
              return vF;
      }
      return null;
  } // findField()
  
  public FieldContent findFieldType( String pType )
  {
      for ( FieldContent vF : fieldList ) {
          if ( vF.fieldType().equals( pType ) )
              return vF;
      }
      return null;
  } // findFieldType()
  
  public boolean hasFieldType( String pType )
  {
      for ( FieldContent vF : fieldList ) {
          if ( vF.fieldType().equals( pType ) )
              return true;
      }
      return false;
  } // hasFieldType()
    
  public String toString()
  {
      String vS = "";
      for ( FieldContent vF : fieldList ) {
          vS += vF.toString() + ClassContent.LN;
      }
      return vS;
  } // toString()
} // FieldsContent
