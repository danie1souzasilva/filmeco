package com.fimaco.filmaco.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

// Record (DTO) que mapeia exatamente o JSON da OMDb
public record FilmeOmdb(
        @JsonProperty("Title") String titulo,
        @JsonProperty("Year") String ano,
        @JsonProperty("Director") String diretor,
        @JsonProperty("Genre") String genero,
        @JsonProperty("imdbID") String imdbId
) {}

