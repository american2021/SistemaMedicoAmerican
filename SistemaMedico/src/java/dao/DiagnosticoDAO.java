/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Diagnosticos;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class DiagnosticoDAO {

    /**
     * Método para crear o actualizar un diagnóstico.
     *
     * @param diagnostico
     */
    public static void crearActualizarDiagnostico(Diagnosticos diagnostico) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(diagnostico);
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
}
