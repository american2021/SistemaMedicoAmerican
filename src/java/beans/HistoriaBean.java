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
@ManagedBean(name = "HistoriaBean")
@SessionScoped
public final class HistoriaBean implements Serializable{
    
    private List<Historias> historias;
    private Historias historia;
    private Signos signos;
    
    public HistoriaBean(){
        inicializarHistorias();
        inicializarSignos();
    }
    
    public void inicializarHistorias(){
        historias = new ArrayList<>();
        recuperarHistorias();
    }
    
    public void inicializarSignos(){
        signos = new Signos();
    }
    
    public void actualizarSignosHistoria(){
        SignosDAO.crearActualizarSignos(signos);
        FacesMessages.info(":growlInfo", "Se han actualizado los signos del paciente", "This is a specific message!");
    }
    
    public void actualizarHistoria(){
        HistoriaDAO.crearActualizarHistoria(historia);
        FacesMessages.info(":growlInfo", "Se han actualizado la historia clínica", "This is a specific message!");
    }
    
    public void recuperarHistorias() {

        historias.clear();
        
        historias = HistoriaDAO.recuperarHistorias();
    }
    
    public void recuperarHistoriaID(int id){
        historia = HistoriaDAO.recuperarHistoriaID(id);
    }
    
    /**
     * Id de la historia a cargar
     * @param id 
     */
    public void recuperarSignosHistoria(int id){
        signos = SignosDAO.recuperarSignosHistoria(id);
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
}
