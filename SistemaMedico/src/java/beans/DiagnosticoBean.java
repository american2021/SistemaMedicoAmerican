/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.DiagnosticoDAO;
import datos.Diagnosticos;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "DiagnosticoBean")
@SessionScoped
public final class DiagnosticoBean implements Serializable{
    
    public DiagnosticoBean(){
        inicializarEnfermedad();
    }
    
    public void inicializarEnfermedad(){
    }
    
    public void crearDiagnostico(Diagnosticos diagnostico){
        DiagnosticoDAO.crearActualizarDiagnostico(diagnostico);
    }
}
