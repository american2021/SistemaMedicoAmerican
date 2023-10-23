package datos;
// Generated 23/10/2023 16:51:36 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * RevisionSistemas generated by hbm2java
 */
public class RevisionSistemas  implements java.io.Serializable {


     private Integer revSisId;
     private String revSisPatologia;
     private String revSisSentidos;
     private String revSisRespiratorio;
     private String revSisCardiovascular;
     private String revSisDigestivo;
     private String revSisGenital;
     private String revSisUrinario;
     private String revSisEsqueletico;
     private String revSisMuscular;
     private String revSisNervioso;
     private String revSisEndocrino;
     private String revSisHemolinfatico;
     private String revSisTegumentario;
     private String revSisFisicoPatologia;
     private String revSisFisicoObservacion;
     private Date revSisFechaUlt;
     private String revSisUsuario;
     private Set historiases = new HashSet(0);

    public RevisionSistemas() {
    }

	
    public RevisionSistemas(String revSisPatologia, String revSisSentidos, String revSisRespiratorio, String revSisCardiovascular, String revSisDigestivo, String revSisGenital, String revSisUrinario, String revSisEsqueletico, String revSisMuscular, String revSisNervioso, String revSisEndocrino, String revSisHemolinfatico, String revSisTegumentario, String revSisFisicoPatologia, String revSisFisicoObservacion, Date revSisFechaUlt, String revSisUsuario) {
        this.revSisPatologia = revSisPatologia;
        this.revSisSentidos = revSisSentidos;
        this.revSisRespiratorio = revSisRespiratorio;
        this.revSisCardiovascular = revSisCardiovascular;
        this.revSisDigestivo = revSisDigestivo;
        this.revSisGenital = revSisGenital;
        this.revSisUrinario = revSisUrinario;
        this.revSisEsqueletico = revSisEsqueletico;
        this.revSisMuscular = revSisMuscular;
        this.revSisNervioso = revSisNervioso;
        this.revSisEndocrino = revSisEndocrino;
        this.revSisHemolinfatico = revSisHemolinfatico;
        this.revSisTegumentario = revSisTegumentario;
        this.revSisFisicoPatologia = revSisFisicoPatologia;
        this.revSisFisicoObservacion = revSisFisicoObservacion;
        this.revSisFechaUlt = revSisFechaUlt;
        this.revSisUsuario = revSisUsuario;
    }
    public RevisionSistemas(String revSisPatologia, String revSisSentidos, String revSisRespiratorio, String revSisCardiovascular, String revSisDigestivo, String revSisGenital, String revSisUrinario, String revSisEsqueletico, String revSisMuscular, String revSisNervioso, String revSisEndocrino, String revSisHemolinfatico, String revSisTegumentario, String revSisFisicoPatologia, String revSisFisicoObservacion, Date revSisFechaUlt, String revSisUsuario, Set historiases) {
       this.revSisPatologia = revSisPatologia;
       this.revSisSentidos = revSisSentidos;
       this.revSisRespiratorio = revSisRespiratorio;
       this.revSisCardiovascular = revSisCardiovascular;
       this.revSisDigestivo = revSisDigestivo;
       this.revSisGenital = revSisGenital;
       this.revSisUrinario = revSisUrinario;
       this.revSisEsqueletico = revSisEsqueletico;
       this.revSisMuscular = revSisMuscular;
       this.revSisNervioso = revSisNervioso;
       this.revSisEndocrino = revSisEndocrino;
       this.revSisHemolinfatico = revSisHemolinfatico;
       this.revSisTegumentario = revSisTegumentario;
       this.revSisFisicoPatologia = revSisFisicoPatologia;
       this.revSisFisicoObservacion = revSisFisicoObservacion;
       this.revSisFechaUlt = revSisFechaUlt;
       this.revSisUsuario = revSisUsuario;
       this.historiases = historiases;
    }
   
    public Integer getRevSisId() {
        return this.revSisId;
    }
    
    public void setRevSisId(Integer revSisId) {
        this.revSisId = revSisId;
    }
    public String getRevSisPatologia() {
        return this.revSisPatologia;
    }
    
    public void setRevSisPatologia(String revSisPatologia) {
        this.revSisPatologia = revSisPatologia;
    }
    public String getRevSisSentidos() {
        return this.revSisSentidos;
    }
    
    public void setRevSisSentidos(String revSisSentidos) {
        this.revSisSentidos = revSisSentidos;
    }
    public String getRevSisRespiratorio() {
        return this.revSisRespiratorio;
    }
    
    public void setRevSisRespiratorio(String revSisRespiratorio) {
        this.revSisRespiratorio = revSisRespiratorio;
    }
    public String getRevSisCardiovascular() {
        return this.revSisCardiovascular;
    }
    
    public void setRevSisCardiovascular(String revSisCardiovascular) {
        this.revSisCardiovascular = revSisCardiovascular;
    }
    public String getRevSisDigestivo() {
        return this.revSisDigestivo;
    }
    
    public void setRevSisDigestivo(String revSisDigestivo) {
        this.revSisDigestivo = revSisDigestivo;
    }
    public String getRevSisGenital() {
        return this.revSisGenital;
    }
    
    public void setRevSisGenital(String revSisGenital) {
        this.revSisGenital = revSisGenital;
    }
    public String getRevSisUrinario() {
        return this.revSisUrinario;
    }
    
    public void setRevSisUrinario(String revSisUrinario) {
        this.revSisUrinario = revSisUrinario;
    }
    public String getRevSisEsqueletico() {
        return this.revSisEsqueletico;
    }
    
    public void setRevSisEsqueletico(String revSisEsqueletico) {
        this.revSisEsqueletico = revSisEsqueletico;
    }
    public String getRevSisMuscular() {
        return this.revSisMuscular;
    }
    
    public void setRevSisMuscular(String revSisMuscular) {
        this.revSisMuscular = revSisMuscular;
    }
    public String getRevSisNervioso() {
        return this.revSisNervioso;
    }
    
    public void setRevSisNervioso(String revSisNervioso) {
        this.revSisNervioso = revSisNervioso;
    }
    public String getRevSisEndocrino() {
        return this.revSisEndocrino;
    }
    
    public void setRevSisEndocrino(String revSisEndocrino) {
        this.revSisEndocrino = revSisEndocrino;
    }
    public String getRevSisHemolinfatico() {
        return this.revSisHemolinfatico;
    }
    
    public void setRevSisHemolinfatico(String revSisHemolinfatico) {
        this.revSisHemolinfatico = revSisHemolinfatico;
    }
    public String getRevSisTegumentario() {
        return this.revSisTegumentario;
    }
    
    public void setRevSisTegumentario(String revSisTegumentario) {
        this.revSisTegumentario = revSisTegumentario;
    }
    public String getRevSisFisicoPatologia() {
        return this.revSisFisicoPatologia;
    }
    
    public void setRevSisFisicoPatologia(String revSisFisicoPatologia) {
        this.revSisFisicoPatologia = revSisFisicoPatologia;
    }
    public String getRevSisFisicoObservacion() {
        return this.revSisFisicoObservacion;
    }
    
    public void setRevSisFisicoObservacion(String revSisFisicoObservacion) {
        this.revSisFisicoObservacion = revSisFisicoObservacion;
    }
    public Date getRevSisFechaUlt() {
        return this.revSisFechaUlt;
    }
    
    public void setRevSisFechaUlt(Date revSisFechaUlt) {
        this.revSisFechaUlt = revSisFechaUlt;
    }
    public String getRevSisUsuario() {
        return this.revSisUsuario;
    }
    
    public void setRevSisUsuario(String revSisUsuario) {
        this.revSisUsuario = revSisUsuario;
    }
    public Set getHistoriases() {
        return this.historiases;
    }
    
    public void setHistoriases(Set historiases) {
        this.historiases = historiases;
    }




}


