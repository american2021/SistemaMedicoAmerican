/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.PersonaExamen;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Programacion
 */
public class PersonaExamenDAO {
    
    /**
     * Método para crear personaExamen.
     *
     * @param personaExamen
     */
    public static void crearActualizarPersonaExamen(PersonaExamen personaExamen) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(personaExamen);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método para recuperar personaExamen según su id.
     *
     * @param id
     * @return
     */
    public static PersonaExamen recuperarPersonaExamenId(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from PersonaExamen where perExaId = " + id);
        PersonaExamen personaExamen = null;
        if (!query.list().isEmpty()) {

            personaExamen = (PersonaExamen) query.uniqueResult();

        }
        session.getTransaction().commit();
        session.close();
        return personaExamen;
    }
    
        /**
     * Método para recuperar los nombres de los diagnostico personal
     *
     * @param id_persona
     * @return
     */
    public static List<PersonaExamen> recuperarPersonaExamenes(String id_persona) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM PersonaExamen where per_id= '" + id_persona + "'");         
        List<PersonaExamen> personaExamen= query.list();
        personaExamen.forEach((personaExame) -> {
            personaExame.setExamenes(ExamenesDAO.recuperarExamenesId(personaExame.getExamenes().getExaId()));
            personaExame.setPersonasByMedPerId(PersonaDAO.recuperarPersonaID(personaExame.getPersonasByMedPerId().getPerId()));
        });
        session.getTransaction().commit();
        session.close();
        return personaExamen;
    }
    
    /**
     * Método para recuperar personaExamen según su id.
     *
     * @param personaId
     * @return
     */
    public static PersonaExamen recuperarPersonaExamenGeneralNeurologico(String personaId, String hist) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from PersonaExamen where personasByPerId = " + personaId);
        PersonaExamen personaExamen = null;
        if (!query.list().isEmpty()) {
            personaExamen = (PersonaExamen) query.uniqueResult();
        }
        session.getTransaction().commit();
        session.close();
        return personaExamen;
    }
}
