/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.RevisionSistemas;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class RevisionSistemasDAO {

    /**
     * Método para crear o actualizar una revisión
     *
     * @param rev
     */
    public static void crearActualizarRevision(RevisionSistemas rev) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(rev);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método para recuperar una revisión según su id
     *
     * @param rev_id
     * @return
     */
    public static RevisionSistemas recuperarRevision(int rev_id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from RevisionSistemas where rev_sis_id = " + rev_id);
        RevisionSistemas revision = (RevisionSistemas) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        if (revision != null) {
            return revision;
        }
        return null;
    }
}
