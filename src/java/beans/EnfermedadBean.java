/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.EnfermedadDAO;
import dao.HistoriaDAO;
import dao.PacienteDAO;
import dao.SignosDAO;
import dao.UsuarioDAO;
import datos.Antecedentes;
import datos.Enfermedades;
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
@ManagedBean(name = "EnfermedadBean")
@SessionScoped
public final class EnfermedadBean implements Serializable{
    
    private Enfermedades enfermedad;
    private String enf_nombre;
    
    public EnfermedadBean(){
        inicializarEnfermedad();
    }
    
    public void inicializarEnfermedad(){
        enfermedad = new Enfermedades();
        enf_nombre = "";
    }
    
    public void crearEnfermedad(){
        EnfermedadDAO.crearActualizarEnfermedad(enfermedad);
        FacesMessages.info(":growlInfo", "Se ha creado la patología", "This is a specific message!");
    }
    
    public void actualizarEnfermedad(){
        EnfermedadDAO.crearActualizarEnfermedad(enfermedad);
        FacesMessages.info(":growlInfo", "Se ha creado la patología", "This is a specific message!");
    }
    
    public List<String> listarEnfermedades(){
        return EnfermedadDAO.listarEnfermedades();
    }
    
    public void listarEnfermedadListener(){
        if (enf_nombre.length() > 1) {

            enfermedad = EnfermedadDAO.recuperarEnfermedadNombre(enf_nombre);

            if (enfermedad != null) {
                enf_nombre = enfermedad.getEnfNombre();

            } else {
                FacesMessages.info(":growl", "Enfermedad no registrado", "This is a specific message!");
            }

        } else {
            enf_nombre = "";
        }
    }

    public Enfermedades getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedades enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getEnf_nombre() {
        return enf_nombre;
    }

    public void setEnf_nombre(String enf_nombre) {
        this.enf_nombre = enf_nombre;
    }
    
    
}
