/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.HistoriaExamenDAO;
import datos.HistoriaExamen;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Programacion
 */
@ManagedBean(name = "HistoriaExamenesBean")
@SessionScoped
public final class HistoriaExamenesBean implements Serializable{
    
    public HistoriaExamenesBean(){
        inicializarHistoriaExamen();
    }
    
    public void inicializarHistoriaExamen(){
    }
    
    public void crearPersonaAntecedente(HistoriaExamen personaExamen){
        HistoriaExamenDAO.crearActualizarHistoriaExamen(personaExamen);
    }
    
}
