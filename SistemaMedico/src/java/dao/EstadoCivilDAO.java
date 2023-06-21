/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Estadocivil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class EstadoCivilDAO {

    /**
     * Método para recuperar la lista de estados civiles.
     *
     * @return
     */
    public static List<Estadocivil> recuperarEstados() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Estadocivil");
        List<Estadocivil> estados = query.list();
        session.getTransaction().commit();
        session.close();
        return estados;
    }

    /**
     * Método para recuperar el nombre de un estado civil según su código.
     *
     * @param codigo
     * @return
     */
    public static String recuperarEstadoCivilPorCodigo(String codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Estadocivil where ecCodigo = '" + codigo + "'");
        Estadocivil estado = (Estadocivil) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return (String) estado.getEcDescripcion();
    }
}
