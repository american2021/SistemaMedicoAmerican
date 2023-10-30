package datos;
// Generated 30/10/2023 8:25:50 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Historias generated by hbm2java
 */
public class Historias  implements java.io.Serializable {


     private Integer hisId;
     private Diagnosticos diagnosticos;
     private Enfermedades enfermedades;
     private Personas personasByMedicoPerId;
     private Personas personasByPacientePerId;
     private Resultados resultados;
     private RevisionSistemas revisionSistemas;
     private Signos signos;
     private Tratamientos tratamientos;
     private String hisMotivo;
     private String hisEnfermedad;
     private String hisIndicaciones;
     private Date hisFechaCreacion;
     private Date hisFechaUlt;
     private String hisUsuario;
     private Set enfermedadeses = new HashSet(0);

    public Historias() {
    }

	
    public Historias(Personas personasByPacientePerId, String hisMotivo, String hisEnfermedad, Date hisFechaCreacion) {
        this.personasByPacientePerId = personasByPacientePerId;
        this.hisMotivo = hisMotivo;
        this.hisEnfermedad = hisEnfermedad;
        this.hisFechaCreacion = hisFechaCreacion;
    }
    public Historias(Diagnosticos diagnosticos, Enfermedades enfermedades, Personas personasByMedicoPerId, Personas personasByPacientePerId, Resultados resultados, RevisionSistemas revisionSistemas, Signos signos, Tratamientos tratamientos, String hisMotivo, String hisEnfermedad, String hisIndicaciones, Date hisFechaCreacion, Date hisFechaUlt, String hisUsuario, Set enfermedadeses) {
       this.diagnosticos = diagnosticos;
       this.enfermedades = enfermedades;
       this.personasByMedicoPerId = personasByMedicoPerId;
       this.personasByPacientePerId = personasByPacientePerId;
       this.resultados = resultados;
       this.revisionSistemas = revisionSistemas;
       this.signos = signos;
       this.tratamientos = tratamientos;
       this.hisMotivo = hisMotivo;
       this.hisEnfermedad = hisEnfermedad;
       this.hisIndicaciones = hisIndicaciones;
       this.hisFechaCreacion = hisFechaCreacion;
       this.hisFechaUlt = hisFechaUlt;
       this.hisUsuario = hisUsuario;
       this.enfermedadeses = enfermedadeses;
    }
   
    public Integer getHisId() {
        return this.hisId;
    }
    
    public void setHisId(Integer hisId) {
        this.hisId = hisId;
    }
    public Diagnosticos getDiagnosticos() {
        return this.diagnosticos;
    }
    
    public void setDiagnosticos(Diagnosticos diagnosticos) {
        this.diagnosticos = diagnosticos;
    }
    public Enfermedades getEnfermedades() {
        return this.enfermedades;
    }
    
    public void setEnfermedades(Enfermedades enfermedades) {
        this.enfermedades = enfermedades;
    }
    public Personas getPersonasByMedicoPerId() {
        return this.personasByMedicoPerId;
    }
    
    public void setPersonasByMedicoPerId(Personas personasByMedicoPerId) {
        this.personasByMedicoPerId = personasByMedicoPerId;
    }
    public Personas getPersonasByPacientePerId() {
        return this.personasByPacientePerId;
    }
    
    public void setPersonasByPacientePerId(Personas personasByPacientePerId) {
        this.personasByPacientePerId = personasByPacientePerId;
    }
    public Resultados getResultados() {
        return this.resultados;
    }
    
    public void setResultados(Resultados resultados) {
        this.resultados = resultados;
    }
    public RevisionSistemas getRevisionSistemas() {
        return this.revisionSistemas;
    }
    
    public void setRevisionSistemas(RevisionSistemas revisionSistemas) {
        this.revisionSistemas = revisionSistemas;
    }
    public Signos getSignos() {
        return this.signos;
    }
    
    public void setSignos(Signos signos) {
        this.signos = signos;
    }
    public Tratamientos getTratamientos() {
        return this.tratamientos;
    }
    
    public void setTratamientos(Tratamientos tratamientos) {
        this.tratamientos = tratamientos;
    }
    public String getHisMotivo() {
        return this.hisMotivo;
    }
    
    public void setHisMotivo(String hisMotivo) {
        this.hisMotivo = hisMotivo;
    }
    public String getHisEnfermedad() {
        return this.hisEnfermedad;
    }
    
    public void setHisEnfermedad(String hisEnfermedad) {
        this.hisEnfermedad = hisEnfermedad;
    }
    public String getHisIndicaciones() {
        return this.hisIndicaciones;
    }
    
    public void setHisIndicaciones(String hisIndicaciones) {
        this.hisIndicaciones = hisIndicaciones;
    }
    public Date getHisFechaCreacion() {
        return this.hisFechaCreacion;
    }
    
    public void setHisFechaCreacion(Date hisFechaCreacion) {
        this.hisFechaCreacion = hisFechaCreacion;
    }
    public Date getHisFechaUlt() {
        return this.hisFechaUlt;
    }
    
    public void setHisFechaUlt(Date hisFechaUlt) {
        this.hisFechaUlt = hisFechaUlt;
    }
    public String getHisUsuario() {
        return this.hisUsuario;
    }
    
    public void setHisUsuario(String hisUsuario) {
        this.hisUsuario = hisUsuario;
    }
    public Set getEnfermedadeses() {
        return this.enfermedadeses;
    }
    
    public void setEnfermedadeses(Set enfermedadeses) {
        this.enfermedadeses = enfermedadeses;
    }




}


