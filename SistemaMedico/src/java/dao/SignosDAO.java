/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Signos;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class SignosDAO {

    /**
     * Método para crear o actualizar una toma de signos.
     *
     * @param signos
     */
    public static void crearActualizarSignos(Signos signos) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(signos);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método para recuperar una toma de signos según su id.
     *
     * @param id
     * @return
     */
    public static Signos recuperarSignosId(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Signos where sig_id = " + id);
        Signos signos = null;
        if (!query.list().isEmpty()) {

            signos = (Signos) query.uniqueResult();

        }
        session.getTransaction().commit();
        session.close();
        return signos;
    }
}
