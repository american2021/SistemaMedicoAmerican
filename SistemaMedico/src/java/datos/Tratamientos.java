package datos;
// Generated 18/10/2023 17:37:10 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tratamientos generated by hbm2java
 */
public class Tratamientos  implements java.io.Serializable {


     private Integer traId;
     private String traCodigoCie;
     private String traDescripcionCie;
     private String traObservacionCie;
     private String traEdicionCie;
     private String traMedicamento;
     private char traViaAdministracion;
     private String traDosisUnitaria;
     private String traUnidadAdministracion;
     private Integer traDuracion;
     private String traIndicaciones;
     private String traAdvertencias;
     private Date traFechaUlt;
     private String traUsuario;
     private Set historiases = new HashSet(0);
     private Set medicamentoses = new HashSet(0);

    public Tratamientos() {
    }

	
    public Tratamientos(String traMedicamento, char traViaAdministracion, String traDosisUnitaria, String traUnidadAdministracion, String traIndicaciones, String traAdvertencias, Date traFechaUlt, String traUsuario) {
        this.traMedicamento = traMedicamento;
        this.traViaAdministracion = traViaAdministracion;
        this.traDosisUnitaria = traDosisUnitaria;
        this.traUnidadAdministracion = traUnidadAdministracion;
        this.traIndicaciones = traIndicaciones;
        this.traAdvertencias = traAdvertencias;
        this.traFechaUlt = traFechaUlt;
        this.traUsuario = traUsuario;
    }
    public Tratamientos(String traCodigoCie, String traDescripcionCie, String traObservacionCie, String traEdicionCie, String traMedicamento, char traViaAdministracion, String traDosisUnitaria, String traUnidadAdministracion, Integer traDuracion, String traIndicaciones, String traAdvertencias, Date traFechaUlt, String traUsuario, Set historiases, Set medicamentoses) {
       this.traCodigoCie = traCodigoCie;
       this.traDescripcionCie = traDescripcionCie;
       this.traObservacionCie = traObservacionCie;
       this.traEdicionCie = traEdicionCie;
       this.traMedicamento = traMedicamento;
       this.traViaAdministracion = traViaAdministracion;
       this.traDosisUnitaria = traDosisUnitaria;
       this.traUnidadAdministracion = traUnidadAdministracion;
       this.traDuracion = traDuracion;
       this.traIndicaciones = traIndicaciones;
       this.traAdvertencias = traAdvertencias;
       this.traFechaUlt = traFechaUlt;
       this.traUsuario = traUsuario;
       this.historiases = historiases;
       this.medicamentoses = medicamentoses;
    }
   
    public Integer getTraId() {
        return this.traId;
    }
    
    public void setTraId(Integer traId) {
        this.traId = traId;
    }
    public String getTraCodigoCie() {
        return this.traCodigoCie;
    }
    
    public void setTraCodigoCie(String traCodigoCie) {
        this.traCodigoCie = traCodigoCie;
    }
    public String getTraDescripcionCie() {
        return this.traDescripcionCie;
    }
    
    public void setTraDescripcionCie(String traDescripcionCie) {
        this.traDescripcionCie = traDescripcionCie;
    }
    public String getTraObservacionCie() {
        return this.traObservacionCie;
    }
    
    public void setTraObservacionCie(String traObservacionCie) {
        this.traObservacionCie = traObservacionCie;
    }
    public String getTraEdicionCie() {
        return this.traEdicionCie;
    }
    
    public void setTraEdicionCie(String traEdicionCie) {
        this.traEdicionCie = traEdicionCie;
    }
    public String getTraMedicamento() {
        return this.traMedicamento;
    }
    
    public void setTraMedicamento(String traMedicamento) {
        this.traMedicamento = traMedicamento;
    }
    public char getTraViaAdministracion() {
        return this.traViaAdministracion;
    }
    
    public void setTraViaAdministracion(char traViaAdministracion) {
        this.traViaAdministracion = traViaAdministracion;
    }
    public String getTraDosisUnitaria() {
        return this.traDosisUnitaria;
    }
    
    public void setTraDosisUnitaria(String traDosisUnitaria) {
        this.traDosisUnitaria = traDosisUnitaria;
    }
    public String getTraUnidadAdministracion() {
        return this.traUnidadAdministracion;
    }
    
    public void setTraUnidadAdministracion(String traUnidadAdministracion) {
        this.traUnidadAdministracion = traUnidadAdministracion;
    }
    public Integer getTraDuracion() {
        return this.traDuracion;
    }
    
    public void setTraDuracion(Integer traDuracion) {
        this.traDuracion = traDuracion;
    }
    public String getTraIndicaciones() {
        return this.traIndicaciones;
    }
    
    public void setTraIndicaciones(String traIndicaciones) {
        this.traIndicaciones = traIndicaciones;
    }
    public String getTraAdvertencias() {
        return this.traAdvertencias;
    }
    
    public void setTraAdvertencias(String traAdvertencias) {
        this.traAdvertencias = traAdvertencias;
    }
    public Date getTraFechaUlt() {
        return this.traFechaUlt;
    }
    
    public void setTraFechaUlt(Date traFechaUlt) {
        this.traFechaUlt = traFechaUlt;
    }
    public String getTraUsuario() {
        return this.traUsuario;
    }
    
    public void setTraUsuario(String traUsuario) {
        this.traUsuario = traUsuario;
    }
    public Set getHistoriases() {
        return this.historiases;
    }
    
    public void setHistoriases(Set historiases) {
        this.historiases = historiases;
    }
    public Set getMedicamentoses() {
        return this.medicamentoses;
    }
    
    public void setMedicamentoses(Set medicamentoses) {
        this.medicamentoses = medicamentoses;
    }




}


