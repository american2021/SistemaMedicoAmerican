/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Historias;
import datos.Ocupaciones;
import datos.RevisionSistemas;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class RevisionSistemasDAO {
    
    public static RevisionSistemas recuperarRevision(int rev_id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from RevisionSistemas where rev_sis_id = "+rev_id);
        RevisionSistemas revision = (RevisionSistemas)query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        if(revision != null){
            return revision;
        }
        return null;
    }
    
    public static void crearActualizarRevision(RevisionSistemas rev) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(rev);
        session.getTransaction().commit();
        session.close();
    }
}
