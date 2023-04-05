/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Antecedentes;
import datos.Enfermedades;
import datos.Historias;
import datos.Personas;
import datos.Signos;
import datos.Usuarios;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class EnfermedadDAO {
    
    public static void crearActualizarEnfermedad(Enfermedades enfermedad){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(enfermedad);
        session.getTransaction().commit();
        session.close();
    }
    
    public static List<String> listarEnfermedades() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT concat(enf_nombre) FROM Enfermedades");
        List<String> enfermedades = query.list();
        session.getTransaction().commit();
        session.close();
        return enfermedades;

    }
    
    public static Enfermedades recuperarEnfermedadNombre(String nombre) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Enfermedad where enf_nombre LIKE '" + nombre +"'");
        Enfermedades enfermedad = null;
        if (!query.list().isEmpty()) {

            enfermedad = (Enfermedades) query.list().get(0);

        }
        //persona.setSign(recuperarSignosPorCodigo(persona.getPerId()));
        session.getTransaction().commit();
        session.close();
        return enfermedad;
    }
}
