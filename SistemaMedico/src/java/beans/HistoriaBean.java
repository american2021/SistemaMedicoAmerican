/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.HistoriaDAO;
import dao.OcupacionDAO;
import dao.SignosDAO;
import datos.Historias;
import datos.Ocupaciones;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import datos.Signos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.bootsfaces.utils.FacesMessages;
import java.util.Date;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "HistoriaBean")
@SessionScoped
public final class HistoriaBean implements Serializable{
    
    private List<Historias> historias;
    private List<Historias> historiasdia;
    private String nombre_enfermedad;
    private Historias historia;
    private Signos signos;
    private int historia_actual_id;
    private List<Ocupaciones> lista_ocupaciones;
    
    public HistoriaBean(){
        inicializarHistorias();
        inicializarSignos();
    }
    
    public void inicializarHistorias(){
        historias = new ArrayList<>();
        historiasdia = new ArrayList<>();
        nombre_enfermedad = "";
        recuperarHistorias();
        recuperarHistoriasDia();
        inicializarProfesiones();
    }
    
    public void inicializarSignos(){
        signos = new Signos();
    }
    
    public void actualizarSignosHistoria(){
        SignosDAO.crearActualizarSignos(signos);
        FacesMessages.info(":growlInfo", "Se han actualizado los signos del paciente", "This is a specific message!");
    }
    
    public void actualizarHistoria(){
        HistoriaDAO.crearActualizarHistoriaConEnfermedad(historia, nombre_enfermedad);
        FacesMessages.info(":growlInfo", "Se han actualizado la historia clínica", "This is a specific message!");
    }
    
    public void recuperarHistorias() {

        historias.clear();
        
        historias = HistoriaDAO.recuperarHistorias();
    }
    
    public void recuperarHistoriasDia() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dia = new Date();

        historiasdia.clear();
        
        historiasdia = HistoriaDAO.recuperarHistoriasDia(formatter.format(dia));
    }
    
    public void recuperarHistoriaID(int id){
        historia_actual_id = id;
        historia = HistoriaDAO.recuperarHistoriaID(id);
        nombre_enfermedad = historia.getEnfermedades().getEnfNombre();
    }
    
    /**
     * Id de la historia a cargar
     * @param id 
     */
    public void recuperarSignosId(int id){
        signos = SignosDAO.recuperarSignosId(id);
    }
    
    public String VerCitaMedica(int hisId) {
        this.historia_actual_id = hisId;
        historia = HistoriaDAO.recuperarHistoriaID(hisId);
        recuperarSignosId(historia.getSignos().getSigId());
//        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect("./ficha-admision-est");
//        } catch (IOException ex) {
//            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return "/faces/medico/citaMedica.xhtml?faces-redirect=true";
    }
    
    public void inicializarProfesiones(){
        lista_ocupaciones = OcupacionDAO.recuperarOcupaciones();
    }

    public List<Historias> getHistorias() {
        return historias;
    }

    public void setHistorias(List<Historias> historias) {
        this.historias = historias;
    }    

    public Signos getSignos() {
        return signos;
    }

    public void setSignos(Signos signos) {
        this.signos = signos;
    }

    public Historias getHistoria() {
        return historia;
    }

    public void setHistoria(Historias historia) {
        this.historia = historia;
    }

    public List<Historias> getHistoriasdia() {
        return historiasdia;
    }

    public void setHistoriasdia(List<Historias> historiasdia) {
        this.historiasdia = historiasdia;
    }

    public String getNombre_enfermedad() {
        return nombre_enfermedad;
    }

    public void setNombre_enfermedad(String nombre_enfermedad) {
        this.nombre_enfermedad = nombre_enfermedad;
    }

    public int getHistoria_actual_id() {
        return historia_actual_id;
    }

    public void setHistoria_actual_id(int historia_actual_id) {
        this.historia_actual_id = historia_actual_id;
    }

    public List<Ocupaciones> getLista_ocupaciones() {
        return lista_ocupaciones;
    }

    public void setLista_ocupaciones(List<Ocupaciones> lista_ocupaciones) {
        this.lista_ocupaciones = lista_ocupaciones;
    }
    
}
