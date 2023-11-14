/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.HistoriaDiagnostico;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Programacion
 */
public class HistoriaDiagnosticoDAO {
    
    /**
     * Método para crear historia examen.
     *
     * @param historiaDiagnostico
     */
    public static void crearActualizarHistoriaDiagnostico(HistoriaDiagnostico historiaDiagnostico) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(historiaDiagnostico);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método para recuperar historiaDiagnostico según su id.
     *
     * @param id
     * @return
     */
    public static HistoriaDiagnostico recuperarHistoriaDiagnosticoId(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from HistoriaDiagnostico where hisDiaId = " + id);
        HistoriaDiagnostico historiaDiagnostico = null;
        if (!query.list().isEmpty()) {

            historiaDiagnostico = (HistoriaDiagnostico) query.uniqueResult();

        }
        session.getTransaction().commit();
        session.close();
        return historiaDiagnostico;
    }
    
        /**
     * Método para recuperar los nombres de los diagnostico de un historial
     *
     * @param id_historia
     * @return
     */
    public static List<HistoriaDiagnostico> recuperarHistoriaDiagnostico(String id_historia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM HistoriaDiagnostico where historias= '" + id_historia + "'");         
        List<HistoriaDiagnostico> historiaDiagnostico= query.list();
        historiaDiagnostico.forEach((historiaDiagnostic) -> {
            historiaDiagnostic.setDiagnosticos(DiagnosticoDAO.recuperarDiagnosticosId(historiaDiagnostic.getDiagnosticos().getDiaId()));
        });
        session.getTransaction().commit();
        session.close();
        return historiaDiagnostico;
    }
}
