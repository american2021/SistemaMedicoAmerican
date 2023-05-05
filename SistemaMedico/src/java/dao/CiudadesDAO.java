/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Ciudades;
import datos.Ocupaciones;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class CiudadesDAO {
    
    public static List<Ciudades> recuperarCiudades() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Ciudades where paiCodigo = 'ECU' order by ciuNombre asc");
        List<Ciudades> ciudades = query.list();
        session.getTransaction().commit();
        session.close();
        return ciudades;
    }
}
