package datos;
// Generated 05-may-2023 11:01:23 by Hibernate Tools 4.3.1


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
     private Personas personas;
     private Resultados resultados;
     private RevisionSistemas revisionSistemas;
     private Signos signos;
     private String hisMotivo;
     private Date hisFechaCreacion;
     private Date hisFechaUlt;
     private String hisUsuario;
     private Set enfermedadeses = new HashSet(0);
     private Set diagnosticoses = new HashSet(0);

    public Historias() {
    }

	
    public Historias(Personas personas, String hisMotivo, Date hisFechaCreacion) {
        this.personas = personas;
        this.hisMotivo = hisMotivo;
        this.hisFechaCreacion = hisFechaCreacion;
    }
    public Historias(Diagnosticos diagnosticos, Enfermedades enfermedades, Personas personas, Resultados resultados, RevisionSistemas revisionSistemas, Signos signos, String hisMotivo, Date hisFechaCreacion, Date hisFechaUlt, String hisUsuario, Set enfermedadeses, Set diagnosticoses) {
       this.diagnosticos = diagnosticos;
       this.enfermedades = enfermedades;
       this.personas = personas;
       this.resultados = resultados;
       this.revisionSistemas = revisionSistemas;
       this.signos = signos;
       this.hisMotivo = hisMotivo;
       this.hisFechaCreacion = hisFechaCreacion;
       this.hisFechaUlt = hisFechaUlt;
       this.hisUsuario = hisUsuario;
       this.enfermedadeses = enfermedadeses;
       this.diagnosticoses = diagnosticoses;
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
    public Personas getPersonas() {
        return this.personas;
    }
    
    public void setPersonas(Personas personas) {
        this.personas = personas;
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
    public String getHisMotivo() {
        return this.hisMotivo;
    }
    
    public void setHisMotivo(String hisMotivo) {
        this.hisMotivo = hisMotivo;
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
    public Set getDiagnosticoses() {
        return this.diagnosticoses;
    }
    
    public void setDiagnosticoses(Set diagnosticoses) {
        this.diagnosticoses = diagnosticoses;
    }




}


