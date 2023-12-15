/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Diagnosticos;
import datos.Antecedente;
import datos.Historias;
import datos.Tratamientos;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrador
 */
public class CitaDAO {

    /**
     * Método para crear o actualizar una historia (Encabezado de cita).
     *
     * @param historia
     */
    public static void crearActualizarHistoriaConDatos(Historias historia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(historia.getPersonasByPacientePerId());
        session.saveOrUpdate(historia.getRevisionSistemas());
        session.saveOrUpdate(historia.getSignos());
        session.saveOrUpdate(historia);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método para crear o actualizar una historia.
     *
     * @param historia
     */
    public static void crearActualizarHistoria(Historias historia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(historia);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método para recuperar todas las historias en orden ascendente.
     *
     * @return
     */
    public static List<Historias> recuperarHistorias() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Historias order by his_id desc, his_fecha_ult desc");
        List<Historias> historias = query.list();
        historias.forEach((historia) -> {
            //Necesario para cargar los datos de la persona en modo eager
            historia.getPersonasByPacientePerId().getPerNombres();
            // Actuaizar Edad
            historia.getSignos().getSigEstatura();
            try {
                historia.getPersonasByMedicoPerId().getPerNombres();
            } catch (Exception e) {

            }
        });
        session.getTransaction().commit();
        session.close();
        return historias;
    }

    /**
     * Método para recuperar las historias asignadas a un médico.
     *
     * @param medico_id
     * @return
     */
    public static List<Historias> recuperarHistoriasMedico(int medico_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Historias where medico_per_id = " + medico_id + " order by his_id asc");
        List<Historias> historias = query.list();
        historias.forEach((historia) -> {
            //Necesario para cargar los datos de la persona en modo eager
            historia.getPersonasByPacientePerId().getPerNombres();
            //historia.getEnfermedades().getEnfNombre();
            historia.getSignos().getSigEstatura();
            try {
                historia.getPersonasByMedicoPerId().getPerNombres();
            } catch (Exception e) {

            }
        });
        session.getTransaction().commit();
        session.close();
        return historias;
    }

    /**
     * Método para recuperar las historias del día.
     *
     * @param dia
     * @return
     */
    public static List<Historias> recuperarHistoriasDia(String dia) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        Query query = session.createQuery("from Historias where his_fecha_creacion like '%" + dia + "%' order by his_fecha_ult desc");
//        List<Historias> historias = query.list();
//        historias.forEach((historia) -> {
//            //Necesario para cargar los datos de la persona en modo eager
//            historia.getPersonasByPacientePerId().getPerNombres();
//            try {
//                historia.getPersonasByMedicoPerId().getPerNombres();
//            } catch (Exception e) {
//
//            }
//        });
//        session.getTransaction().commit();
//        session.close();
//        return historias;

        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Historias where his_fecha_creacion like '%" + dia + "%' order by his_fecha_ult desc");

            List<Historias> historias = query.list();

            historias.forEach(historiaTratamient -> {
                Hibernate.initialize(historiaTratamient.getPersonasByMedicoPerId());
                Hibernate.initialize(historiaTratamient.getPersonasByPacientePerId());
//                Hibernate.initialize(historiaTratamient.getTratamientos().getMedicamentos());
//                Hibernate.initialize(historiaTratamient.getHistoriaDiagnostico().getDiagnosticos());
            });

            transaction.commit();
            return historias;
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
     * Método para recuperar las historias del día asignadas a un médico.
     *
     * @param dia
     * @param medico_id
     * @return
     */
    public static List<Historias> recuperarHistoriasMedicoDia(String dia, int medico_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Historias "
                + "where his_fecha_creacion like '%" + dia + "%' "
                + "and medico_per_id = " + medico_id + " order by his_id asc");
        List<Historias> historias = query.list();
        historias.forEach((historia) -> {
            //Necesario para cargar los datos de la persona en modo eager
            historia.getPersonasByPacientePerId().getPerNombres();
            try {
                historia.getPersonasByMedicoPerId().getPerNombres();
            } catch (Exception e) {

            }
        });
        session.getTransaction().commit();
        session.close();
        return historias;
    }

    /**
     * Método para recuperar una historia según su id.
     *
     * @param id
     * @return
     */
    public static Historias recuperarHistoriaID(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Historias where his_id = " + id);
        Historias historia = null;
        if (!query.list().isEmpty()) {

            historia = (Historias) query.uniqueResult();
            historia.getSignos().getHistoriases().size();
            historia.getRevisionSistemas().getRevSisId();
            historia.getPersonasByPacientePerId().getPerNombres(); 
        }
        session.close();
        return historia;
    }

    /**
     * Método para recuperar la última historia registrada.
     *
     * @param id_paciente
     * @return
     */
    public static int recuperarIDUltimaHistoria(int id_paciente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Historias WHERE  personasByPacientePerId.perId = '"+id_paciente+"' order by his_id desc");
        Historias h = (Historias) query.list().get(0);
        int id = h.getHisId();
        session.getTransaction().commit();
        session.close();
        return id;
    }

    /**
     * Método para recuperar los nombres de los tratamientos con su codigo cie
     *
     * @return
     */
    public static List<String> recuperarNombresTratamientos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                "SELECT concat(tra_codigo_cie) FROM Tratamientos");
        List<String> tratamientos = query.list();
        session.getTransaction().commit();
        session.close();
        return tratamientos;
    }
    
    /**
     * Método para recuperar un diagnóstico según su código
     *
     * @param codigo_cie
     * @return
     */
    public static Tratamientos recuperarTratamientoCodigoCie(String codigo_cie) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Tratamientos where tra_codigo_cie = '" + codigo_cie + "'");
        Tratamientos tratamiento = null;
        if (!query.list().isEmpty()) {

            tratamiento = (Tratamientos) query.uniqueResult();

        }
        session.getTransaction().commit();
        session.close();
        return tratamiento;
    }
    
    
    /**
     * Método para verificarHistorias
     *
     */
    public static void verificarHistoria() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        // Calcula la fecha actual menos una hora
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime unaHoraMenos = localDateTime.minusHours(2);
        // Convierte LocalDateTime a Date
        Date horaHaceUnaHora = Date.from(unaHoraMenos.atZone(ZoneId.systemDefault()).toInstant());

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Historias WHERE hisCompletado = 0 AND hisFechaUlt < :horaLimite");
            query.setParameter("horaLimite", horaHaceUnaHora);
            query.executeUpdate();
            transaction.commit();
            session.flush();
            session.clear();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                session.clear();
            }
            e.printStackTrace(); // Log or handle the exception appropriately
        } finally {
            if (session != null && session.isOpen()) {
                session.clear();
                session.close();
                
            }
        }
        
    }
}
