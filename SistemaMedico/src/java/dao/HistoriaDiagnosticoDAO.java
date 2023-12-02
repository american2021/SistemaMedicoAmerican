/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.HistoriaDiagnostico;
import datos.Historias;
import datos.Personas;
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
            Historias aux_histori = historiaDiagnostic.getHistorias();
            // Recuperar persona
            Personas aux_per = historiaDiagnostic.getHistorias().getPersonasByMedicoPerId();
            // Setear la persona para el campo medico dentro de la entidad historia
            aux_per.getPerApellidos();
            aux_histori.setPersonasByMedicoPerId(aux_per);
            // Setear la historia
            historiaDiagnostic.setHistorias(aux_histori);
        });
        session.getTransaction().commit();
        session.close();
        return historiaDiagnostico;
    }
    
    /**
     * Método para recuperar los nombres de los diagnostico de un historial
     *
     * @param id_historia
     * @param id_paciente
     * @return
     */
    public static List<HistoriaDiagnostico> recuperarHistorialHistoriaDiagnostico(int id_historia, int id_paciente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT d FROM HistoriaDiagnostico d, Historias h  WHERE h.hisId = d.historias.hisId AND h.personasByPacientePerId ='" + id_paciente + "' AND h.hisId != '"+ id_historia + "'");         
        List<HistoriaDiagnostico> historiaDiagnostico= query.list();
        historiaDiagnostico.forEach((historiaDiagnostic) -> {
            historiaDiagnostic.setDiagnosticos(DiagnosticoDAO.recuperarDiagnosticosId(historiaDiagnostic.getDiagnosticos().getDiaId()));
            Historias aux_histori = historiaDiagnostic.getHistorias();
            // Recuperar persona
            Personas aux_per = historiaDiagnostic.getHistorias().getPersonasByMedicoPerId();
            // Setear la persona para el campo medico dentro de la entidad historia
            aux_per.getPerApellidos();
            aux_histori.setPersonasByMedicoPerId(aux_per);
            // Setear la historia
            historiaDiagnostic.setHistorias(aux_histori);
        });
        session.getTransaction().commit();
        session.close();
        return historiaDiagnostico;
    }
    
    /**
     * Método para recuperar los nombres de los diagnostico de un historial
     *
     * @param consulta
     * @param id_historia
     * @return
     */
    public static List<HistoriaDiagnostico> completarHistoriaDiagnostico(String consulta, int id_historia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM HistoriaDiagnostico WHERE  hisDiaObservacion like '%" + consulta.toUpperCase() + "%' AND historias.hisId = '"+ id_historia + "'");         
        List<HistoriaDiagnostico> historiaDiagnostico = query.list();
        historiaDiagnostico.forEach((historiaDiagnostic) -> {
            historiaDiagnostic.setDiagnosticos(DiagnosticoDAO.recuperarDiagnosticosId(historiaDiagnostic.getDiagnosticos().getDiaId()));
            Historias aux_histori = historiaDiagnostic.getHistorias();
            // Recuperar persona
            Personas aux_per = historiaDiagnostic.getHistorias().getPersonasByMedicoPerId();
            // Setear la persona para el campo medico dentro de la entidad historia
            aux_per.getPerApellidos();
            aux_histori.setPersonasByMedicoPerId(aux_per);
            // Setear la historia
            historiaDiagnostic.setHistorias(aux_histori);
        });
        session.getTransaction().commit();
        session.close();
        return historiaDiagnostico;
    }
}
