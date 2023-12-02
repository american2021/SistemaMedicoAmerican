/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.HistoriaAntecedente;
import datos.Historias;
import datos.Personas;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Programacion
 */
public class HistoriaAntecedenteDAO {
    
    /**
     * Método para crear o actualizar un historiaAntecedente.
     *
     * @param historiaAntecedente
     */
    public static void crearActualizarHistoriaAntecedente(HistoriaAntecedente historiaAntecedente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(historiaAntecedente);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método para recuperar historiaAntecedente según su id.
     *
     * @param id
     * @return
     */
    public static HistoriaAntecedente recuperarHistoriaAntecedenteId(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from HistoriaAntecedente where per_ant_id = " + id);
        HistoriaAntecedente historiaAntecedente = null;
        if (!query.list().isEmpty()) {

            historiaAntecedente = (HistoriaAntecedente) query.uniqueResult();

        }
        session.getTransaction().commit();
        session.close();
        return historiaAntecedente;
    }
    
    /**
     * Método para recuperar los nombres de los diagnostico personal
     *
     * @param id_historia
     * @return
     */
    public static List<HistoriaAntecedente> recuperarHistoriaAntecedente(int id_historia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM HistoriaAntecedente where his_id= '" + id_historia + "'");         
        List<HistoriaAntecedente> historiaAntecedente= query.list();
        historiaAntecedente.forEach((historiaAntecedent) -> {
            historiaAntecedent.getAntecedente().getAntTipo();
            historiaAntecedent.setAntecedente(historiaAntecedent.getAntecedente());
            // Recuperar historia
            Historias aux_histori = historiaAntecedent.getHistorias();
            // Recuperar persona
            Personas aux_per = historiaAntecedent.getHistorias().getPersonasByMedicoPerId();
            // Setear la persona para el campo medico dentro de la entidad historia
            aux_per.getPerApellidos();
            aux_histori.setPersonasByMedicoPerId(aux_per);
            // Setear la historia
            historiaAntecedent.setHistorias(aux_histori);
        });
        session.getTransaction().commit();
        session.close();
        return historiaAntecedente;
    }
    /**
     * Método para recuperar los nombres de los diagnostico personal
     *
     * @param id_persona
     * @return
     */
    public static List<HistoriaAntecedente> recuperarAlergiasHistoriaAntecedente(int id_persona) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT DISTINCT h FROM HistoriaAntecedente h, Antecedente a, Historias b  where b.personasByPacientePerId.perId= '" + id_persona + "' AND h.historias.hisId = b.hisId AND a.antCategoria like '%ALERGIA%' group by h.hisAntDescripcion");         
        List<HistoriaAntecedente> historiaAntecedente= query.list();
        historiaAntecedente.forEach((historiaAntecedent) -> {
            historiaAntecedent.getAntecedente().getAntTipo();
            historiaAntecedent.setAntecedente(historiaAntecedent.getAntecedente());
            // Recuperar historia
            Historias aux_histori = historiaAntecedent.getHistorias();
            // Recuperar persona
            Personas aux_per = historiaAntecedent.getHistorias().getPersonasByMedicoPerId();
            // Setear la persona para el campo medico dentro de la entidad historia
            aux_per.getPerApellidos();
            aux_histori.setPersonasByMedicoPerId(aux_per);
            // Setear la historia
            historiaAntecedent.setHistorias(aux_histori);
        });
        session.getTransaction().commit();
        session.close();
        return historiaAntecedente;
    }
}
