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
}
