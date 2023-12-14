/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Antecedente;
import datos.HistoriaAntecedente;
import datos.Historias;
import datos.Personas;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
     * Método para eliminar un historiaAntecedente.
     *
     * @param historiaAntecedente
     */
    public static void eliminarHistoriaAntecedente(HistoriaAntecedente historiaAntecedente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(historiaAntecedente);
        session.getTransaction().commit();
        session.close();
    }
    
    /**
     * Método para eliminar un historiaAntecedente.
     *
     * @param id_historia
     */
    public static void crearDefaultHistoriaAntecedente(int id_historia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction(); 
        try {
            // Obtener la historia por ID
            Historias historia = (Historias) session.get(Historias.class, id_historia);
 
            // Obtener antecedentes que no están asociados a la historia
            Query query = session.createQuery("FROM Antecedente a WHERE NOT EXISTS " +
                                              "(FROM HistoriaAntecedente h WHERE h.antecedente = a AND h.historias = :historia)");
            query.setParameter("historia", historia);
            List<Antecedente> antecedentesNoAsociados = query.list();
            
            // Calcula la fecha actual menos una hora
            LocalDateTime localDateTime = LocalDateTime.now();

            // Convierte LocalDateTime a Date
            Date horaActual = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

            // Crear registros HistoriaAntecedente predeterminados
            for (Antecedente antecedente : antecedentesNoAsociados) {
                HistoriaAntecedente historiaAntecedente = new HistoriaAntecedente();
                historiaAntecedente.setAntecedente(antecedente);
                historiaAntecedente.setHistorias(historia);
                historiaAntecedente.setHisAntDescripcion("NO APLICA");
                historiaAntecedente.setHisAntFechaUlt(horaActual); // ajusta esto según tus necesidades
                historiaAntecedente.setHisAntUsuario(historia.getHisUsuario()); // ajusta esto según tus necesidades

                session.save(historiaAntecedente);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
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
     * Método para recuperar los nombres de los diagnostico de un historial
     *
     * @param id_historia
     * @param id_paciente
     * @return
     */
    public static List<HistoriaAntecedente> recuperarHistorialHistoriaAntecedente(int id_historia, int id_paciente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT d FROM HistoriaAntecedente d, Historias h  WHERE h.hisId = d.historias.hisId AND h.personasByPacientePerId ='" + id_paciente + "' AND h.hisId != '"+ id_historia + "' AND d.hisAntDescripcion != 'NO APLICA'");         
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
     * Método para recuperar los nombres de los diagnostico de un historial
     *
     * @param id_historia
     * @param id_paciente
     * @return
     */
    public static List<HistoriaAntecedente> recuperarHistorialDefectoHistoriaAntecedente(int id_historia, int id_paciente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT d FROM HistoriaAntecedente d, Historias h  WHERE h.hisId = d.historias.hisId AND h.personasByPacientePerId ='" + id_paciente + "' AND h.hisId != '"+ id_historia + "' AND d.hisAntDescripcion = 'NO APLICA'");         
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
