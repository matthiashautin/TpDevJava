package tp;

/**
 * Décrivez votre classe Point ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Point
{
     private int aX;
     private int aY;
     
     public Point(final int pAX, final int pAY) {
         this.aX = pAX;
         this.aY = pAY;
     } //Point()
     
     public Point() {
         this(10,10);
     } //Point()
     
     public void deplace(final int pDeltaX, final int pDeltaY) {
         this.aX = this.aX + pDeltaX;
         this.aY = this.aY + pDeltaY;
     }
     
     @Override
     public String toString() { 
         return "(" + this.aX + "," + this.aY + ")";
     }
     
     public void affiche() {
         System.out.println(toString());
     }
     
    @Override  
     public boolean equals( final Object pObj) {
        if(pObj == this ) {
            return true;
        } else { 
         
        if(pObj == null) {
            return false;
        } else {
            
        Point vP = (Point)pObj;
            if(pObj.getClass() != vP.getClass()) {
                      return false;
                    } else {         
                      
                     if(vP.aX == this.aX  &&  vP.aY == this.aY ) {
                        return true;
                    } 
                         
                    }
                    
             } 
        return false;
            
     }
    }
    }


