package datos;
// Generated 30/10/2023 8:25:50 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Diagnosticos generated by hbm2java
 */
public class Diagnosticos  implements java.io.Serializable {


     private Integer diaId;
     private String diaCodigoCie;
     private String diaDescripcionCie;
     private String diaObservacionCie;
     private String diaEdicionCie;
     private Date diaFechaUlt;
     private String diaUsuario;
     private Set historiases = new HashSet(0);

    public Diagnosticos() {
    }

	
    public Diagnosticos(String diaObservacionCie) {
        this.diaObservacionCie = diaObservacionCie;
    }
    public Diagnosticos(String diaCodigoCie, String diaDescripcionCie, String diaObservacionCie, String diaEdicionCie, Date diaFechaUlt, String diaUsuario, Set historiases) {
       this.diaCodigoCie = diaCodigoCie;
       this.diaDescripcionCie = diaDescripcionCie;
       this.diaObservacionCie = diaObservacionCie;
       this.diaEdicionCie = diaEdicionCie;
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
    public String getDiaObservacionCie() {
        return this.diaObservacionCie;
    }
    
    public void setDiaObservacionCie(String diaObservacionCie) {
        this.diaObservacionCie = diaObservacionCie;
    }
    public String getDiaEdicionCie() {
        return this.diaEdicionCie;
    }
    
    public void setDiaEdicionCie(String diaEdicionCie) {
        this.diaEdicionCie = diaEdicionCie;
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


