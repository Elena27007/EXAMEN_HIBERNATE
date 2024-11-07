package org.example.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Clase que representa una opinión sobre una película.
 */
@Data
@Entity
@Table(name = "opinion")
public class Opinion implements Serializable {
    /**
     * Identificador de la opinión.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Descripción de la opinión.
     */
    @Lob
    private String descripcion;

    /**
     * Usuario que ha realizado la opinión.
     */
    private String usuario;

    /**
     * Puntuación de la opinión.
     */
    private Long puntuacion;

    /**
     * Película a la que pertenece la opinión.
     */
    @ManyToOne
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;

    @Override
    public String toString() {
        return "Opinion{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", usuario='" + usuario + '\'' +
                ", puntuacion=" + puntuacion +
                ", pelicula=" + pelicula.getTitulo() +
                '}';
    }
}