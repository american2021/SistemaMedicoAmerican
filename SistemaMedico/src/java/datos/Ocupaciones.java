package datos;
// Generated 03/01/2024 17:24:14 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Ocupaciones generated by hbm2java
 */
public class Ocupaciones  implements java.io.Serializable {


     private int ocuId;
     private String ocuDescripcion;
     private Date ocuFechaUtl;
     private String ocuUsuario;

    public Ocupaciones() {
    }

    public Ocupaciones(int ocuId, String ocuDescripcion, Date ocuFechaUtl, String ocuUsuario) {
       this.ocuId = ocuId;
       this.ocuDescripcion = ocuDescripcion;
       this.ocuFechaUtl = ocuFechaUtl;
       this.ocuUsuario = ocuUsuario;
    }
   
    public int getOcuId() {
        return this.ocuId;
    }
    
    public void setOcuId(int ocuId) {
        this.ocuId = ocuId;
    }
    public String getOcuDescripcion() {
        return this.ocuDescripcion;
    }
    
    public void setOcuDescripcion(String ocuDescripcion) {
        this.ocuDescripcion = ocuDescripcion;
    }
    public Date getOcuFechaUtl() {
        return this.ocuFechaUtl;
    }
    
    public void setOcuFechaUtl(Date ocuFechaUtl) {
        this.ocuFechaUtl = ocuFechaUtl;
    }
    public String getOcuUsuario() {
        return this.ocuUsuario;
    }
    
    public void setOcuUsuario(String ocuUsuario) {
        this.ocuUsuario = ocuUsuario;
    }




}


