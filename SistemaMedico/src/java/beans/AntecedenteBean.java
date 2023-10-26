/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import datos.Antecedente;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Programacion
 */
@ManagedBean(name = "AntecedenteBean")
@SessionScoped
public final class AntecedenteBean implements Serializable{
    Antecedente antecedente;
    
    public void AntecedenteBean(){
        inicializarAntecedente();
    }
    
    public void inicializarAntecedente(){
        antecedente = new Antecedente();
    }
    
}