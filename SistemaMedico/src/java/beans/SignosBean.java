/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import datos.Signos;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "SignosBean")
@SessionScoped
public final class SignosBean implements Serializable{
    Signos signos;
    
    public void SignosBean(){
        inicializarSignos();
    }
    
    public void inicializarSignos(){
        signos = new Signos();
    }
    
}