/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Antecedentes;
import datos.Historias;
import datos.Personas;
import datos.Signos;
import datos.Usuarios;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class PacienteDAO {
    
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

            persona = (Personas) query.list().get(0);

        }
        //persona.setSign(recuperarSignosPorCodigo(persona.getPerId()));
        session.getTransaction().commit();
        session.close();
        return persona;
    }
    
    public static List<String> listarNombres() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT concat(per_apellidos,' - ',per_nombres) FROM Personas");
        List<String> personas = query.list();
        session.getTransaction().commit();
        session.close();
        return personas;

    }
}
