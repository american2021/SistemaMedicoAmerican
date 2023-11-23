/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.HistoriaAntecedenteDAO;
import datos.HistoriaAntecedente;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Programacion
 */
@ManagedBean(name = "HistoriaAntecedenteBean")
@SessionScoped
public final class HistoriaAntecedenteBean implements Serializable {
    
    public HistoriaAntecedenteBean(){
        inicializarHistoriaAntecedente();
    }
    
    public void inicializarHistoriaAntecedente(){
    }
    
    public void crearHistoriaAntecedente(HistoriaAntecedente personaAntecedente){
        HistoriaAntecedenteDAO.crearActualizarHistoriaAntecedente(personaAntecedente);
    }
    
}
