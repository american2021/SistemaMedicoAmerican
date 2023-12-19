/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Antecedente;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Programacion
 */
public class AntecedenteDAO {
    
    /**
     * Método para crear o actualizar un antecedente.
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
     * Método para eliminar un antecedente.
     *
     * @param antecedente
     */
    public static void eliminarAntecedente(Antecedente antecedente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(antecedente);
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
    
    /**
     * Método para recuperar antecedente según su id.
     *
     * @param grupo
     * @param categoria
     * @param tipo
     * @return
     */
    public static Antecedente recuperarAntecedenteGrupoCategoria(String grupo, String categoria, String tipo) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Antecedente where antCategoria like '%" + categoria + "%' AND antGrupo = '" + grupo + "' AND antTipo = '" + tipo + "'");
        Antecedente antecedente = null;
        if (!query.list().isEmpty()) {
            antecedente = (Antecedente) query.uniqueResult();
        }
        session.getTransaction().commit();
        session.close();
        return antecedente;
    }
    
    /**
     * Método para recuperar la categoria de los antecedentes
     *
     * @return
     */
    public static List<String> recuperarCategoriaAntecedentes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT DISTINCT antCategoria FROM Antecedente ORDER BY antCategoria");
        List<String> antecedente = query.list();
        session.getTransaction().commit();
        session.close();
        return antecedente;
    }
    
    /**
     * Método para recuperar los nombres de los antecedente
     *
     * @return
     */
    public static List<String> recuperarNombresAntecedentes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                "SELECT concat(CASE antTipo WHEN 1 THEN 'Personal' WHEN 2 THEN 'Familiar' WHEN 3 THEN 'Gineco/Obstétrico' WHEN 4 THEN 'Vacunación' ELSE '' END, ' - ', antCategoria, ' - ',antGrupo ) AS Antecedente_Completo FROM Antecedente WHERE  antTipo NOT IN(0)");
        List<String> antecedente = query.list();
        session.getTransaction().commit();
        session.close();
        return antecedente;
    }
    
    /**
     * Método para recuperar un antecedente según su código
     *
     * @param grupo
     * @param categoria
     * @param tipo
     * @return
     */
    public static Antecedente recuperarAntecedenteNombre(String tipo, String categoria, String grupo) {
        if (tipo.equals("Personal")) {
            tipo = tipo.replace("Personal", "1");
        } else if (tipo.equals("Familiar")) {
          tipo = tipo.replace("Familiar", "2");
        } else if (tipo.equals("Gineco/Obstétrico")) {
          tipo = tipo.replace("Gineco/Obstétrico", "3");
        } else if (tipo.equals("Vacunación")){
          tipo = tipo.replace("Vacunación", "4");
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Antecedente where antGrupo = '" + grupo + "' AND antTipo = '" + tipo + "'"+ " AND antCategoria = '" + categoria + "'");
        Antecedente antecedente = null;
        if (!query.list().isEmpty()) {
            antecedente = (Antecedente) query.uniqueResult();
        }
        session.getTransaction().commit();
        session.close();
        return antecedente;
    }
    
    /**
     * Método para recuperar los nombres de los antecedentes por su tipo
     *
     * @return
     */
    public static List<Antecedente> recuperarAntecedentes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Antecedente");
        List<Antecedente> antecedente= query.list();
        session.getTransaction().commit();
        session.close();
        return antecedente;
    }
}
