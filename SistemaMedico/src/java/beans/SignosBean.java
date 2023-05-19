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
import dao.PersonaDAO;
import dao.RevisionSistemasDAO;
import dao.SignosDAO;
import dao.UsuarioDAO;
import datos.Ciudades;
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
import java.util.List;
import javax.faces.context.FacesContext;
import net.bootsfaces.utils.FacesMessages;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import javax.faces.bean.ManagedProperty;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "SignosBean")
@SessionScoped
public final class SignosBean implements Serializable{
    Signos signos;
    
    public void SignosBean(){
        inicializarSignos();
    }
    
    public void inicializarSignos(){
        signos = new Signos();
    }
    
}