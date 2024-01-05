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
    public static void crearActualizarHistoriaTratamiento(HistoriaTratamiento historiaTratamiento) {

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
     * Método para recuperar las historias tratamiento por la historia
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
    
    /**
     * Método para recuperar el historial de historias tratamientos de diferentes historias
     *
     * @param id_historia
     * @return
     */
    public static List<HistoriaTratamiento> recuperarHistorialHistoriaTratamiento(int id_historia, int id_paciente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT d FROM HistoriaTratamiento d, Historias h  WHERE h.hisId = d.historias.hisId AND h.personasByPacientePerId ='" + id_paciente + "' AND h.hisId != '"+ id_historia + "'"); 

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
