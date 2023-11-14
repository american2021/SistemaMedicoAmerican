package datos;
// Generated 14/11/2023 9:49:10 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Usuarios generated by hbm2java
 */
public class Usuarios  implements java.io.Serializable {


     private Integer usuId;
     private Personas personas;
     private String usuNombre;
     private String usuContra;
     private Date usuFechaUlt;
     private String usuUsuario;
     private int rolesRolId;

    public Usuarios() {
    }

    public Usuarios(Personas personas, String usuNombre, String usuContra, Date usuFechaUlt, String usuUsuario, int rolesRolId) {
       this.personas = personas;
       this.usuNombre = usuNombre;
       this.usuContra = usuContra;
       this.usuFechaUlt = usuFechaUlt;
       this.usuUsuario = usuUsuario;
       this.rolesRolId = rolesRolId;
    }
   
    public Integer getUsuId() {
        return this.usuId;
    }
    
    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }
    public Personas getPersonas() {
        return this.personas;
    }
    
    public void setPersonas(Personas personas) {
        this.personas = personas;
    }
    public String getUsuNombre() {
        return this.usuNombre;
    }
    
    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }
    public String getUsuContra() {
        return this.usuContra;
    }
    
    public void setUsuContra(String usuContra) {
        this.usuContra = usuContra;
    }
    public Date getUsuFechaUlt() {
        return this.usuFechaUlt;
    }
    
    public void setUsuFechaUlt(Date usuFechaUlt) {
        this.usuFechaUlt = usuFechaUlt;
    }
    public String getUsuUsuario() {
        return this.usuUsuario;
    }
    
    public void setUsuUsuario(String usuUsuario) {
        this.usuUsuario = usuUsuario;
    }
    public int getRolesRolId() {
        return this.rolesRolId;
    }
    
    public void setRolesRolId(int rolesRolId) {
        this.rolesRolId = rolesRolId;
    }




}


