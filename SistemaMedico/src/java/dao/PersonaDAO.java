/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Personas;
import datos.Usuarios;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class PersonaDAO {
    
    public static void crearPersona(Personas persona) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(persona);
        session.getTransaction().commit();
        session.close();
    }
    
    public static void crearActualizarPersona(Personas persona){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(persona);
        session.getTransaction().commit();
        session.close();
    }    
    
    public static void crearUsuario(Usuarios usuario) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(usuario);
        session.getTransaction().commit();
        session.close();
    }
    
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
        //persona.setSign(recuperarSignosPorCodigo(persona.getPerId()));
        session.getTransaction().commit();
        session.close();
        return persona;
    }
    
    public static Personas recuperarPersonaID(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Personas where per_id = " + id);
        System.out.println("Aquí hay un query en persona dao: "+query);
        Personas persona = null;
        if (!query.list().isEmpty()) {

            persona = (Personas) query.uniqueResult();
            persona.getHistoriasesForPacientePerId().size();

        }
        //persona.setSign(recuperarSignosPorCodigo(persona.getPerId()));
        session.getTransaction().commit();
        session.close();
        return persona;
    }
    
    public static List<String> recuperarNombres() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT concat(per_apellidos,' - ',per_nombres) FROM Personas");
        List<String> personas = query.list();
        session.getTransaction().commit();
        session.close();
        return personas;
    }
    
    public static List<Personas> recuperarPersonas() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Personas");
        List<Personas> personas = query.list();
        session.getTransaction().commit();
        session.close();
        return personas;
    }
    
    public static List<Personas> recuperarColaboradores() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Personas WHERE per_es_paciente = 'N'");
        List<Personas> personas = query.list();
        personas.forEach((persona)->{
            if(persona.getUsuarioses().size() != 0){
                Usuarios u = (Usuarios)persona.getUsuarioses().iterator().next();
            }
        });
        session.getTransaction().commit();
        session.close();
        return personas;
    }
    
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