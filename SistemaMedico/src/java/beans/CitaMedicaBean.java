/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.EnfermedadDAO;
import datos.Enfermedades;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "CitaMedicaBean")
@SessionScoped
public final class CitaMedicaBean implements Serializable{
    
    private int id_historia;
}
