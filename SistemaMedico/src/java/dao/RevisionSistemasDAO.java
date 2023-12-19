/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.RevisionSistemas;
import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    
    /**
     * Método para recuperar los nombres de los revision sistemas personal
     *
     * @param id_historia
     * @param id_paciente
     * @return
     */
    public static List<RevisionSistemas> recuperarHistoriaRevisionSistemas(int id_historia, int id_paciente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery(" SELECT e FROM RevisionSistemas e, Historias h  WHERE h.revisionSistemas.revSisId = e.revSisId AND h.personasByPacientePerId ='" + 
                    id_paciente + "' AND h.hisId != '" + 
                    id_historia + "'");

            List<RevisionSistemas> historiaRevisionSistemas= query.list();

//            historiaSignos.forEach((historiaSigno) -> {
//                Hibernate.initialize(historiaSigno.getExamenes());
//                Hibernate.initialize(historiaSigno.getHistorias().getPersonasByPacientePerId());
//            });

            transaction.commit();
            return historiaRevisionSistemas;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Log or handle the exception appropriately
            return Collections.emptyList();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
