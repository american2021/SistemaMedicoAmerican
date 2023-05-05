package datos;
// Generated 05-may-2023 11:01:23 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Diagnosticos generated by hbm2java
 */
public class Diagnosticos  implements java.io.Serializable {


     private Integer diaId;
     private Historias historias;
     private Tratamientos tratamientos;
     private String diaCodigoCie;
     private String diaDescripcionCie;
     private String diaObservacion;
     private Date diaFechaUlt;
     private String diaUsuario;
     private Set historiases = new HashSet(0);

    public Diagnosticos() {
    }

	
    public Diagnosticos(Historias historias, Tratamientos tratamientos, String diaCodigoCie, String diaDescripcionCie, String diaObservacion) {
        this.historias = historias;
        this.tratamientos = tratamientos;
        this.diaCodigoCie = diaCodigoCie;
        this.diaDescripcionCie = diaDescripcionCie;
        this.diaObservacion = diaObservacion;
    }
    public Diagnosticos(Historias historias, Tratamientos tratamientos, String diaCodigoCie, String diaDescripcionCie, String diaObservacion, Date diaFechaUlt, String diaUsuario, Set historiases) {
       this.historias = historias;
       this.tratamientos = tratamientos;
       this.diaCodigoCie = diaCodigoCie;
       this.diaDescripcionCie = diaDescripcionCie;
       this.diaObservacion = diaObservacion;
       this.diaFechaUlt = diaFechaUlt;
       this.diaUsuario = diaUsuario;
       this.historiases = historiases;
    }
   
    public Integer getDiaId() {
        return this.diaId;
    }
    
    public void setDiaId(Integer diaId) {
        this.diaId = diaId;
    }
    public Historias getHistorias() {
        return this.historias;
    }
    
    public void setHistorias(Historias historias) {
        this.historias = historias;
    }
    public Tratamientos getTratamientos() {
        return this.tratamientos;
    }
    
    public void setTratamientos(Tratamientos tratamientos) {
        this.tratamientos = tratamientos;
    }
    public String getDiaCodigoCie() {
        return this.diaCodigoCie;
    }
    
    public void setDiaCodigoCie(String diaCodigoCie) {
        this.diaCodigoCie = diaCodigoCie;
    }
    public String getDiaDescripcionCie() {
        return this.diaDescripcionCie;
    }
    
    public void setDiaDescripcionCie(String diaDescripcionCie) {
        this.diaDescripcionCie = diaDescripcionCie;
    }
    public String getDiaObservacion() {
        return this.diaObservacion;
    }
    
    public void setDiaObservacion(String diaObservacion) {
        this.diaObservacion = diaObservacion;
    }
    public Date getDiaFechaUlt() {
        return this.diaFechaUlt;
    }
    
    public void setDiaFechaUlt(Date diaFechaUlt) {
        this.diaFechaUlt = diaFechaUlt;
    }
    public String getDiaUsuario() {
        return this.diaUsuario;
    }
    
    public void setDiaUsuario(String diaUsuario) {
        this.diaUsuario = diaUsuario;
    }
    public Set getHistoriases() {
        return this.historiases;
    }
    
    public void setHistoriases(Set historiases) {
        this.historiases = historiases;
    }




}


