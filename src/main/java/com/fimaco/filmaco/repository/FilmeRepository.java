package com.fimaco.filmaco.repository;

import com.fimaco.filmaco.modelo.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository <Filme, Long>{
    List<Filme>findByNomeIgnoreCase(String nome);
    List<Filme>findByAno(String ano);
    List<Filme>findByDiretorIgnoreCase(String dietor);
    List<Filme>findByGeneroIgnoreCase(String genero);
    List<Filme>findByImdbID(String imdbID);
    List<Filme>findByPoster(String poster);
}
