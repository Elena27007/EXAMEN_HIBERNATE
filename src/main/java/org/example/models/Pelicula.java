package org.example.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una película.
 */
@Data
@Entity
@Table(name = "pelicula")
public class Pelicula implements Serializable {
    /**
     * Identificador de la película.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título de la película.
     */
    private String titulo;

    /**
     * Opiniones de la película.
     */
    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Opinion> opiniones = new ArrayList<>();

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", opiniones=" + opiniones +
                '}';
    }

    /**
     * Añade una opinión a la película.
     * @param o
     */
    public void añadirOpinion(Opinion o) {
        opiniones.add(o);
        o.setPelicula(this);
    }
}