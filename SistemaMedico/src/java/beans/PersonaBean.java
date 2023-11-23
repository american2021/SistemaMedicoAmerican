/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CiudadesDAO;
import dao.EstadoCivilDAO;
import dao.CitaDAO;
import dao.OcupacionDAO;
import dao.ParentescoDAO;
import dao.PersonaDAO;
import dao.RevisionSistemasDAO;
import dao.SignosDAO;
import dao.UsuarioDAO;
import datos.Ciudades;
import datos.Estadocivil;
import datos.Historias;
import datos.Ocupaciones;
import datos.Parentescos;
import java.util.Date;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import datos.Personas;
import datos.RevisionSistemas;
import datos.Signos;
import datos.Usuarios;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.faces.context.FacesContext;
import net.bootsfaces.utils.FacesMessages;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "PersonaBean")
@SessionScoped
public final class PersonaBean implements Serializable {

    // Inyección de dependencia para usar el historiaBean
    @ManagedProperty(value = "#{CitaBean}")
    private CitaBean citaBean;

    // Banderas para renderizar opciones abiertas
    private String renderizar_profesion_abierta;
    private String renderizar_parentesco_abierto;
    private String inhabilitar_profesion;
    private String inhabilitar_parentesco;

    // Auxiliar para cargar opciones abiertas
    private String profesion_abierta;
    private String parentesco_abierto;

    private String per_nombre_completo;
    private String nombre_medico;
    private String nombre_usuario;
    private String nombre_enfermedad;

    //Creación de objetos
    private Personas persona;
    private Usuarios usuario;
    private Signos signos;
    private Historias historia;
    private RevisionSistemas revision;

    //Atributos de la tabla signos vitales
    private Signos nuevo_signos;
    private Date sig_fecha_ult;
    private String sig_usuario;
    private List<Ocupaciones> lista_ocupaciones;
    private List<Parentescos> lista_parentescos;
    private List<Ciudades> lista_ciudades;
    private List<Estadocivil> lista_estados_civiles;

    private int cantidad_historias;

    HttpSession session;

    //Declaración de colecciones de datos
    private List<Personas> pacientes;
    private List<Personas> colaboradores;

    FacesContext context;

    public PersonaBean() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        nombre_usuario = session.getAttribute("nombre_usuario").toString().split(" ")[0];
        // Para conservar mensajes entre vistas
        inicializarPersona();
        inicializarSignos();
        inicializarProfesionesYParentescos();
        inicializarCiudades();
        inicializarEstadosCiviles();
    }

    public void inicializarPacientes() {
        pacientes = PersonaDAO.recuperarPacientes();
    }
    
    public void inicializarColaboradores() {
        colaboradores = PersonaDAO.recuperarColaboradores();
    }

    public void inicializarPersona() {
        // Inicialización datos paciente
        persona = new Personas();
        usuario = new Usuarios();
        signos = new Signos();
        historia = new Historias();
        cantidad_historias = 0;
        revision = new RevisionSistemas();
        per_nombre_completo = "";
        inhabilitar_parentesco = "true";
        inhabilitar_profesion = "true";
        renderizar_profesion_abierta = "false";
        renderizar_parentesco_abierto = "false";
        profesion_abierta = "";
        parentesco_abierto = "";
        cantidad_historias = 0;
    }

    public void inicializarColaborador() {
        persona = new Personas();
        usuario = new Usuarios();
        per_nombre_completo = "";
    }

    public void inicializarSignos() {
        // Inicialización datos signos
        signos = new Signos();
        nuevo_signos = new Signos();
        sig_usuario = "";
    }

    public void calcularPresionArterialMedia() {
        if (nuevo_signos.getSigPresionDiastolica() != 0 && nuevo_signos.getSigPresionSistolica() != 0) {
            nuevo_signos.setSigPresionArterialMedia(nuevo_signos.getSigPresionSistolica() + (2 * nuevo_signos.getSigPresionDiastolica()) / 3); 
        }
    }

    public void calcularHemoglobinaCorregido() {
        nuevo_signos.setSigValorHemoglobinaCorr(nuevo_signos.getSigValorHemoglobina() - 1.3f);
    }

    public void calcularIMC() {
        if (nuevo_signos.getSigPeso() != null && nuevo_signos.getSigEstatura() != null) {
            nuevo_signos.setSigImc(Math.round(nuevo_signos.getSigPeso() / (float) (Math.pow(nuevo_signos.getSigEstatura()/100, 2))));
        }
    }

    public void cargarHistoria(int his_id) {
        historia = CitaDAO.recuperarHistoriaID(his_id);
        try {
            nombre_medico = historia.getPersonasByMedicoPerId().getPerNombres()
                    + " " + historia.getPersonasByMedicoPerId().getPerApellidos();
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
        nombre_usuario = splited[0].substring(0, 1).toUpperCase() + splited[0].substring(1).toLowerCase();
    }

    /**
     * Método para guarda la información de la persona
     *
     * @return
     */
    public String guardarPacienteYSignos() {
        if (profesion_abierta.length() > 0) {
            persona.setPerProfesion(profesion_abierta);
        }
        if (parentesco_abierto.length() > 0) {
            persona.setPerParentesco(parentesco_abierto);
        }
        //Seteo de los datos de la persona
        persona.setPerFechaUlt(new Date());
        persona.setPerEsPaciente('S');
        persona.setPerUsuario(session.getAttribute("usuario").toString());

        return "/medico/registroSignos.xhtml?faces-redirect=true";
    }

    public String guardarPaciente() {
        if (profesion_abierta.length() > 0) {
            persona.setPerProfesion(profesion_abierta);
        }
        if (parentesco_abierto.length() > 0) {
            persona.setPerParentesco(parentesco_abierto);
        }
        //Seteo de los datos de la persona
        persona.setPerFechaUlt(new Date());
        persona.setPerEsPaciente('S');
        // Calcular la edad en caso de que no se haya visto reflejada en el campo correspondiente
        calcularEdad();
        persona.setPerUsuario(session.getAttribute("usuario").toString());
        PersonaDAO.crearActualizarPersona(persona);

        guardarDatosHistoriaInicial();

        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);

        FacesMessages.info(":growlInfo", "Paciente creado con éxito", "This is a specific message!");
        return "/privado/home.xhtml?faces-redirect=true";
    }

    public String recuperarSexoPorCodigo(int id) {
        if (id == '1') {
            return "M";
        }
        return "F";
    }
    
    public String recuperarSexoPorTipo(String tipo){
        String sexo = "";
        if (tipo.equals("H")){
            sexo = "Hombre";
        } else if(tipo.equals("M")){
            sexo = "Mujer";
        }
        return sexo;
    }

    public String recuperarEstadoCivilPorCodigo(String codigo) {
        return EstadoCivilDAO.recuperarEstadoCivilPorCodigo(codigo);
    }

    public String registrarCitaNueva() {
        CitaDAO.verificarHistoria();
        
        String e  = guardarDatosHistoriaInicial();
        if (e.equals("Correcto")) {
            FacesMessages.info(":growlInfo", "Cita nueva creada con éxito", "This is a specific message!");
        } else {
            FacesMessages.error(":growlInfo", "Error al crear un historia: "+e, "This is a specific message!");
        }
        
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        //Inicializando signos y revision
        return "/privado/home?faces-redirect=true";
    }

    public String guardarColaborador() {
        //Seteo de los datos de la persona
        persona.setPerFechaUlt(new Date());
        persona.setPerEsPaciente('N');
        persona.setPerUsuario(session.getAttribute("usuario").toString());
//        
//        //Seteo de los datos del usuario
        usuario.setUsuContra(convertirMD5(generarClaveAleatoria()));
        usuario.setUsuNombre(generarUsuario(persona.getPerNombres(), persona.getPerApellidos()));
        usuario.setUsuUsuario(session.getAttribute("usuario").toString());
        usuario.setPersonas(persona);
        usuario.setUsuFechaUlt(new Date());
        PersonaDAO.crearActualizarPersona(persona);
        UsuarioDAO.crearActualizarUsuario(usuario);
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessages.info(":growlInfo", "Colaborador creado con éxito", "This is a specific message!");
        return "/privado/home?faces-redirect=true";
    }
    
    public String eliminarPaciente() throws InterruptedException, IOException {
        eliminarHistorialPersona();
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessages.info(":growlInfo", "Paciente eliminado con éxito", "This is a specific message!");
        return "/privado/home?faces-redirect=true";
    }
    
    public void eliminarHistorialPersona(){
        PersonaDAO.eliminarPersona(persona);
    }

    public void guardarPacienteConSignos() {
        nuevo_signos.setSigFechaUlt(new Date());
        nuevo_signos.setSigUsuario(session.getAttribute("usuario").toString());
        signos = nuevo_signos;
        SignosDAO.crearActualizarSignos(signos);

        //Definiendo los items de la revision
        revisionDefault();
        RevisionSistemasDAO.crearActualizarRevision(revision);

        //Proceso para guardar historia
        PersonaDAO.crearActualizarPersona(persona);
        //Definiendo los items de la historia
        historiaDefaut();
        
        try {
            //Llamada a beans para guardar datos        
            CitaDAO.crearActualizarHistoria(historia);
            FacesMessages.info(":growlInfo", "Historia creada con éxito!", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error crear una historia: "+e, "This is a specific message!");
        }
    }

    public String guardarDatosHistoriaInicial() {
        signos.setSigPresionSistolica(0);
        signos.setSigPresionDiastolica(0);
        signos.setSigPresionArterialMedia(0);
        signos.setSigTemperatura(0f);
        signos.setSigFrecuenciaRespiratoria(0);
        signos.setSigFrecuenciaCardiaca(0);
        signos.setSigSaturacion(0);
        signos.setSigPeso(0f);
        signos.setSigEstatura(0f);
        signos.setSigImc(0);
        signos.setSigPerimetroAbdominal(0f);
        signos.setSigPerimetroBrazo(0f);
        signos.setSigGlucosaCapilar(0f);
        signos.setSigValorHemoglobina(0f);
        signos.setSigValorHemoglobinaCorr(0f);
        signos.setSigFechaUlt(new Date());
        signos.setSigUsuario(session.getAttribute("usuario").toString());
        SignosDAO.crearActualizarSignos(signos);

        //Definiendo los items de la revision
        revisionDefault();
        RevisionSistemasDAO.crearActualizarRevision(revision);

        //Proceso para guardar historia
        historiaDefaut();

        
        try {
            //Llamada a beans para guardar datos        
            CitaDAO.crearActualizarHistoria(historia);
            return "Correcto";
        } catch (Exception e) {
            return e.getCause().getMessage();
        }
    }
    
    public void revisionDefault(){
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
    }
    
    private void historiaDefaut(){
        historia.setHisFechaUlt(new Date());
        historia.setHisFechaCreacion(new Date());
        historia.setHisUsuario(session.getAttribute("usuario").toString());
        historia.setHisMotivo("Por definir");
        historia.setHisEnfermedad("Por definir");
        historia.setSignos(signos);
        historia.setRevisionSistemas(revision);
        historia.setPersonasByPacientePerId(persona);
        historia.setHisCompletado(Byte.valueOf("0"));
        historia.setPersonasByMedicoPerId(PersonaDAO.recuperarPersonaUsuario(session.getAttribute("usuario").toString()));
    }

    public String redireccionarPacienteGuardado() throws InterruptedException {
        guardarPacienteConSignos();
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessages.info(":growlInfo", "Paciente creado con éxito", "This is a specific message!");
        // Fragmento para conservar los mensajes entre vistas
        return "/privado/home.xhtml?faces-redirect=true";
    }

    public String redireccionarIniciarCita() {
        guardarPacienteConSignos();
        citaBean.VerCitaMedica(CitaDAO.recuperarIDUltimaHistoria());
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessages.info(":growlInfo", "Cita Iniciada", "This is a specific message!");
        // Fragmento para conservar los mensajes entre vistas
        return "/medico/citaMedica.xhtml?faces-redirect=true";
    }

    public String actualizarPaciente() {
        if (profesion_abierta.length() > 0) {
            persona.setPerProfesion(profesion_abierta);
        }
        if (parentesco_abierto.length() > 0) {
            persona.setPerParentesco(parentesco_abierto);
        }
        PersonaDAO.crearActualizarPersona(persona);

        // Fragmento para conservar los mensajes entre vistas
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessages.info(":growlInfo", "Se han actualizado los datos del paciente", "This is a specific message!");
        return "/privado/home.xhtml?faces-redirect=true";
    }
    
    public void actualizarEdad(int id){
    }

    public void especificar_ocupacion() {
        if (persona.getPerProfesion().equals("51")) {
            renderizar_profesion_abierta = "true";
        } else {
            profesion_abierta = "";
            renderizar_profesion_abierta = "false";
        }
    }

    public void especificar_parentesco() {
        if (persona.getPerParentesco().equals("9")) {
            renderizar_parentesco_abierto = "true";
        } else {
            parentesco_abierto = "";
            renderizar_parentesco_abierto = "false";
        }
    }
    public Personas actualizarEdad(Personas persona1){
        persona = persona1;
        calcularEdad();
        return persona;
    }

    public void calcularEdad() {
        String edad;
        if (persona.getPerNac() != null) {
            try {
                LocalDate actual = LocalDate.now();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(persona.getPerNac());
                LocalDate nacimiento = LocalDate.of(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1,  // Los meses en Calendar son 0-indexados
                    calendar.get(Calendar.DAY_OF_MONTH)
                );
                Period periodo = Period.between(actual, nacimiento);
                edad = periodo.getYears()*-1+ "Años-"+ periodo.getMonths()*-1+"Meses-"+ periodo.getDays()*-1+"Dias"; 
                persona.setPerEdad(edad);
            } catch (Exception e) {
                persona.setPerEdad("0");
            }
        } else {
            persona.setPerEdad("0");
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

    public void validarCedulaCreada(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (PersonaDAO.recuperarPersonaCedula((String) value) == null) {
            validarCedula((String) value);
        } else {

            throw new ValidatorException(new FacesMessage("Esta cédula ya existe en los registros"));

        }

    }

    public void validarCedulaExistente(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        validarCedula((String) value);

    }

    public static void validarCedula(String cedula) {
        boolean valido = false;
        int suma = 0;
        if (cedula.length() < 10) {
            throw new ValidatorException(new FacesMessage("Cedula inválida"));

        } else {
            try {
                int a[] = new int[cedula.length() / 2];
                int b[] = new int[(cedula.length() / 2)];
                int c = 0;
                int d = 1;
                for (int i = 0; i < cedula.length() / 2; i++) {
                    a[i] = Integer.parseInt(String.valueOf(cedula.charAt(c)));
                    c = c + 2;
                    if (i < (cedula.length() / 2) - 1) {
                        b[i] = Integer.parseInt(String.valueOf(cedula.charAt(d)));
                        d = d + 2;
                    }

                }

                for (int i = 0; i < a.length; i++) {
                    a[i] = a[i] * 2;
                    if (a[i] > 9) {
                        a[i] = a[i] - 9;
                    }
                    suma = suma + a[i] + b[i];
                }
                int aux = suma / 10;
                int dec = (aux + 1) * 10;
                if ((dec - suma) == Integer.parseInt(String.valueOf(cedula.charAt(cedula.length() - 1)))) {
                    valido = true;
                } else {
                    valido = suma % 10 == 0 && cedula.charAt(cedula.length() - 1) == '0';
                }
                if (!valido) {
                    throw new ValidatorException(new FacesMessage("Cedula inválida"));
                }
            } catch (Exception e) {
                throw new ValidatorException(new FacesMessage("Cedula inválida"));
            }
        }
    }

    public void validarEdad(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (persona.getPerEdad() == "0") {
            throw new ValidatorException(new FacesMessage("Compruebe la edad del paciente"));
        }

    }

    public String convertirMD5(String valor) {

        String md5Hex = DigestUtils
                .md5Hex(valor).toUpperCase();
        return md5Hex;

    }

    public List<String> recuperarNombresRol(int rol) {
        List<String> colaboradores = new ArrayList<>();
        Usuarios u = new Usuarios();
        int tipo_usuario;
        System.out.println("TEST");
        for (Personas p : PersonaDAO.recuperarColaboradores()) {
            System.out.println("p: " + p.getPerUsuario());
            u = (Usuarios) p.getUsuarioses().iterator().next();
            System.out.println("u: "+u);
            tipo_usuario = u.getRolesRolId();
            if (tipo_usuario == rol) {
                colaboradores.add(p.getPerNombres() + " - " + p.getPerApellidos());
            }
        }
        return colaboradores;
    }

    public List<String> recuperarNombresPacientes() {
        return PersonaDAO.recuperarNombresPacientes();
    }

    public List<String> recuperarNombresColaboradores() {
        List<String> colaboradores = new ArrayList<>();
        for (Personas colaborador : PersonaDAO.recuperarColaboradores()) {
            colaboradores.add(colaborador.getPerNombres() + " - " + colaborador.getPerApellidos());
        }
        return colaboradores;
    }

    public String asignarMedico() {
        Personas medico
                = PersonaDAO.recuperarPersonaNombre(nombre_medico.split(" - ")[0],
                        nombre_medico.split(" - ")[1]);
        historia.setPersonasByMedicoPerId(medico);
        try {
            CitaDAO.crearActualizarHistoria(historia);
            context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            FacesMessages.info(":growlInfo", "Médico asignado con éxito!", "This is a specific message!");
            return "/medico/asignacionMedico.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al asignar cita: "+e, "This is a specific message!");
            return null;
        }
        
    }

    public void inicializarProfesionesYParentescos() {
        lista_ocupaciones = OcupacionDAO.recuperarOcupaciones();
        lista_parentescos = ParentescoDAO.recuperarParentescos();
    }

    public void inicializarCiudades() {
        lista_ciudades = CiudadesDAO.recuperarCiudades();
    }

    public void inicializarEstadosCiviles() {
        lista_estados_civiles = EstadoCivilDAO.recuperarEstados();
    }
    
    public String recuperarNombreUsuario(int per_id){
        return UsuarioDAO.obtenerUsuarioPorPerId(per_id).getUsuNombre();
    }

    public void recuperarPacientesListener() {
        String nombres[] = getPer_nombre_completo().split(" - ");
        if (nombres.length == 3) {
            persona = PersonaDAO.recuperarPersonaNombre(nombres[0], nombres[1]);
            calcularEdad();

            if (persona != null) {
                per_nombre_completo = persona.getPerApellidos() + " - " + persona.getPerNombres();
                cantidad_historias = persona.getHistoriasesForPacientePerId().size();
                inhabilitar_parentesco = "false";
                inhabilitar_profesion = "false";
                try {
                    Integer.valueOf(persona.getPerProfesion());
                } catch (NumberFormatException e) {
                    renderizar_profesion_abierta = "true";
                    profesion_abierta = persona.getPerProfesion();
                    persona.setPerProfesion("51");
                }
                try {
                    Integer.valueOf(persona.getPerParentesco());
                } catch (NumberFormatException e) {
                    renderizar_parentesco_abierto = "true";
                    parentesco_abierto = persona.getPerParentesco();
                    persona.setPerParentesco("9");
                }
            } else {
                FacesMessages.info(":growlInfo", "No se ha encontrado el diasgnóstico", "This is a specific message!");
                per_nombre_completo = "";
            }

        } else {
            FacesMessages.info(":growlInfo", "Nombre no válido", "This is a specific message!");
            per_nombre_completo = "";
        }
    }

    public void recuperarSignosListener() {
        String nombres[] = getPer_nombre_completo().split(" - ");
        if (nombres.length > 1) {

            persona = PersonaDAO.recuperarPersonaNombre(nombres[1], nombres[0]);

        } else {
            FacesMessages.info(":growl", "Paciente no registrado", "This is a specific message!");
            per_nombre_completo = "";
        }
    }

    public Personas getPersona() {
        return persona;
    }
    
    public String getFechaActual(){
        //return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        return "09/08/2023";
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

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
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

    public String getRenderizar_profesion_abierta() {
        return renderizar_profesion_abierta;
    }

    public void setRenderizar_profesion_abierta(String renderizar_profesion_abierta) {
        this.renderizar_profesion_abierta = renderizar_profesion_abierta;
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

    public CitaBean getCitaBean() {
        return citaBean;
    }

    // setter must be present or managed property won't work
    public void setCitaBean(CitaBean cita) {
        this.citaBean = cita;
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

    public String getInhabilitar_profesion() {
        return inhabilitar_profesion;
    }

    public void setInhabilitar_profesion(String inhabilitar_profesion) {
        this.inhabilitar_profesion = inhabilitar_profesion;
    }

    public String getInhabilitar_parentesco() {
        return inhabilitar_parentesco;
    }

    public void setInhabilitar_parentesco(String inhabilitar_parentesco) {
        this.inhabilitar_parentesco = inhabilitar_parentesco;
    }

    public List<Parentescos> getLista_parentescos() {
        return lista_parentescos;
    }

    public void setLista_parentescos(List<Parentescos> lista_parentescos) {
        this.lista_parentescos = lista_parentescos;
    }

    public List<Personas> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Personas> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public Signos getNuevo_signos() {
        return nuevo_signos;
    }

    public void setNuevo_signos(Signos nuevo_signos) {
        this.nuevo_signos = nuevo_signos;
    }

}
