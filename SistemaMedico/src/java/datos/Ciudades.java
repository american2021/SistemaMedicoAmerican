package datos;
// Generated 05-may-2023 11:01:23 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Ciudades generated by hbm2java
 */
public class Ciudades  implements java.io.Serializable {


     private CiudadesId id;
     private String ciuNombre;
     private String ciuCanton;
     private Date fechaUlt;
     private String usuario;

    public Ciudades() {
    }

    public Ciudades(CiudadesId id, String ciuNombre, String ciuCanton, Date fechaUlt, String usuario) {
       this.id = id;
       this.ciuNombre = ciuNombre;
       this.ciuCanton = ciuCanton;
       this.fechaUlt = fechaUlt;
       this.usuario = usuario;
    }
   
    public CiudadesId getId() {
        return this.id;
    }
    
    public void setId(CiudadesId id) {
        this.id = id;
    }
    public String getCiuNombre() {
        return this.ciuNombre;
    }
    
    public void setCiuNombre(String ciuNombre) {
        this.ciuNombre = ciuNombre;
    }
    public String getCiuCanton() {
        return this.ciuCanton;
    }
    
    public void setCiuCanton(String ciuCanton) {
        this.ciuCanton = ciuCanton;
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


