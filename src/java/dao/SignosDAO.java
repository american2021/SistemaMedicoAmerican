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
public class SignosDAO {
    
    public static void crearActualizarSignos(Signos signos){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(signos);
        session.getTransaction().commit();
        session.close();
    }    
    
    public static void crearSignosPrimeraVez(Signos signos) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Historias order by his_id desc");
        Historias historia = (Historias) query.list().get(0);
        signos.setHistoriasHisId(historia.getHisId());
        session.save(signos);
        session.getTransaction().commit();
        session.close();
    }
    
    public static Signos recuperarSignosHistoria(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Signos where historias_his_id = "+id);
        Signos signos = null;
        if (!query.list().isEmpty()) {

            signos = (Signos) query.list().get(0);

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
        return (new HashSet<>(query.list()));
    }
}
