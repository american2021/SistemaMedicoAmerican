/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.PersonaAntecedenteDAO;
import datos.PersonaAntecedente;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Programacion
 */
@ManagedBean(name = "PersonaAntecedenteBean")
@SessionScoped
public final class PersonaAntecedenteBean implements Serializable {
    
    public PersonaAntecedenteBean(){
        inicializarPersonaAntecedente();
    }
    
    public void inicializarPersonaAntecedente(){
    }
    
    public void crearPersonaAntecedente(PersonaAntecedente personaAntecedente){
        PersonaAntecedenteDAO.crearActualizarPersonaAntecedente(personaAntecedente);
    }
    
}
