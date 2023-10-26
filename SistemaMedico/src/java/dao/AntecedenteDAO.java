/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Antecedente;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Programacion
 */
public class AntecedenteDAO {
    
    /**
     * Método para crear o actualizar una toma de antecedente.
     *
     * @param antecedente
     */
    public static void crearActualizarAntecedente(Antecedente antecedente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(antecedente);
        session.getTransaction().commit();
        session.close();
    }
    
    /**
     * Método para recuperar antecedente según su id.
     *
     * @param id
     * @return
     */
    public static Antecedente recuperarAntecedenteId(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Antecedente where ant_id = " + id);
        Antecedente antecedente = null;
        if (!query.list().isEmpty()) {

            antecedente = (Antecedente) query.uniqueResult();

        }
        session.getTransaction().commit();
        session.close();
        return antecedente;
    }
}
