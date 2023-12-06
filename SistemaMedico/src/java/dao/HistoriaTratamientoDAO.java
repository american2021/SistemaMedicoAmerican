/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Diagnosticos;
import datos.HistoriaDiagnostico;
import datos.HistoriaTratamiento;
import datos.Historias;
import datos.Medicamentos;
import datos.Personas;
import datos.Tratamientos;
import java.util.Collections;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Programacion
 */
public class HistoriaTratamientoDAO {
    
    /**
     * Método para crear o actualizar un historiaExamen.
     *
     * @param historiaTratamiento
     */
    public static void crearActualizarHistoriaExamen(HistoriaTratamiento historiaTratamiento) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        session.saveOrUpdate(historiaTratamiento);
//        session.refresh(historiaTratamiento);
//        session.getTransaction().commit();
//        session.close();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.saveOrUpdate(historiaTratamiento);
            session.flush();  // Sincronizar con la base de datos
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();  // Manejar la excepción adecuadamente
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    /**
     * Método para eliminar un historiaExamen.
     *
     * @param historiaTratamiento
     */
    public static void eliminarHistoriaTratamiento(HistoriaTratamiento historiaTratamiento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(historiaTratamiento);
        session.getTransaction().commit();
        session.close();
    }
    
    /**
     * Método para recuperar los nombres de los diagnostico personal
     *
     * @param id_historia
     * @return
     */
    public static List<HistoriaTratamiento> recuperarHistoriaTratamiento(int id_historia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery("FROM HistoriaTratamiento WHERE historias.hisId = :id");
            query.setParameter("id", id_historia);

            List<HistoriaTratamiento> historiaTratamiento = query.list();

            historiaTratamiento.forEach(historiaTratamient -> {
                Hibernate.initialize(historiaTratamient.getTratamientos());
                Hibernate.initialize(historiaTratamient.getTratamientos().getMedicamentos());
                Hibernate.initialize(historiaTratamient.getHistoriaDiagnostico().getDiagnosticos());
            });

            transaction.commit();
            return historiaTratamiento;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Log or handle the exception appropriately
            return Collections.emptyList();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
