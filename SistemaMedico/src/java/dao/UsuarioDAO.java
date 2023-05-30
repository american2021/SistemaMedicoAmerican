/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Usuarios;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class UsuarioDAO {
        public static void crearActualizarUsuario(Usuarios usuario){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(usuario);
        session.getTransaction().commit();
        session.close();
    }
    
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
}