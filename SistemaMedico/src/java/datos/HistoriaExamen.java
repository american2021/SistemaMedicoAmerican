package datos;
// Generated 12/12/2023 11:30:07 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * HistoriaExamen generated by hbm2java
 */
public class HistoriaExamen  implements java.io.Serializable {


     private Integer hisExaId;
     private Examenes examenes;
     private Historias historias;
     private String hisExaIndicaciones;
     private String hisExaDescripcion;
     private Date hisExaFecha;
     private Date hisExaFechaUlt;
     private String hisExaUsuario;
     private Byte hisExaCompletado;

    public HistoriaExamen() {
    }

	
    public HistoriaExamen(Examenes examenes, Historias historias, String hisExaDescripcion, Date hisExaFecha, Date hisExaFechaUlt, String hisExaUsuario) {
        this.examenes = examenes;
        this.historias = historias;
        this.hisExaDescripcion = hisExaDescripcion;
        this.hisExaFecha = hisExaFecha;
        this.hisExaFechaUlt = hisExaFechaUlt;
        this.hisExaUsuario = hisExaUsuario;
    }
    public HistoriaExamen(Examenes examenes, Historias historias, String hisExaIndicaciones, String hisExaDescripcion, Date hisExaFecha, Date hisExaFechaUlt, String hisExaUsuario, Byte hisExaCompletado) {
       this.examenes = examenes;
       this.historias = historias;
       this.hisExaIndicaciones = hisExaIndicaciones;
       this.hisExaDescripcion = hisExaDescripcion;
       this.hisExaFecha = hisExaFecha;
       this.hisExaFechaUlt = hisExaFechaUlt;
       this.hisExaUsuario = hisExaUsuario;
       this.hisExaCompletado = hisExaCompletado;
    }
   
    public Integer getHisExaId() {
        return this.hisExaId;
    }
    
    public void setHisExaId(Integer hisExaId) {
        this.hisExaId = hisExaId;
    }
    public Examenes getExamenes() {
        return this.examenes;
    }
    
    public void setExamenes(Examenes examenes) {
        this.examenes = examenes;
    }
    public Historias getHistorias() {
        return this.historias;
    }
    
    public void setHistorias(Historias historias) {
        this.historias = historias;
    }
    public String getHisExaIndicaciones() {
        return this.hisExaIndicaciones;
    }
    
    public void setHisExaIndicaciones(String hisExaIndicaciones) {
        this.hisExaIndicaciones = hisExaIndicaciones;
    }
    public String getHisExaDescripcion() {
        return this.hisExaDescripcion;
    }
    
    public void setHisExaDescripcion(String hisExaDescripcion) {
        this.hisExaDescripcion = hisExaDescripcion;
    }
    public Date getHisExaFecha() {
        return this.hisExaFecha;
    }
    
    public void setHisExaFecha(Date hisExaFecha) {
        this.hisExaFecha = hisExaFecha;
    }
    public Date getHisExaFechaUlt() {
        return this.hisExaFechaUlt;
    }
    
    public void setHisExaFechaUlt(Date hisExaFechaUlt) {
        this.hisExaFechaUlt = hisExaFechaUlt;
    }
    public String getHisExaUsuario() {
        return this.hisExaUsuario;
    }
    
    public void setHisExaUsuario(String hisExaUsuario) {
        this.hisExaUsuario = hisExaUsuario;
    }
    public Byte getHisExaCompletado() {
        return this.hisExaCompletado;
    }
    
    public void setHisExaCompletado(Byte hisExaCompletado) {
        this.hisExaCompletado = hisExaCompletado;
    }




}


