/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CiudadesDAO;
import dao.EstadosCivilesDAO;
import dao.CitaDAO;
import dao.OcupacionDAO;
import dao.ParentescoDAO;
import dao.SignosDAO;
import datos.Ciudades;
import datos.Diagnosticos;
import datos.Estadocivil;
import datos.Historias;
import datos.Ocupaciones;
import datos.Parentescos;
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
@ManagedBean(name = "CitaBean")
@SessionScoped
public final class CitaBean implements Serializable{
    
    private List<Historias> historias;
    private List<Historias> historiasdia;
    private String nombre_enfermedad;
    private Diagnosticos diagnostico;
    private Historias historia;
    private Signos signos;
    private int historia_actual_id;
    private List<Ocupaciones> lista_ocupaciones;
    private List<Parentescos> lista_parentescos;
    private RevisionSistemas revision;
    
    private String renderizar_profesion_abierta;
    private String renderizar_parentesco_abierto;
    
    private String profesion_abierta;
    private String parentesco_abierto;
    
    private List<Ciudades> lista_ciudades;
    private List<Estadocivil> lista_estados_civiles;
    
    // Variable para setear los checkbox de la revisión del sistema
    private ArrayList<Boolean> revision_checks;
    
    public CitaBean(){
        inicializarHistorias();
        inicializarSignos();
    }
    
    public void inicializarHistorias(){
        historias = new ArrayList<>();
        historiasdia = new ArrayList<>();
        revision_checks = new ArrayList<>();        
        diagnostico = new Diagnosticos();
        nombre_enfermedad = "";
        profesion_abierta = "";
        parentesco_abierto = "";
        inicializarProfesionesYParentescos();
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
        CitaDAO.crearActualizarHistoriaConEnfermedad(historia);
        FacesMessages.info(":growlInfo", "Sistemas Digestivo"+historia.getRevisionSistemas().getRevSisDigestivo(), "This is a specific message!");
        //FacesMessages.info(":growlInfo", "Se han actualizado la historia clínica", "This is a specific message!");
    }
    
    public void recuperarHistorias() {

        historias.clear();
        
        historias = CitaDAO.recuperarHistorias();
    }
    
    public String getNombreCompleto(Historias h){
        return h.getPersonasByPacientePerId().getPerNombres()
                +" "+h.getPersonasByPacientePerId().getPerApellidos();
    }
    
     public String getNombreCompletoMedico(Historias h){
        try{
            return h.getPersonasByMedicoPerId().getPerNombres()
                    +" "+h.getPersonasByMedicoPerId().getPerApellidos();
        }
        catch(Exception e){
            return "Por asignar";
        }
    }
    
    public void actualizarCita(){
        //diagnostico.setHistorias(historia);
        //DiagnosticoDAO.crearActualizarDiagnostico(diagnostico);
        historia.setRevisionSistemas(revision);
        setRevisionChecks();
        CitaDAO.crearActualizarHistoriaConEnfermedad(historia);
        FacesMessages.info(":growlInfo", "Se ha actualizado la cita médica", "This is a specific message!");
    }
    
    public void recuperarHistoriasDia() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dia = new Date();

        historiasdia.clear();
        
        historiasdia = CitaDAO.recuperarHistoriasDia(formatter.format(dia));
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
        historia = CitaDAO.recuperarHistoriaID(hisId);
        nombre_enfermedad = historia.getEnfermedades().getEnfNombre();
        revision = historia.getRevisionSistemas();
        
        setRevisionChecks();
        
        signos = historia.getSignos();
        
        try {
            Integer.valueOf(historia.getPersonasByPacientePerId().getPerProfesion());
        } catch (NumberFormatException e) {
            renderizar_profesion_abierta = "true";
            profesion_abierta = historia.getPersonasByPacientePerId().getPerProfesion();
            historia.getPersonasByPacientePerId().setPerProfesion("51");
        }
        try {
            Integer.valueOf(historia.getPersonasByPacientePerId().getPerParentesco());
        } catch (NumberFormatException e) {
            renderizar_parentesco_abierto = "true";
            parentesco_abierto = historia.getPersonasByPacientePerId().getPerParentesco();
            historia.getPersonasByPacientePerId().setPerParentesco("9");
        }
        
        return "/medico/citaMedica.xhtml?faces-redirect=true";
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
    
    public void especificar_ocupacion(){
        if(historia.getPersonasByPacientePerId().getPerProfesion().equals("51")){
            renderizar_profesion_abierta = "true";
        }
        else{
            profesion_abierta = "";
            renderizar_profesion_abierta = "false";
        }
    }
    
    public void especificar_parentesco(){
        if(historia.getPersonasByPacientePerId().getPerParentesco().equals("9")){
            renderizar_parentesco_abierto = "true";
        }
        else{
            parentesco_abierto = "";
            renderizar_parentesco_abierto = "false";
        }
    }
    
    public void calcularPresionArterialMedia(){
        if(signos.getSigPresionSistolica() != 0 && signos.getSigPresionDiastolica() != 0){
            signos.setSigPresionArterialMedia((signos.getSigPresionSistolica()
                                        +(2*signos.getSigPresionDiastolica()))/3);
        }
    }
    
    public void calcularHemoglobinaCorregido(){
        signos.setSigValorHemoglobinaCorr(signos.getSigValorHemoglobina()-1.3f);
    }
    
    public void calcularIMC(){
        if(signos.getSigPeso() != 0 && signos.getSigEstatura() != 0){
            signos.setSigImc(Math.round(signos.getSigPeso() /(int)(Math.pow(signos.getSigEstatura(), 2))));
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
    
    public void inicializarProfesionesYParentescos(){        
        lista_ocupaciones = OcupacionDAO.recuperarOcupaciones();
        lista_parentescos = ParentescoDAO.recuperarParentescos();
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

    public Diagnosticos getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnosticos diagnostico) {
        this.diagnostico = diagnostico;
    }    

    public String getRenderizar_profesion_abierta() {
        return renderizar_profesion_abierta;
    }

    public void setRenderizar_profesion_abierta(String renderizar_profesion_abierta) {
        this.renderizar_profesion_abierta = renderizar_profesion_abierta;
    }

    public String getRenderizar_parentesco_abierto() {
        return renderizar_parentesco_abierto;
    }

    public void setRenderizar_parentesco_abierto(String renderizar_parentesco_abierto) {
        this.renderizar_parentesco_abierto = renderizar_parentesco_abierto;
    }

    public String getProfesion_abierta() {
        return profesion_abierta;
    }

    public void setProfesion_abierta(String profesion_abierta) {
        this.profesion_abierta = profesion_abierta;
    }

    public String getParentesco_abierto() {
        return parentesco_abierto;
    }

    public void setParentesco_abierto(String parentesco_abierto) {
        this.parentesco_abierto = parentesco_abierto;
    }

    public List<Parentescos> getLista_parentescos() {
        return lista_parentescos;
    }

    public void setLista_parentescos(List<Parentescos> lista_parentescos) {
        this.lista_parentescos = lista_parentescos;
    }
}
