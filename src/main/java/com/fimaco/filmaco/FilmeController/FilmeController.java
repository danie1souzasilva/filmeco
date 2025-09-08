package com.fimaco.filmaco.FilmeController;

import com.fimaco.filmaco.FilmeService.FilmeService;
import com.fimaco.filmaco.modelo.Filme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }
    @GetMapping("/api/{imdbID}")
    public Filme buscarFilme(@PathVariable String imdbID) {
        return filmeService.buscarFilme(imdbID);
    }
    @GetMapping("/{id}")
    public Filme buscarPorId(@PathVariable Long id){
        return filmeService.buscarPorId(id);
    }
    @GetMapping
    public List<Filme> listarTodos() {
        return filmeService.listarTodos();
    }
}
