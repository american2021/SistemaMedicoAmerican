/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.AntecedenteDAO;
import dao.CiudadesDAO;
import dao.EstadoCivilDAO;
import dao.CitaDAO;
import dao.DiagnosticoDAO;
import dao.ExamenesDAO;
import dao.HistoriaDiagnosticoDAO;
import dao.OcupacionDAO;
import dao.ParentescoDAO;
import dao.HistoriaAntecedenteDAO;
import dao.PersonaDAO;
import dao.HistoriaExamenDAO;
import dao.HistoriaTratamientoDAO;
import dao.MedicamentosDAO;
import dao.RevisionSistemasDAO;
import dao.SignosDAO;
import dao.TratamientoDAO;
import dao.UsuarioDAO;
import datos.Antecedente;
import datos.Ciudades;
import datos.Diagnosticos;
import datos.Estadocivil;
import datos.Examenes;
import datos.HistoriaDiagnostico;
import datos.Historias;
import datos.Ocupaciones;
import datos.Parentescos;
import datos.HistoriaAntecedente;
import datos.HistoriaExamen;
import datos.HistoriaTratamiento;
import datos.Medicamentos;
import datos.Personas;
import datos.RevisionSistemas;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import datos.Signos;
import datos.Tratamientos;
import datos.Usuarios;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.bootsfaces.utils.FacesMessages;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "CitaBean")
@SessionScoped
public final class CitaBean implements Serializable{
   
    private Historias historia;
    private List<Historias> historias;
    private List<Historias> historiasdia;
    private String nombre_enfermedad;
    private Diagnosticos diagnostico;
    private Antecedente antecedente;
    private Examenes examenes;
    private List<Antecedente> lista_antecedente;
    private List<Diagnosticos> lista_diagnosticos;
    private List<Diagnosticos> lista_diagnosticos_all;
    private List<HistoriaAntecedente> lista_historia_antecedente;
    private List<HistoriaAntecedente> lista_historial_historia_antecedente;
    private List<Historias> lista_historial_historias;
    private List<Signos> lista_historial_signos;
    private List<RevisionSistemas> lista_historial_revision_sistema;
    private List<HistoriaAntecedente> lista_historial_default_historia_antecedente;
    private List<HistoriaTratamiento> lista_historia_tratamiento;
    private List<HistoriaTratamiento> lista_historial_historia_tratamiento;
    private List<HistoriaAntecedente> lista_alergias_historia_antecedente;
    private List<HistoriaExamen> lista_historia_examen;
    private List<HistoriaExamen> lista_Historial_historia_examen;
    private List<HistoriaDiagnostico> lista_historia_diagnostico;
    private List<HistoriaDiagnostico> lista_historial_historia_diagnostico;
    private List<HistoriaDiagnostico> lista_seleccion_historia_diagnostico;
    private List<String> lista_seleccion_medicamento;
    private Tratamientos tratamiento;
    private HistoriaAntecedente nuevo_historia_antecedente;
    private HistoriaExamen nuevo_historia_examen;
    private HistoriaDiagnostico nuevo_historia_diagnostico;
    private Tratamientos nuevo_tratamiento;
    private Medicamentos nuevo_medicamento;
    private HistoriaTratamiento nuevo_historia_tratamiento;
    private Signos signos;
    private int historia_actual_id;
    private List<Ocupaciones> lista_ocupaciones;
    private List<Parentescos> lista_parentescos;
    private RevisionSistemas revision;
    private HistoriaExamen eliminarHistoriaExamen;
    private HistoriaExamen actualizarHistoriaExamen;
    private HistoriaTratamiento updateHistoriaTratamiento;
    private HistoriaAntecedente updateHistoriaAntecedente;
    private HistoriaDiagnostico updateHistoriaDiagnostico;
    private HistoriaTratamiento deleteHistoriaTratamiento;
    private List<Examenes> lista_examenes;
    private Diagnosticos nuevo_diagnostico;
        
    private String ultimoValorValido;
    private String consultaUsuario;
    
    private Map<Integer, String> editedDescriptions;
    private Map<Long, String> editedDescriptionsExamen;
    private Map<Long, String> editedDescriptionsDiagnostico;
    private Map<Long, String> editedIndicacionesExamen;
    private Map<Long, Date> editedDateExamen;
    private Map<Long, Boolean> editedCheckExamen;
    private Map<Long, String> editedTipoDiagnostico;
    private Map<Long, String> editedCondicionDiagnostico;
    private Map<Long, String> editedCronologiaDiagnostico;
    private Map<Integer, String> editedSelectAntecedente;
    
    Map<String, Antecedente> mapAntecedentePer;
    Map<String, Antecedente> mapAntecedenteFam;
    Map<String, Antecedente> mapAntecedenteGin;
    Map<String, Antecedente> mapAntecedenteVac;
    
    private Map<String, Boolean> menuPanel;
    private Map<String, Boolean> historialLista;
    
    private String descripcionAntecedenteTemp;
    private Boolean realizarExamen;
    private Boolean updatePanel;
    private Boolean renderedEvolucion;
    
    private String renderizar_profesion_abierta;
    private String renderizar_parentesco_abierto;
    private String renderizar_antecedente;
    
    private String profesion_abierta;
    private String antecedente_abierta;
    private String parentesco_abierto;
    private String nombre_diagnostico;
    private String nombre_medicamento;
    private String nombre_historia_diagnostico;
    private String update_name_diagnostic_history;
    private String nombre_antecedente;
    private String nombre_examen;
    private String nombre_tratamiento;
    private String mensajeDiagnostico;
    
    private String panelActual = "panel1";
    
    private List<Ciudades> lista_ciudades;
    private List<Estadocivil> lista_estados_civiles;
    private List<String> lista_categoria_antecedentes;
    
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
        nombre_medicamento = null;
        nombre_historia_diagnostico = null;
        update_name_diagnostic_history = null;
        realizarExamen = false;
        renderedEvolucion = true;
        updatePanel = true;
        historias = new ArrayList<>();
        historiasdia = new ArrayList<>();
        revision_checks = new ArrayList<>();
        lista_diagnosticos = new ArrayList<>();
        lista_diagnosticos_all = new ArrayList<>();
        lista_categoria_antecedentes = new ArrayList<>();
        lista_historia_antecedente = new ArrayList<>();
        lista_historial_historia_antecedente = new ArrayList<>();
        lista_historial_historias = new ArrayList<>();
        lista_historial_signos = new ArrayList<>();
        lista_historial_revision_sistema = new ArrayList<>();
        lista_historial_default_historia_antecedente = new ArrayList<>();
        lista_historia_tratamiento = new ArrayList<>();
        lista_historial_historia_tratamiento = new ArrayList<>();
        lista_alergias_historia_antecedente = new ArrayList<>();
        diagnostico = new Diagnosticos();
        antecedente = new Antecedente();
        examenes = new Examenes();
        lista_historia_examen = new ArrayList<>();
        lista_Historial_historia_examen = new ArrayList<>();
        lista_historia_diagnostico = new ArrayList<>();
        lista_historial_historia_diagnostico = new ArrayList<>();
        lista_seleccion_historia_diagnostico = new ArrayList<>();
        lista_seleccion_medicamento = new ArrayList<>();
        lista_examenes = new ArrayList<>();
        nombre_enfermedad = "";
        renderizar_profesion_abierta = "false";
        renderizar_parentesco_abierto = "false";
        renderizar_antecedente = "false";
        profesion_abierta = "";
        antecedente_abierta = "";
        parentesco_abierto = "";
        nombre_tratamiento ="";
        mensajeDiagnostico = "";
        nuevo_historia_antecedente = new HistoriaAntecedente();
        nuevo_historia_diagnostico = new HistoriaDiagnostico();
        nuevo_historia_tratamiento = new HistoriaTratamiento();
        nuevo_historia_examen = new HistoriaExamen();
        nuevo_tratamiento = new Tratamientos();
        nuevo_medicamento = new Medicamentos();
        nuevo_diagnostico = new Diagnosticos();
        inicializarProfesionesYParentescos();
        inicializarCiudades();
        inicializarEstadosCiviles();
        recuperarAntecedente();
        recuperarExamenes();
        editedDescriptions = new HashMap<>();
        editedDescriptionsExamen = new HashMap<>();
        editedDescriptionsDiagnostico = new HashMap<>();
        editedIndicacionesExamen = new HashMap<>();
        editedTipoDiagnostico = new HashMap<>();
        editedCondicionDiagnostico = new HashMap<>();
        editedCronologiaDiagnostico = new HashMap<>();
        editedSelectAntecedente = new HashMap<>();
        mapAntecedentePer = new HashMap<>();
        mapAntecedenteFam = new HashMap<>();
        mapAntecedenteGin = new HashMap<>();
        mapAntecedenteVac = new HashMap<>();
        menuPanel = new HashMap<>();
        historialLista = new HashMap<>();
        editedDateExamen = new HashMap<>();
        editedCheckExamen = new HashMap<>();
        descripcionAntecedenteTemp = "";
        actualizarHistoriaExamen = new HistoriaExamen();
        updateHistoriaTratamiento = new HistoriaTratamiento();
        updateHistoriaAntecedente = new HistoriaAntecedente();
        updateHistoriaDiagnostico = new HistoriaDiagnostico();
        deleteHistoriaTratamiento = new HistoriaTratamiento();
        cargaPanel();
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
     * Método para actualizar la cita
     * @param panel
     * @return 
     */
    public String actualizarHistoriaPrueba(String panel){
        if (historia.getHisCompletado().toString().contains("0") && !panel.contains("panel11")) {
            if (panel.contains("panel10")) {
                return actualizarCita();
            } else {
                if (panel.contains("panel5")) {
                    revision.setRevSisUsuario(session.getAttribute("usuario").toString());
                    RevisionSistemasDAO.crearActualizarRevision(revision);
                    historia.setRevisionSistemas(revision);
                } else if(panel.contains("panel4")){
                    SignosDAO.crearActualizarSignos(signos);
                    historia.setSignos(signos);
                    setRevisionChecks();
                }
                CitaDAO.crearActualizarHistoriaConDatos(historia);
            }
        } else if (historia.getHisCompletado().toString().contains("1") && panel.contains("panel11")){
            try {
                context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                CitaDAO.crearActualizarHistoria(historia);
                FacesMessages.info(":growlInfo", "Cita médica finalizada", "This is a specific message!");
                return "/medico/listadoPaciente.xhtml?faces-redirect=true";
            } catch (Exception e) {
                FacesMessages.error(":growlInfo", "Error al finalizar la cita médica: "+e.getCause().getMessage(), "This is a specific message!");
            }
        }
        return "Completado";
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
        verificarHistorias();
        historias.clear();
        
        historias = CitaDAO.recuperarHistorias();
        //}
    }
    
    /**
     * Método para recuperar lso diagnósticos registrados en la base
     * @return 
     */
    public List<String> recuperarNombresDiagnosticos() {
        return DiagnosticoDAO.recuperarNombresDiagnosticos();
    } 
    
    /**
     * Método para recuperar los medicamentos registrados en la base
     * @return 
     */
    public List<String> recuperarNombresMedicamentos() {
        return MedicamentosDAO.recuperarNombresMedicamentos();
    }
    
    /**
     * Método para recuperar los medicamentos registrados en la base
     * @return 
     */
    public List<String> recuperarNombresHistoriaDiagnosticos() {
        return HistoriaDiagnosticoDAO.recuperarNombresHistoriaDiagnosticos(historia_actual_id);
    }
    
    /**
     * Método para recuperar los antecedente registrados en la base
     * @return 
     */
    public List<String> recuperarNombresAntecedente() {
        return AntecedenteDAO.recuperarNombresAntecedentes();
    }
    
    /**
     * Método para recuperar los antecedente registrados en la base
     * @return 
     */
    public List<String> recuperarNombresExamen() {
        return ExamenesDAO.recuperarNombresExamenes();
    }
    
    /**
     * Método para recuperar los diagnosticos registrados en la base por el tipo
     * @param codigo_cie
     * @return 
     */
    public List<Diagnosticos> recuperarNombresPorTipoDiagnosticos(String codigo_cie) {
        lista_diagnosticos = DiagnosticoDAO.recuperarNombresPorTipoDiagnostico(codigo_cie);
        return lista_diagnosticos;
    }
    
    /**
     * Método para recuperar los diagnosticos registrados en la base por el tipo
     * @return 
     */
    public List<Diagnosticos> recuperarDiagnosticos() {
        lista_diagnosticos_all = DiagnosticoDAO.recuperarDiagnosticoAll();
        return lista_diagnosticos_all;
    }
    
    /**
     * Método para recuperar las categorias de los antecedente registrados en la base
     * @return 
     */
    public List<String> recuperarCategoriaAntecedente() {
        lista_categoria_antecedentes = AntecedenteDAO.recuperarCategoriaAntecedentes();
        return lista_categoria_antecedentes;
    }
    
    /**
     * Método para recuperar los antecedente registrados en la base de una persona
     * @return 
     */
    public List<HistoriaAntecedente> recuperarHistoriaAntecedente() {
        lista_historia_antecedente = HistoriaAntecedenteDAO.recuperarHistoriaAntecedente(historia.getHisId());
        return lista_historia_antecedente;
    }
    
    /**
     * Método para recuperar los antecedente registrados en la base de una persona
     * @return 
     */
    public List<HistoriaAntecedente> recuperarHistorialHistoriaAntecedente() {
        lista_historial_historia_antecedente = HistoriaAntecedenteDAO.recuperarHistorialHistoriaAntecedente(historia.getHisId(), historia.getPersonasByPacientePerId().getPerId());
        return lista_historial_historia_antecedente;
    }
    
    /**
     * Método para recuperar las historias registrados en la base de una persona
     * @return 
     */
    public List<Historias> recuperarHistorialHistorias() {
        lista_historial_historias =  CitaDAO.recuperarHistorialHistorias(historia.getHisId(), historia.getPersonasByPacientePerId().getPerId());
        return lista_historial_historias;
    }
    
    /**
     * Método para recuperar los signos registrados en la base de una persona
     * @return 
     */
    public List<Signos> recuperarHistorialSignos() {
        lista_historial_signos =  SignosDAO.recuperarHistoriaSignos(historia.getHisId(), historia.getPersonasByPacientePerId().getPerId());
        return lista_historial_signos;
    }
    
    /**
     * Método para recuperar los revision sistema registrados en la base de una persona
     * @return 
     */
    public List<RevisionSistemas> recuperarHistorialRevisionSistema() {
        lista_historial_revision_sistema =  RevisionSistemasDAO.recuperarHistoriaRevisionSistemas(historia.getHisId(), historia.getPersonasByPacientePerId().getPerId());
        return lista_historial_revision_sistema;
    }
    
    /**
     * Método para recuperar los antecedente registrados en la base de una persona
     * @return 
     */
    public List<HistoriaAntecedente> recuperarHistorialDefaultHistoriaAntecedente() {
        lista_historial_default_historia_antecedente = HistoriaAntecedenteDAO.recuperarHistorialDefectoHistoriaAntecedente(historia.getHisId(), historia.getPersonasByPacientePerId().getPerId());
        return lista_historial_default_historia_antecedente;
    }
    
    /**
     * Método para recuperar los antecedente registrados en la base de una persona
     * @return 
     */
    public List<HistoriaTratamiento> recuperarHistoriaTratamiento() {
        lista_historia_tratamiento = HistoriaTratamientoDAO.recuperarHistoriaTratamiento(historia.getHisId());
        return lista_historia_tratamiento;
    }
    
    /**
     * Método para recuperar los antecedente registrados en la base de una persona
     * @return 
     */
    public List<HistoriaTratamiento> recuperarHistorialHistoriaTratamiento() {
        lista_historial_historia_tratamiento = HistoriaTratamientoDAO.recuperarHistorialHistoriaTratamiento(historia.getHisId(), historia.getPersonasByPacientePerId().getPerId());
        return lista_historial_historia_tratamiento;
    }
    
    /**
     * Método para recuperar los antecedente de alergias registrados en la base de una persona
     * @return 
     */
    public List<HistoriaAntecedente> recuperarAlergiasHistoriaAntecedente() {
        lista_alergias_historia_antecedente = HistoriaAntecedenteDAO.recuperarAlergiasHistoriaAntecedente(historia.getPersonasByPacientePerId().getPerId());
        return lista_alergias_historia_antecedente;
    }
    
    /**
     * Método para recuperar los examenes registrados en la base de una persona
     * @return 
     */
    public List<HistoriaExamen> recuperarHistoriaExamenes() {
        lista_historia_examen= HistoriaExamenDAO.recuperarHistoriaExamenes(historia_actual_id, historia.getPersonasByPacientePerId().getPerId());
        return lista_historia_examen;
    }
    
    /**
     * Método para recuperar los examenes registrados en la base de una persona
     * @return 
     */
    public List<HistoriaExamen> recuperarHistorialHistoriaExamenes() {
        lista_Historial_historia_examen= HistoriaExamenDAO.recuperarHistorialHistoriaExamenes(historia.getHisId(), historia.getPersonasByPacientePerId().getPerId());
        return lista_Historial_historia_examen;
    }
    
    /**
     * Método para recuperar los examenes registrados en la base de una persona
     * @return 
     */
    public List<HistoriaDiagnostico> recuperarHistoriaDiagnostico() {
        lista_historia_diagnostico= HistoriaDiagnosticoDAO.recuperarHistoriaDiagnostico(historia.getHisId().toString());
        for (HistoriaDiagnostico historiaDiagnostico : lista_historia_diagnostico) {
            if (historiaDiagnostico.getHisDiaCronologia().contains("1")) {
                renderedEvolucion = false;
            }
        }
        return lista_historia_diagnostico;
    }
    
    /**
     * Método para recuperar los examenes registrados en la base de una persona
     * @return 
     */
    public List<HistoriaDiagnostico> recuperarHistorialHistoriaDiagnostico() {
        lista_historial_historia_diagnostico= HistoriaDiagnosticoDAO.recuperarHistorialHistoriaDiagnostico(historia.getHisId(), historia.getPersonasByPacientePerId().getPerId());
        return lista_historial_historia_diagnostico;
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
     */
    public void recuperarDiagnosticosListener(){
        String diagnostico_codigo[] = getNombre_diagnostico().split(" - ");
        if (diagnostico_codigo.length == 2) {
            diagnostico = DiagnosticoDAO.recuperarDiagnosticoCodigoCie(diagnostico_codigo[0]);
        }
        else {
            FacesMessages.info(":growlInfo", "Nombre no válido", "This is a specific message!");
            nombre_diagnostico = "";
        }
    }
    
    /**
     * Método que realiza una acción cuando un medicamento es seleccionado
     */
    public void recuperarMedicamentosListener(){
        String medicamento_codigo[] = getNombre_medicamento().split(" - ");
        if (medicamento_codigo.length == 3) {
            nuevo_medicamento = MedicamentosDAO.recuperarMedicamento(medicamento_codigo[0],medicamento_codigo[1], revert(medicamento_codigo[2]) );
            nombre_medicamento = nuevo_medicamento.getMedNombre();
        }
        else {
            nombre_medicamento = getNombre_medicamento();
        }
    }
    
    /**
     * Método que realiza una acción cuando un medicamento es seleccionado
     */
    public void recuperarHistoriaDiagnosticoListener(){
        if (lista_historia_diagnostico == null || lista_historia_diagnostico.isEmpty()) {
            mensajeDiagnostico = "No hay diagnósticos disponibles.";
        } else {
            mensajeDiagnostico = null;
        }
        String historio_diagnostico_codigo[] = getNombre_historia_diagnostico().split(" - ");
        if (historio_diagnostico_codigo.length == 2) {
            nuevo_historia_diagnostico = HistoriaDiagnosticoDAO.recuperarHistoriaDiagnosticoListener(historio_diagnostico_codigo[0],historio_diagnostico_codigo[1], historia_actual_id );
        }
        else { 
            FacesMessages.info(":growlInfo", "No se ha encontrado el tratamiento", "This is a specific message!");
            nombre_historia_diagnostico = "";
        }
    }
    
    /**
     * Método que realiza una acción cuando un antecedente es seleccionado
     */
    public void recuperarAntecedentesListener(){
        String antecedenteArray[] = getNombre_antecedente().split(" - ");
        antecedente = AntecedenteDAO.recuperarAntecedenteNombre(antecedenteArray[0], antecedenteArray[1], antecedenteArray[2]);
        if (antecedente == null) {
            FacesMessages.info(":growlInfo", "No se ha encontrado el antecedente", "This is a specific message!");
            nombre_antecedente = "";
        }
    }
    
    /**
     * Método que realiza una acción cuando un examen es seleccionado
     */
    public void recuperarExamenListener(){
        String examenArray[] = getNombre_examen().split(" - ");
        examenes = ExamenesDAO.recuperarExamenNombre(examenArray[0], examenArray[1]);
        if (examenes == null) {
            FacesMessages.info(":growlInfo", "No se ha encontrado el examen", "This is a specific message!");
            nombre_examen = "";
        }
    }
    
    /**
     * Método que realiza una acción cuando un diagnóstico es seleccionado
     */
    public void recuperarTratamientosListener(){
        String tratamiento_codigo[] = getNombre_tratamiento().split(" - ");
        if (tratamiento_codigo.length == 2) {
            tratamiento = CitaDAO.recuperarTratamientoCodigoCie(tratamiento_codigo[0]);

//            if (tratamiento != null) {
//                historia.setTratamientos(tratamiento);
//                }
//            else {
//                FacesMessages.info(":growlInfo", "No se ha encontrado el tratamiento", "This is a specific message!");
//                nombre_tratamiento = "";
//            }
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dia = new Date();

//        if(session.getAttribute("rol").toString().equals("2")){
//            historiasdia.clear();
//            historiasdia = CitaDAO.recuperarHistoriasMedicoDia(formatter.format(dia),(Integer)session.getAttribute("per_id"));
//        }
//        else{
            verificarHistorias();
            historiasdia.clear();
        
            historiasdia = CitaDAO.recuperarHistoriasDia(format.format(dia));
        //}
        
    }
    
    /**
     * Método para recuperar los antecedente registrados en la base por el tipo
     * @return 
     */
    public List<Antecedente> recuperarAntecedente() {
        lista_antecedente = AntecedenteDAO.recuperarAntecedentes();
        return lista_antecedente;
    }
    
    /**
     * Método para recuperar los examenes registrados en la base por el tipo
     * @return 
     */
    public List<Examenes> recuperarExamenes() {
        lista_examenes = ExamenesDAO.recuperarExamenes();
        return lista_examenes;
    }
    
    /**
     * Método para recuperar los examenes registrados en la base por el tipo
     * @param query
     * @return 
     */
    public List<HistoriaDiagnostico> completarHistoriaDiagnostico(String query) {
        return HistoriaDiagnosticoDAO.completarHistoriaDiagnostico(query, historia.getHisId());
    }
    
    /**
     * Método para recuperar los diagnosticos registrados en la base por el query
     * @param query
     * @return 
     */
    public List<Medicamentos> completarMedicamentos(String query) {
        return MedicamentosDAO.completarMedicamentos(query);
    }
    
    /**
     * Método para verificar las hisrtorias que no estan completas
     * pasado una hora se va eliminar las historias
     */
    public void verificarHistorias(){
        CitaDAO.verificarHistoria();
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
    
    public String getFormatoFecha(Date date){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formato.format(date);
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
    
    public String getMedidaMedicamento(String medida){
        return formatoMedidaMedicamento( medida);
    }
    
    public String actualizarCita(){
        if (profesion_abierta.length() > 0) {
            historia.getPersonasByPacientePerId().setPerProfesion(profesion_abierta);
        }
        if (parentesco_abierto.length() > 0) {
            historia.getPersonasByPacientePerId().setPerParentesco(parentesco_abierto);
        }
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        
        if (lista_historia_tratamiento.isEmpty() && historia.getHisIndicacionesNoFarmacologica().isEmpty()) {
            FacesMessages.info(":growlInfo", "No se ha actualizado la cita médica, no tiene tratamiento y/o indicaciones no farmacológicas", "This is a specific message!");
            return "Sin tratamiento";
        }else {
            
            historia.setHisCompletado(Byte.valueOf("1"));
            try {
                CitaDAO.crearActualizarHistoria(historia);
                FacesMessages.info(":growlInfo", "Se ha actualizado la cita médica", "This is a specific message!");
                FacesContext.getCurrentInstance().getExternalContext().redirect("/SistemaMedico/listado-citas-dia");
                HistoriaAntecedenteDAO.crearDefaultHistoriaAntecedente(historia_actual_id);
                return "Completado"; // No olvides devolver null para indicar que no hay navegación implícita.
            } catch (Exception e) {
                FacesMessages.error(":growlInfo", "Error al finalizar la cita médica: "+e.getCause().getMessage(), "This is a specific message!");
                return "Completado";
            }
        }
        
        
    }

    /**
     * Método para crear un nuevo Historia antecedente
     */
    public void crearHistoriaAntecedente(){

        nuevo_historia_antecedente.setAntecedente(antecedente);
        nuevo_historia_antecedente.setHisAntFechaUlt(new Date());
        nuevo_historia_antecedente.setHisAntUsuario(session.getAttribute("usuario").toString());
        nuevo_historia_antecedente.setHistorias(historia);
        try {
            HistoriaAntecedenteDAO.crearActualizarHistoriaAntecedente(nuevo_historia_antecedente);
            nuevo_historia_antecedente = new HistoriaAntecedente();
            setNombre_antecedente("");
            recuperarHistoriaAntecedente();
            recuperarAlergiasHistoriaAntecedente();
            FacesMessages.info(":growlInfo", "Antecedente Creado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.error(":growlInfo", "Error al crear el antecedente: "+e.getCause().getMessage(), "This is a specific message!");
        }
    }
    
    private boolean validarAntecedente(Antecedente antecedente, int id) {
        return antecedente != null && antecedente.getAntId() != null && StringUtils.isNotBlank(editedDescriptions.get(id));
    }
        
    /**
     * Método para crear un nuevo Historia antecedente
     * @param keyMap
     * @param tipo
     * @param id
     */
    public void crearHistoriaAntecedentePrueba(String keyMap, String tipo, int id){
        Antecedente antecedent = AntecedenteDAO.recuperarAntecedenteGrupoCategoria(editedSelectAntecedente.get(id), keyMap, tipo);
        if (validarAntecedente(antecedent, id)) {
            // Lógica para procesar el antecedente si la validación pasa
            nuevo_historia_antecedente.setAntecedente(antecedent);
            nuevo_historia_antecedente.setHisAntFechaUlt(new Date());
            nuevo_historia_antecedente.setHisAntUsuario(session.getAttribute("usuario").toString());
            nuevo_historia_antecedente.setHistorias(historia);
            nuevo_historia_antecedente.setHisAntDescripcion(editedDescriptions.get(id) );
            try {
                HistoriaAntecedenteDAO.crearActualizarHistoriaAntecedente(nuevo_historia_antecedente);
                nuevo_historia_antecedente = new HistoriaAntecedente();
                editedDescriptions = new HashMap<>();
                editedSelectAntecedente = new HashMap<>();
                setNombre_antecedente("");
                recuperarHistoriaAntecedente();
                recuperarAlergiasHistoriaAntecedente();
                FacesMessages.info(":growlInfo", "Antecedente Creado", "This is a specific message!");
            } catch (Exception e) {
                FacesMessages.error(":growlInfo", "Error al crear el antecedente: "+e.getCause().getMessage(), "This is a specific message!");
            }
        } else {
            FacesMessages.error(":growlInfo", "Error: El campo de descripción no puede estar vacío.", "This is a specific message!");
        }
    }
    
    /**
     * Método para crear un nuevo Historia Examen
     */
    public void crearHistoriaExamen(){
        Usuarios u = UsuarioDAO.obtenerUsuario(session.getAttribute("usuario").toString());
        Personas p = PersonaDAO.recuperarPersonaID(u.getPersonas().getPerId());
        nuevo_historia_examen.setExamenes(examenes);
        nuevo_historia_examen.setHistorias(historia);
        nuevo_historia_examen.setHisExaFechaUlt(new Date());
        nuevo_historia_examen.setHisExaUsuario(session.getAttribute("usuario").toString());
        nuevo_historia_examen.setHisExaCompletado(realizarExamen ? Byte.parseByte("1") : Byte.parseByte("0"));
        nuevo_historia_examen.setHisExaDescripcion(realizarExamen ? "Sin resultado"  : nuevo_historia_examen.getHisExaDescripcion());
        try {
            HistoriaExamenDAO.crearActualizarHistoriaExamen(nuevo_historia_examen);
            nuevo_historia_examen = new HistoriaExamen();
            realizarExamen = false;
            examenes = new Examenes();
            setNombre_examen("");
            recuperarHistoriaExamenes();
            FacesMessages.info(":growlInfo", "Exámen Creado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al crear el exámen: "+e.getCause().getMessage(), "This is a specific message!");
        }
    }
    
    private boolean validarExamen(Examenes examenes) {
        return examenes != null && examenes.getExaId() != null && getEditedCheckExamen().get(examenes.getExaId()) ? StringUtils.isBlank(editedDescriptionsExamen.get(examenes.getExaId())) :StringUtils.isNotBlank(editedDescriptionsExamen.get(examenes.getExaId()));
    }
    
    private boolean validarExamen1(Examenes examenes) {
        return examenes != null && examenes.getExaId() != null && !getEditedCheckExamen().get(examenes.getExaId()) ? StringUtils.isBlank(editedIndicacionesExamen.get(examenes.getExaId())) :StringUtils.isNotBlank(editedIndicacionesExamen.get(examenes.getExaId()));
    }

    /**
     * Método para crear un nuevo Historia Examen
     * @param examens
     */
    public void crearHistoriaExamenPrueba(Examenes examens){
        
        if (getEditedDateExamen().get(examens.getExaId() ) == null &&
                !getEditedCheckExamen().get(examens.getExaId()) &&
                StringUtils.isBlank(getEditedDescriptionsExamen().get(examens.getExaId()))) {
            FacesMessages.error(":growlInfo", "Error: El campo de fecha del exámen no puede estar vacío.", "This is a specific message!");
            FacesMessages.error(":growlInfo", "Error: El campo de descripción no puede estar vacío.", "This is a specific message!");
        } else
            if(getEditedDateExamen().get(examens.getExaId() ) == null &&
                    getEditedCheckExamen().get(examens.getExaId()) &&
                    StringUtils.isBlank(getEditedIndicacionesExamen().get(examens.getExaId()))
                ){
            FacesMessages.error(":growlInfo", "Error: El campo de fecha del exámen no puede estar vacío.", "This is a specific message!");
            FacesMessages.error(":growlInfo", "Error: El campo de indicaciones no puede estar vacío.", "This is a specific message!");
        }

        if (getEditedDateExamen().get(examens.getExaId() ) != null) {
            if(!getEditedCheckExamen().get(examens.getExaId())){
                if (validarExamen(examens)) {
                    crearHisExa(examens);
                } else {
                    FacesMessages.error(":growlInfo", "Error: El campo de descripción no puede estar vacío.", "This is a specific message!");
                }
 
            } else {
                if (validarExamen1(examens)) {
                    crearHisExa(examens);
                } else {
                    FacesMessages.error(":growlInfo", "Error: El campo de indicaciones no puede estar vacío.", "This is a specific message!");
                }
            }
        } else {
            FacesMessages.error(":growlInfo", "Error: El campo de fecha del exámen no puede estar vacío.", "This is a specific message!");
        }
    }
    
    public void crearHisExa(Examenes examens){
        nuevo_historia_examen.setExamenes(examens);
        nuevo_historia_examen.setHistorias(historia);
        nuevo_historia_examen.setHisExaFechaUlt(new Date());
        nuevo_historia_examen.setHisExaUsuario(session.getAttribute("usuario").toString());
        nuevo_historia_examen.setHisExaCompletado(getEditedCheckExamen().get(examens.getExaId()) ? Byte.parseByte("0") : Byte.parseByte("1"));
        nuevo_historia_examen.setHisExaIndicaciones(!getEditedCheckExamen().get(examens.getExaId()) ? "Sin indicaciones"  : getEditedIndicacionesExamen().get(examens.getExaId()));
        nuevo_historia_examen.setHisExaDescripcion(getEditedCheckExamen().get(examens.getExaId()) ? "Sin resultado"  : getEditedDescriptionsExamen().get(examens.getExaId()));
        nuevo_historia_examen.setHisExaFecha(getEditedDateExamen().get(examens.getExaId()));
        try {
            HistoriaExamenDAO.crearActualizarHistoriaExamen(nuevo_historia_examen);
            nuevo_historia_examen = new HistoriaExamen();
            editedDescriptionsExamen = new HashMap<>();
            editedIndicacionesExamen = new HashMap<>();
            editedCheckExamen = new HashMap<>();
            editedDateExamen = new HashMap<>();
            realizarExamen = false;
            examenes = new Examenes();
            setNombre_examen("");
            recuperarHistoriaExamenes();
            FacesMessages.info(":growlInfo", "Exámen Creado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al crear el exámen: "+e.getCause().getMessage(), "This is a specific message!");
        }
    }
    
    /**
     * Método para crear un nuevo Examen
     */
    public void crearHistoriaDiagnostico(){
        
        nuevo_historia_diagnostico.setDiagnosticos(diagnostico);
        nuevo_historia_diagnostico.setHistorias(historia);
        nuevo_historia_diagnostico.setHisDiaFechaUlt(new Date());
        nuevo_historia_diagnostico.setHisDiaUsuario(session.getAttribute("usuario").toString());
        try {
            HistoriaDiagnosticoDAO.crearActualizarHistoriaDiagnostico(nuevo_historia_diagnostico);
            nuevo_historia_diagnostico = new HistoriaDiagnostico();
            setNombre_diagnostico("");
            recuperarHistoriaDiagnostico();
            FacesMessages.info(":growlInfo", "Diagnóstico Creado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al crear el diagnóstico: "+e.getCause().getMessage(), "This is a specific message!");
        }
    }
    
    private boolean validarDiagnostic(Diagnosticos diagnosticos) {
        return diagnosticos != null && diagnosticos.getDiaId() != null  && StringUtils.isNotBlank(editedDescriptionsDiagnostico.get(diagnosticos.getDiaId()));
    }
        
    private boolean isBlank(String value) {
        return StringUtils.isBlank(value);
    }
    
    private void addErrorMessage(String clientId, String summary, String detail) {
        FacesMessages.error(clientId, summary, detail);
    }
    private void validarCampo(String clientId, String value, String errorMessage) {
        if (isBlank(value)) {
            addErrorMessage(":growlInfo", errorMessage, "This is a specific message!");
        }
    }
    
    /**
     * Método para crear un nuevo Examen
     * @param diagnosticos
     */
    public void crearHistoriaDiagnosticoPrueba(Diagnosticos diagnosticos){
        validarCampo(":growlInfo", editedTipoDiagnostico.get(diagnosticos.getDiaId()), "Error: Seleccione el tipo del diagnóstico");
        validarCampo(":growlInfo", editedCondicionDiagnostico.get(diagnosticos.getDiaId()), "Error: Seleccione la condición del diagnóstico");
        validarCampo(":growlInfo", editedCronologiaDiagnostico.get(diagnosticos.getDiaId()), "Error: Seleccione la cronología del diagnóstico");

        if (!isBlank(editedTipoDiagnostico.get(diagnosticos.getDiaId()))
                && !isBlank(editedCondicionDiagnostico.get(diagnosticos.getDiaId()))
                && !isBlank(editedCronologiaDiagnostico.get(diagnosticos.getDiaId()))) {
            nuevo_historia_diagnostico.setDiagnosticos(diagnosticos);
            nuevo_historia_diagnostico.setHistorias(historia);
            nuevo_historia_diagnostico.setHisDiaFechaUlt(new Date());
            nuevo_historia_diagnostico.setHisDiaUsuario(session.getAttribute("usuario").toString());
            nuevo_historia_diagnostico.setHisDiaObservacion(getEditedDescriptionsDiagnostico().get(diagnosticos.getDiaId()));
            nuevo_historia_diagnostico.setHisDiaTipo(getEditedTipoDiagnostico().get(diagnosticos.getDiaId()));
            nuevo_historia_diagnostico.setHisDiaCondicion(getEditedCondicionDiagnostico().get(diagnosticos.getDiaId()));
            nuevo_historia_diagnostico.setHisDiaCronologia(getEditedCronologiaDiagnostico().get(diagnosticos.getDiaId()));
            try {
                HistoriaDiagnosticoDAO.crearActualizarHistoriaDiagnostico(nuevo_historia_diagnostico);
                nuevo_historia_diagnostico = new HistoriaDiagnostico();
                editedDescriptionsDiagnostico = new HashMap<>();
                editedTipoDiagnostico = new HashMap<>();
                editedCondicionDiagnostico = new HashMap<>();
                editedCronologiaDiagnostico = new HashMap<>();
                removeListDiagnostico(diagnosticos);
                recuperarHistoriaDiagnostico();
                FacesMessages.info(":growlInfo", "Diagnóstico Creado", "This is a specific message!");
            } catch (Exception e) {
                FacesMessages.info(":growlInfo", "Error al crear el diagnóstico: "+e.getCause().getMessage(), "This is a specific message!");
            }
        }
    }
    
    public void removeListDiagnostico(Diagnosticos diagnosticos){
//        lista_diagnosticos_all.remove(diagnosticos.getDiaId());
        Iterator<Diagnosticos> iterator = lista_diagnosticos_all.iterator();
        while (iterator.hasNext()) {
            Diagnosticos currentDiagnostico = iterator.next();
            if (currentDiagnostico.getDiaId() == diagnosticos.getDiaId()) {
                iterator.remove();
            }
        }
    }
    
    public void addListDiagnostico(Diagnosticos diagnosticos){
        lista_diagnosticos_all.add(diagnosticos);    
    }

    /**
     * Método para crear un nuevo diagnóstico CIE 10ma edición
     */
    public void crearDiagnosticoCIE10(){
        nuevo_diagnostico.setDiaEdicionCie("10");
        nuevo_diagnostico.setDiaFechaUlt(new Date());
        nuevo_diagnostico.setDiaUsuario(session.getAttribute("usuario").toString());
        try {
            DiagnosticoDAO.crearActualizarDiagnostico(nuevo_diagnostico);
            nuevo_diagnostico = new Diagnosticos();
            recuperarDiagnosticos();
            FacesMessages.info(":growlInfo", "Diagnóstico Creado", "This is a specific message!");
        } catch (ConstraintViolationException e) {
            String sqlErrorCode = e.getSQLException().getSQLState();
            int sqlErrorCod = e.getSQLException().getErrorCode();
            if ("23000".equals(sqlErrorCode) && sqlErrorCod == 1062){
                // Código de error SQL 1062 (violación de restricción única)
                FacesMessages.error(":growlInfo", "Error al crear diagnóstico: El código de diagnóstico ya se encuentra registrado", "This is a specific message!");
            } else {
                // Otro código de error SQL
                FacesMessages.error(":growlInfo", "Error al crear diagnóstico: " + e.getMessage(), "This is a specific message!");
            }
        } catch (Exception e) {
            FacesMessages.error(":growlInfo", "Error al crear diagnóstico: "+e.getCause().getMessage(), "This is a specific message!");
        }
        
    }
    
    /**
     * Método para crear un nuevo tratamiento CIE 10ma edición
     */
    public void crearTratamientoCIE10(){
        try {
            if (nuevo_medicamento.getMedId() == null) {
                nuevo_medicamento.setMedFechaUlt(new Date());
                nuevo_medicamento.setMedUsuario(session.getAttribute("usuario").toString());
                nuevo_medicamento.setMedNombre(nombre_medicamento);
                nuevo_medicamento = MedicamentosDAO.crearActualizarMedicamentos(nuevo_medicamento);
            }
            nuevo_tratamiento.setMedicamentos(nuevo_medicamento);
            nuevo_tratamiento.setTraEdicionCie("10");
            nuevo_tratamiento.setTraFechaUlt(new Date());
            nuevo_tratamiento.setTraUsuario(session.getAttribute("usuario").toString());
            nuevo_tratamiento = TratamientoDAO.crearTratamiento(nuevo_tratamiento);
            nuevo_historia_tratamiento.setHistorias(historia);
            nuevo_historia_tratamiento.setHistoriaDiagnostico(nuevo_historia_diagnostico);
            nuevo_historia_tratamiento.setTratamientos(nuevo_tratamiento);
            nuevo_historia_tratamiento.setHisTraFechaUlt(new Date());
            nuevo_historia_tratamiento.setHisTraUsuario(session.getAttribute("usuario").toString());
            HistoriaTratamientoDAO.crearActualizarHistoriaExamen(nuevo_historia_tratamiento);
            setNombre_historia_diagnostico("");
            setNombre_medicamento("");
            nuevo_medicamento = new Medicamentos();
            nuevo_tratamiento = new Tratamientos();
            nuevo_historia_diagnostico = new HistoriaDiagnostico();
            nuevo_historia_tratamiento = new HistoriaTratamiento();
            FacesMessages.info(":growlInfo", "Tratamiento Creado", "This is a specific message!");
            recuperarHistoriaTratamiento();
            recuperarHistoriaTratamiento();
        } catch (Exception e) {
            FacesMessages.error(":growlInfo", "Error al crear el diagnóstico: "+e.getCause().getMessage(), "This is a specific message!");
        }
    }
    
    /**
     * Método para crear un nuevo tratamiento CIE 10ma edición
     */
    public void generarIndicaciones(){
        if (nuevo_medicamento.getMedMedida() != null || nuevo_tratamiento.getTraFrecuencia() != null || nuevo_medicamento.getMedDosisUnitaria() != 0 || nuevo_tratamiento.getTraDuracion() != 0){
            String day = (nuevo_tratamiento.getTraDuracion() <= 1 ) ?  " día." :  " días.";
            String indicaciones = String.valueOf(nuevo_medicamento.getMedDosisUnitaria()) + " "+
                    formatoMedidaMedicamento(nuevo_medicamento.getMedMedida()) + " "+
                    formatoFrecuenciaTratamiento(nuevo_tratamiento.getTraFrecuencia()) + " por "+
                    String.valueOf(nuevo_tratamiento.getTraDuracion()) + day;
            nuevo_tratamiento.setTraIndicaciones(indicaciones);
        }
    }
    
    /**
     * Método para crear un nuevo tratamiento CIE 10ma edición
     */
    public void generarIndicacionesUpdate(){
        if ( updateHistoriaTratamiento.getTratamientos().getTraFrecuencia() != null || updateHistoriaTratamiento.getTratamientos().getTraDuracion() != 0){
            String day = (updateHistoriaTratamiento.getTratamientos().getTraDuracion() <= 1 ) ?  " día." :  " días.";
            String indicaciones = String.valueOf(updateHistoriaTratamiento.getTratamientos().getMedicamentos().getMedDosisUnitaria()) + " "+
                    formatoMedidaMedicamento(updateHistoriaTratamiento.getTratamientos().getMedicamentos().getMedMedida()) + " "+
                    formatoFrecuenciaTratamiento(updateHistoriaTratamiento.getTratamientos().getTraFrecuencia()) + " por "+
                    String.valueOf(updateHistoriaTratamiento.getTratamientos().getTraDuracion()) + day;
            updateHistoriaTratamiento.getTratamientos().setTraIndicaciones(indicaciones);
        }
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
//        parametros.put("medicamentos",historia.getTratamientos().getTraMedicamento());
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
    
    public void seleccionarMedicamento(SelectEvent event) {
        String consulta = (String) event.getObject();
        consultaUsuario = consulta;
        ultimoValorValido = consulta;
        if (!lista_seleccion_medicamento.isEmpty()) {
            nuevo_medicamento = MedicamentosDAO.recuperarMedicamentosId(Integer.valueOf(lista_seleccion_medicamento.get(0)));
        }
    }
    
    public List<SelectItem> obtenerListaGrupos(Antecedente antecedente) {
        if (antecedente == null || antecedente.getAntGrupo() == null) {
            return Collections.emptyList(); // o devuelve una lista vacía, dependiendo de tus necesidades
        }

        // Divide la cadena de antGrupo en una lista usando la coma como delimitador
        List<String> grupos = Arrays.asList(antecedente.getAntGrupo().split(","));

        // Crea una lista de SelectItem a partir de los elementos en la lista
        List<SelectItem> items = new ArrayList<>();
        for (String grupo : grupos) {
            items.add(new SelectItem(grupo.trim(), grupo.trim()));
        }

        return items;
    }
    
//    public List<GrupoItem> obtenerListaGrupos(String antGrupo, String antId) {
//        // Divide la cadena de antGrupo en una lista usando la coma como delimitador
//        List<String> grupos = Arrays.asList(antGrupo.split(","));
//
//        // Crea una lista de GrupoItem a partir de los elementos en la lista
//        List<GrupoItem> items = new ArrayList<>();
//        for (String grupo : grupos) {
//            GrupoItem grupoItem = new GrupoItem();
//            grupoItem.setAntId(antId);
//            grupoItem.setAntGrupo(grupo);
//            items.add(grupoItem);
//        }
//
//        return items;
//    }


    
    public String VerCitaMedica(int hisId) {
        updatePanel = true;
        renderedEvolucion = true;
        nombre_medicamento = null;
        nombre_historia_diagnostico = null;
        update_name_diagnostic_history = null;
        nuevo_historia_antecedente = new HistoriaAntecedente();
        nuevo_historia_examen = new HistoriaExamen();
        nuevo_historia_diagnostico = new HistoriaDiagnostico();
        nuevo_historia_tratamiento = new HistoriaTratamiento();
        nuevo_tratamiento = new Tratamientos();
        nuevo_medicamento = new Medicamentos();
        nuevo_diagnostico = new Diagnosticos();
        editedDescriptions = new HashMap<>(); 
        editedDescriptionsExamen = new HashMap<>();
        editedDescriptionsDiagnostico = new HashMap<>();
        editedIndicacionesExamen = new HashMap<>();
        editedCheckExamen = new HashMap<>();
        editedDateExamen = new HashMap<>();
        lista_seleccion_historia_diagnostico = new ArrayList<>();
        lista_seleccion_medicamento = new ArrayList<>();
        updateHistoriaTratamiento = new HistoriaTratamiento();
        updateHistoriaAntecedente = new HistoriaAntecedente();
        updateHistoriaDiagnostico = new HistoriaDiagnostico();
        deleteHistoriaTratamiento = new HistoriaTratamiento();
        editedTipoDiagnostico = new HashMap<>();
        editedCondicionDiagnostico = new HashMap<>();
        editedCronologiaDiagnostico = new HashMap<>();
        editedSelectAntecedente = new HashMap<>();
        mapAntecedentePer = new HashMap<>();
        mapAntecedenteFam = new HashMap<>();
        mapAntecedenteGin = new HashMap<>();
        mapAntecedenteVac = new HashMap<>();
        menuPanel = new HashMap<>();
        historialLista = new HashMap<>();
        lista_historia_examen = new ArrayList<>();
        cargaPanel();
        cargaHistoriaLista();
        this.historia_actual_id = hisId;
        historia = CitaDAO.recuperarHistoriaID(hisId);
        recuperarHistoriaAntecedente();
        recuperarHistorialHistoriaAntecedente();
        recuperarHistorialHistorias();
        recuperarHistorialSignos();
        recuperarHistorialRevisionSistema();
        recuperarHistorialDefaultHistoriaAntecedente();
        recuperarAlergiasHistoriaAntecedente();
        recuperarHistoriaExamenes();
        recuperarHistorialHistoriaExamenes();
        recuperarCategoriaAntecedente();
        recuperarHistoriaDiagnostico();
        recuperarHistorialHistoriaDiagnostico();
        recuperarHistoriaTratamiento();
        recuperarHistorialHistoriaTratamiento();
        recuperarDiagnosticos();
        getListaAntecedentePer();
        getListaAntecedenteFan();
        getListaAntecedenteGen();
        getListaAntecedenteVac();
        // Eliminar los diagnosticos registrados
        for (HistoriaDiagnostico historiaDiagnostico : lista_historia_diagnostico) {
            removeListDiagnostico(historiaDiagnostico.getDiagnosticos());
        }
        signos = SignosDAO.recuperarSignosId(historia.getSignos().getSigId());
        revision = RevisionSistemasDAO.recuperarRevision(historia.getRevisionSistemas().getRevSisId());

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
   
    
    public void prepararEliminacionHistoriaExamen(HistoriaExamen historiaExamenEliminar){
        eliminarHistoriaExamen = historiaExamenEliminar;
    }
    
    public void cargaPanel(){
        menuPanel.put("panel1", true);
        menuPanel.put("panel2", false);
        menuPanel.put("panel3", false);
        menuPanel.put("panel4", false);
        menuPanel.put("panel5", false);
        menuPanel.put("panel6", false);
        menuPanel.put("panel7", false);
        menuPanel.put("panel8", false);
        menuPanel.put("panel9", false);
        menuPanel.put("panel10", false);
        menuPanel.put("panel11", false);
    }
    
    public void cargaHistoriaLista(){
        historialLista.put("historia_clinica", true);
        historialLista.put("antecedente", true);
        historialLista.put("signo", true);
        historialLista.put("revision_sistema", true);
        historialLista.put("examen_fisico", true);
        historialLista.put("examenes", true);
        historialLista.put("diagnosticos", true);
        historialLista.put("tratamiento", true);  
        historialLista.put("indicaciones", true);  
        historialLista.put("evolucion", true);  
    }
    
    /**
     * Método  para cambiar de paneles
     *
     * @param panelActual
     * @param estado
     */
    public void cambioPanel(String panelActual, Boolean estado) {
        if (lista_historia_diagnostico.isEmpty() && panelActual.contains("panel8") != false) {
            FacesMessages.warning(":growlInfo", "Ingresar un diagnóstico. para seguir con el tratamiento", "This is a specific message!");
            updatePanel = false;
        } 
    //        else if (lista_historia_tratamiento.isEmpty() && panelActual.contains("panel9") != false) {
    //            FacesMessages.warning(":growlInfo", "Ingresar un tratamiento", "This is a specific message!");
    //            updatePanel = false;        
    //        } 
        else {
            String valor = "";
            for (String panel : menuPanel.keySet()) {
                boolean estadoAll = panel.equals(panelActual);
                if (panel.contains(panelActual)) {
                    valor = actualizarHistoriaPrueba(panelActual);
                    if(valor.contains("Sin tratamiento")){
                        menuPanel.put("panel9", estado);
                        menuPanel.put(panel, false);
                    } else {
                        menuPanel.put(panelActual, estado);
                    }
                } 
                if (!panelActual.contains("panel11") && !valor.contains("Sin tratamiento")) {
                    menuPanel.put(panel, estadoAll);
                }
            }
            updatePanel = true;
        }
    }
    
    public void cambioHistoriaList(String keyMap ){
        boolean estados = historialLista.get(keyMap);
        historialLista.put(keyMap, !estados);
    }
    
    public void eliminarHistoriaTratamiento(){
        System.out.println("entra a eliminar");
    }
    
    public void eliminarExamen(){
        try {
            HistoriaExamenDAO.eliminarHistoriaExamen(eliminarHistoriaExamen);
            eliminarHistoriaExamen = new HistoriaExamen();
            recuperarHistoriaExamenes();
            FacesMessages.info(":growlInfo", "Exámen eliminado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al eliminar el examen: "+e.getCause().getMessage(), "This is a specific message!");
        }
    }
    
    public void prepararActualizacionHistoriaExamen(HistoriaExamen historiaExamenActualizar){
        actualizarHistoriaExamen = historiaExamenActualizar;
    }
    
    /**
     * Método para preparar el tratamiento a actualizar
     *
     * @param historiaTratamiento
     */
    public void prepareUpdateHistoriaTratamiento(HistoriaTratamiento historiaTratamiento){
        updateHistoriaTratamiento = historiaTratamiento;
        update_name_diagnostic_history = updateHistoriaTratamiento.getHistoriaDiagnostico().getHisDiaObservacion();
    }
    
    /**
     * Método para preparar el antecedente a actualizar
     *
     * @param historiaAntecedente
     */
    public void prepareUpdateHistoriaAntecedente(HistoriaAntecedente historiaAntecedente){
        updateHistoriaAntecedente = historiaAntecedente;
    }
    
    /**
     * Método para preparar el tratamiento a actualizar
     *
     * @param historiaDiagnostico
     */
    public void prepareUpdateHistoriaDiagnostico(HistoriaDiagnostico historiaDiagnostico){
        updateHistoriaDiagnostico = historiaDiagnostico;
    }
    
    /**
     * Método para preparar el tratamiento a actualizar
     *
     * @param historiaTratamiento
     */
    public void prepareDeleteHistoriaTratamiento(HistoriaTratamiento historiaTratamiento){
        deleteHistoriaTratamiento = historiaTratamiento;
    }
    
    /**
     * Método para preparar el tratamiento a actualizar
     *
     * @param historiaTratamiento
     */
    public void eliminarHistoriaTratamiento(HistoriaTratamiento historiaTratamiento){
        try {
            Tratamientos tratamientos = historiaTratamiento.getTratamientos();
            HistoriaTratamientoDAO.eliminarHistoriaTratamiento(historiaTratamiento);
            TratamientoDAO.eliminarTratamiento(tratamientos);  
            recuperarHistoriaTratamiento();
            FacesMessages.info(":growlInfo", "Tratamiento eliminado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al eliminar el tratamiento: "+e.getCause().getMessage(), "This is a specific message!");
        }
    }
    
    /**
     * Método para eliminar el historia diagnóstico
     *
     * @param historiaDiagnostico
     */
    public void eliminarHistoriaDiagnostico(HistoriaDiagnostico historiaDiagnostico){
        try {
            Diagnosticos diagnosticos = historiaDiagnostico.getDiagnosticos();
            HistoriaDiagnosticoDAO.eliminarHistoriaDiagnostico(historiaDiagnostico);
            addListDiagnostico(diagnosticos);
            recuperarHistoriaDiagnostico();
            FacesMessages.info(":growlInfo", "Diagnóstico eliminado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al eliminar el diagnóstico: "+e.getCause().getMessage(), "This is a specific message!");
        }
    }
    
    /**
     * Método para eliminar el historia antecedente
     *
     * @param historiaAntecedente
     */
    public void eliminarHistoriaAntecedente(HistoriaAntecedente historiaAntecedente){
        try {
            HistoriaAntecedenteDAO.eliminarHistoriaAntecedente(historiaAntecedente);
            recuperarHistoriaAntecedente();
            FacesMessages.info(":growlInfo", "Antecedente eliminado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al eliminar el antecedente: "+e.getCause().getMessage(), "This is a specific message!");
        }
    }
    
    /**
     * Método para eliminar el historia examen
     *
     * @param historiaExamen
     */
    public void eliminarHistoriaExamen(HistoriaExamen historiaExamen){
        try {
            HistoriaExamenDAO.eliminarHistoriaExamen(historiaExamen);
            recuperarHistoriaExamenes();
            FacesMessages.info(":growlInfo", "Exámen eliminado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al eliminar el exámen: "+e.getCause().getMessage(), "This is a specific message!");
        }
    }
    
    public void actualizarExamen(){
        try {
            actualizarHistoriaExamen.setHisExaCompletado(actualizarHistoriaExamen.getHisExaDescripcion().contains("Sin resultado") ? Byte.parseByte("0") : Byte.parseByte("1"));
            HistoriaExamenDAO.crearActualizarHistoriaExamen(actualizarHistoriaExamen);
            actualizarHistoriaExamen = new HistoriaExamen();
            recuperarHistoriaExamenes();
            recuperarHistorialHistoriaExamenes();
            FacesMessages.info(":growlInfo", "Exámen actualizado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.error(":growlInfo", "Error al actualizar el exámen: "+e.getCause().getMessage(), "This is a specific message!");
        }
    }
    
    /**
     * Método para preparar actualizar el diagnostico
     *
     */
    public void actualizarHistoriaAntecedente(){
        try {
            HistoriaAntecedenteDAO.crearActualizarHistoriaAntecedente(updateHistoriaAntecedente);
            updateHistoriaAntecedente = new HistoriaAntecedente();
            recuperarHistoriaAntecedente();
            FacesMessages.info(":growlInfo", "Antecedente actualizado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.error(":growlInfo", "Error al actualizar el antecedente: "+e.getCause().getMessage(), "This is a specific message!");
        }
    }
    
    /**
     * Método para preparar actualizar el diagnostico
     *
     */
    public void actualizarHistoriaDiagnostico(){
        try {
            HistoriaDiagnosticoDAO.crearActualizarHistoriaDiagnostico(updateHistoriaDiagnostico);
            updateHistoriaDiagnostico = new HistoriaDiagnostico();
            recuperarHistoriaDiagnostico();
            FacesMessages.info(":growlInfo", "Diagnóstico actualizado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.error(":growlInfo", "Error al actualizar el diagnóstico: "+e.getCause().getMessage(), "This is a specific message!");
        }
    }
    
    /**
     * Método para preparar actualizar el tratamiento
     *
     */
    public void actualizarHistoriaTratamiento(){
        try {
            TratamientoDAO.crearActualizarTratamiento(updateHistoriaTratamiento.getTratamientos());
            updateHistoriaTratamiento = new HistoriaTratamiento();
            recuperarHistoriaTratamiento();
            FacesMessages.info(":growlInfo", "Tratamiento actualizado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.error(":growlInfo", "Error al actualizar el tratamiento: "+e.getCause().getMessage(), "This is a specific message!");
        }
    }
    
    public String fechaFormatoYMDS(Date fecha) {
        SimpleDateFormat formatt = new SimpleDateFormat("yyyy/MM/dd");
        return formatt.format(fecha);
    }
    
    public String antecedenteFormato(String tipo) {
        switch (tipo) {
            case "1":
                return "Personal";
            case "2":
                return "Familiar";
            case "3":
                return "Gineco/Obstétrico";
            case "4":
                return "Vacunación";
            default:
                return tipo;
        }
    }
    
    public String formatoTipoDiagostico(String tipo) {
        switch (tipo) {
            case "1":
                return "Morbilidad";
            case "2":
                return "Prevención";
            default:
                return tipo;
        }
    }
    
    public String formatoCondicionDiagostico(String tipo) {
        switch (tipo) {
            case "1":
                return "Presuntivo";
            case "2":
                return "Definitivo inicial";
            case "3":
                return "Definitivo control";
            default:
                return tipo;
        }
    }
    
    public String formatoCronologiaDiagostico(String tipo) {
        switch (tipo) {
            case "1":
                return "Primera";
            case "2":
                return "Subsecuente";
            default:
                return tipo;
        }
    }
    
    public String antecedenteFormatoExamen(String tipo) {
        switch (tipo) {
            case "1":
                return "Laboratorio";
            case "2":
                return "Imagenologia";
            case "3":
                return "Histopatología";
            default:
                return tipo;
        }
    }
    
    public String formatoMedidaMedicamento(String tipo) {
        switch (tipo) {
            case "1":
                return "Microgramo";
            case "2":
                return "Miligramo";
            case "3":
                return "Gramo";
            case "4":
                return "Kilogramno";
            case "5":
                return "Unidades internacionales";
            case "6":
                return "Microlitro";
            case "7":
                return "Mililitro";
            case "8":
                return "Litro";
            case "9":
                return "Ingreso Manual";
            case "10":
                return "Porcentaje";
            case "11":
                return "Unidades";
            case "12":
                return "Miliequivalente";
            default:
                return tipo;
        }
    }
    
    public String revert(String med_medida){
        String aux_medida;
        switch(med_medida){
            case "Microgramo":
                return aux_medida = "1";
            case "Miligramo":
                return aux_medida = "2";
            case "Gramo":
                return aux_medida = "3";
            case "Kilogramno":
                return aux_medida = "4";
            case "Unidades internacionales":
                return aux_medida = "5";
            case "Microlitro":
                return aux_medida = "6";
            case "Mililitro":
                return aux_medida = "7";
            case "Litro":
                return aux_medida = "8";
            case "Ingreso Manual":
                return aux_medida = "9";
            case "Porcentaje":
                return aux_medida = "10";
            case "Unidades":
                return aux_medida = "11";
            case "Miliequivalente":
                return aux_medida = "12";
            default:
                return aux_medida = "0";
        }
    }
    
    public String formatoFrecuenciaTratamiento(String tipo) {
        switch (tipo) {
            case "1":
                return "Cada 4 Horas";
            case "2":
                return "Cada 6 Horas";
            case "3":
                return "Cada 8 Horas";
            case "4":
                return "Cada 12 Horas";
            case "5":
                return "Cada 24 Horas";
            case "6":
                return "En este momento";
            case "7":
                return "Por razones necesarias";
            case "8":
                return "Otra";
            default:
                return tipo;
        }
    }
    
    public String formatoViaAdministracionTratamiento(String tipo) {
        switch (tipo) {
            case "1":
                return "ORAL";
            case "2":
                return "INHALATORIA";
            case "3":
                return "PARENTERAL";
            case "4":
                return "INTRATRAQUEAL";
            case "5":
                return "RECTAL";
            case "6":
                return "VAGINAL";
            case "7":
                return "INTRAUTERINA";
            case "8":
                return "INTRAVESICAL";
            case "9":
                return "OCULAR";
            case "10":
                return "PARENTERAL (INTRAVENOSA)";
            case "11":
                return "PARENTERAL (INTRAMUSCULAR)";
            case "12":
                return "PARENTERAL (INTRAVENOSO/INTRAMUSCULAR)";
            case "13":
                return "PARENTERAL (INTRAVENOSO/INTRAMUSCULAR/SUBCUTÁNEO)";
            case "14":
                return "PARENTERAL (SUBCUTANEA)";
            default:
                return tipo;
        }
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
    
    public void mostrarPanel(String panel) {
        panelActual = panel;
    }

    public String getPanelActual() {
        return panelActual;
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

    public String getRenderizar_antecedente() {
        return renderizar_antecedente;
    }

    public void setRenderizar_antecedente(String renderizar_antecedente) {
        this.renderizar_antecedente = renderizar_antecedente;
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

    public String getAntecedente_abierta() {
        return antecedente_abierta;
    }

    public void setAntecedente_abierta(String antecedente_abierta) {
        this.antecedente_abierta = antecedente_abierta;
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

    public String getNombre_medicamento() {
        return nombre_medicamento;
    }

    public void setNombre_medicamento(String nombre_medicamento) {
        this.nombre_medicamento = nombre_medicamento;
    }

    public String getNombre_historia_diagnostico() {
        return nombre_historia_diagnostico;
    }

    public void setNombre_historia_diagnostico(String nombre_historia_diagnostico) {
        this.nombre_historia_diagnostico = nombre_historia_diagnostico;
    }

    public String getUpdate_name_diagnostic_history() {
        return update_name_diagnostic_history;
    }

    public void setUpdate_name_diagnostic_history(String update_name_diagnostic_history) {
        this.update_name_diagnostic_history = update_name_diagnostic_history;
    }
    
    public String getNombre_antecedente() {
        return nombre_antecedente;
    }

    public void setNombre_antecedente(String nombre_antecedente) {
        this.nombre_antecedente = nombre_antecedente;
    }

    public String getNombre_examen() {
        return nombre_examen;
    }

    public void setNombre_examen(String nombre_examen) {
        this.nombre_examen = nombre_examen;
    }

    public HistoriaAntecedente getNuevo_historia_antecedente() {
        return nuevo_historia_antecedente;
    }

    public void setNuevo_historia_antecedente(HistoriaAntecedente nuevo_historia_antecedente) {
        this.nuevo_historia_antecedente = nuevo_historia_antecedente;
    }

    public HistoriaExamen getNuevo_historia_examen() {
        return nuevo_historia_examen;
    }

    public void setNuevo_historia_examen(HistoriaExamen nuevo_historia_examen) {
        this.nuevo_historia_examen = nuevo_historia_examen;
    }

    public HistoriaDiagnostico getNuevo_historia_diagnostico() {
        return nuevo_historia_diagnostico;
    }

    public void setNuevo_historia_diagnostico(HistoriaDiagnostico nuevo_historia_diagnostico) {
        this.nuevo_historia_diagnostico = nuevo_historia_diagnostico;
    }

    public HistoriaTratamiento getNuevo_historia_tratamiento() {
        return nuevo_historia_tratamiento;
    }

    public void setNuevo_historia_tratamiento(HistoriaTratamiento nuevo_historia_tratamiento) {
        this.nuevo_historia_tratamiento = nuevo_historia_tratamiento;
    }

    public List<Diagnosticos> getLista_diagnosticos() {
        return lista_diagnosticos;
    }

    public List<Diagnosticos> getLista_diagnosticos_all() {
        return lista_diagnosticos_all;
    }

    public void setLista_diagnosticos_all(List<Diagnosticos> lista_diagnosticos_all) {
        this.lista_diagnosticos_all = lista_diagnosticos_all;
    }

    public List<String> getLista_categoria_antecedentes() {
        return lista_categoria_antecedentes;
    }
    
    public List<HistoriaExamen> getLista_historia_examen() {
        return lista_historia_examen;
    }

    public List<HistoriaExamen> getLista_Historial_historia_examen() {
        return lista_Historial_historia_examen;
    }
    
    public List<HistoriaAntecedente> getLista_historia_antecedente() {
        return lista_historia_antecedente;
    }

    public List<HistoriaAntecedente> getLista_historial_historia_antecedente() {
        return lista_historial_historia_antecedente;
    }

    public List<Historias> getLista_historial_historias() {
        return lista_historial_historias;
    }

    public List<Signos> getLista_historial_signos() {
        return lista_historial_signos;
    }

    public List<RevisionSistemas> getLista_historial_revision_sistema() {
        return lista_historial_revision_sistema;
    }

    public void setLista_historial_revision_sistema(List<RevisionSistemas> lista_historial_revision_sistema) {
        this.lista_historial_revision_sistema = lista_historial_revision_sistema;
    }
    
    public List<HistoriaAntecedente> getLista_historial_default_historia_antecedente() {
        return lista_historial_default_historia_antecedente;
    }

    public List<HistoriaTratamiento> getLista_historia_tratamiento() {
        return lista_historia_tratamiento;
    }

    public List<HistoriaTratamiento> getLista_historial_historia_tratamiento() {
        return lista_historial_historia_tratamiento;
    }
    
    public List<HistoriaAntecedente> getLista_alergias_historia_antecedente() {
        return lista_alergias_historia_antecedente;
    }
    
    public List<HistoriaDiagnostico> getLista_historia_diagnostico() {
        return lista_historia_diagnostico;
    }

    public List<HistoriaDiagnostico> getLista_historial_historia_diagnostico() {
        return lista_historial_historia_diagnostico;
    }

    public List<HistoriaDiagnostico> getLista_seleccion_historia_diagnostico() {
        return lista_seleccion_historia_diagnostico;
    }

    public void setLista_seleccion_historia_diagnostico(List<HistoriaDiagnostico> lista_seleccion_historia_diagnostico) {
        this.lista_seleccion_historia_diagnostico = lista_seleccion_historia_diagnostico;
    }

    public List<String> getLista_seleccion_medicamento() {
        return lista_seleccion_medicamento;
    }

    public void setLista_seleccion_medicamento(List<String> lista_seleccion_medicamento) {
        this.lista_seleccion_medicamento = lista_seleccion_medicamento;
    }
    
    public Tratamientos getNuevo_tratamiento() {
        return nuevo_tratamiento;
    }

    public void setNuevo_tratamiento(Tratamientos nuevo_tratamiento) {
        this.nuevo_tratamiento = nuevo_tratamiento;
    }

    public Medicamentos getNuevo_medicamento() {
        return nuevo_medicamento;
    }

    public void setNuevo_medicamento(Medicamentos nuevo_medicamento) {
        this.nuevo_medicamento = nuevo_medicamento;
    }

    public Diagnosticos getNuevo_diagnostico() {
        return nuevo_diagnostico;
    }

    public void setNuevo_diagnostico(Diagnosticos nuevo_diagnostico) {
        this.nuevo_diagnostico = nuevo_diagnostico;
    }

    public HistoriaExamen getEliminarHistoriaExamen() {
        return eliminarHistoriaExamen;
    }

    public void setEliminarHistoriaExamen(HistoriaExamen eliminarHistoriaExamen) {
        this.eliminarHistoriaExamen = eliminarHistoriaExamen;
    }

    public HistoriaExamen getActualizarHistoriaExamen() {
        return actualizarHistoriaExamen;
    }

    public void setActualizarHistoriaExamen(HistoriaExamen actualizarHistoriaExamen) {
        this.actualizarHistoriaExamen = actualizarHistoriaExamen;
    }

    public HistoriaTratamiento getUpdateHistoriaTratamiento() {
        return updateHistoriaTratamiento;
    }

    public void setUpdateHistoriaTratamiento(HistoriaTratamiento updateHistoriaTratamiento) {
        this.updateHistoriaTratamiento = updateHistoriaTratamiento;
    }

    public HistoriaAntecedente getUpdateHistoriaAntecedente() {
        return updateHistoriaAntecedente;
    }

    public void setUpdateHistoriaAntecedente(HistoriaAntecedente updateHistoriaAntecedente) {
        this.updateHistoriaAntecedente = updateHistoriaAntecedente;
    }

    public HistoriaDiagnostico getUpdateHistoriaDiagnostico() {
        return updateHistoriaDiagnostico;
    }

    public void setUpdateHistoriaDiagnostico(HistoriaDiagnostico updateHistoriaDiagnostico) {
        this.updateHistoriaDiagnostico = updateHistoriaDiagnostico;
    }
    
    

    public HistoriaTratamiento getDeleteHistoriaTratamiento() {
        return deleteHistoriaTratamiento;
    }

    public void setDeleteHistoriaTratamiento(HistoriaTratamiento deleteHistoriaTratamiento) {
        this.deleteHistoriaTratamiento = deleteHistoriaTratamiento;
    }
    
    public String getNombre_tratamiento() {
        return nombre_tratamiento;
    }

    public void setNombre_tratamiento(String nombre_tratamiento) {
        this.nombre_tratamiento = nombre_tratamiento;
    }

    public Boolean getRealizarExamen() {
        return realizarExamen;
    }

    public void setRealizarExamen(Boolean realizarExamen) {
        this.realizarExamen = realizarExamen;
    }

    public Boolean getRenderedEvolucion() {
        return renderedEvolucion;
    }

    public void setRenderedEvolucion(Boolean renderedEvolucion) {
        this.renderedEvolucion = renderedEvolucion;
    }

    public Boolean getUpdatePanel() {
        return updatePanel;
    }

    public void setUpdatePanel(Boolean updatePanel) {
        this.updatePanel = updatePanel;
    }
    
    
    public List<Antecedente> getListaAntecedentePer() {
        List<Antecedente> listAntecedente = filtrarPorTipos("1");
        
        mapAntecedentePer = listAntecedente.stream()
        .collect(Collectors.toMap(
                Antecedente::getAntCategoria,
                Function.identity(),
                (elementoExistente, nuevoElemento) -> {
                    elementoExistente.setAntGrupo(elementoExistente.getAntGrupo() + "," + nuevoElemento.getAntGrupo());
                    elementoExistente.setAntTipo('1');
                    return elementoExistente;
                }));
                
        return filtrarPorTipos("1");
    }
    
    public Map<String, Antecedente> getMapAntecedenteFan() {
        List<Antecedente> listAntecedente = filtrarPorTipos("2");

        mapAntecedenteFam = listAntecedente.stream()
        .collect(Collectors.toMap(
                Antecedente::getAntCategoria,
                Function.identity(),
                (elementoExistente, nuevoElemento) -> {
                    elementoExistente.setAntGrupo(elementoExistente.getAntGrupo() + "," + nuevoElemento.getAntGrupo());
                    elementoExistente.setAntTipo('2');
                    return elementoExistente;
                }));
       
        return mapAntecedenteFam;
    }
    
    public List<Antecedente> getListaAntecedenteFan() {
        List<Antecedente> listAntecedente = filtrarPorTipos("2");
        mapAntecedenteFam = listAntecedente.stream()
        .collect(Collectors.toMap(
                Antecedente::getAntCategoria,
                Function.identity(),
                (elementoExistente, nuevoElemento) -> {
                    elementoExistente.setAntGrupo(elementoExistente.getAntGrupo() + "," + nuevoElemento.getAntGrupo());
                    elementoExistente.setAntTipo('2');
                    return elementoExistente;
                }));
        return filtrarPorTipos("2");
    }

    public List<Antecedente> getListaAntecedenteGen() {
        List<Antecedente> listAntecedente = filtrarPorTipos("3");
        mapAntecedenteGin = listAntecedente.stream()
        .collect(Collectors.toMap(
                Antecedente::getAntCategoria,
                Function.identity(),
                (elementoExistente, nuevoElemento) -> {
                    elementoExistente.setAntGrupo(elementoExistente.getAntGrupo() + "," + nuevoElemento.getAntGrupo());
                    elementoExistente.setAntTipo('3');
                    return elementoExistente;
                }));
        return filtrarPorTipos("3");
    }
    
    public List<Antecedente> getListaAntecedenteVac() {
        List<Antecedente> listAntecedente = filtrarPorTipos("4");
        mapAntecedenteVac = listAntecedente.stream()
        .collect(Collectors.toMap(
                Antecedente::getAntCategoria,
                Function.identity(),
                (elementoExistente, nuevoElemento) -> {
                    elementoExistente.setAntGrupo(elementoExistente.getAntGrupo() + "," + nuevoElemento.getAntGrupo());
                    elementoExistente.setAntTipo('4');
                    return elementoExistente;
                }));
        return filtrarPorTipos("4");
    }

    private List<Antecedente> filtrarPorTipos(String tipo) {;
        return lista_antecedente.stream()
                .filter(antecedente -> String.valueOf(antecedente.getAntTipo()).equals(tipo))
                .collect(Collectors.toList());
    }

    public Map<Integer, String> getEditedDescriptions() {
        return editedDescriptions;
    }

    public void setEditedDescriptions(Map<Integer, String> editedDescriptions) {
        this.editedDescriptions = editedDescriptions;
    }
  
    public Map<Long, String> getEditedDescriptionsExamen() {
        return editedDescriptionsExamen;
    }

    public void setEditedDescriptionsExamen(Map<Long, String> editedDescriptionsExamen) {
        this.editedDescriptionsExamen = editedDescriptionsExamen;
    }

    public Map<Long, String> getEditedDescriptionsDiagnostico() {
        return editedDescriptionsDiagnostico;
    }

    public void setEditedDescriptionsDiagnostico(Map<Long, String> editedDescriptionsDiagnostico) {
        this.editedDescriptionsDiagnostico = editedDescriptionsDiagnostico;
    }

    public Map<Long, String> getEditedIndicacionesExamen() {
        return editedIndicacionesExamen;
    }

    public void setEditedIndicacionesExamen(Map<Long, String> editedIndicacionesExamen) {
        this.editedIndicacionesExamen = editedIndicacionesExamen;
    }

    public Map<Long, String> getEditedTipoDiagnostico() {
        return editedTipoDiagnostico;
    }

    public void setEditedTipoDiagnostico(Map<Long, String> editedTipoDiagnostico) {
        this.editedTipoDiagnostico = editedTipoDiagnostico;
    }

    public Map<Long, String> getEditedCondicionDiagnostico() {
        return editedCondicionDiagnostico;
    }

    public void setEditedCondicionDiagnostico(Map<Long, String> editedCondicionDiagnostico) {
        this.editedCondicionDiagnostico = editedCondicionDiagnostico;
    }

    public Map<Long, String> getEditedCronologiaDiagnostico() {
        return editedCronologiaDiagnostico;
    }

    public void setEditedCronologiaDiagnostico(Map<Long, String> editedCronologiaDiagnostico) {
        this.editedCronologiaDiagnostico = editedCronologiaDiagnostico;
    }

    public Map<Integer, String> getEditedSelectAntecedente() {
        return editedSelectAntecedente;
    }

    public void setEditedSelectAntecedente(Map<Integer, String> editedSelectAntecedente) {
        this.editedSelectAntecedente = editedSelectAntecedente;
    }

    public Map<String, Antecedente> getMapAntecedentePer() {
        return mapAntecedentePer;
    }

    public void setMapAntecedentePer(Map<String, Antecedente> mapAntecedentePer) {
        this.mapAntecedentePer = mapAntecedentePer;
    }

    public Map<String, Antecedente> getMapAntecedenteFam() {
        return mapAntecedenteFam;
    }

    public void setMapAntecedenteFam(Map<String, Antecedente> mapAntecedenteFam) {
        this.mapAntecedenteFam = mapAntecedenteFam;
    }

    public Map<String, Antecedente> getMapAntecedenteGin() {
        return mapAntecedenteGin;
    }

    public void setMapAntecedenteGin(Map<String, Antecedente> mapAntecedenteGin) {
        this.mapAntecedenteGin = mapAntecedenteGin;
    }

    public Map<String, Antecedente> getMapAntecedenteVac() {
        return mapAntecedenteVac;
    }

    public void setMapAntecedenteVac(Map<String, Antecedente> mapAntecedenteVac) {
        this.mapAntecedenteVac = mapAntecedenteVac;
    }

    public Map<String, Boolean> getMenuPanel() {
        return menuPanel;
    }

    public void setMenuPanel(Map<String, Boolean> menuPanel) {
        this.menuPanel = menuPanel;
    }

    public Map<String, Boolean> getHistorialLista() {
        return historialLista;
    }

    public void setHistorialLista(Map<String, Boolean> historialLista) {
        this.historialLista = historialLista;
    }
    
    public Map<Long, Date> getEditedDateExamen() {
        return editedDateExamen;
    }

    public void setEditedDateExamen(Map<Long, Date> editedDateExamen) {
        this.editedDateExamen = editedDateExamen;
    }

    public Map<Long, Boolean> getEditedCheckExamen() {
        return editedCheckExamen;
    }

    public void setEditedCheckExamen(Map<Long, Boolean> editedCheckExamen) {
        this.editedCheckExamen = editedCheckExamen;
    }
    
    public String getDescripcionAntecedenteTemp() {
        return descripcionAntecedenteTemp;
    }
    
    public void setDescripcionAntecedenteTemp(String descripcionAntecedenteTemp) {
        this.descripcionAntecedenteTemp = descripcionAntecedenteTemp;
    }

    public List<Examenes> getLista_examenes() {
        return lista_examenes;
    }
   
    public List<Examenes> getListaExamenesLab() {
        return filtrarPorTipoExamen("1");
    }

    public List<Examenes> getListaExamenesImg() {
        return filtrarPorTipoExamen("2");
    }

    public List<Examenes> getListaExamenesHist() {
        return filtrarPorTipoExamen("3");
    }
    
    private List<Examenes> filtrarPorTipoExamen(String tipo) {;
        return lista_examenes.stream()
                .filter(examen -> String.valueOf(examen.getExaTipo()).equals(tipo))
                .collect(Collectors.toList());
    }

    public String getUltimoValorValido() {
        return ultimoValorValido;
    }

    public void setUltimoValorValido(String ultimoValorValido) {
        this.ultimoValorValido = ultimoValorValido;
    }

    public String getConsultaUsuario() {
        return consultaUsuario;
    }

    public void setConsultaUsuario(String consultaUsuario) {
        this.consultaUsuario = consultaUsuario;
    }
    
    public String getMensajeDiagnostico() {
        return mensajeDiagnostico;
    }

    public void setMensajeDiagnostico(String mensajeDiagnostico) {
        this.mensajeDiagnostico = mensajeDiagnostico;
    }
    
}
