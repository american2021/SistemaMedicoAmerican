package datos;
// Generated 29-jun-2023 8:59:55 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Ciudades generated by hbm2java
 */
public class Ciudades  implements java.io.Serializable {


     private int ciuCodigo;
     private String ciuNombre;
     private Date fechaUlt;
     private String usuario;

    public Ciudades() {
    }

    public Ciudades(int ciuCodigo, String ciuNombre, Date fechaUlt, String usuario) {
       this.ciuCodigo = ciuCodigo;
       this.ciuNombre = ciuNombre;
       this.fechaUlt = fechaUlt;
       this.usuario = usuario;
    }
   
    public int getCiuCodigo() {
        return this.ciuCodigo;
    }
    
    public void setCiuCodigo(int ciuCodigo) {
        this.ciuCodigo = ciuCodigo;
    }
    public String getCiuNombre() {
        return this.ciuNombre;
    }
    
    public void setCiuNombre(String ciuNombre) {
        this.ciuNombre = ciuNombre;
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


