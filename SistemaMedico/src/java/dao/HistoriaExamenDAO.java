/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.HistoriaExamen;
import datos.Historias;
import datos.Personas;
import java.util.Collections;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Programacion
 */
public class HistoriaExamenDAO {
    
    /**
     * Método para crear o actualizar un historiaExamen.
     *
     * @param historiaExamen
     */
    public static void crearActualizarHistoriaExamen(HistoriaExamen historiaExamen) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(historiaExamen);
        session.getTransaction().commit();
        session.close();
    }
    
    /**
     * Método para eliminar un historiaExamen.
     *
     * @param historiaExamen
     */
    public static void eliminarHistoriaExamen(HistoriaExamen historiaExamen) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(historiaExamen);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método para recuperar historiaExamen según su id.
     *
     * @param id
     * @return
     */
    public static HistoriaExamen recuperarHistoriaExamenId(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from HistoriaExamen where perExaId = " + id);
        HistoriaExamen historiaExamen = null;
        if (!query.list().isEmpty()) {

            historiaExamen = (HistoriaExamen) query.uniqueResult();

        }
        session.getTransaction().commit();
        session.close();
        return historiaExamen;
    }
    
    /**
     * Método para recuperar los nombres de los diagnostico personal
     *
     * @param id_historia
     * @param id_paciente
     * @return
     */
    public static List<HistoriaExamen> recuperarHistoriaExamenes(int id_historia, int id_paciente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery(" SELECT e FROM HistoriaExamen e, Historias h  WHERE h.hisId = e.historias.hisId AND h.personasByPacientePerId ='" + 
                    id_paciente + "' AND e.historias.hisId = '" + 
                    id_historia + "'");
            
            List<HistoriaExamen> historiaExamen= query.list();
 
            historiaExamen.forEach((historiaExame) -> {
                Hibernate.initialize(historiaExame.getExamenes());
                Hibernate.initialize(historiaExame.getHistorias().getPersonasByPacientePerId());
            });

            transaction.commit();
            return historiaExamen;
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
    
    /**
     * Método para recuperar los nombres de los diagnostico personal
     *
     * @param id_historia
     * @param id_paciente
     * @return
     */
    public static List<HistoriaExamen> recuperarHistorialHistoriaExamenes(int id_historia, int id_paciente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT d FROM HistoriaExamen d, Historias h  WHERE h.hisId = d.historias.hisId AND h.personasByPacientePerId ='" + id_paciente + "' AND h.hisId != '"+ id_historia + "' AND his_exa_completado = '1'");         

        List<HistoriaExamen> historiaExamen= query.list();
        historiaExamen.forEach((historiaExame) -> {
            historiaExame.setExamenes(ExamenesDAO.recuperarExamenesId(historiaExame.getExamenes().getExaId()));
            Historias aux_histori = historiaExame.getHistorias();
            // Recuperar persona
            Personas aux_per = historiaExame.getHistorias().getPersonasByMedicoPerId();
            // Setear la persona para el campo medico dentro de la entidad historia
            aux_per.getPerApellidos();
            aux_histori.setPersonasByMedicoPerId(aux_per);
            // Setear la historia
            historiaExame.setHistorias(aux_histori);
        });
        session.getTransaction().commit();
        session.close();
        return historiaExamen;
    }
    
}
