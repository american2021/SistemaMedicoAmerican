/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.DiagnosticoDAO;
import dao.EnfermedadDAO;
import datos.Diagnosticos;
import datos.Enfermedades;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import net.bootsfaces.utils.FacesMessages;

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
