package datos;
// Generated 14/11/2023 9:49:10 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * HistoriaDiagnostico generated by hbm2java
 */
public class HistoriaDiagnostico  implements java.io.Serializable {


     private Integer hisDiaId;
     private Diagnosticos diagnosticos;
     private Historias historias;
     private String hisDiaObservacion;
     private Date hisDiaFechaUlt;
     private String hisDiaUsuario;

    public HistoriaDiagnostico() {
    }

    public HistoriaDiagnostico(Diagnosticos diagnosticos, Historias historias, String hisDiaObservacion, Date hisDiaFechaUlt, String hisDiaUsuario) {
       this.diagnosticos = diagnosticos;
       this.historias = historias;
       this.hisDiaObservacion = hisDiaObservacion;
       this.hisDiaFechaUlt = hisDiaFechaUlt;
       this.hisDiaUsuario = hisDiaUsuario;
    }
   
    public Integer getHisDiaId() {
        return this.hisDiaId;
    }
    
    public void setHisDiaId(Integer hisDiaId) {
        this.hisDiaId = hisDiaId;
    }
    public Diagnosticos getDiagnosticos() {
        return this.diagnosticos;
    }
    
    public void setDiagnosticos(Diagnosticos diagnosticos) {
        this.diagnosticos = diagnosticos;
    }
    public Historias getHistorias() {
        return this.historias;
    }
    
    public void setHistorias(Historias historias) {
        this.historias = historias;
    }
    public String getHisDiaObservacion() {
        return this.hisDiaObservacion;
    }
    
    public void setHisDiaObservacion(String hisDiaObservacion) {
        this.hisDiaObservacion = hisDiaObservacion;
    }
    public Date getHisDiaFechaUlt() {
        return this.hisDiaFechaUlt;
    }
    
    public void setHisDiaFechaUlt(Date hisDiaFechaUlt) {
        this.hisDiaFechaUlt = hisDiaFechaUlt;
    }
    public String getHisDiaUsuario() {
        return this.hisDiaUsuario;
    }
    
    public void setHisDiaUsuario(String hisDiaUsuario) {
        this.hisDiaUsuario = hisDiaUsuario;
    }




}


