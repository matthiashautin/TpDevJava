package veref;

public class ClassContent
{
//   public  static Class<?>            getClass( String pName )
//   public  static Class<?>            getClass( String pName, String pPkg )
//   public                             ClassContent( String pName )
//   public                             ClassContent( String pName, String pPkg )
//   public         FieldsContent       getFields()
//   public         ConstructorsContent getConstructors()
//   public         String              classType()
//   public         Class<?>            getTheClass()
//   public         String              className()
//   public  static void                setRefClass( Class<?> pClass )
//   public  static void                setRefPkg( String pPkg )
//   public  static String              getClassName( Class<?> pClass )
//   public         String              classPackage()
//   private        String              parametersFake()
//   public         String              toString()

    public static final String LN ="\n";
    public static final String INDENT ="  ";
    public static Class<?> sRefClass = null;
    public static String   sRefPkg = null;
    private Class<?> aClass;
    // modifiers
    private ModifiersContent aModifiers;
    // dependancies
    private DependanciesContent aDependancies;
    // fields
    private FieldsContent aFields;
    // constructors
    private ConstructorsContent aConstructors;
    // methods
    private MethodsContent aMethods;

    public static Class<?> getClass( String pName )
    {
        return ClassContent.getClass( pName, null );
    } // getClass(.)
    
    public static Class<?> getClass( String pName, String pPkg )
    {
        String vQName = pName;
        if ( pPkg != null ) sRefPkg = pPkg;
        if ( sRefPkg != null ) vQName = sRefPkg + "." + vQName;

        try {
            return Class.forName( vQName );
        } catch ( ClassNotFoundException e ) {
            return null;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    } // getClass(..)

    public ClassContent( String pName )
    {
        this( pName, null );
    } // ClassContent(.)

    public ClassContent( String pName, String pPkg )
    {
        aClass        = ClassContent.getClass( pName, pPkg );
        aModifiers    = new ModifiersContent( aClass.getModifiers() );
        aDependancies = new DependanciesContent( aClass );
        aFields       = new FieldsContent( aClass );
        aConstructors = new ConstructorsContent( aClass );
        aMethods      = new MethodsContent( aClass );
    } // ClassContent(..)
  
  public FieldsContent getFields()
  {
      return aFields;
  } // getFields()
  
  public ConstructorsContent getConstructors()
  {
      return aConstructors;
  } // getConstructors()
  
  public MethodsContent getMethods()
  {
      return aMethods;
  } // getMethods()

    public String classType()
    {
        if ( aModifiers.hasModifier( "interface" ) )
            return "interface";
            
        if ( aModifiers.hasModifier( "abstract" ) )
            return "abstract class";
            
        return "class";
    } // classType()

    public Class<?> getTheClass()
    {
        return aClass;
    } // getClass()

    public String className()
    {
        return aClass.getSimpleName();
    } // className()

    public static void setRefClass( Class<?> pClass ) // ??? a quoi ca sert ?
    {
        sRefClass = pClass;
    } // setRefClass()

    public static void setRefPkg( String pPkg )
    {
        sRefPkg = pPkg;
    } // setRefPkg()

    public static String getClassName( Class<?> pClass )
    {
        String vRefName;
        if ( sRefClass == null )
          vRefName = "java.lang";
        else
          vRefName = sRefClass.getPackage().getName();
        
        String vName;
        Package vPkg = pClass.getPackage();
        if ( vPkg == null || vPkg.getName().equals( "java.lang" ) || vPkg.getName().equals( vRefName ) )
          vName = pClass.getSimpleName();
        else
          vName = pClass.getName();
        return vName;
    } // getClassName()

    public String classPackage()
    {
        Package vP =aClass.getPackage();
        if ( vP == null )   return LN;
        return "package " + aClass.getPackage().getName() + ";" + LN;
    } // classPackage()
    
    private String parametersFake()
    {
        String vS ="";
        int vNbP =aClass.getTypeParameters().length;
        if ( vNbP > 0 ) {
            vS += "<P1";
            for ( int vI=2; vI<=vNbP; vI++ ) {
                vS += ",P"+vI;
            } // for
            vS += ">";
        } // if
        return vS;
    } // parametersFake()
    
    @Override public String toString()
    {
        String vS = "";
        vS += classPackage();
        vS += aModifiers.accessString() + " " + classType();
        vS += " " + className() + parametersFake();
        vS += aDependancies.toString() + LN + "{" + LN;
        vS += aFields.toString() + aConstructors.toString()
              + aMethods.toString();
        return vS + "} // " + className();
    } // toString()
} // ClassContent
