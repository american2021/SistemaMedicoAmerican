package datos;
// Generated 19-may-2023 16:40:11 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Resultados generated by hbm2java
 */
public class Resultados  implements java.io.Serializable {


     private Integer resId;
     private String resExamen;
     private Date resFecha;
     private String resResultado;
     private Date resFechaUlt;
     private String resUsuario;
     private Set historiases = new HashSet(0);

    public Resultados() {
    }

	
    public Resultados(String resExamen, Date resFecha, Date resFechaUlt, String resUsuario) {
        this.resExamen = resExamen;
        this.resFecha = resFecha;
        this.resFechaUlt = resFechaUlt;
        this.resUsuario = resUsuario;
    }
    public Resultados(String resExamen, Date resFecha, String resResultado, Date resFechaUlt, String resUsuario, Set historiases) {
       this.resExamen = resExamen;
       this.resFecha = resFecha;
       this.resResultado = resResultado;
       this.resFechaUlt = resFechaUlt;
       this.resUsuario = resUsuario;
       this.historiases = historiases;
    }
   
    public Integer getResId() {
        return this.resId;
    }
    
    public void setResId(Integer resId) {
        this.resId = resId;
    }
    public String getResExamen() {
        return this.resExamen;
    }
    
    public void setResExamen(String resExamen) {
        this.resExamen = resExamen;
    }
    public Date getResFecha() {
        return this.resFecha;
    }
    
    public void setResFecha(Date resFecha) {
        this.resFecha = resFecha;
    }
    public String getResResultado() {
        return this.resResultado;
    }
    
    public void setResResultado(String resResultado) {
        this.resResultado = resResultado;
    }
    public Date getResFechaUlt() {
        return this.resFechaUlt;
    }
    
    public void setResFechaUlt(Date resFechaUlt) {
        this.resFechaUlt = resFechaUlt;
    }
    public String getResUsuario() {
        return this.resUsuario;
    }
    
    public void setResUsuario(String resUsuario) {
        this.resUsuario = resUsuario;
    }
    public Set getHistoriases() {
        return this.historiases;
    }
    
    public void setHistoriases(Set historiases) {
        this.historiases = historiases;
    }




}


