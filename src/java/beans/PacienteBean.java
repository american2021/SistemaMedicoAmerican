/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.PacienteDAO;
import dao.UsuarioDAO;
import java.util.Date;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import datos.Personas;
import datos.Usuarios;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "PacienteBean")
@SessionScoped
public final class PacienteBean implements Serializable{
    private String per_nombres;
    private String per_apellidos;
    //1 masculino, 2 femenino
    private char per_sexo;
    private Date per_nac;
    private Date per_fecha_ult;
    private String per_grupo_sanguineo;
    //Opciones por declarar
    private char per_identidad_genero;
    //Opciones por declarar
    private String per_profesion;
    private Personas persona;
    private Usuarios usuario;
    
    public PacienteBean(){
        inicializarPaciente();
    }
    
    public void inicializarPaciente(){
        per_nombres = "";
        per_apellidos = "";
        per_sexo = '0';
        per_nac = new Date();
        per_grupo_sanguineo = "";
        per_identidad_genero = '0';
        per_profesion = "0";
        persona = new Personas();
        usuario = new Usuarios();
    }
    
    public void guardarPaciente(){
        //Seteo de los datos de la persona
        persona.setPerNombres(per_nombres);
        persona.setPerApellidos(per_apellidos);
        persona.setPerSexo(per_sexo);
        persona.setPerIdentidadGenero(per_identidad_genero);
        persona.setPerNac(per_nac);
        persona.setPerGrupoSanguineo(per_grupo_sanguineo);
        persona.setPerProfesion(per_profesion);
        persona.setPerFechaUlt(new Date());
        persona.setPerUsuario("default");
        
        PacienteDAO.crearPersona(persona);
        
        //Seteo de los datos del usuario
        usuario.setUsuContra(convertirMD5(generarClaveAleatoria()));
        usuario.setUsuNombre(generarUsuario(per_nombres, per_apellidos));
        usuario.setUsuUsuario("defecto");
        usuario.setPersonas(persona);
        usuario.setUsuFechaUlt(new Date());
        
        PacienteDAO.crearUsuario(usuario);
    }
    
    public static String generarClaveAleatoria() {

        return RandomStringUtils.randomAlphanumeric(8);

    }
    
    public static String generarUsuario(String nombre, String apellido) {

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
    
    public static String convertirMD5(String valor) {

        String md5Hex = DigestUtils
                .md5Hex(valor).toUpperCase();
        return md5Hex;

    }

    public String getPer_nombres() {
        return per_nombres;
    }

    public void setPer_nombres(String per_nombres) {
        this.per_nombres = per_nombres;
    }

    public String getPer_apellidos() {
        return per_apellidos;
    }

    public void setPer_apellidos(String per_apellidos) {
        this.per_apellidos = per_apellidos;
    }

    public char getPer_sexo() {
        return per_sexo;
    }

    public void setPer_sexo(char per_sexo) {
        this.per_sexo = per_sexo;
    }

    public Date getPer_nac() {
        return per_nac;
    }

    public void setPer_nac(Date per_nac) {
        this.per_nac = per_nac;
    }

    public String getPer_grupo_sanguineo() {
        return per_grupo_sanguineo;
    }

    public void setPer_grupo_sanguineo(String per_grupo_sanguineo) {
        this.per_grupo_sanguineo = per_grupo_sanguineo;
    }

    public char getPer_identidad_genero() {
        return per_identidad_genero;
    }

    public void setPer_identidad_genero(char per_identidad_genero) {
        this.per_identidad_genero = per_identidad_genero;
    }

    public String getPer_profesion() {
        return per_profesion;
    }

    public void setPer_profesion(String per_profesion) {
        this.per_profesion = per_profesion;
    }

    public Date getPer_fecha_ult() {
        return per_fecha_ult;
    }

    public void setPer_fecha_ult(Date per_fecha_ult) {
        this.per_fecha_ult = per_fecha_ult;
    }

    public Personas getPersona() {
        return persona;
    }
}
