package datos;
// Generated 17-abr-2023 10:51:47 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Enfermedades generated by hbm2java
 */
public class Enfermedades  implements java.io.Serializable {


     private Integer enfId;
     private Historias historias;
     private String enfNombre;
     private String enfDescripcion;
     private String enfCodigoCie;
     private Date enfFechaUlt;
     private String enfUsuario;
     private Set historiases = new HashSet(0);

    public Enfermedades() {
    }

	
    public Enfermedades(String enfNombre, String enfDescripcion, String enfCodigoCie) {
        this.enfNombre = enfNombre;
        this.enfDescripcion = enfDescripcion;
        this.enfCodigoCie = enfCodigoCie;
    }
    public Enfermedades(Historias historias, String enfNombre, String enfDescripcion, String enfCodigoCie, Date enfFechaUlt, String enfUsuario, Set historiases) {
       this.historias = historias;
       this.enfNombre = enfNombre;
       this.enfDescripcion = enfDescripcion;
       this.enfCodigoCie = enfCodigoCie;
       this.enfFechaUlt = enfFechaUlt;
       this.enfUsuario = enfUsuario;
       this.historiases = historiases;
    }
   
    public Integer getEnfId() {
        return this.enfId;
    }
    
    public void setEnfId(Integer enfId) {
        this.enfId = enfId;
    }
    public Historias getHistorias() {
        return this.historias;
    }
    
    public void setHistorias(Historias historias) {
        this.historias = historias;
    }
    public String getEnfNombre() {
        return this.enfNombre;
    }
    
    public void setEnfNombre(String enfNombre) {
        this.enfNombre = enfNombre;
    }
    public String getEnfDescripcion() {
        return this.enfDescripcion;
    }
    
    public void setEnfDescripcion(String enfDescripcion) {
        this.enfDescripcion = enfDescripcion;
    }
    public String getEnfCodigoCie() {
        return this.enfCodigoCie;
    }
    
    public void setEnfCodigoCie(String enfCodigoCie) {
        this.enfCodigoCie = enfCodigoCie;
    }
    public Date getEnfFechaUlt() {
        return this.enfFechaUlt;
    }
    
    public void setEnfFechaUlt(Date enfFechaUlt) {
        this.enfFechaUlt = enfFechaUlt;
    }
    public String getEnfUsuario() {
        return this.enfUsuario;
    }
    
    public void setEnfUsuario(String enfUsuario) {
        this.enfUsuario = enfUsuario;
    }
    public Set getHistoriases() {
        return this.historiases;
    }
    
    public void setHistoriases(Set historiases) {
        this.historiases = historiases;
    }




}

