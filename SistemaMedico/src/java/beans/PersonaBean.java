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
import dao.PersonaDAO;
import dao.RevisionSistemasDAO;
import dao.SignosDAO;
import dao.UsuarioDAO;
import datos.Ciudades;
import datos.Enfermedades;
import datos.Estadocivil;
import datos.Historias;
import datos.Ocupaciones;
import java.util.Date;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import datos.Personas;
import datos.RevisionSistemas;
import datos.Signos;
import datos.Usuarios;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.faces.context.FacesContext;
import net.bootsfaces.utils.FacesMessages;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Set;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "PersonaBean")
@SessionScoped
public final class PersonaBean implements Serializable{
    String renderizar_ocupacion_abierta;
    String renderizar_parentesco_abierto;
    
    private String per_nombre_completo;
    private String nombre_medico;
    private String primerNombre;
    private String nombre_enfermedad;
    
    //Creación de objetos
    private Personas persona;
    private Usuarios usuario;
    private Signos signos;
    private Historias historia;
    private RevisionSistemas revision;
    
    //Atributos de la tabla signos vitales
    private Integer sig_presion_sistolica;
    private Integer sig_presion_diastolica;
    private Integer sig_frecuencia_respiratoria;
    private Integer sig_frecuencia_cardiaca;
    private Integer sig_presion_arterial_media;
    private Integer sig_saturacion;
    private Integer sig_temperatura;
    private Float sig_peso;
    private Float sig_estatura;
    private Integer sig_imc;
    private Integer sig_perimetro_abdominal;
    private Float sig_glucosa_capilar;
    private Float sig_valor_hemoglobina;
    private Float sig_valor_hemoglobina_corr;
    private Date sig_fecha_ult;
    private String sig_usuario;
    private List<Ocupaciones> lista_ocupaciones;
    private List<Ciudades> lista_ciudades;
    private List<Estadocivil> lista_estados_civiles;
    
    private int cantidad_historias;
    
    HttpSession session;
    
    //Declaración de colecciones de datos
    private List<Personas> pacientes;
    
    FacesContext context;
            
    public PersonaBean(){
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        // Para conservar mensajes entre vistas
        inicializarPersona();
        inicializarSignos();
        inicializarHistoria();
        inicializarRevision();
        inicializarProfesiones();
        inicializarCiudades();
        inicializarEstadosCiviles();
    }
    
    public void inicializarPersona(){
        // Inicialización datos paciente
        persona = new Personas();
        usuario = new Usuarios();
        per_nombre_completo = "";
        renderizar_ocupacion_abierta = "false";
        renderizar_parentesco_abierto = "false";
        cantidad_historias = 0;
    }
    
    public void inicializarColaborador(){
        persona = new Personas();
        usuario = new Usuarios();
        per_nombre_completo = "";
    }
    
    public void inicializarSignos() {
        // Inicialización datos signos
        signos = new Signos();
        sig_presion_sistolica = null;
        sig_presion_diastolica = null;
        sig_frecuencia_respiratoria = null;
        sig_frecuencia_cardiaca = null;
        sig_presion_arterial_media = null;
        sig_saturacion = null;
        sig_temperatura = null;
        sig_peso = null;
        sig_estatura = null;
        sig_imc = null;
        sig_perimetro_abdominal = null;
        sig_glucosa_capilar = null;
        sig_valor_hemoglobina = null;
        sig_valor_hemoglobina_corr = null;
        sig_usuario = "";
    }
    
    public void calcularPresionArterialMedia(){
        if(sig_presion_diastolica != null && sig_presion_sistolica != null){
            sig_presion_arterial_media = (sig_presion_sistolica
                                        +(2*sig_presion_diastolica))/3;
        }
    }
    
    public void calcularHemoglobinaCorregido(){
        sig_valor_hemoglobina_corr = sig_valor_hemoglobina-1.3f;
    }
    
    public void calcularIMC(){
        if(sig_peso != null && sig_estatura != null){
            sig_imc = Math.round(sig_peso /(int)(Math.pow(sig_estatura, 2)));
        }
    }
    
    public void cargarHistoria(int his_id){
        historia = HistoriaDAO.recuperarHistoriaID(his_id);
        try {
            nombre_medico = historia.getPersonasByMedicoPerId().getPerNombres()
                +" "+historia.getPersonasByMedicoPerId().getPerApellidos();
        } catch (Exception e) {
            nombre_medico = "";
        }
    }
    
    public void cargarPersona() {
        LoginBean lb = (LoginBean) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("LoginBean");
        Usuarios usu = lb.getUsuario();
        persona = PersonaDAO.recuperarPersonaID(usu.getPersonas().getPerId());
        
        String[] splited = persona.getPerNombres().split("\\s+");
        primerNombre = splited[0].substring(0, 1).toUpperCase() + splited[0].substring(1).toLowerCase();
    }
    
    public void inicializarHistoria(){
        historia = new Historias();
        cantidad_historias = 0;
    }
    
    public void inicializarRevision(){
        revision = new RevisionSistemas();
    }
    
    /**
     * Método para guarda la información de la persona
     * @return 
     */
    public String guardarPersona(){
        //Seteo de los datos de la persona
        persona.setPerFechaUlt(new Date());
        persona.setPerUsuario(session.getAttribute("usuario").toString());
        
        //Seteo de los datos del usuario
        usuario.setUsuContra(convertirMD5(generarClaveAleatoria()));
        usuario.setUsuNombre(generarUsuario(persona.getPerNombres(), persona.getPerApellidos()));
        usuario.setUsuUsuario(session.getAttribute("usuario").toString());
        usuario.setPersonas(persona);
        usuario.setUsuFechaUlt(new Date());
        return "/faces/medico/registroSignos.xhtml?faces-redirect=true";
    }
    
    public String guardarColaborador(){
        //Seteo de los datos de la persona
        persona.setPerFechaUlt(new Date());
        persona.setPerUsuario(session.getAttribute("usuario").toString());
//        
//        //Seteo de los datos del usuario
        usuario.setUsuContra(convertirMD5(generarClaveAleatoria()));
        usuario.setUsuNombre(generarUsuario(persona.getPerNombres(), persona.getPerApellidos()));
        usuario.setUsuUsuario(session.getAttribute("usuario").toString());
        usuario.setPersonas(persona);
        usuario.setUsuFechaUlt(new Date());
        PersonaDAO.crearPersona(persona);
        PersonaDAO.crearUsuario(usuario);
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessages.info(":growlInfo", "Colaborador creado con éxito", "This is a specific message!");
        return "/faces/privado/home?faces-redirect=true";
    }
    
    public String guardarPaciente() throws InterruptedException{
        signos.setSigPresionSistolica(sig_presion_sistolica);
        signos.setSigPresionDiastolica(sig_presion_diastolica);
        signos.setSigPresionArterialMedia(sig_presion_arterial_media);
        signos.setSigTemperatura(sig_temperatura);
        signos.setSigFrecuenciaRespiratoria(sig_frecuencia_respiratoria);
        signos.setSigFrecuenciaCardiaca(sig_frecuencia_cardiaca);
        signos.setSigSaturacion(sig_saturacion);
        signos.setSigPeso(sig_peso);
        signos.setSigEstatura(sig_estatura);
        signos.setSigImc(sig_imc);
        signos.setSigPerimetroAbdominal(sig_perimetro_abdominal);
        signos.setSigGlucosaCapilar(sig_glucosa_capilar);
        signos.setSigValorHemoglobina(sig_valor_hemoglobina);
        signos.setSigValorHemoglobinaCorr(sig_valor_hemoglobina_corr);
        //Proceso para guardar historia
        historia.setHisFechaUlt(new Date());
        historia.setHisFechaCreacion(new Date());
        historia.setHisUsuario("defecto");
        historia.setHisMotivo("Por definir");
        
        //Definiendo los items de la revision
        revision.setRevSisPatologia("false");
        revision.setRevSisSentidos("");
        revision.setRevSisRespiratorio("");
        revision.setRevSisCardiovascular("");
        revision.setRevSisDigestivo("");
        revision.setRevSisGenital("");
        revision.setRevSisUrinario("");
        revision.setRevSisEsqueletico("");
        revision.setRevSisMuscular("");
        revision.setRevSisNervioso("");
        revision.setRevSisEndocrino("");
        revision.setRevSisHemolinfatico("");
        revision.setRevSisTegumentario("");
        revision.setRevSisFisicoPatologia("false");
        revision.setRevSisFisicoObservacion("N/A");
        revision.setRevSisFechaUlt(new Date());
        revision.setRevSisUsuario("defecto");
        //Proceso para guardar paciente
        signos.setSigFechaUlt(new Date());
        signos.setSigUsuario("defecto");
        
        //Llamada a beans para guardar datos
        PersonaDAO.crearPersona(persona);
        PersonaDAO.crearUsuario(usuario);
        RevisionSistemasDAO.crearActualizarRevision(revision);
        HistoriaDAO.crearHistoriaPrimeraVez(historia, revision);
        SignosDAO.crearSignosPrimeraVez(signos);
        FacesMessages.info(":growlInfo", "Paciente creado con éxito", "This is a specific message!");
        // Fragmento para conservar los mensajes entre vistas
        return "/faces/privado/home.xhtml?faces-redirect=true";
    }
    
    public void actualizarPaciente(){
        PersonaDAO.crearActualizarPersona(persona);
        FacesMessages.info(":growlInfo", "Se han actualizado los datos del paciente", "This is a specific message!");
    }
    
    public void especificar_ocupacion(){
        if(persona.getPerProfesion().equals("51")){
            persona.setPerProfesion("");
            renderizar_ocupacion_abierta = "true";
        }
        else{
            renderizar_ocupacion_abierta = "false";
        }
    }
    
    public void especificar_parentesco(){
        if(persona.getPerParentesco().equals("Otro")){
            persona.setPerParentesco("");
            renderizar_parentesco_abierto = "true";
        }
        else{
            renderizar_parentesco_abierto = "false";
        }
    }
    
    public void calcularEdad(){
        int edad;
        if(persona.getPerNac()!=null){
        try{
            LocalDate actual = new Date()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
            LocalDate nacimiento = persona.getPerNac()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
            edad = Period.between(actual, nacimiento).getYears()*-1;
            persona.setPerEdad(edad);
            }
            catch(Exception e){
            }
        }
        else{
            persona.setPerEdad(0);
        }
    }
    
    public String generarClaveAleatoria() {

        return RandomStringUtils.randomAlphanumeric(8);

    }
    
    public String generarUsuario(String nombre, String apellido) {

        int cont = 1;
        String nomUsu = "";
        String[] nom = nombre.split(" ");
        nomUsu += nom[0].substring(0, 1);
        String[] ape = apellido.split(" ");
        nomUsu += ape[0];
        String temp = nomUsu;
        if (UsuarioDAO.obtenerUsuario(nomUsu.toLowerCase()) != null && nom.length > 1) {

            if (!nom[1].equalsIgnoreCase("")) {

                nomUsu = "";
                nomUsu += nom[1].substring(0, 1);
                nomUsu += ape[0];

            }

        }
        while (UsuarioDAO.obtenerUsuario(nomUsu.toLowerCase()) != null) {

            nomUsu = temp + cont;
            cont++;

        }
        return nomUsu.toLowerCase();

    }
    
    public String convertirMD5(String valor) {

        String md5Hex = DigestUtils
                .md5Hex(valor).toUpperCase();
        return md5Hex;

    }
    
    public List<String> recuperarNombres(){
        return PersonaDAO.recuperarNombres();
    }
    
    public List<String> recuperarNombresMedicos(){
        List<String> medicos = new ArrayList<>();
        Usuarios usuario;
        int tipo_usuario;
        for (Personas persona : PersonaDAO.recuperarPersonas()) {
            usuario = (Usuarios)persona.getUsuarioses().iterator().next();
            tipo_usuario = usuario.getRolesRolId();
            if(tipo_usuario == 1){
                medicos.add(persona.getPerNombres()+" - "+persona.getPerApellidos());
            }
        }
        return medicos;
    }
    
    public void asignarMedico(){
        Personas medico = 
                PersonaDAO.recuperarPersonaNombre(nombre_medico.split(" - ")[0],
                nombre_medico.split(" - ")[1]);
        historia.setPersonasByMedicoPerId(medico);
        HistoriaDAO.crearActualizarHistoria(historia);
        FacesMessages.info(":growlInfo", "Médico asignado con éxito!", "This is a specific message!");
    }
    
    public void inicializarProfesiones(){
        lista_ocupaciones = OcupacionDAO.recuperarOcupaciones();
    }
    
    public void inicializarCiudades(){
        lista_ciudades = CiudadesDAO.recuperarCiudades();
    }
    
    public void inicializarEstadosCiviles(){
        lista_estados_civiles = EstadosCivilesDAO.recuperarEstados();
    }
    
    public void recuperarPacientesListener(){
        String nombres[] = getPer_nombre_completo().split(" - ");
        if (nombres.length > 1) {

            persona = PersonaDAO.recuperarPersonaNombre(nombres[1], nombres[0]);

            if (persona != null) {
                per_nombre_completo = persona.getPerApellidos() + " - " + persona.getPerNombres();
                cantidad_historias = persona.getHistoriasesForPacientePerId().size();
                //List<Signos> signos = new ArrayList<>();
                //antecedentes.addAll(p.getAntecedenteses());
                //sig_presion_sistolica = antecedentes.get(-1).get;
                //FacesMessages.info(":growlInfo", "El motivo de la consulta es: "+motivo, "This is a specific message!");

            } else {
                per_nombre_completo = "";
                FacesMessages.info(":growl", "Paciente no registrado", "This is a specific message!");
            }

        } else {
            FacesMessages.info(":growl", "Paciente no registrado", "This is a specific message!");
            per_nombre_completo = "";
        }
    }
    
    public Personas getPersona() {
        return persona;
    }

    public Integer getSig_presion_sistolica() {
        return sig_presion_sistolica;
    }

    public void setSig_presion_sistolica(Integer sig_presion_sistolica) {
        this.sig_presion_sistolica = sig_presion_sistolica;
    }

    public Integer getSig_presion_diastolica() {
        return sig_presion_diastolica;
    }

    public void setSig_presion_diastolica(Integer sig_presion_diastolica) {
        this.sig_presion_diastolica = sig_presion_diastolica;
    }

    public Integer getSig_frecuencia_respiratoria() {
        return sig_frecuencia_respiratoria;
    }

    public void setSig_frecuencia_respiratoria(Integer sig_frecuencia_respiratoria) {
        this.sig_frecuencia_respiratoria = sig_frecuencia_respiratoria;
    }

    public Integer getSig_frecuencia_cardiaca() {
        return sig_frecuencia_cardiaca;
    }

    public void setSig_frecuencia_cardiaca(Integer sig_frecuencia_cardiaca) {
        this.sig_frecuencia_cardiaca = sig_frecuencia_cardiaca;
    }

    public Integer getSig_presion_arterial_media() {
        return sig_presion_arterial_media;
    }

    public void setSig_presion_arterial_media(Integer sig_presion_arterial_media) {
        this.sig_presion_arterial_media = sig_presion_arterial_media;
    }

    public Integer getSig_saturacion() {
        return sig_saturacion;
    }

    public void setSig_saturacion(Integer sig_saturacion) {
        this.sig_saturacion = sig_saturacion;
    }

    public Integer getSig_temperatura() {
        return sig_temperatura;
    }

    public void setSig_temperatura(Integer sig_temperatura) {
        this.sig_temperatura = sig_temperatura;
    }

    public Float getSig_peso() {
        return sig_peso;
    }

    public void setSig_peso(Float sig_peso) {
        this.sig_peso = sig_peso;
    }

    public Float getSig_estatura() {
        return sig_estatura;
    }

    public void setSig_estatura(Float sig_estatura) {
        this.sig_estatura = sig_estatura;
    }

    public Integer getSig_imc() {
        return sig_imc;
    }

    public void setSig_imc(Integer sig_imc) {
        this.sig_imc = sig_imc;
    }

    public Integer getSig_perimetro_abdominal() {
        return sig_perimetro_abdominal;
    }

    public void setSig_perimetro_abdominal(Integer sig_perimetro_abdominal) {
        this.sig_perimetro_abdominal = sig_perimetro_abdominal;
    }

    public Float getSig_glucosa_capilar() {
        return sig_glucosa_capilar;
    }

    public void setSig_glucosa_capilar(Float sig_glucosa_capilar) {
        this.sig_glucosa_capilar = sig_glucosa_capilar;
    }

    public Float getSig_valor_hemoglobina() {
        return sig_valor_hemoglobina;
    }

    public void setSig_valor_hemoglobina(Float sig_valor_hemoglobina) {
        this.sig_valor_hemoglobina = sig_valor_hemoglobina;
    }

    public Float getSig_valor_hemoglobina_corr() {
        return sig_valor_hemoglobina_corr;
    }

    public void setSig_valor_hemoglobina_corr(Float sig_valor_hemoglobina_corr) {
        this.sig_valor_hemoglobina_corr = sig_valor_hemoglobina_corr;
    }

    public Date getSig_fecha_ult() {
        return sig_fecha_ult;
    }

    public void setSig_fecha_ult(Date sig_fecha_ult) {
        this.sig_fecha_ult = sig_fecha_ult;
    }

    public String getSig_usuario() {
        return sig_usuario;
    }

    public void setSig_usuario(String sig_usuario) {
        this.sig_usuario = sig_usuario;
    }

    public List<Personas> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Personas> pacientes) {
        this.pacientes = pacientes;
    }

    public String getPer_nombre_completo() {
        return per_nombre_completo;
    }

    public void setPer_nombre_completo(String per_nombre_completo) {
        this.per_nombre_completo = per_nombre_completo;
    }

    public Signos getSignos() {
        return signos;
    }

    public void setSignos(Signos signos) {
        this.signos = signos;
    }

    public List<Ocupaciones> getLista_ocupaciones() {
        return lista_ocupaciones;
    }

    public void setLista_ocupaciones(List<Ocupaciones> lista_ocupaciones) {
        this.lista_ocupaciones = lista_ocupaciones;
    }

    public List<Ciudades> getLista_ciudades() {
        return lista_ciudades;
    }

    public void setLista_ciudades(List<Ciudades> lista_ciudades) {
        this.lista_ciudades = lista_ciudades;
    }

    public int getCantidad_historias() {
        return cantidad_historias;
    }

    public void setCantidad_historias(int cantidad_historias) {
        this.cantidad_historias = cantidad_historias;
    }

    public List<Estadocivil> getLista_estados_civiles() {
        return lista_estados_civiles;
    }

    public void setLista_estados_civiles(List<Estadocivil> lista_estados_civiles) {
        this.lista_estados_civiles = lista_estados_civiles;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getNombre_medico() {
        return nombre_medico;
    }

    public void setNombre_medico(String nombre_medico) {
        this.nombre_medico = nombre_medico;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getRenderizar_ocupacion_abierta() {
        return renderizar_ocupacion_abierta;
    }

    public void setRenderizar_ocupacion_abierta(String renderizar_ocupacion_abierta) {
        this.renderizar_ocupacion_abierta = renderizar_ocupacion_abierta;
    }

    public Historias getHistoria() {
        return historia;
    }

    public void setHistoria(Historias historia) {
        this.historia = historia;
    }

    public String getNombre_enfermedad() {
        return nombre_enfermedad;
    }

    public void setNombre_enfermedad(String nombre_enfermedad) {
        this.nombre_enfermedad = nombre_enfermedad;
    }    

    public String getRenderizar_parentesco_abierto() {
        return renderizar_parentesco_abierto;
    }

    public void setRenderizar_parentesco_abierto(String renderizar_parentesco_abierto) {
        this.renderizar_parentesco_abierto = renderizar_parentesco_abierto;
    }
}
