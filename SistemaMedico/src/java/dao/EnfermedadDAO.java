/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Enfermedades;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class EnfermedadDAO {

    /**
     * Método para crear o actualizar una enfermedad.
     *
     * @param enfermedad
     */
    public static void crearActualizarEnfermedad(Enfermedades enfermedad) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(enfermedad);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método para obtener una lista del nombre de las enfermedades.
     *
     * @return
     */
    public static List<String> listarEnfermedades() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT concat(enf_nombre) FROM Enfermedades");
        List<String> enfermedades = query.list();
        session.getTransaction().commit();
        session.close();
        return enfermedades;

    }

    /**
     * Método para obtener una enfermedad según su nombre.
     *
     * @param nombre
     * @return
     */
    public static Enfermedades recuperarEnfermedadNombre(String nombre) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Enfermedades where enf_nombre = '" + nombre + "'");
        Enfermedades enfermedad = null;
        if (!query.list().isEmpty()) {

            enfermedad = (Enfermedades) query.list().get(0);

        }
        session.getTransaction().commit();
        session.close();
        return enfermedad;
    }
}
