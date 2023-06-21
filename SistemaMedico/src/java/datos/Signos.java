package datos;
// Generated 07-jun-2023 17:26:51 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Signos generated by hbm2java
 */
public class Signos  implements java.io.Serializable {


     private Integer sigId;
     private int sigTipo;
     private int sigPresionSistolica;
     private int sigPresionDiastolica;
     private int sigFrecuenciaRespiratoria;
     private int sigFrecuenciaCardiaca;
     private int sigPresionArterialMedia;
     private int sigSaturacion;
     private Float sigTemperatura;
     private Float sigPeso;
     private Float sigEstatura;
     private int sigImc;
     private Float sigPerimetroAbdominal;
     private Float sigPerimetroBrazo;
     private Float sigGlucosaCapilar;
     private Float sigValorHemoglobina;
     private Float sigValorHemoglobinaCorr;
     private Date sigFechaUlt;
     private String sigUsuario;
     private Set historiases = new HashSet(0);

    public Signos() {
    }

	
    public Signos(int sigTipo, int sigPresionSistolica, int sigPresionDiastolica, int sigFrecuenciaRespiratoria, int sigFrecuenciaCardiaca, int sigPresionArterialMedia, int sigSaturacion, int sigImc) {
        this.sigTipo = sigTipo;
        this.sigPresionSistolica = sigPresionSistolica;
        this.sigPresionDiastolica = sigPresionDiastolica;
        this.sigFrecuenciaRespiratoria = sigFrecuenciaRespiratoria;
        this.sigFrecuenciaCardiaca = sigFrecuenciaCardiaca;
        this.sigPresionArterialMedia = sigPresionArterialMedia;
        this.sigSaturacion = sigSaturacion;
        this.sigImc = sigImc;
    }
    public Signos(int sigTipo, int sigPresionSistolica, int sigPresionDiastolica, int sigFrecuenciaRespiratoria, int sigFrecuenciaCardiaca, int sigPresionArterialMedia, int sigSaturacion, Float sigTemperatura, Float sigPeso, Float sigEstatura, int sigImc, Float sigPerimetroAbdominal, Float sigPerimetroBrazo, Float sigGlucosaCapilar, Float sigValorHemoglobina, Float sigValorHemoglobinaCorr, Date sigFechaUlt, String sigUsuario, Set historiases) {
       this.sigTipo = sigTipo;
       this.sigPresionSistolica = sigPresionSistolica;
       this.sigPresionDiastolica = sigPresionDiastolica;
       this.sigFrecuenciaRespiratoria = sigFrecuenciaRespiratoria;
       this.sigFrecuenciaCardiaca = sigFrecuenciaCardiaca;
       this.sigPresionArterialMedia = sigPresionArterialMedia;
       this.sigSaturacion = sigSaturacion;
       this.sigTemperatura = sigTemperatura;
       this.sigPeso = sigPeso;
       this.sigEstatura = sigEstatura;
       this.sigImc = sigImc;
       this.sigPerimetroAbdominal = sigPerimetroAbdominal;
       this.sigPerimetroBrazo = sigPerimetroBrazo;
       this.sigGlucosaCapilar = sigGlucosaCapilar;
       this.sigValorHemoglobina = sigValorHemoglobina;
       this.sigValorHemoglobinaCorr = sigValorHemoglobinaCorr;
       this.sigFechaUlt = sigFechaUlt;
       this.sigUsuario = sigUsuario;
       this.historiases = historiases;
    }
   
    public Integer getSigId() {
        return this.sigId;
    }
    
    public void setSigId(Integer sigId) {
        this.sigId = sigId;
    }
    public int getSigTipo() {
        return this.sigTipo;
    }
    
    public void setSigTipo(int sigTipo) {
        this.sigTipo = sigTipo;
    }
    public int getSigPresionSistolica() {
        return this.sigPresionSistolica;
    }
    
    public void setSigPresionSistolica(int sigPresionSistolica) {
        this.sigPresionSistolica = sigPresionSistolica;
    }
    public int getSigPresionDiastolica() {
        return this.sigPresionDiastolica;
    }
    
    public void setSigPresionDiastolica(int sigPresionDiastolica) {
        this.sigPresionDiastolica = sigPresionDiastolica;
    }
    public int getSigFrecuenciaRespiratoria() {
        return this.sigFrecuenciaRespiratoria;
    }
    
    public void setSigFrecuenciaRespiratoria(int sigFrecuenciaRespiratoria) {
        this.sigFrecuenciaRespiratoria = sigFrecuenciaRespiratoria;
    }
    public int getSigFrecuenciaCardiaca() {
        return this.sigFrecuenciaCardiaca;
    }
    
    public void setSigFrecuenciaCardiaca(int sigFrecuenciaCardiaca) {
        this.sigFrecuenciaCardiaca = sigFrecuenciaCardiaca;
    }
    public int getSigPresionArterialMedia() {
        return this.sigPresionArterialMedia;
    }
    
    public void setSigPresionArterialMedia(int sigPresionArterialMedia) {
        this.sigPresionArterialMedia = sigPresionArterialMedia;
    }
    public int getSigSaturacion() {
        return this.sigSaturacion;
    }
    
    public void setSigSaturacion(int sigSaturacion) {
        this.sigSaturacion = sigSaturacion;
    }
    public Float getSigTemperatura() {
        return this.sigTemperatura;
    }
    
    public void setSigTemperatura(Float sigTemperatura) {
        this.sigTemperatura = sigTemperatura;
    }
    public Float getSigPeso() {
        return this.sigPeso;
    }
    
    public void setSigPeso(Float sigPeso) {
        this.sigPeso = sigPeso;
    }
    public Float getSigEstatura() {
        return this.sigEstatura;
    }
    
    public void setSigEstatura(Float sigEstatura) {
        this.sigEstatura = sigEstatura;
    }
    public int getSigImc() {
        return this.sigImc;
    }
    
    public void setSigImc(int sigImc) {
        this.sigImc = sigImc;
    }
    public Float getSigPerimetroAbdominal() {
        return this.sigPerimetroAbdominal;
    }
    
    public void setSigPerimetroAbdominal(Float sigPerimetroAbdominal) {
        this.sigPerimetroAbdominal = sigPerimetroAbdominal;
    }
    public Float getSigPerimetroBrazo() {
        return this.sigPerimetroBrazo;
    }
    
    public void setSigPerimetroBrazo(Float sigPerimetroBrazo) {
        this.sigPerimetroBrazo = sigPerimetroBrazo;
    }
    public Float getSigGlucosaCapilar() {
        return this.sigGlucosaCapilar;
    }
    
    public void setSigGlucosaCapilar(Float sigGlucosaCapilar) {
        this.sigGlucosaCapilar = sigGlucosaCapilar;
    }
    public Float getSigValorHemoglobina() {
        return this.sigValorHemoglobina;
    }
    
    public void setSigValorHemoglobina(Float sigValorHemoglobina) {
        this.sigValorHemoglobina = sigValorHemoglobina;
    }
    public Float getSigValorHemoglobinaCorr() {
        return this.sigValorHemoglobinaCorr;
    }
    
    public void setSigValorHemoglobinaCorr(Float sigValorHemoglobinaCorr) {
        this.sigValorHemoglobinaCorr = sigValorHemoglobinaCorr;
    }
    public Date getSigFechaUlt() {
        return this.sigFechaUlt;
    }
    
    public void setSigFechaUlt(Date sigFechaUlt) {
        this.sigFechaUlt = sigFechaUlt;
    }
    public String getSigUsuario() {
        return this.sigUsuario;
    }
    
    public void setSigUsuario(String sigUsuario) {
        this.sigUsuario = sigUsuario;
    }
    public Set getHistoriases() {
        return this.historiases;
    }
    
    public void setHistoriases(Set historiases) {
        this.historiases = historiases;
    }




}


