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
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

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
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(historiaTratamiento);
        session.getTransaction().commit();
        session.close();
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
        session.beginTransaction();
        Query query = session.createQuery("FROM HistoriaTratamiento where historias.hisId = '" + id_historia + "'");         
        List<HistoriaTratamiento> historiaTratamiento= query.list();
        System.out.println("historiaTratamiento: "+historiaTratamiento);
        historiaTratamiento.forEach((historiaTratamient) -> {

            historiaTratamient.setHistoriaDiagnostico( HistoriaDiagnosticoDAO.recuperarHistoriaDiagnosticoId(historiaTratamient.getHistoriaDiagnostico().getHisDiaId()));
            Diagnosticos dia_aux = historiaTratamient.getHistoriaDiagnostico().getDiagnosticos();
            
            HistoriaDiagnostico his_dia_aux = historiaTratamient.getHistoriaDiagnostico();
            his_dia_aux.setDiagnosticos(DiagnosticoDAO.recuperarDiagnosticosId(dia_aux.getDiaId()));
            historiaTratamient.setHistoriaDiagnostico(his_dia_aux);
            
            
            Historias aux_histori = historiaTratamient.getHistorias();
            // Recuperar persona
            Personas aux_per = historiaTratamient.getHistorias().getPersonasByMedicoPerId();
            // Setear la persona para el campo medico dentro de la entidad historia
            aux_per.getPerApellidos();
            aux_histori.setPersonasByMedicoPerId(aux_per);
            // Setear la historia
            historiaTratamient.setHistorias(aux_histori);
            
            Medicamentos med_aux = historiaTratamient.getTratamientos().getMedicamentos();
            med_aux.getMedNombre();            
            
        });
        session.getTransaction().commit();
        session.close();
        return historiaTratamiento;
    }
}
