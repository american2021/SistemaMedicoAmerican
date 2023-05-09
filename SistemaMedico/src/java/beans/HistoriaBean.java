/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CiudadesDAO;
import dao.EstadosCivilesDAO;
import dao.HistoriaDAO;
import dao.OcupacionDAO;
import dao.SignosDAO;
import datos.Ciudades;
import datos.Estadocivil;
import datos.Historias;
import datos.Ocupaciones;
import datos.RevisionSistemas;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import datos.Signos;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import net.bootsfaces.utils.FacesMessages;
import java.util.Date;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "HistoriaBean")
@SessionScoped
public final class HistoriaBean implements Serializable{
    
    private List<Historias> historias;
    private List<Historias> historiasdia;
    private String nombre_enfermedad;
    private Historias historia;
    private Signos signos;
    private int historia_actual_id;
    private List<Ocupaciones> lista_ocupaciones;
    private RevisionSistemas revision;
    
    private List<Ciudades> lista_ciudades;
    private List<Estadocivil> lista_estados_civiles;
    
    // Variable para setear los checkbox de la revisión del sistema
    private ArrayList<Boolean> revision_checks;
    
    public HistoriaBean(){
        inicializarHistorias();
        inicializarSignos();
    }
    
    public void inicializarHistorias(){
        historias = new ArrayList<>();
        historiasdia = new ArrayList<>();
        revision_checks = new ArrayList<>();        
        nombre_enfermedad = "";
        inicializarProfesiones();
        inicializarCiudades();
        inicializarEstadosCiviles();
    }
    
    public void inicializarSignos(){
        signos = new Signos();
    }
    
    public void actualizarSignosHistoria(){
        SignosDAO.crearActualizarSignos(signos);
        FacesMessages.info(":growlInfo", "Se han actualizado los signos del paciente", "This is a specific message!");
    }
    
    public void actualizarHistoria(){
        HistoriaDAO.crearActualizarHistoriaConEnfermedad(historia, nombre_enfermedad);
        FacesMessages.info(":growlInfo", "Sistemas Digestivo"+historia.getRevisionSistemas().getRevSisDigestivo(), "This is a specific message!");
        //FacesMessages.info(":growlInfo", "Se han actualizado la historia clínica", "This is a specific message!");
    }
    
    public void recuperarHistorias() {

        historias.clear();
        
        historias = HistoriaDAO.recuperarHistorias();
    }
    
    public String getNombreCompleto(int id){
        Historias h = HistoriaDAO.recuperarHistoriaID(id);
        return h.getPersonasByPacientePerId().getPerNombres()
                +" "+h.getPersonasByPacientePerId().getPerApellidos();
    }
    
    public void actualizarCita(){
        historia.setRevisionSistemas(revision);
        setRevisionChecks();
        HistoriaDAO.crearActualizarHistoriaConEnfermedad(historia, nombre_enfermedad);
        FacesMessages.info(":growlInfo", "Se ha actualizado la cita médica", "This is a specific message!");
    }
    
    public void recuperarHistoriasDia() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dia = new Date();

        historiasdia.clear();
        
        historiasdia = HistoriaDAO.recuperarHistoriasDia(formatter.format(dia));
    }
    
    public void recuperarHistoriaID(int id){
        historia_actual_id = id;
        historia = HistoriaDAO.recuperarHistoriaID(id);
        nombre_enfermedad = historia.getEnfermedades().getEnfNombre();
    }
    
    /**
     * Id de la historia a cargar
     * @param id 
     */
    public void recuperarSignosId(int id){
        signos = SignosDAO.recuperarSignosId(id);
    }
    
    public String VerCitaMedica(int hisId) {
        this.historia_actual_id = hisId;
        historia = HistoriaDAO.recuperarHistoriaID(hisId);
        nombre_enfermedad = historia.getEnfermedades().getEnfNombre();
        revision = historia.getRevisionSistemas();
        
        setRevisionChecks();
        
        signos = historia.getSignos();
        return "/faces/medico/citaMedica.xhtml?faces-redirect=true";
    }
    
    public void cambiarCheck(int id_check){
        switch(id_check){
            case 0:
                revision.setRevSisSentidos("");
            break;
            case 1:
                revision.setRevSisRespiratorio("");
            break;
            case 2:
                revision.setRevSisCardiovascular("");
            break;
            case 3:
                revision.setRevSisDigestivo("");
            break;
            case 4:
                revision.setRevSisGenital("");
            break;
            case 5:
                revision.setRevSisUsuario("");
            break;
            case 6:
                revision.setRevSisEsqueletico("");
            break;
            case 7:
                revision.setRevSisMuscular("");
            break;
            case 8:
                revision.setRevSisNervioso("");
            break;
            case 9:
                revision.setRevSisEndocrino("");
            break;
            case 10:
                revision.setRevSisHemolinfatico("");
            break;
            case 11:
                revision.setRevSisTegumentario("");
            break;
        }
    }
    
    public void setRevisionChecks(){
        revision_checks.clear();
        //Definiendo los items de la revision
        if(revision.getRevSisSentidos().length() != 0){
            revision_checks.add(true);
        }
        else{
            revision_checks.add(false);
        }
        if(revision.getRevSisRespiratorio().length() != 0){
            revision_checks.add(true);
        }
        else{
            revision_checks.add(false);
        }
        if(revision.getRevSisCardiovascular().length() != 0){
            revision_checks.add(true);
        }
        else{
            revision_checks.add(false);
        }
        if(revision.getRevSisDigestivo().length() != 0){
            revision_checks.add(true);
        }
        else{
            revision_checks.add(false);
        }
        if(revision.getRevSisGenital().length() != 0){
            revision_checks.add(true);
        }
        else{
            revision_checks.add(false);
        }
        if(revision.getRevSisUrinario().length() != 0){
            revision_checks.add(true);
        }
        else{
            revision_checks.add(false);
        }
        if(revision.getRevSisEsqueletico().length() != 0){
            revision_checks.add(true);
        }
        else{
            revision_checks.add(false);
        }
        if(revision.getRevSisMuscular().length() != 0){
            revision_checks.add(true);
        }
        else{
            revision_checks.add(false);
        }
        if(revision.getRevSisNervioso().length() != 0){
            revision_checks.add(true);
        }
        else{
            revision_checks.add(false);
        }
        if(revision.getRevSisEndocrino().length() != 0){
            revision_checks.add(true);
        }
        else{
            revision_checks.add(false);
        }
        if(revision.getRevSisHemolinfatico().length() != 0){
            revision_checks.add(true);
        }
        else{
            revision_checks.add(false);
        }
        if(revision.getRevSisTegumentario().length() != 0){
            revision_checks.add(true);
        }
        else{
            revision_checks.add(false);
        }
    }
    
    public void calcularEdad(){
        int edad;
        if(historia.getPersonasByPacientePerId().getPerNac()!=null){
        try{
            LocalDate actual = new Date()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
            LocalDate nacimiento = historia.getPersonasByPacientePerId().getPerNac()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
            edad = Period.between(actual, nacimiento).getYears()*-1;
            historia.getPersonasByPacientePerId().setPerEdad(edad);
            }
            catch(Exception e){
            }
        }
        else{
            historia.getPersonasByPacientePerId().setPerEdad(0);
        }
    }
    
    public void inicializarCiudades(){
        lista_ciudades = CiudadesDAO.recuperarCiudades();
    }
    
    public void inicializarEstadosCiviles(){
        lista_estados_civiles = EstadosCivilesDAO.recuperarEstados();
    }
    
    public void inicializarProfesiones(){
        lista_ocupaciones = OcupacionDAO.recuperarOcupaciones();
    }

    public List<Historias> getHistorias() {
        return historias;
    }

    public void setHistorias(List<Historias> historias) {
        this.historias = historias;
    }    

    public Signos getSignos() {
        return signos;
    }

    public void setSignos(Signos signos) {
        this.signos = signos;
    }

    public Historias getHistoria() {
        return historia;
    }

    public void setHistoria(Historias historia) {
        this.historia = historia;
    }

    public List<Historias> getHistoriasdia() {
        return historiasdia;
    }

    public void setHistoriasdia(List<Historias> historiasdia) {
        this.historiasdia = historiasdia;
    }

    public String getNombre_enfermedad() {
        return nombre_enfermedad;
    }

    public void setNombre_enfermedad(String nombre_enfermedad) {
        this.nombre_enfermedad = nombre_enfermedad;
    }

    public int getHistoria_actual_id() {
        return historia_actual_id;
    }

    public void setHistoria_actual_id(int historia_actual_id) {
        this.historia_actual_id = historia_actual_id;
    }

    public List<Ocupaciones> getLista_ocupaciones() {
        return lista_ocupaciones;
    }

    public void setLista_ocupaciones(List<Ocupaciones> lista_ocupaciones) {
        this.lista_ocupaciones = lista_ocupaciones;
    }
    public RevisionSistemas getRevision() {
        return revision;
    }

    public void setRevision(RevisionSistemas revision) {
        this.revision = revision;
    }

    public List<Ciudades> getLista_ciudades() {
        return lista_ciudades;
    }

    public void setLista_ciudades(List<Ciudades> lista_ciudades) {
        this.lista_ciudades = lista_ciudades;
    }

    public List<Estadocivil> getLista_estados_civiles() {
        return lista_estados_civiles;
    }

    public void setLista_estados_civiles(List<Estadocivil> lista_estados_civiles) {
        this.lista_estados_civiles = lista_estados_civiles;
    }
    
    public ArrayList<Boolean> getRevision_checks() {
        return revision_checks;
    }

    public void setRevision_checks(ArrayList<Boolean> revision_checks) {
        this.revision_checks = revision_checks;
    }
}
