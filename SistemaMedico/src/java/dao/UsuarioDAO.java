/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Usuarios;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class UsuarioDAO {

    /**
     * Método para crear o actualizar un usuario
     *
     * @param usuario
     */
    public static void crearActualizarUsuario(Usuarios usuario) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(usuario);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método para obtener un usuario según su nombre.
     *
     * @param usu_nombre
     * @return
     */
    public static Usuarios obtenerUsuario(String usu_nombre) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Usuarios where usu_nombre = '" + usu_nombre + "'");
        Usuarios usuario = null;
        if (!query.list().isEmpty()) {

            usuario = (Usuarios) query.uniqueResult();

        }
        session.getTransaction().commit();
        session.close();
        return usuario;
    }
    
    /**
     * Método para recuperar el usuario
     * @param per_id
     * @return 
     */
    public static Usuarios obtenerUsuarioPorPerId(int per_id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Usuarios where personas_per_id = '" + per_id + "'");
        Usuarios usuario = null;
        if (!query.list().isEmpty()) {

            usuario = (Usuarios) query.uniqueResult();

        }
        session.getTransaction().commit();
        session.close();
        return usuario;
    }
    
    /**
     * Método para obtener todos los usuarios registrados en el sistema.
     * @return 
     */
    public static List<Usuarios> recuperarUsuarios() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Usuarios");
        List<Usuarios> usuarios = query.list();
        session.getTransaction().commit();
        session.close();
        return usuarios;
    }
}
