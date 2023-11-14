/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.HibernateUtil;
import datos.Examenes;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Programacion
 */
public class ExamenesDAO {
    
    /**
     * Método para crear o actualizar una toma de un examen.
     *
     * @param examenes
     */
    public static void crearActualizarExamanes(Examenes examenes) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(examenes);
        session.getTransaction().commit();
        session.close();
    }
    
    /**
     * Método para recuperar examenes según su id.
     *
     * @param id
     * @return
     */
    public static Examenes recuperarExamenesId(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Examenes where exa_id = " + id);
        Examenes examenes = null;
        if (!query.list().isEmpty()) {

            examenes = (Examenes) query.uniqueResult();

        }
        session.getTransaction().commit();
        session.close();
        return examenes;
    }
    
    /**
     * Método para recuperar los nombres de los examenes
     *
     * @return
     */
    public static List<String> recuperarNombresExamenes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                "SELECT concat(exaGrupo, ' - ', CASE exaTipo WHEN 1 THEN 'Laboratorio' WHEN 2 THEN 'Imagenologia' ELSE 'Histopatología' END) AS Examen_Completo FROM Examenes");
        List<String> examen = query.list();
        session.getTransaction().commit();
        session.close();
        return examen;
    }
    
    /**
     * Método para recuperar los nombres de los examenes por su tipo
     *
     * @param tipo
     * @return
     */
    public static List<Examenes> recuperarNombresPorTipoExamenes(String tipo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Examenes where exaTipo= '" + tipo + "'");
        List<Examenes> examanes= query.list();
        session.getTransaction().commit();
        session.close();
        return examanes;
    }
    
    /**
     * Método para recuperar un diagnóstico según su código
     *
     * @param nombre
     * @param tipo
     * @return
     */
    public static Examenes recuperarExamenNombre(String nombre, String tipo) {
        if (tipo.equals("Laboratorio")) {
            tipo = tipo.replace("Laboratorio", "1");
          } else if (tipo.equals("Imagenologia")) {
              tipo = tipo.replace("Imagenologia", "2");
          } else {
              tipo = tipo.replace("Histopatología", "3");
          }
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Examenes where exaGrupo = '" + nombre + "' AND exaTipo = '" + tipo + "'");
        Examenes examenes = null;
        if (!query.list().isEmpty()) {
            examenes = (Examenes) query.uniqueResult();
        }
        session.getTransaction().commit();
        session.close();
        return examenes;
    }
    
}
