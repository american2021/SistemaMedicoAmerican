/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.HistoriaDAO;
import dao.PacienteDAO;
import dao.SignosDAO;
import dao.UsuarioDAO;
import datos.Antecedentes;
import datos.Historias;
import java.util.Date;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import datos.Personas;
import datos.Signos;
import datos.Usuarios;
import java.util.ArrayList;
import java.util.List;
import net.bootsfaces.utils.FacesMessages;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "PacienteBean")
@SessionScoped
public final class PacienteBean implements Serializable{
    private String per_nombre_completo;
    
    //Creación de objetos
    private Personas persona;
    private Usuarios usuario;
    private Signos signos;
    private Historias historia;
    
    //Atributos de la tabla signos vitales
    private Integer sig_presion_sistolica;
    private Integer sig_presion_diastolica;
    private Integer sig_frecuencia_respiratoria;
    private Integer sig_frecuencia_cardiaca;
    private Integer sig_presion_arterial_media;
    private Integer sig_saturacion;
    private Integer sig_temperatura;
    private Integer sig_peso;
    private Integer sig_estatura;
    private Integer sig_imc;
    private Integer sig_perimetro_abdominal;
    private Integer sig_glucosa_capilar;
    private Integer sig_valor_hemoglobina;
    private Integer sig_valor_hemoglobina_corr;
    private Date sig_fecha_ult;
    private String sig_usuario;
    
    //Declaración de colecciones de datos
    private List<Personas> pacientes;
            
    public PacienteBean(){
        inicializarPaciente();
        inicializarSignos();
        inicializarHistoria();
    }
    
    public void inicializarPaciente(){
        // Inicialización datos paciente
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
    
    public void inicializarHistoria(){
        historia = new Historias();
    }
    
    /**
     * Método para guarda la información de la persona
     * @return 
     */
    public String guardarPersona(){
        //Seteo de los datos de la persona
        persona.setPerFechaUlt(new Date());
        persona.setPerUsuario("defecto");
        
        //Seteo de los datos del usuario
        usuario.setUsuContra(convertirMD5(generarClaveAleatoria()));
        usuario.setUsuNombre(generarUsuario(persona.getPerNombres(), persona.getPerApellidos()));
        usuario.setUsuUsuario("defecto");
        usuario.setPersonas(persona);
        usuario.setUsuFechaUlt(new Date());
        return "/faces/medico/registroSignos.xhtml?faces-redirect=true";
    }
    
    public void guardarPaciente(){
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
        historia.setHisUsuario("defecto");
        historia.setHisMotivo("Por definir");
        historia.setHisEnfermedadActual("Por definir");
        //Proceso para guardar paciente
        signos.setSigFechaUlt(new Date());
        signos.setSigUsuario("defecto");
        
        //Llamada a beans para guardar datos
        PacienteDAO.crearPersona(persona);
        PacienteDAO.crearUsuario(usuario);
        HistoriaDAO.crearHistoriaPrimeraVez(historia);
        SignosDAO.crearSignosPrimeraVez(signos);
    }
    
    public void actualizarPaciente(){
        PacienteDAO.crearActualizarPaciente(persona);
        FacesMessages.info(":growlInfo", "Se han actualizado los datos del paciente", "This is a specific message!");
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
    
    public List<String> listarNombres(){
        return PacienteDAO.listarNombres();
    }
    
    public void recuperarPacientesListener(){
        String nombres[] = getPer_nombre_completo().split(" - ");
        if (nombres.length > 1) {

            persona = PacienteDAO.recuperarPersonaNombre(nombres[1], nombres[0]);

            if (persona != null) {
                per_nombre_completo = persona.getPerApellidos() + " - " + persona.getPerNombres();
                //List<Signos> signos = new ArrayList<>();
                //antecedentes.addAll(p.getAntecedenteses());
                //sig_presion_sistolica = antecedentes.get(-1).get;
                //FacesMessages.info(":growlInfo", "El motivo de la consulta es: "+motivo, "This is a specific message!");

            } else {
                FacesMessages.info(":growl", "Paciente no registrado", "This is a specific message!");
            }

        } else {
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

    public Integer getSig_peso() {
        return sig_peso;
    }

    public void setSig_peso(Integer sig_peso) {
        this.sig_peso = sig_peso;
    }

    public Integer getSig_estatura() {
        return sig_estatura;
    }

    public void setSig_estatura(Integer sig_estatura) {
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

    public Integer getSig_glucosa_capilar() {
        return sig_glucosa_capilar;
    }

    public void setSig_glucosa_capilar(Integer sig_glucosa_capilar) {
        this.sig_glucosa_capilar = sig_glucosa_capilar;
    }

    public Integer getSig_valor_hemoglobina() {
        return sig_valor_hemoglobina;
    }

    public void setSig_valor_hemoglobina(Integer sig_valor_hemoglobina) {
        this.sig_valor_hemoglobina = sig_valor_hemoglobina;
    }

    public Integer getSig_valor_hemoglobina_corr() {
        return sig_valor_hemoglobina_corr;
    }

    public void setSig_valor_hemoglobina_corr(Integer sig_valor_hemoglobina_corr) {
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
    
    
}
