package datos;
// Generated 23/11/2023 16:28:38 by Hibernate Tools 4.3.1


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
     private String diaEdicionCie;
     private Date diaFechaUlt;
     private String diaUsuario;
     private Set historiaDiagnosticos = new HashSet(0);

    public Diagnosticos() {
    }

    public Diagnosticos(String diaCodigoCie, String diaDescripcionCie, String diaEdicionCie, Date diaFechaUlt, String diaUsuario, Set historiaDiagnosticos) {
       this.diaCodigoCie = diaCodigoCie;
       this.diaDescripcionCie = diaDescripcionCie;
       this.diaEdicionCie = diaEdicionCie;
       this.diaFechaUlt = diaFechaUlt;
       this.diaUsuario = diaUsuario;
       this.historiaDiagnosticos = historiaDiagnosticos;
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
    public Set getHistoriaDiagnosticos() {
        return this.historiaDiagnosticos;
    }
    
    public void setHistoriaDiagnosticos(Set historiaDiagnosticos) {
        this.historiaDiagnosticos = historiaDiagnosticos;
    }




}


