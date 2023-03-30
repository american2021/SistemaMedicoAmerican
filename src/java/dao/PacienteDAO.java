/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Personas;
import datos.Usuarios;
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
    
    public static void crearUsuario(Usuarios usuario) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(usuario);
        session.getTransaction().commit();
        session.close();
    }
}
