package datos;
// Generated 03/01/2024 17:24:14 by Hibernate Tools 4.3.1



/**
 * Parentescos generated by hbm2java
 */
public class Parentescos  implements java.io.Serializable {


     private int parId;
     private String parNombre;

    public Parentescos() {
    }

    public Parentescos(int parId, String parNombre) {
       this.parId = parId;
       this.parNombre = parNombre;
    }
   
    public int getParId() {
        return this.parId;
    }
    
    public void setParId(int parId) {
        this.parId = parId;
    }
    public String getParNombre() {
        return this.parNombre;
    }
    
    public void setParNombre(String parNombre) {
        this.parNombre = parNombre;
    }




}


