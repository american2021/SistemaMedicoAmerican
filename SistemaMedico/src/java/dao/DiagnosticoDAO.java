/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Diagnosticos;
import java.sql.SQLException;
import java.util.List;
import net.bootsfaces.utils.FacesMessages;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Administrador
 */
public class DiagnosticoDAO {

    /**
     * Método para crear o actualizar un diagnóstico.
     *
     * @param diagnostico
     * @throws java.lang.Exception 
     */
    public static void crearActualizarDiagnostico(Diagnosticos diagnostico) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(diagnostico);
            transaction.commit();
        } catch (ConstraintViolationException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("e"+e);
            throw e;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("h"+e);
            throw e;
        } finally {
            session.close();
        }
    }
    
    /**
     * Método para eliminar un diagnóstico.
     *
     * @param diagnostico
     */
    public static void eliminarDiagnostico(Diagnosticos diagnostico) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(diagnostico);
        session.getTransaction().commit();
        session.close();
    }
    
    /**
     * Método para recuperar diagnóstico según su id.
     *
     * @param id
     * @return
     */
    public static Diagnosticos recuperarDiagnosticosId(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Diagnosticos where dia_id = " + id);
        Diagnosticos diagnosticos = null;
        if (!query.list().isEmpty()) {
            diagnosticos = (Diagnosticos) query.uniqueResult();
        }
        session.getTransaction().commit();
        session.close();
        return diagnosticos;
    }
    
    /**
     * Método para recuperar los nombres de los diagnostico por su tipo
     *
     * @param codigo_cie
     * @return
     */
    public static List<Diagnosticos> recuperarNombresPorTipoDiagnostico(String codigo_cie) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Diagnosticos where diaCodigoCie= '" + codigo_cie + "'");
        List<Diagnosticos> diagnosticos= query.list();
        session.getTransaction().commit();
        session.close();
        return diagnosticos;
    }
    
    /**
     * Método para recuperar los nombres de los diagnostico por su tipo
     *
     * @return
     */
    public static List<Diagnosticos> recuperarDiagnosticoAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Diagnosticos");
        List<Diagnosticos> diagnosticos= query.list();
        session.getTransaction().commit();
        session.close();
        return diagnosticos;
    }
    
    /**
     * Método para recuperar los nombres de los diagnostico por su tipo
     *
     * @return
     */
    public static List<Diagnosticos> recuperarDiagnostico() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Diagnosticos");
        List<Diagnosticos> diagnosticos = query.list();
        session.getTransaction().commit();
        session.close();
        return diagnosticos;
    }
    
    /**
     * Método para recuperar los nombres de los diagnósticos con su codigo cie
     *
     * @return
     */
    public static List<String> recuperarNombresDiagnosticos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                "SELECT concat(dia_codigo_cie,' - ',dia_descripcion_cie) FROM Diagnosticos");
        List<String> diagnosticos = query.list();
        session.getTransaction().commit();
        session.close();
        return diagnosticos;
    }
    
    /**
     * Método para recuperar un diagnóstico según su código
     *
     * @param codigo_cie
     * @return
     */
    public static Diagnosticos recuperarDiagnosticoCodigoCie(String codigo_cie) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Diagnosticos where dia_codigo_cie = '" + codigo_cie + "'");
        Diagnosticos diagnostico = null;
        if (!query.list().isEmpty()) {
            diagnostico = (Diagnosticos) query.uniqueResult();
        }
        session.getTransaction().commit();
        session.close();
        return diagnostico;
    }
}
