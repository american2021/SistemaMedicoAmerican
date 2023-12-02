/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Medicamentos;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Programacion
 */
public class MedicamentosDAO {
    
    /**
     * Método para crear o actualizar una toma de un examen.
     *
     * @param medicamentos
     */
    public static void crearActualizarMedicamentos(Medicamentos medicamentos) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(medicamentos);
        session.getTransaction().commit();
        session.close();
    }
    
    /**
     * Método para recuperar examenes según su id.
     *
     * @param id
     * @return
     */
    public static Medicamentos recuperarMedicamentosId(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Medicamentos where medId = " + id);
        Medicamentos medicamentos = null;
        if (!query.list().isEmpty()) {
            medicamentos = (Medicamentos) query.uniqueResult();
        }
        session.getTransaction().commit();
        session.close();
        return medicamentos;
    }
    
    /**
     * Método para recuperar los nombres de los examenes por su tipo
     *
     * @return
     */
    public static List<Medicamentos> recuperarMedicamentos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Medicamentos");
        List<Medicamentos> medicamentos= query.list();
        session.getTransaction().commit();
        session.close();
        return medicamentos;
    }
    
    /**
     * Método para recuperar los nombres de los diagnostico de un historial
     *
     * @param consulta
     * @return
     */
    public static List<Medicamentos> completarMedicamentos(String consulta) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Medicamentos WHERE  medNombre like '%" + consulta.toUpperCase() + "%'");         
        List<Medicamentos> medicamentos = query.list();
        session.getTransaction().commit();
        session.close();
        return medicamentos;
    }
}
