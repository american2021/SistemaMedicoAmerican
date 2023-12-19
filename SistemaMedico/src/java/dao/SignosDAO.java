/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Signos;
import java.util.Collections;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    
    /**
     * Método para recuperar el ultimo signos según el paciente.
     *
     * @param id_persona
     * @return
     */
    public static Signos recuperarUltimoSignoPaciente(int id_persona) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
//        select s.* from historias h, signos s where h.signos_sig_id = s.sig_id and paciente_per_id = 18 order by his_fecha_ult desc limit 1;
        Query query = session.createQuery("SELECT s FROM Historias h, Signos s WHERE h.signos.sigId = s.sigId and h.personasByPacientePerId.perId = '" + id_persona + "' order by h.hisFechaUlt desc");
        query.setMaxResults(1);
        Signos signos = null;
        if (!query.list().isEmpty()) {

            signos = (Signos) query.uniqueResult();

        }
        session.getTransaction().commit();
        session.close();
        return signos;
    }
    
    /**
     * Método para recuperar los nombres de los signos personal
     *
     * @param id_historia
     * @param id_paciente
     * @return
     */
    public static List<Signos> recuperarHistoriaSignos(int id_historia, int id_paciente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery(" SELECT s FROM Signos s, Historias h  WHERE h.signos.sigId = s.sigId AND h.personasByPacientePerId ='" + 
                    id_paciente + "' AND h.hisId != '" + 
                    id_historia + "'");

            List<Signos> historiaSignos= query.list();

//            historiaSignos.forEach((historiaSigno) -> {
//                Hibernate.initialize(historiaSigno.getExamenes());
//                Hibernate.initialize(historiaSigno.getHistorias().getPersonasByPacientePerId());
//            });

            transaction.commit();
            return historiaSignos;
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
