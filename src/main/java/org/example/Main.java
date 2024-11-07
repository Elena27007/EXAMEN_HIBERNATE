package org.example;

import org.example.models.Opinion;
import org.example.models.Pelicula;

/**
 * Clase principal de la aplicación.
 */
public class Main {
    public static void main(String[] args) {
        DataService ds = new DataService(HibernateUtil.getSessionFactory());

        Opinion opinion1 = new Opinion();
        opinion1.setDescripcion("Me ha gustado mucho");
        opinion1.setPuntuacion(9L);
        opinion1.setUsuario("Pepe");
        ds.añadirOpinion(opinion1, 1L);

        ds.pelisBajaPuntuacion().forEach(System.out::println);

        ds.encontrarOpinionPorUsuario("Pepe").forEach(System.out::println);

        Pelicula p = new Pelicula();
        p.setTitulo("La guerra de las galaxias");
        ds.registrarPeli(p);
    }
}