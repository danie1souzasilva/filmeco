package com.fimaco.filmaco.FilmeController;

import com.fimaco.filmaco.FilmeService.FilmeService;
import com.fimaco.filmaco.modelo.Filme;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "/{id}/poster", produces = MediaType.TEXT_HTML_VALUE)
    public @ResponseBody String mostrarPoster(@PathVariable Long id) {
        Filme filme = filmeService.buscarPorId(id);
        return "<html><body>"
                + "<h1>" + filme.nome + "</h1>"
                + "<img src='" + filme.poster + "' alt='" + filme.nome + "'/>"
                + "</body></html>";
    }


}
