/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Ocupaciones;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class OcupacionDAO {

    /**
     * Método para recuperar las ocupaciones registradas en el sistema.
     *
     * @return
     */
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
