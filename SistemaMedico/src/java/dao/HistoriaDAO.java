/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Historias;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class HistoriaDAO {
    public static void crearActualizarHistoria(Historias h){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(h);
        session.getTransaction().commit();
        session.close();
    }  
}
