/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Diagnosticos;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class DiagnosticoDAO {

    /**
     * Método para crear o actualizar un diagnóstico.
     *
     * @param diagnostico
     */
    public static void crearActualizarDiagnostico(Diagnosticos diagnostico) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(diagnostico);
        session.getTransaction().commit();
        session.close();
    }
}
