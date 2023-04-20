/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Antecedentes;
import datos.Enfermedades;
import datos.Historias;
import datos.Personas;
import datos.RevisionSistemas;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class HistoriaDAO {
    
    public static void crearActualizarHistoriaConEnfermedad(Historias historia, String enf_nombre){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Enfermedades where enf_nombre = '"+enf_nombre+"'");
        Enfermedades enfermedad = (Enfermedades) query.uniqueResult();
        
        historia.setEnfermedades(enfermedad);
        session.saveOrUpdate(historia.getPersonas());
        session.saveOrUpdate(historia.getRevisionSistemas());
        session.saveOrUpdate(historia.getSignos());
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
            //Necesario para cargar los datos de la persona en modo eager
            historia.getPersonas().getPerNombres();
            historia.getEnfermedades().getEnfNombre();
            historia.getSignos().getSigEstatura();
        });
        //System.out.println(historias.get(0).getPersonas().getPerApellidos());
        session.getTransaction().commit();
        session.close();
        return historias;
    }
    
    public static List<Historias> recuperarHistoriasDia(String dia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Historias where his_fecha_creacion like '%"+dia+"%'");
        List<Historias> historias = query.list();
        historias.forEach((historia) -> {
            //Necesario para cargar los datos de la persona en modo eager
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

            historia = (Historias) query.uniqueResult();
            historia.getEnfermedades().getEnfNombre();
            historia.getSignos().getSigPresionSistolica();
            historia.getRevisionSistemas().getRevSisSentidos();
            historia.getPersonas().getPerNombres();
        }
        session.close();
        return historia;
    }
    
    public static void crearHistoriaPrimeraVez(Historias historia, RevisionSistemas revision) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Personas order by per_id desc");
        Personas persona = (Personas) query.list().get(0);
        historia.setPersonas(persona);
        historia.setRevisionSistemas(revision);
        session.save(historia);
        session.getTransaction().commit();
        session.close();
    }
}
