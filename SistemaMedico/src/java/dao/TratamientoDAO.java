/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Tratamientos;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class TratamientoDAO {

    /**
     * Método para crear o actualizar un diagnóstico.
     *
     * @param tratamiento
     */
    public static void crearActualizarTratamiento(Tratamientos tratamiento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(tratamiento);
        session.getTransaction().commit();
        session.close();
    }
}
