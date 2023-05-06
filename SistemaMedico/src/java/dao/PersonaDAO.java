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
    
    public static void crearActualizarPaciente(Personas persona){
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
        Query query = session.createQuery("from Personas where perNombres = '" + nombre + "' and perApellidos = '" + apellido + "'");
        Personas persona = null;
        if (!query.list().isEmpty()) {

            try {
                persona = (Personas) query.uniqueResult();
                persona.getHistoriases().size();
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
        Personas persona = null;
        if (!query.list().isEmpty()) {

            persona = (Personas) query.uniqueResult();
            persona.getHistoriases().size();

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
        personas.forEach((persona)->{
            Usuarios u = (Usuarios)persona.getUsuarioses().iterator().next();
        });
        session.getTransaction().commit();
        session.close();
        return personas;
    }
}