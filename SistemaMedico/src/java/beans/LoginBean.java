/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UsuarioDAO;
import datos.Personas;
import datos.Usuarios;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private Usuarios usuario;
    private int rolActual;
    private String usu_nombre;
    private String usu_contra;
    private boolean estaLogueado;
    private String correo;
    private Personas persona;
    private String usuCorRecu;
    private String claveCamb1;
    private String claveCamb2;
    private String claveAct;
    private String cambioRol;
    private boolean valido;
    
    HttpSession session;

    public LoginBean() {
        //inicializarUsuario();
    }
    
    public void inicializarUsuario(){
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        usuario = null;
        usu_contra = "";
        usu_nombre = "";
    }

    public String login() {
        usuario = UsuarioDAO.obtenerUsuario(usu_nombre);
        
        if (usuario != null) {
            rolActual = usuario.getRolesRolId();
            valido = usuario.getUsuContra().equalsIgnoreCase(convertirMD5(usu_contra));
            if (valido) {
                estaLogueado = true;
                session.setAttribute("usuario", usuario.getUsuNombre());
                return "/privado/home.xhtml?faces-redirect=true";
            } else {
                FacesMessages.warning(":growl", "Revise sus credenciales", "This is a specific message!");
                return "";
            }
        } else {
            FacesMessages.warning(":growl", "El usuario no existe en el sistema", "This is a specific message!");
            return "";
        }
    }
    
    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        estaLogueado = false;
        return "/?faces-redirect=true";

    }

    public void accessoLogueado() {

        if (!estaLogueado) {

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("./");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void accessoNoLogueado() {

        if (estaLogueado) {

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("./home");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void accessoRestringido() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("./");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public String renderizarAdministrador() {

        return String.valueOf(rolActual == 1);

    }
    
    public String renderizarEstudiante() {

        return String.valueOf(rolActual == 4);

    }
    
    public String renderizarMedico() {

        return String.valueOf(rolActual == 2);

    }
    
    public String renderizarContador() {
        return String.valueOf(rolActual == 3);

    }

    public void comprobarLogin() {
        if (!estaLogueado) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("./");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void accessoExterno() {

        if (!estaLogueado) {

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("./");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public String sesionInactiva() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        Log l = new Log(1, usuario.getId().getNombreUsu(), "Logout Expirado", new Date());
//        CrudDAO.guardarLogueo(l);
        ec.invalidateSession();
        return "index.xhtml?faces-redirect=true";

    }

    public static String convertirMD5(String valor) {

        String md5Hex = DigestUtils.md5Hex(valor).toUpperCase();
        return md5Hex;

    }

    /**
     * @return the usuario
     */
    public Usuarios getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the nombreUsu
     */
    public String getUsu_nombre() {
        return usu_nombre;
    }

    /**
     * @param usu_nombre the nombreUsu to set
     */
    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    /**
     * @return the clave
     */
    public String getUsu_contra() {
        return usu_contra;
    }

    /**
     * @param usu_contra the clave to set
     */
    public void setUsu_contra(String usu_contra) {
        this.usu_contra = usu_contra;
    }

    /**
     * @return the estaLogueado
     */
    public boolean isEstaLogueado() {
        return estaLogueado;
    }

    /**
     * @param estaLogueado the estaLogueado to set
     */
    public void setEstaLogueado(boolean estaLogueado) {
        this.estaLogueado = estaLogueado;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the persona
     */
    public Personas getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    /**
     * @return the usuCorRecu
     */
    public String getUsuCorRecu() {
        return usuCorRecu;
    }

    /**
     * @param usuCorRecu the usuCorRecu to set
     */
    public void setUsuCorRecu(String usuCorRecu) {
        this.usuCorRecu = usuCorRecu;
    }

    public void eliminarSesion() {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    }

    /**
     * @return the claveCamb1
     */
    public String getClaveCamb1() {
        return claveCamb1;
    }

    /**
     * @param claveCamb1 the claveCamb1 to set
     */
    public void setClaveCamb1(String claveCamb1) {
        this.claveCamb1 = claveCamb1;
    }

    /**
     * @return the claveCamb2
     */
    public String getClaveCamb2() {
        return claveCamb2;
    }

    /**
     * @param claveCamb2 the claveCamb2 to set
     */
    public void setClaveCamb2(String claveCamb2) {
        this.claveCamb2 = claveCamb2;
    }

    public void clavesIguales(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String tempC1 = (String) component.getAttributes().get("clave1");
        String tempC2 = (String) value;

        Pattern pat = Pattern.compile("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$");
        Matcher mat1 = pat.matcher(tempC1);
        Matcher mat2 = pat.matcher(tempC2);

        if (!tempC2.equals("")) {
            if (!tempC1.equals(tempC2)) {
                throw new ValidatorException(new FacesMessage("Las dos claves deben ser iguales"));
            } else if (!mat1.matches() || !mat2.matches()) {
                throw new ValidatorException(new FacesMessage("La contraseña debe tener entre 8 y 16 caracteres, un dígito, una minúscula y una mayúscula"));
            }
        } else {
            throw new ValidatorException(new FacesMessage("Los campos no pueden ser vacios"));
        }
    }

    /**
     * @return the claveAct
     */
    public String getClaveAct() {
        return claveAct;
    }

    /**
     * @param claveAct the claveAct to set
     */
    public void setClaveAct(String claveAct) {
        this.claveAct = claveAct;
    }

    /**
     * @return the rolActual
     */
    public int getRolActual() {
        return rolActual;
    }

    /**
     * @param rolActual the rolActual to set
     */
    public void setRolActual(int rolActual) {
        this.rolActual = rolActual;
    }

    /**
     * @return the cambioRol
     */
    public String getCambioRol() {
        return cambioRol;
    }

    /**
     * @param cambioRol the cambioRol to set
     */
    public void setCambioRol(String cambioRol) {
        this.cambioRol = cambioRol;
    }

}
