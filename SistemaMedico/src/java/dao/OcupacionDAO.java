/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Antecedentes;
import datos.Historias;
import datos.Ocupaciones;
import datos.Personas;
import datos.Signos;
import datos.Usuarios;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class OcupacionDAO {
    
    public static List<Ocupaciones> recuperarOcupaciones() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Ocupaciones");
        List<Ocupaciones> ocupaciones = query.list();
        //System.out.println(historias.get(0).getPersonas().getPerApellidos());
        session.getTransaction().commit();
        session.close();
        return ocupaciones;
    }
}