/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Enfermedades;
import datos.Historias;
import datos.Signos;
import java.util.HashSet;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class SignosDAO {
    
    public static void crearActualizarSignos(Signos signos){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(signos);
        session.getTransaction().commit();
        session.close();
    }    
    
    public static Signos recuperarSignosId(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Signos where sig_id = "+id);
        Signos signos = null;
        if (!query.list().isEmpty()) {

            signos = (Signos) query.uniqueResult();

        }
        //persona.setSign(recuperarSignosPorCodigo(persona.getPerId()));
        session.getTransaction().commit();
        session.close();
        return signos;
    }
    
    public static HashSet<Signos> recuperarSignosPorCodigo(int per_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Signos where personas_per_id = " + per_id);
        session.close();
        return (new HashSet<>(query.list()));
    }
}
