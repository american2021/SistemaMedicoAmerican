package datos;
// Generated 12/12/2023 11:30:07 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Estadocivil generated by hbm2java
 */
public class Estadocivil  implements java.io.Serializable {


     private String ecCodigo;
     private String ecDescripcion;
     private Date fechaUlt;
     private String usuario;

    public Estadocivil() {
    }

    public Estadocivil(String ecCodigo, String ecDescripcion, Date fechaUlt, String usuario) {
       this.ecCodigo = ecCodigo;
       this.ecDescripcion = ecDescripcion;
       this.fechaUlt = fechaUlt;
       this.usuario = usuario;
    }
   
    public String getEcCodigo() {
        return this.ecCodigo;
    }
    
    public void setEcCodigo(String ecCodigo) {
        this.ecCodigo = ecCodigo;
    }
    public String getEcDescripcion() {
        return this.ecDescripcion;
    }
    
    public void setEcDescripcion(String ecDescripcion) {
        this.ecDescripcion = ecDescripcion;
    }
    public Date getFechaUlt() {
        return this.fechaUlt;
    }
    
    public void setFechaUlt(Date fechaUlt) {
        this.fechaUlt = fechaUlt;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }




}


