package datos;
// Generated 28-mar-2023 16:08:58 by Hibernate Tools 4.3.1



/**
 * Diagnosticos generated by hbm2java
 */
public class Diagnosticos  implements java.io.Serializable {


     private Integer diagnosticosId;
     private int diaId;
     private String diaCodigoCie;
     private String diaDescripcionCie;
     private String diaObservacion;
     private int historiasHisId;
     private int tratamientosTraId;
     private int historiasHistoriasId;
     private int tratamientosTratamientosId;

    public Diagnosticos() {
    }

    public Diagnosticos(int diaId, String diaCodigoCie, String diaDescripcionCie, String diaObservacion, int historiasHisId, int tratamientosTraId, int historiasHistoriasId, int tratamientosTratamientosId) {
       this.diaId = diaId;
       this.diaCodigoCie = diaCodigoCie;
       this.diaDescripcionCie = diaDescripcionCie;
       this.diaObservacion = diaObservacion;
       this.historiasHisId = historiasHisId;
       this.tratamientosTraId = tratamientosTraId;
       this.historiasHistoriasId = historiasHistoriasId;
       this.tratamientosTratamientosId = tratamientosTratamientosId;
    }
   
    public Integer getDiagnosticosId() {
        return this.diagnosticosId;
    }
    
    public void setDiagnosticosId(Integer diagnosticosId) {
        this.diagnosticosId = diagnosticosId;
    }
    public int getDiaId() {
        return this.diaId;
    }
    
    public void setDiaId(int diaId) {
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
    public String getDiaObservacion() {
        return this.diaObservacion;
    }
    
    public void setDiaObservacion(String diaObservacion) {
        this.diaObservacion = diaObservacion;
    }
    public int getHistoriasHisId() {
        return this.historiasHisId;
    }
    
    public void setHistoriasHisId(int historiasHisId) {
        this.historiasHisId = historiasHisId;
    }
    public int getTratamientosTraId() {
        return this.tratamientosTraId;
    }
    
    public void setTratamientosTraId(int tratamientosTraId) {
        this.tratamientosTraId = tratamientosTraId;
    }
    public int getHistoriasHistoriasId() {
        return this.historiasHistoriasId;
    }
    
    public void setHistoriasHistoriasId(int historiasHistoriasId) {
        this.historiasHistoriasId = historiasHistoriasId;
    }
    public int getTratamientosTratamientosId() {
        return this.tratamientosTratamientosId;
    }
    
    public void setTratamientosTratamientosId(int tratamientosTratamientosId) {
        this.tratamientosTratamientosId = tratamientosTratamientosId;
    }




}


