/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Tratamientos;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class TratamientoDAO {

    /**
     * Método para crear o actualizar un diagnóstico.
     *
     * @param tratamiento
     */
    public static void crearActualizarTratamiento(Tratamientos tratamiento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(tratamiento);
        session.getTransaction().commit();
        session.close();
    }
    
    /**
     * Método para crear o actualizar un diagnóstico.
     *
     * @param tratamiento
     * @return 
     */
    public static Tratamientos crearTratamiento(Tratamientos tratamiento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(tratamiento);
        session.getTransaction().commit();
        session.refresh(tratamiento);
        session.close();
        return tratamiento;
    }
    
    
    public static Tratamientos recuperarTratamiento(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Tratamientos where tra_id = " + id);
        Tratamientos tratamiento = (Tratamientos) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        if (tratamiento != null) {
            return tratamiento;
        }
        return null;
    }
}
