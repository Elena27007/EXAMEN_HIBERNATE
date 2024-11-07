package org.example;

import org.example.models.Opinion;
import org.example.models.Pelicula;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Clase que se encarga de realizar las operaciones de la base de datos
 */
public class DataService {
    private final SessionFactory sessionFactory;

    /**
     * Constructor de la clase
     * @param sessionFactory
     */
    public DataService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Metodo que se encarga de obtener las opiniones de un usuario específico
     * @param usuario
     * @return
     */
    public List<Opinion> encontrarOpinionPorUsuario(String usuario){
        List<Opinion> opiniones;
        try(Session session = sessionFactory.openSession()){
            Query<Opinion> q = session.createQuery("from Opinion o where o.usuario = :elUsuario",Opinion.class);
            q.setParameter("elUsuario",usuario);
            opiniones = q.list();
        }
        return opiniones;
    }

    /**
     * Metodo que se encarga de obtener las peliculas con una puntuación menor o igual a 3
     * @return
     */
    public List<String> pelisBajaPuntuacion() {
        List<String> titulos;
        try (Session session = sessionFactory.openSession()) {
            Query<String> q = session.createQuery("select distinct p.titulo from Pelicula p join p.opiniones o where o.puntuacion <= 3", String.class);
            titulos = q.list();
        }
        return titulos;
    }

    /**
     * Metodo que se encarga de registrar nuevas películas
     * @param p
     */
    public void registrarPeli(Pelicula p){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(p);
            session.getTransaction().commit();
        }
    }

    /**
     * Metodo que se encarga de añadir una opinión a una película ya existente
     * @param o
     * @param idPeli
     */
    public void añadirOpinion(Opinion o, Long idPeli){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Pelicula p = session.get(Pelicula.class,idPeli);
            o.setPelicula(p);
            session.persist(o);
            session.getTransaction().commit();
        }
    }
}
