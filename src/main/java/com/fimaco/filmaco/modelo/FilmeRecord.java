package com.fimaco.filmaco.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilmeRecord(@JsonProperty("Title")String titulo,
                          @JsonProperty("Year")String ano,
                          @JsonProperty("Director")String diretor,
                          @JsonProperty("Genre")String genero,
                          @JsonProperty("imdbID")String imdbID,
                          @JsonProperty("Poster") String poster) {
}
