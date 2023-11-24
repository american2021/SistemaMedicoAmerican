/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.DiagnosticoDAO;
import datos.Diagnosticos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "DiagnosticoBean")
@SessionScoped
public final class DiagnosticoBean implements Serializable{
    
    private Diagnosticos diagnosticos;
    private Diagnosticos nuevo_diagnostico;
    private List<Diagnosticos> lista_diagnostico;
    private String nombre_diagnostico;
    private String update_diagnostico = "0";
    
    FacesContext context;
    HttpSession session;
    
    public DiagnosticoBean(){
        inicializarDiagnostico();
    }
    
    public void inicializarDiagnostico(){
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (this.update_diagnostico.equals("0")) {
            diagnosticos = new Diagnosticos();
        }
        nuevo_diagnostico = new Diagnosticos();
        nombre_diagnostico = null;
        lista_diagnostico = new ArrayList<>();
        recuperarDiagnosticos();
    }
    
    public String cargarDiagnostoco(Diagnosticos d, String actualizar){
        this.update_diagnostico = actualizar;
        this.diagnosticos = d;
        return "/administrador/actualizacionDiagnostico.xhtml?faces-redirect=true";
    }
    
    /**
     * Método para recuperar los diagnósticos registrados en la base
     * @return 
     */
    public List<String> recuperarNombresDiagnosticos() {
        return DiagnosticoDAO.recuperarNombresDiagnosticos();
    }
    
    /**
     * Método que realiza una acción cuando un diagnóstico es seleccionado
     */
    public void recuperarDiagnosticosListener(){
        String diagnostico_codigo[] = getNombre_diagnostico().split(" - ");
        if (diagnostico_codigo.length == 2) {
            diagnosticos = DiagnosticoDAO.recuperarDiagnosticoCodigoCie(diagnostico_codigo[0]);
        }
        else {
            FacesMessages.info(":growlInfo", "Nombre no válido", "This is a specific message!");
            nombre_diagnostico = "";
        }
    }
    
    /**
     * Método para recuperar los diagnosticos registrados en la base
     * @return 
     */
    public List<Diagnosticos> recuperarDiagnosticos() {
        lista_diagnostico = DiagnosticoDAO.recuperarDiagnostico();
        return lista_diagnostico;
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
            recuperarNombresDiagnosticos();
            recuperarDiagnosticos();
            FacesMessages.info(":growlInfo", "Diagnóstico Creado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al crear diagnóstico: "+e.getCause().getMessage(), "This is a specific message!");
        }
        
    }
    
    /**
     * Método para actualizar un examen
     * @return 
     */
    public String actualizarDiagnostico(){
        try {
            DiagnosticoDAO.crearActualizarDiagnostico(diagnosticos);
            diagnosticos = new Diagnosticos();
            nombre_diagnostico = null;
            recuperarNombresDiagnosticos();
            recuperarDiagnosticos();
            FacesMessages.info(":growlInfo", "Diagnóstico actualizado", "This is a specific message!");
            if (this.update_diagnostico.equals("1")) {
                return "/administrador/registroDiagnostico.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al actualizar diagnóstico: "+e.getCause().getMessage(), "This is a specific message!");
            return null;
        }
        return null;
    }
    
    /**
     * Método para eliminar un examen
     */
    public void eliminarDiagnostico(){
        try {
            DiagnosticoDAO.eliminarDiagnostico(diagnosticos);
            recuperarNombresDiagnosticos();
            recuperarDiagnosticos();
            FacesMessages.info(":growlInfo", "Diagnóstico eliminado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al eliminar diagnóstico:"+e.getCause().getMessage(), "This is a specific message!");
        }
    }
    
    /**
     * Método para preparar examen
     * @param diagnosticos
     */
    public void prepararDiagnostico(Diagnosticos diagnosticos){
        this.diagnosticos = diagnosticos;
    }

    public Diagnosticos getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(Diagnosticos diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    public Diagnosticos getNuevo_diagnostico() {
        return nuevo_diagnostico;
    }

    public void setNuevo_diagnostico(Diagnosticos nuevo_diagnostico) {
        this.nuevo_diagnostico = nuevo_diagnostico;
    }

    public String getUpdate_diagnostico() {
        return update_diagnostico;
    }

    public void setUpdate_diagnostico(String update_diagnostico) {
        this.update_diagnostico = update_diagnostico;
    }

    public String getNombre_diagnostico() {
        return nombre_diagnostico;
    }

    public void setNombre_diagnostico(String nombre_diagnostico) {
        this.nombre_diagnostico = nombre_diagnostico;
    }

    public List<Diagnosticos> getLista_diagnostico() {
        return lista_diagnostico;
    }
}
