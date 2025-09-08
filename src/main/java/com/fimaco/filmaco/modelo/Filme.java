package com.fimaco.filmaco.modelo;

import jakarta.persistence.*;

@Entity(name = "filme")
@Table(name = "filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nome;
    public String ano;
    public String diretor;
    public String genero;
    public String imdbID;

    public Filme() {
    }
}
