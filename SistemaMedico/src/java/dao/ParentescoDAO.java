/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Parentescos;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class ParentescoDAO {

    /**
     * Método para recuperar los parentescos registrados.
     *
     * @return
     */
    public static List<Parentescos> recuperarParentescos() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Parentescos");
        List<Parentescos> parentescos = query.list();
        session.getTransaction().commit();
        session.close();
        return parentescos;
    }
}
