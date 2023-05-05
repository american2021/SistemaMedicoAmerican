package datos;
// Generated 05-may-2023 11:01:23 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Antecedentes generated by hbm2java
 */
public class Antecedentes  implements java.io.Serializable {


     private Integer antId;
     private Personas personas;
     private String antPersonal;
     private String antFamiliar;
     private char antPubarquia;
     private char antVelloAxilar;
     private char antEspermaquia;
     private String antObsDesarrollo;
     private Character antSecrecionPeneana;
     private char antHistoriaAnticonceptiva;
     private char antMetodoAnticonceptivo;
     private char antApoyoPareja;
     private String antObsAndrologico;
     private char antAcesora;
     private char antInformante;
     private char antTienePareja;
     private int antCantidadParejas;
     private char antTipoExperienciaSexual;
     private int antEdadPrimeraVez;
     private char antMasturbacion;
     private char antViolenciaGenero;
     private int antLapsoAbuso;
     private char antTratamientoPsicologico;
     private char antCalidadInformacion;
     private char antTipoRelacionesSexuales;
     private char antDificultadRelaciones;
     private char antRelacionesVoluntarias;
     private String antObsSexualidad;
     private Date antFechaUlt;
     private String antUsuario;

    public Antecedentes() {
    }

	
    public Antecedentes(Personas personas, String antPersonal, String antFamiliar, char antPubarquia, char antVelloAxilar, char antEspermaquia, String antObsDesarrollo, char antHistoriaAnticonceptiva, char antMetodoAnticonceptivo, char antApoyoPareja, char antAcesora, char antInformante, char antTienePareja, int antCantidadParejas, char antTipoExperienciaSexual, int antEdadPrimeraVez, char antMasturbacion, char antViolenciaGenero, int antLapsoAbuso, char antTratamientoPsicologico, char antCalidadInformacion, char antTipoRelacionesSexuales, char antDificultadRelaciones, char antRelacionesVoluntarias) {
        this.personas = personas;
        this.antPersonal = antPersonal;
        this.antFamiliar = antFamiliar;
        this.antPubarquia = antPubarquia;
        this.antVelloAxilar = antVelloAxilar;
        this.antEspermaquia = antEspermaquia;
        this.antObsDesarrollo = antObsDesarrollo;
        this.antHistoriaAnticonceptiva = antHistoriaAnticonceptiva;
        this.antMetodoAnticonceptivo = antMetodoAnticonceptivo;
        this.antApoyoPareja = antApoyoPareja;
        this.antAcesora = antAcesora;
        this.antInformante = antInformante;
        this.antTienePareja = antTienePareja;
        this.antCantidadParejas = antCantidadParejas;
        this.antTipoExperienciaSexual = antTipoExperienciaSexual;
        this.antEdadPrimeraVez = antEdadPrimeraVez;
        this.antMasturbacion = antMasturbacion;
        this.antViolenciaGenero = antViolenciaGenero;
        this.antLapsoAbuso = antLapsoAbuso;
        this.antTratamientoPsicologico = antTratamientoPsicologico;
        this.antCalidadInformacion = antCalidadInformacion;
        this.antTipoRelacionesSexuales = antTipoRelacionesSexuales;
        this.antDificultadRelaciones = antDificultadRelaciones;
        this.antRelacionesVoluntarias = antRelacionesVoluntarias;
    }
    public Antecedentes(Personas personas, String antPersonal, String antFamiliar, char antPubarquia, char antVelloAxilar, char antEspermaquia, String antObsDesarrollo, Character antSecrecionPeneana, char antHistoriaAnticonceptiva, char antMetodoAnticonceptivo, char antApoyoPareja, String antObsAndrologico, char antAcesora, char antInformante, char antTienePareja, int antCantidadParejas, char antTipoExperienciaSexual, int antEdadPrimeraVez, char antMasturbacion, char antViolenciaGenero, int antLapsoAbuso, char antTratamientoPsicologico, char antCalidadInformacion, char antTipoRelacionesSexuales, char antDificultadRelaciones, char antRelacionesVoluntarias, String antObsSexualidad, Date antFechaUlt, String antUsuario) {
       this.personas = personas;
       this.antPersonal = antPersonal;
       this.antFamiliar = antFamiliar;
       this.antPubarquia = antPubarquia;
       this.antVelloAxilar = antVelloAxilar;
       this.antEspermaquia = antEspermaquia;
       this.antObsDesarrollo = antObsDesarrollo;
       this.antSecrecionPeneana = antSecrecionPeneana;
       this.antHistoriaAnticonceptiva = antHistoriaAnticonceptiva;
       this.antMetodoAnticonceptivo = antMetodoAnticonceptivo;
       this.antApoyoPareja = antApoyoPareja;
       this.antObsAndrologico = antObsAndrologico;
       this.antAcesora = antAcesora;
       this.antInformante = antInformante;
       this.antTienePareja = antTienePareja;
       this.antCantidadParejas = antCantidadParejas;
       this.antTipoExperienciaSexual = antTipoExperienciaSexual;
       this.antEdadPrimeraVez = antEdadPrimeraVez;
       this.antMasturbacion = antMasturbacion;
       this.antViolenciaGenero = antViolenciaGenero;
       this.antLapsoAbuso = antLapsoAbuso;
       this.antTratamientoPsicologico = antTratamientoPsicologico;
       this.antCalidadInformacion = antCalidadInformacion;
       this.antTipoRelacionesSexuales = antTipoRelacionesSexuales;
       this.antDificultadRelaciones = antDificultadRelaciones;
       this.antRelacionesVoluntarias = antRelacionesVoluntarias;
       this.antObsSexualidad = antObsSexualidad;
       this.antFechaUlt = antFechaUlt;
       this.antUsuario = antUsuario;
    }
   
    public Integer getAntId() {
        return this.antId;
    }
    
    public void setAntId(Integer antId) {
        this.antId = antId;
    }
    public Personas getPersonas() {
        return this.personas;
    }
    
    public void setPersonas(Personas personas) {
        this.personas = personas;
    }
    public String getAntPersonal() {
        return this.antPersonal;
    }
    
    public void setAntPersonal(String antPersonal) {
        this.antPersonal = antPersonal;
    }
    public String getAntFamiliar() {
        return this.antFamiliar;
    }
    
    public void setAntFamiliar(String antFamiliar) {
        this.antFamiliar = antFamiliar;
    }
    public char getAntPubarquia() {
        return this.antPubarquia;
    }
    
    public void setAntPubarquia(char antPubarquia) {
        this.antPubarquia = antPubarquia;
    }
    public char getAntVelloAxilar() {
        return this.antVelloAxilar;
    }
    
    public void setAntVelloAxilar(char antVelloAxilar) {
        this.antVelloAxilar = antVelloAxilar;
    }
    public char getAntEspermaquia() {
        return this.antEspermaquia;
    }
    
    public void setAntEspermaquia(char antEspermaquia) {
        this.antEspermaquia = antEspermaquia;
    }
    public String getAntObsDesarrollo() {
        return this.antObsDesarrollo;
    }
    
    public void setAntObsDesarrollo(String antObsDesarrollo) {
        this.antObsDesarrollo = antObsDesarrollo;
    }
    public Character getAntSecrecionPeneana() {
        return this.antSecrecionPeneana;
    }
    
    public void setAntSecrecionPeneana(Character antSecrecionPeneana) {
        this.antSecrecionPeneana = antSecrecionPeneana;
    }
    public char getAntHistoriaAnticonceptiva() {
        return this.antHistoriaAnticonceptiva;
    }
    
    public void setAntHistoriaAnticonceptiva(char antHistoriaAnticonceptiva) {
        this.antHistoriaAnticonceptiva = antHistoriaAnticonceptiva;
    }
    public char getAntMetodoAnticonceptivo() {
        return this.antMetodoAnticonceptivo;
    }
    
    public void setAntMetodoAnticonceptivo(char antMetodoAnticonceptivo) {
        this.antMetodoAnticonceptivo = antMetodoAnticonceptivo;
    }
    public char getAntApoyoPareja() {
        return this.antApoyoPareja;
    }
    
    public void setAntApoyoPareja(char antApoyoPareja) {
        this.antApoyoPareja = antApoyoPareja;
    }
    public String getAntObsAndrologico() {
        return this.antObsAndrologico;
    }
    
    public void setAntObsAndrologico(String antObsAndrologico) {
        this.antObsAndrologico = antObsAndrologico;
    }
    public char getAntAcesora() {
        return this.antAcesora;
    }
    
    public void setAntAcesora(char antAcesora) {
        this.antAcesora = antAcesora;
    }
    public char getAntInformante() {
        return this.antInformante;
    }
    
    public void setAntInformante(char antInformante) {
        this.antInformante = antInformante;
    }
    public char getAntTienePareja() {
        return this.antTienePareja;
    }
    
    public void setAntTienePareja(char antTienePareja) {
        this.antTienePareja = antTienePareja;
    }
    public int getAntCantidadParejas() {
        return this.antCantidadParejas;
    }
    
    public void setAntCantidadParejas(int antCantidadParejas) {
        this.antCantidadParejas = antCantidadParejas;
    }
    public char getAntTipoExperienciaSexual() {
        return this.antTipoExperienciaSexual;
    }
    
    public void setAntTipoExperienciaSexual(char antTipoExperienciaSexual) {
        this.antTipoExperienciaSexual = antTipoExperienciaSexual;
    }
    public int getAntEdadPrimeraVez() {
        return this.antEdadPrimeraVez;
    }
    
    public void setAntEdadPrimeraVez(int antEdadPrimeraVez) {
        this.antEdadPrimeraVez = antEdadPrimeraVez;
    }
    public char getAntMasturbacion() {
        return this.antMasturbacion;
    }
    
    public void setAntMasturbacion(char antMasturbacion) {
        this.antMasturbacion = antMasturbacion;
    }
    public char getAntViolenciaGenero() {
        return this.antViolenciaGenero;
    }
    
    public void setAntViolenciaGenero(char antViolenciaGenero) {
        this.antViolenciaGenero = antViolenciaGenero;
    }
    public int getAntLapsoAbuso() {
        return this.antLapsoAbuso;
    }
    
    public void setAntLapsoAbuso(int antLapsoAbuso) {
        this.antLapsoAbuso = antLapsoAbuso;
    }
    public char getAntTratamientoPsicologico() {
        return this.antTratamientoPsicologico;
    }
    
    public void setAntTratamientoPsicologico(char antTratamientoPsicologico) {
        this.antTratamientoPsicologico = antTratamientoPsicologico;
    }
    public char getAntCalidadInformacion() {
        return this.antCalidadInformacion;
    }
    
    public void setAntCalidadInformacion(char antCalidadInformacion) {
        this.antCalidadInformacion = antCalidadInformacion;
    }
    public char getAntTipoRelacionesSexuales() {
        return this.antTipoRelacionesSexuales;
    }
    
    public void setAntTipoRelacionesSexuales(char antTipoRelacionesSexuales) {
        this.antTipoRelacionesSexuales = antTipoRelacionesSexuales;
    }
    public char getAntDificultadRelaciones() {
        return this.antDificultadRelaciones;
    }
    
    public void setAntDificultadRelaciones(char antDificultadRelaciones) {
        this.antDificultadRelaciones = antDificultadRelaciones;
    }
    public char getAntRelacionesVoluntarias() {
        return this.antRelacionesVoluntarias;
    }
    
    public void setAntRelacionesVoluntarias(char antRelacionesVoluntarias) {
        this.antRelacionesVoluntarias = antRelacionesVoluntarias;
    }
    public String getAntObsSexualidad() {
        return this.antObsSexualidad;
    }
    
    public void setAntObsSexualidad(String antObsSexualidad) {
        this.antObsSexualidad = antObsSexualidad;
    }
    public Date getAntFechaUlt() {
        return this.antFechaUlt;
    }
    
    public void setAntFechaUlt(Date antFechaUlt) {
        this.antFechaUlt = antFechaUlt;
    }
    public String getAntUsuario() {
        return this.antUsuario;
    }
    
    public void setAntUsuario(String antUsuario) {
        this.antUsuario = antUsuario;
    }




}


