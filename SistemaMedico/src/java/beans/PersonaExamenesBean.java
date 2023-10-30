/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.PersonaExamenDAO;
import datos.PersonaExamen;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Programacion
 */
@ManagedBean(name = "PersonaExamenesBean")
@SessionScoped
public class PersonaExamenesBean implements Serializable{
    
    public PersonaExamenesBean(){
        inicializarPersonaExamen();
    }
    
    public void inicializarPersonaExamen(){
    }
    
    public void crearPersonaAntecedente(PersonaExamen personaExamen){
        PersonaExamenDAO.crearActualizarPersonaExamen(personaExamen);
    }
    
    
}
