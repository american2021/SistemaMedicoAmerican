/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.HistoriaDiagnosticoDAO;
import datos.HistoriaDiagnostico;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Programacion
 */
@ManagedBean(name = "HistoriaDiagnosticoBean")
@SessionScoped
public final class HistoriaDiagnosticoBean implements Serializable {
    
    public HistoriaDiagnosticoBean(){
        inicializarHistoriaDiagnostico();
    }
    
    public void inicializarHistoriaDiagnostico(){
    }
    
    public void crearHistoriaDiagnostico(HistoriaDiagnostico historiaDiagnostico){
        HistoriaDiagnosticoDAO.crearActualizarHistoriaDiagnostico(historiaDiagnostico);
    }
}
