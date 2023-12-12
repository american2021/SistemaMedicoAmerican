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
     * @return 
     */
    public static Medicamentos crearActualizarMedicamentos(Medicamentos medicamentos) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(medicamentos);
        session.getTransaction().commit();
        // Refresh the object to get the updated state from the database
        session.refresh(medicamentos);
        session.close();
        return medicamentos;
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
    
    /**
     * Método para recuperar los nombres de los medicamenyos
     *
     * @return
     */
    public static List<String> recuperarNombresMedicamentos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                "SELECT concat(med_nombre,' - ',med_dosis_unitaria,' - ', CASE med_medida WHEN 1 THEN 'Microgramo' WHEN 2 THEN 'Miligramo' WHEN 3 THEN 'Gramo' WHEN 4 THEN 'Kilogramno' WHEN 5 THEN 'Unidades internacionales' WHEN 6 THEN 'Microlitro' WHEN 7 THEN 'Mililitro' WHEN 8 THEN 'Litro' WHEN 9 THEN 'Ingreso Manual' WHEN 10 THEN 'Porcentaje' WHEN 11 THEN 'Unidades' WHEN 12 THEN 'Miliequivalente' ELSE '' END) FROM Medicamentos");
        List<String> diagnosticos = query.list();
        session.getTransaction().commit();
        session.close();
        return diagnosticos;
    }
    
    /**
     * Método para recuperar un diagnóstico según su código
     *
     * @param med_nombre
     * @param med_dosis_unitaria
     * @param med_medida
     * @return
     */
    public static Medicamentos recuperarMedicamento(String med_nombre, String med_dosis_unitaria, String med_medida) {        
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Medicamentos where med_nombre = '" + med_nombre + "' and med_dosis_unitaria = '" + med_dosis_unitaria + "' and med_medida= '" + med_medida + "'");
        Medicamentos medicamentos = null;
        if (!query.list().isEmpty()) {
            medicamentos = (Medicamentos) query.uniqueResult();
        }
        session.getTransaction().commit();
        session.close();
        return medicamentos;
    }
}
