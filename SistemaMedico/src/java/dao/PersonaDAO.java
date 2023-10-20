/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Personas;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class PersonaDAO {

    /**
     * Método para crear o actualizar una persona.
     *
     * @param persona
     */
    public static void crearActualizarPersona(Personas persona) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(persona);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método para encontrar una persona según su nombre y apellido.
     *
     * @param nombre
     * @param apellido
     * @return
     */
    public static Personas recuperarPersonaNombre(String nombre, String apellido) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Personas where per_nombres like '" + nombre + "%' and per_apellidos like '" + apellido + "%'");
        Personas persona = null;
        if (!query.list().isEmpty()) {
            try {
                persona = (Personas) query.uniqueResult();
                persona.getHistoriasesForPacientePerId().size();
            } catch (Exception e) {
                return null;
            }

        }
        session.getTransaction().commit();
        session.close();
        return persona;
    }

    /**
     * Método para recuperar una persona según su id.
     *
     * @param id
     * @return
     */
    public static Personas recuperarPersonaID(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Personas where per_id = " + id);
        System.out.println("Aquí hay un query en persona dao: " + query);
        Personas persona = null;
        if (!query.list().isEmpty()) {

            persona = (Personas) query.uniqueResult();
            persona.getHistoriasesForPacientePerId().size();

        }
        session.getTransaction().commit();
        session.close();
        return persona;
    }

    /**
     * Método para recuperar los colaboradores registrados en el sistema.
     *
     * @return
     */
    public static List<Personas> recuperarColaboradores() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Personas WHERE per_es_paciente = 'N'");
        List<Personas> colaboradores = query.list();
        session.getTransaction().commit();
        session.close();
        System.out.println("colaboradores: "+colaboradores);
        return colaboradores;
    }

    /**
     * Métododo para recuperar los nombres de los pacientes.
     *
     * @return
     */
    public static List<String> recuperarNombresPacientes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                "SELECT concat(per_nombres,' - ',per_apellidos) FROM Personas WHERE per_es_paciente = 'S'");
        List<String> pacientes = query.list();
        session.getTransaction().commit();
        session.close();
        return pacientes;
    }

    /**
     * Métoco para recuperar los pacientes
     *
     * @return
     */
    public static List<Personas> recuperarPacientes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                "FROM Personas WHERE per_es_paciente = 'S'");
        List<Personas> pacientes = query.list();
        session.getTransaction().commit();
        session.close();
        return pacientes;
    }

    /**
     * Método para recuperar una persona según su cédula.
     *
     * @param cedula
     * @return
     */
    public static Personas recuperarPersonaCedula(String cedula) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Personas where per_cedula = '" + cedula + "'");
        Personas persona = null;
        if (!query.list().isEmpty()) {

            persona = (Personas) query.list().get(0);

        }
        session.getTransaction().commit();
        session.close();
        return persona;
    }
}
