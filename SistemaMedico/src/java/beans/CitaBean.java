/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CiudadesDAO;
import dao.EstadoCivilDAO;
import dao.CitaDAO;
import dao.DiagnosticoDAO;
import dao.OcupacionDAO;
import dao.ParentescoDAO;
import dao.PersonaDAO;
import dao.RevisionSistemasDAO;
import dao.SignosDAO;
import dao.TratamientoDAO;
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
import datos.Tratamientos;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import net.bootsfaces.utils.FacesMessages;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "CitaBean")
@SessionScoped
public final class CitaBean implements Serializable{
    
    // Inyección de dependencia para usar el historiaBean
//    @ManagedProperty(value = "#{CitaBean}")
    private Historias historia;
    
    private List<Historias> historias;
    private List<Historias> historiasdia;
    private String nombre_enfermedad;
    private Diagnosticos diagnostico;
    private Tratamientos tratamiento;
    private Diagnosticos nuevo_diagnostico;
    private Tratamientos nuevo_tratamiento;
    private Signos signos;
    private int historia_actual_id;
    private List<Ocupaciones> lista_ocupaciones;
    private List<Parentescos> lista_parentescos;
    private RevisionSistemas revision;
    
    private String renderizar_profesion_abierta;
    private String renderizar_parentesco_abierto;
    
    private String profesion_abierta;
    private String parentesco_abierto;
    private String nombre_diagnostico;
    private String nombre_tratamiento;
    
    private List<Ciudades> lista_ciudades;
    private List<Estadocivil> lista_estados_civiles;
    
    // Variable para setear los checkbox de la revisión del sistema
    private ArrayList<Boolean> revision_checks;
    
    FacesContext context;
    HttpSession session;
    
    Format formatter = new SimpleDateFormat("yyyy-MM-dd");
    
    public CitaBean(){
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        inicializarHistorias();
        inicializarSignos();
    }
    
    /**
     * Método para inicializar las citas vacías 
     */
    public void inicializarHistorias(){
        System.out.println("Inicializando citabean");
        historias = new ArrayList<>();
        historiasdia = new ArrayList<>();
        revision_checks = new ArrayList<>();        
        diagnostico = new Diagnosticos();
        nombre_enfermedad = "";
        renderizar_profesion_abierta = "false";
        renderizar_parentesco_abierto = "false";
        profesion_abierta = "";
        parentesco_abierto = "";
        inicializarProfesionesYParentescos();
        inicializarCiudades();
        inicializarEstadosCiviles();
    }
    
    /**
     * Método para inicializar una toma de signos vacía
     */
    public void inicializarSignos(){
        signos = new Signos();
    }
    
    /**
     * Método para actualizar los signos 
     */
    public void actualizarSignosHistoria(){
        SignosDAO.crearActualizarSignos(signos);
        FacesMessages.info(":growlInfo", "Se han actualizado los signos del paciente", "This is a specific message!");
    }
    
    /**
     * Método para actualizar la cita
     */
    public void actualizarHistoria(){
        CitaDAO.crearActualizarHistoriaConDatos(historia);
        FacesMessages.info(":growlInfo", "Sistemas Digestivo"+historia.getRevisionSistemas().getRevSisDigestivo(), "This is a specific message!");
        //FacesMessages.info(":growlInfo", "Se han actualizado la historia clínica", "This is a specific message!");
    }
    
    /**
     * Método para recuperar todas las historias (En caso de estar logueado
     * como médico, recuperará únicamente las historias asignadas a ese médico)
     */
    public void recuperarHistorias() {
//        if(session.getAttribute("rol").toString().equals("2")){
//            historias.clear();
//            historias = CitaDAO.recuperarHistoriasMedico((Integer)session.getAttribute("per_id"));
//        }
//        else{
        historias.clear();
        
        historias = CitaDAO.recuperarHistorias();
        //}
    }
    
    /**
     * Método para recuperar lso diagnósticos registrados en la base
     * @return 
     */
    public List<String> recuperarNombresDiagnosticos() {
        return CitaDAO.recuperarNombresDiagnosticos();
    }
    
    /**
     * Método para recuperar lso diagnósticos registrados en la base
     * @return 
     */
    public List<String> recuperarNombresTratamientos() {
        return CitaDAO.recuperarNombresTratamientos();
    }
    
    /**
     * Método que realiza una acción cuando un diagnóstico es seleccionado
     * @param historias
     */
    public void recuperarDiagnosticosListener(){
        String diagnostico_codigo[] = getNombre_diagnostico().split(" - ");
        if (diagnostico_codigo.length == 2) {
            diagnostico = CitaDAO.recuperarDiagnosticoCodigoCie(diagnostico_codigo[0]);

            if (diagnostico != null) {
                historia.setDiagnosticos(diagnostico);
                }
            else {
                FacesMessages.info(":growlInfo", "No se ha encontrado el diagnóstico", "This is a specific message!");
                nombre_diagnostico = "";
            }
        }
        else {
            FacesMessages.info(":growlInfo", "Nombre no válido", "This is a specific message!");
            nombre_diagnostico = "";
        }
    }
    
    /**
     * Método que realiza una acción cuando un diagnóstico es seleccionado
     */
    public void recuperarTratamientosListener(){
        String tratamiento_codigo[] = getNombre_tratamiento().split(" - ");
        if (tratamiento_codigo.length == 2) {
            tratamiento = CitaDAO.recuperarTratamientoCodigoCie(tratamiento_codigo[0]);

            if (tratamiento != null) {
                historia.setTratamientos(tratamiento);
                }
            else {
                FacesMessages.info(":growlInfo", "No se ha encontrado el tratamiento", "This is a specific message!");
                nombre_tratamiento = "";
            }
        }
        else {
            FacesMessages.info(":growlInfo", "Nombre no válido", "This is a specific message!");
            nombre_tratamiento = "";
        }
    }
    
    /**
     * Método para recuperar todas las historias del día(En caso de estar logueado
     * como médico, recuperará únicamente las historias asignadas a ese médico)
     */
    public void recuperarHistoriasDia() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dia = new Date();

//        if(session.getAttribute("rol").toString().equals("2")){
//            historiasdia.clear();
//            historiasdia = CitaDAO.recuperarHistoriasMedicoDia(formatter.format(dia),(Integer)session.getAttribute("per_id"));
//        }
//        else{
            historiasdia.clear();
        
            historiasdia = CitaDAO.recuperarHistoriasDia(formatter.format(dia));
        //}
        
    }
    
    /**
     * Método para recuperar el nombre completo del paciente de una cita
     * @param h
     * @return 
     */
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
    
    public String actualizarCita(){
        if (profesion_abierta.length() > 0) {
            historia.getPersonasByPacientePerId().setPerProfesion(profesion_abierta);
        }
        if (parentesco_abierto.length() > 0) {
            historia.getPersonasByPacientePerId().setPerParentesco(parentesco_abierto);
        }
        // Si existe un diagnóstico lo guardará
        revision.setRevSisUsuario(session.getAttribute("usuario").toString());
        RevisionSistemasDAO.crearActualizarRevision(revision);
        historia.setRevisionSistemas(revision);
        SignosDAO.crearActualizarSignos(signos);
        historia.setSignos(signos);
        if(tratamiento != null){
            TratamientoDAO.crearActualizarTratamiento(tratamiento);
        }
        historia.setTratamientos(tratamiento);
        setRevisionChecks();
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        
        CitaDAO.crearActualizarHistoria(historia);
        FacesMessages.info(":growlInfo", "Se ha actualizado la cita médica", "This is a specific message!");
        
        return "/estudiante/listadoCitas.xhtml?faces-redirect=true";
    }
    
    /**
     * Método para crear un nuevo diagnóstico CIE 10ma edición
     */
    public void crearDiagnosticoCIE10(){
        nuevo_diagnostico.setDiaEdicionCie("10");
        nuevo_diagnostico.setDiaFechaUlt(new Date());
        nuevo_diagnostico.setDiaUsuario(session.getAttribute("usuario").toString());
        DiagnosticoDAO.crearActualizarDiagnostico(nuevo_diagnostico);
        recuperarNombresDiagnosticos();
        FacesMessages.info(":growlInfo", "Diagnóstico Creado", "This is a specific message!");
    }
    
    /**
     * Método para crear un nuevo tratamiento CIE 10ma edición
     */
    public void crearTratamientoCIE10(){
        nuevo_tratamiento.setTraEdicionCie("10");
        nuevo_tratamiento.setTraFechaUlt(new Date());
        nuevo_tratamiento.setTraUsuario(session.getAttribute("usuario").toString());
        TratamientoDAO.crearActualizarTratamiento(nuevo_tratamiento);
        recuperarNombresTratamientos();
        FacesMessages.info(":growlInfo", "Tratamiento Creado", "This is a specific message!");
    }
    
    public void imprimirDiagnostico() throws net.sf.jasperreports.engine.JRException, IOException {
        Map<String, Object> parametros = new HashMap<>();

        String pathJRXML = "c:\\formatoReportes\\recetaMedica.jrxml";

        JRBeanCollectionDataSource listaItems = new JRBeanCollectionDataSource(null);
        parametros.put("paciente",
                historia.getPersonasByPacientePerId().getPerNombres()
                +historia.getPersonasByPacientePerId().getPerApellidos());// se agrega la coleccion de datos a los parametros
        parametros.put("edad", historia.getPersonasByPacientePerId().getPerEdad());// se agrega la coleccion de datos a los parametros
        parametros.put("fecha", formatter.format(new Date()));
        parametros.put("medicamentos",historia.getTratamientos().getTraMedicamento());
        parametros.put("indicaciones", historia.getHisIndicaciones());

        JasperReport jasperReport;
        //jasperReport = JasperCompileManager.compileReport(pathJRXML);
        InputStream input = new FileInputStream(new File(pathJRXML));
        JasperDesign jasperDesing = JRXmlLoader.load(input);
        jasperReport = JasperCompileManager.compileReport(jasperDesing);
        //File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rptJSF.jasper"));
        //File jasper = new File(pathJASPER);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
                .getResponse();

        response.addHeader("Content-disposition", "inline; filename=RecetaMedica.pdf");
        BufferedOutputStream output = null;
        response.setHeader("pragma", "public");
        output = new BufferedOutputStream(response.getOutputStream(), 10240);
        byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
        response.getOutputStream().write(bytes);
        response.setContentType("application/pdf");
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    /**
     * Id de la historia a cargar
     * @param id 
     */
    public void recuperarSignosId(int id){
        signos = SignosDAO.recuperarSignosId(id);
    }
    
    public String VerCitaMedica(int hisId) {
        
        nuevo_diagnostico = new Diagnosticos();
        nuevo_tratamiento = new Tratamientos();
        this.historia_actual_id = hisId;
        historia = CitaDAO.recuperarHistoriaID(hisId);
        signos = SignosDAO.recuperarSignosId(historia.getSignos().getSigId());
        revision = RevisionSistemasDAO.recuperarRevision(historia.getRevisionSistemas().getRevSisId());
        if(historia.getDiagnosticos() != null){
            diagnostico = historia.getDiagnosticos();
        }
        if(historia.getTratamientos() != null){
            tratamiento = TratamientoDAO.recuperarTratamiento(historia.getTratamientos().getTraId());
        }
        
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
        
        PersonaBean p = new PersonaBean();
        p.actualizarEdad(historia.getPersonasByPacientePerId());
        
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
        if(signos.getSigPeso() != 0f && signos.getSigEstatura() != 0f){
            signos.setSigImc(Math.round(signos.getSigPeso() / (float)Math.pow(signos.getSigEstatura()/100, 2)));
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
        String edad;
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
            Period periodo = Period.between(actual, nacimiento);
            edad = periodo.getYears()*-1+ "Años-"+ periodo.getMonths()*-1+"Meses-"+ periodo.getDays()*-1+"Dias"; 
            historia.getPersonasByPacientePerId().setPerEdad(edad);
            }
            catch(Exception e){
            }
        }
        else{
            historia.getPersonasByPacientePerId().setPerEdad("0");
        }
    }
    
    public void inicializarCiudades(){
        lista_ciudades = CiudadesDAO.recuperarCiudades();
    }
    
    public void inicializarEstadosCiviles(){
        lista_estados_civiles = EstadoCivilDAO.recuperarEstados();
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

    public Tratamientos getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamientos tratamiento) {
        this.tratamiento = tratamiento;
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

    public String getNombre_diagnostico() {
        return nombre_diagnostico;
    }

    public void setNombre_diagnostico(String nombre_diagnostico) {
        this.nombre_diagnostico = nombre_diagnostico;
    }

    public Diagnosticos getNuevo_diagnostico() {
        return nuevo_diagnostico;
    }

    public void setNuevo_diagnostico(Diagnosticos nuevo_diagnostico) {
        this.nuevo_diagnostico = nuevo_diagnostico;
    }

    public Tratamientos getNuevo_tratamiento() {
        return nuevo_tratamiento;
    }

    public void setNuevo_tratamiento(Tratamientos nuevo_tratamiento) {
        this.nuevo_tratamiento = nuevo_tratamiento;
    }

    public String getNombre_tratamiento() {
        return nombre_tratamiento;
    }

    public void setNombre_tratamiento(String nombre_tratamiento) {
        this.nombre_tratamiento = nombre_tratamiento;
    }
    
    
    
    
    
}
