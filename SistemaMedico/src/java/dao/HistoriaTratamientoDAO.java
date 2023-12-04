/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.HistoriaTratamiento;
import datos.Historias;
import datos.Medicamentos;
import datos.Personas;
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
//        Examenes exa = new Examenes();
        session.beginTransaction();
        Query query = session.createQuery("FROM HistoriaTratamiento where his_id= '" + id_historia + "'");         
        List<HistoriaTratamiento> historiaTratamiento= query.list();
        historiaTratamiento.forEach((historiaTratamient) -> {
            Medicamentos med_aux = historiaTratamient.getTratamientos().getMedicamentos();
            med_aux.getMedNombre();
            
            historiaTratamient.setTratamientos(TratamientoDAO.recuperarTratamiento(historiaTratamient.getTratamientos().getTraId()));
            Historias aux_histori = historiaTratamient.getHistorias();
            // Recuperar persona
            Personas aux_per = historiaTratamient.getHistorias().getPersonasByMedicoPerId();
            // Setear la persona para el campo medico dentro de la entidad historia
            aux_per.getPerApellidos();
            aux_histori.setPersonasByMedicoPerId(aux_per);
            // Setear la historia
            historiaTratamient.setHistorias(aux_histori);
        });
        session.getTransaction().commit();
        session.close();
        return historiaTratamiento;
    }
}
