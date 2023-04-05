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
public class HistoriaDAO {
    
    public static void crearActualizarHistoria(Historias historia){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(historia);
        session.getTransaction().commit();
        session.close();
    }    
    
    public static List<Historias> recuperarHistorias() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Historias");
        List<Historias> historias = query.list();
        historias.forEach((historia) -> {
            historia.getPersonas().getPerNombres();
        });
        //System.out.println(historias.get(0).getPersonas().getPerApellidos());
        session.getTransaction().commit();
        session.close();
        return historias;
    }
    
    public static Historias recuperarHistoriaID(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Historias where his_id = "+id);
        Historias historia = null;
        if (!query.list().isEmpty()) {

            historia = (Historias) query.list().get(0);

        }
        //System.out.println(historias.get(0).getPersonas().getPerApellidos());
        session.getTransaction().commit();
        session.close();
        return historia;
    }
    
    public static void crearHistoriaPrimeraVez(Historias historia) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Personas order by per_id desc");
        Personas persona = (Personas) query.list().get(0);
        historia.setPersonas(persona);
        session.save(historia);
        session.getTransaction().commit();
        session.close();
    }
}
