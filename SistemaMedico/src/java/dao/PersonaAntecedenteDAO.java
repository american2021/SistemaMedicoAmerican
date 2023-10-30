/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.PersonaAntecedente;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Programacion
 */
public class PersonaAntecedenteDAO {
    
    /**
     * Método para crear o actualizar una toma de personaAntecedente.
     *
     * @param personaAntecedente
     */
    public static void crearActualizarPersonaAntecedente(PersonaAntecedente personaAntecedente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(personaAntecedente);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método para recuperar personaAntecedente según su id.
     *
     * @param id
     * @return
     */
    public static PersonaAntecedente recuperarPersonaAntecedenteId(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from PersonaAntecedente where per_ant_id = " + id);
        PersonaAntecedente personaAntecedente = null;
        if (!query.list().isEmpty()) {

            personaAntecedente = (PersonaAntecedente) query.uniqueResult();

        }
        session.getTransaction().commit();
        session.close();
        return personaAntecedente;
    }
    
    /**
     * Método para recuperar los nombres de los diagnostico personal
     *
     * @param id_persona
     * @return
     */
    public static List<PersonaAntecedente> recuperarPersonaAntecedente(String id_persona) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM PersonaAntecedente where per_id= '" + id_persona + "'");         
        List<PersonaAntecedente> personaAntecedente= query.list();
        personaAntecedente.forEach((personaAntecedent) -> {
            personaAntecedent.setAntecedente(AntecedenteDAO.recuperarAntecedenteId(personaAntecedent.getAntecedente().getAntId()));
            personaAntecedent.setPersonasByMedPerId(PersonaDAO.recuperarPersonaID(personaAntecedent.getPersonasByMedPerId().getPerId()));
        });
        session.getTransaction().commit();
        session.close();
        return personaAntecedente;
    }
}
