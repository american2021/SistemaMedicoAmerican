/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ExamenesDAO;
import datos.Examenes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author Programacion
 */
@ManagedBean(name = "ExamenesBean")
@SessionScoped
public final class ExamenesBean implements Serializable{
    
    private Examenes examenes;
    private Examenes nuevo_examenes;
    private List<Examenes> lista_examenes;
    private String nombre_examen;
    private String actualizar = "0";
    
    FacesContext context;
    HttpSession session;
    
    public void ExamenesBean(){
        inicializarExamenes();
    }
    
    public void inicializarExamenes(){
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (this.actualizar.equals("0")) {
            examenes = new Examenes();
        }
        nuevo_examenes = new Examenes();
        nombre_examen = null;
        lista_examenes = new ArrayList<>();
        recuperarExamenes();
    }
    
    
    public String cargarExamen(Examenes e, String actualizar){
        this.actualizar = actualizar;
        this.examenes = e;
        return "/administrador/actualizacionExamen.xhtml?faces-redirect=true";
    }
    
    /**
     * Método para recuperar los examenes registrados en la base
     * @return 
     */
    public List<String> recuperarNombresExamen() {
        return ExamenesDAO.recuperarNombresExamenes();
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
     * Método para recuperar los examenes registrados en la base por el tipo
     * @return 
     */
    public List<Examenes> recuperarExamenes() {
        lista_examenes = ExamenesDAO.recuperarExamenes();
        return lista_examenes;
    }
    
    /**
     * Método para crear un nuevo examen
     */
    public void crearExamen(){
        nuevo_examenes.setExaFechaUlt(new Date());
        nuevo_examenes.setExaUsuario(session.getAttribute("usuario").toString());
        try {
            ExamenesDAO.crearActualizarExamanes(nuevo_examenes);
            nuevo_examenes = new Examenes();
            recuperarNombresExamen();
            recuperarExamenes();
            FacesMessages.info(":growlInfo", "Exámen Creado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al crear exámen"+e, "This is a specific message!");
        }
    }
    
    /**
     * Método para aztualizar un examen
     * @return 
     */
    public String actualizarExamen(){
        try {
            ExamenesDAO.crearActualizarExamanes(examenes);
            examenes = new Examenes();
            nombre_examen = null;
            recuperarNombresExamen();
            FacesMessages.info(":growlInfo", "Exámen actualizado", "This is a specific message!");
            if (this.actualizar.equals("1")) {
                return "/administrador/registroExamen.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al actualizar exámen "+e, "This is a specific message!");
            return null;
        }
        return null;
    }
    
    /**
     * Método para crear un nuevo examen
     */
    public void eliminarExamen(){
        try {
            ExamenesDAO.eliminarExamanes(examenes);
            recuperarNombresExamen();
            recuperarExamenes();
            FacesMessages.info(":growlInfo", "Exámen eliminado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al eliminar examen"+e, "This is a specific message!");
        }
    }
    
    /**
     * Método para preparar examen
     * @param examenes
     */
    public void prepararExamen(Examenes examenes){
        this.examenes = examenes;
    }
    
    /**
     * Método para retornar el tipo de examen
     * @param tipo
     */
    public String formatoTipoExamen(String tipo) {
        if (tipo.equals("1")){
            tipo = "Laboratorio";
        } else if (tipo.equals("2")){
            tipo = "Imagenologia";
        } else {
            tipo = "Histopatología";
        }      
        return tipo;
    }

    public Examenes getExamenes() {
        return examenes;
    }

    public void setExamenes(Examenes examenes) {
        this.examenes = examenes;
    }

    public Examenes getNuevo_examenes() {
        return nuevo_examenes;
    }

    public void setNuevo_examenes(Examenes nuevo_examenes) {
        this.nuevo_examenes = nuevo_examenes;
    }

    public String getNombre_examen() {
        return nombre_examen;
    }

    public void setNombre_examen(String nombre_examen) {
        this.nombre_examen = nombre_examen;
    }

    public List<Examenes> getLista_examenes() {
        return lista_examenes;
    }
    
    public List<Examenes> getListaExamenesLab() {
        return filtrarPorTipo("1");
    }

    public List<Examenes> getListaExamenesImg() {
        return filtrarPorTipo("2");
    }

    public List<Examenes> getListaExamenesHist() {
        return filtrarPorTipo("3");
    }

    private List<Examenes> filtrarPorTipo(String tipo) {;
        return lista_examenes.stream()
                .filter(examen -> String.valueOf(examen.getExaTipo()).equals(tipo))
                .collect(Collectors.toList());
    }

    public String getActualizar() {
        return actualizar;
    }

    public void setActualizar(String actualizar) {
        this.actualizar = actualizar;
    }
    

}
