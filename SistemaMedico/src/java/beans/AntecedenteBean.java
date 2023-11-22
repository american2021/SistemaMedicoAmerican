/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.AntecedenteDAO;
import datos.Antecedente;
import java.io.Serializable;
import java.util.ArrayList;
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
@ManagedBean(name = "AntecedenteBean")
@SessionScoped
public final class AntecedenteBean implements Serializable{
    
    private Antecedente antecedente;
    private Antecedente nuevo_antecedente;
    private List<Antecedente> lista_antecedente;
    private String nombre_antecedente;
    private String updateAntecedente = "0";
    private String antecedente_abierta;
    private String renderizar_antecedente;
    
    private List<String> lista_categoria_antecedentes;
    
    FacesContext context;
    HttpSession session;
    
    public void AntecedenteBean(){
        inicializarAntecedente();
    }
    
    public void inicializarAntecedente(){
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (this.updateAntecedente.equals("0")) {
            antecedente = new Antecedente();
        }
        nuevo_antecedente = new Antecedente();
        nombre_antecedente = null;
        lista_antecedente = new ArrayList<>();
        antecedente_abierta = null;
        renderizar_antecedente = "false";
        lista_categoria_antecedentes = new ArrayList<>();
        recuperarCategoriaAntecedente();
        recuperarAntecedente();
    }
    
    public String cargarAntecedente(Antecedente a, String actualizar){
        this.updateAntecedente = actualizar;
        this.antecedente = a;
        return "/administrador/actualizacionAntecedente.xhtml?faces-redirect=true";
    }
    
    /**
     * Método para recuperar los antecedente registrados en la base
     * @return 
     */
    public List<String> recuperarNombresAntecedente() {
        return AntecedenteDAO.recuperarNombresAntecedentes();
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
     * Método para recuperar los antecedente registrados en la base por el tipo
     * @return 
     */
    public List<Antecedente> recuperarAntecedente() {
        lista_antecedente = AntecedenteDAO.recuperarAntecedentes();
        return lista_antecedente;
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
     * Método para crear un nuevo antecedente
     */
    public void crearAntecedente(){
        nuevo_antecedente.setAntFechaUlt(new Date());
        nuevo_antecedente.setAntUsuario(session.getAttribute("usuario").toString());
        try {
            if(!getAntecedente_abierta().isEmpty()){
                nuevo_antecedente.setAntCategoria(getAntecedente_abierta());
            }
            AntecedenteDAO.crearActualizarAntecedente(nuevo_antecedente);
            nuevo_antecedente = new Antecedente();
            setAntecedente_abierta("");
            renderizar_antecedente = "false";
            recuperarAntecedente();
            recuperarNombresAntecedente();
            recuperarCategoriaAntecedente();
            FacesMessages.info(":growlInfo", "Antecedente Creado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al crear antecedente creado"+e, "This is a specific message!");
        }
    }
    
    /**
     * Método para actualizar un antecedente
     * @return 
     */
    public String actualizarAntecedente(){
        try {
            AntecedenteDAO.crearActualizarAntecedente(antecedente);
            antecedente = new Antecedente();
            nombre_antecedente = null;
            recuperarNombresAntecedente();
            FacesMessages.info(":growlInfo", "Antecedente actualizado", "This is a specific message!");
            if (this.updateAntecedente.equals("1")) {
                return "/administrador/registroAntecedente.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al actualizar antecedente "+e, "This is a specific message!");
            return null;
        }
        return null;
    }
    
    /**
     * Método para eliminar un antecedente
     */
    public void eliminarAntecedente(){
        try {
            AntecedenteDAO.eliminarAntecedente(antecedente);
            recuperarNombresAntecedente();
            recuperarAntecedente();
            FacesMessages.info(":growlInfo", "Antecedente eliminado", "This is a specific message!");
        } catch (Exception e) {
            FacesMessages.info(":growlInfo", "Error al eliminar antecedente"+e, "This is a specific message!");
        }
    }
    
    /**
     * Método para preparar examen
     * @param antecedente
     */
    public void prepararAntecedente(Antecedente antecedente){
        this.antecedente = antecedente;
    }
    
    /**
     * Método para retornar el tipo de examen
     * @param tipo
     * @return 
     */
    public String antecedenteFormato(String tipo) {
        if (tipo.equals("1")){
            tipo = "Personal";
        } else if (tipo.equals("2")){
            tipo = "Familiar";
        } else if (tipo.equals("3")){
            tipo = "Andrológicos";
        } else if (tipo.equals("4")){
            tipo = "Vacunación";
        }  
        return tipo;
    }
    
    public void especificar_antecedente() {
        if (nuevo_antecedente.getAntCategoria().equals("OTRO")) {
            renderizar_antecedente = "true";
        } else {
            antecedente_abierta = "";
            renderizar_antecedente = "false";
        }
    }

    public Antecedente getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(Antecedente antecedente) {
        this.antecedente = antecedente;
    }

    public Antecedente getNuevo_antecedente() {
        return nuevo_antecedente;
    }

    public void setNuevo_antecedente(Antecedente nuevo_antecedente) {
        this.nuevo_antecedente = nuevo_antecedente;
    }

    public String getNombre_antecedente() {
        return nombre_antecedente;
    }

    public void setNombre_antecedente(String nombre_antecedente) {
        this.nombre_antecedente = nombre_antecedente;
    }

    public List<Antecedente> getLista_antecedente() {
        return lista_antecedente;
    }

    public String getUpdateAntecedente() {
        return updateAntecedente;
    }

    public void setUpdateAntecedente(String updateAntecedente) {
        this.updateAntecedente = updateAntecedente;
    }

    public String getAntecedente_abierta() {
        return antecedente_abierta;
    }

    public void setAntecedente_abierta(String antecedente_abierta) {
        this.antecedente_abierta = antecedente_abierta;
    }

    public String getRenderizar_antecedente() {
        return renderizar_antecedente;
    }

    public void setRenderizar_antecedente(String renderizar_antecedente) {
        this.renderizar_antecedente = renderizar_antecedente;
    }

    public List<String> getLista_categoria_antecedentes() {
        return lista_categoria_antecedentes;
    }
    
    public List<Antecedente> getListaExamenesPer() {
        return filtrarPorTipo("1");
    }

    public List<Antecedente> getListaExamenesFan() {
        return filtrarPorTipo("2");
    }

    public List<Antecedente> getListaExamenesAnd() {
        return filtrarPorTipo("3");
    }
    
    public List<Antecedente> getListaExamenesVac() {
        return filtrarPorTipo("4");
    }

    private List<Antecedente> filtrarPorTipo(String tipo) {;
        return lista_antecedente.stream()
                .filter(antecedente -> String.valueOf(antecedente.getAntTipo()).equals(tipo))
                .collect(Collectors.toList());
    }
    
}