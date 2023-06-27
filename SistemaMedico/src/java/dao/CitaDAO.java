/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Diagnosticos;
import datos.Historias;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

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
        //session.saveOrUpdate(historia.getDiagnosticos());
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
        Query query = session.createQuery("from Historias order by his_id asc");
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
        //System.out.println(historias.get(0).getPersonas().getPerApellidos());
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Historias where his_fecha_creacion like '%" + dia + "%' order by his_id asc");
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
            historia.getSignos().getSigPresionSistolica();
            historia.getRevisionSistemas().getRevSisSentidos();
            historia.getPersonasByPacientePerId().getPerNombres();
            try {
                historia.getDiagnosticos().getDiaObservacionCie();
            } catch (Exception e) {

            }
        }
        session.close();
        return historia;
    }

    /**
     * Método para recuperar la última historia registrada.
     *
     * @return
     */
    public static int recuperarIDUltimaHistoria() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Historias order by his_id desc");
        Historias h = (Historias) query.list().get(0);
        int id = h.getHisId();
        session.getTransaction().commit();
        session.close();
        return id;
    }
    
    public static List<String> recuperarNombresDiagnosticos(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                "SELECT concat(dia_codigo_cie,' - ',dia_descripcion_cie) FROM Diagnosticos");
        List<String> diagnosticos = query.list();
        session.getTransaction().commit();
        session.close();
        return diagnosticos;
    }
    
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
