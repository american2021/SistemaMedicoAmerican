package datos;
// Generated 03/01/2024 17:24:14 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * HistoriaAntecedente generated by hbm2java
 */
public class HistoriaAntecedente  implements java.io.Serializable {


     private Integer hisAntId;
     private Antecedente antecedente;
     private Historias historias;
     private String hisAntDescripcion;
     private Date hisAntFechaUlt;
     private String hisAntUsuario;

    public HistoriaAntecedente() {
    }

    public HistoriaAntecedente(Antecedente antecedente, Historias historias, String hisAntDescripcion, Date hisAntFechaUlt, String hisAntUsuario) {
       this.antecedente = antecedente;
       this.historias = historias;
       this.hisAntDescripcion = hisAntDescripcion;
       this.hisAntFechaUlt = hisAntFechaUlt;
       this.hisAntUsuario = hisAntUsuario;
    }
   
    public Integer getHisAntId() {
        return this.hisAntId;
    }
    
    public void setHisAntId(Integer hisAntId) {
        this.hisAntId = hisAntId;
    }
    public Antecedente getAntecedente() {
        return this.antecedente;
    }
    
    public void setAntecedente(Antecedente antecedente) {
        this.antecedente = antecedente;
    }
    public Historias getHistorias() {
        return this.historias;
    }
    
    public void setHistorias(Historias historias) {
        this.historias = historias;
    }
    public String getHisAntDescripcion() {
        return this.hisAntDescripcion;
    }
    
    public void setHisAntDescripcion(String hisAntDescripcion) {
        this.hisAntDescripcion = hisAntDescripcion;
    }
    public Date getHisAntFechaUlt() {
        return this.hisAntFechaUlt;
    }
    
    public void setHisAntFechaUlt(Date hisAntFechaUlt) {
        this.hisAntFechaUlt = hisAntFechaUlt;
    }
    public String getHisAntUsuario() {
        return this.hisAntUsuario;
    }
    
    public void setHisAntUsuario(String hisAntUsuario) {
        this.hisAntUsuario = hisAntUsuario;
    }




}


