package com.fimaco.filmaco.FilmeController;

import com.fimaco.filmaco.FilmeService.FilmeService;
import com.fimaco.filmaco.modelo.Filme;
import com.fimaco.filmaco.repository.FilmeRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private FilmeService filmeService;
    private FilmeRepository filmeRepository;

    public FilmeController(FilmeService filmeService, FilmeRepository filmeRepository) {
        this.filmeService = filmeService;
        this.filmeRepository = filmeRepository;
    }

    @GetMapping("/api/{imdbID}")
    public Filme buscarFilme(@PathVariable String imdbID) {
        return filmeService.buscarFilme(imdbID);
    }
    @GetMapping("/id/{id}")
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
    @GetMapping(value = "/{id}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Filme> mostrarJson(@PathVariable Long id) {
        Filme filme = filmeService.buscarPorId(id);
        return ResponseEntity.ok(filme);

    }
    @GetMapping(value = "/{id}/text", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> mostrarTexto(@PathVariable Long id) {
        Filme filme = filmeService.buscarPorId(id);
        String texto = "Título: " + filme.nome + "\n" +
                "Ano: " + filme.ano + "\n" +
                "Diretor: " + filme.diretor + "\n" +
                "Gênero: " + filme.genero + "\n" +
                "imdbID: " + filme.imdbID + "\n" +
                "Poster: " + filme.poster + "\n";
        return ResponseEntity.ok(texto);
    }
    @GetMapping(value = "/html", produces = MediaType.TEXT_HTML_VALUE)
    public String mostrarTodosHtml() {
        List<Filme> filmes = filmeService.listarTodos();

        StringBuilder html = new StringBuilder("<html><body>");
        html.append("<h1>Lista de Filmes</h1>");

        for (Filme filme : filmes) {
            html.append("<div style='margin-bottom:20px;'>")
                    .append("<h2>").append(filme.nome).append("</h2>")
                    .append("<p>Ano: ").append(filme.ano).append("</p>")
                    .append("<p>Diretor: ").append(filme.diretor).append("</p>")
                    .append("<p>Gênero: ").append(filme.genero).append("</p>")
                    .append("<p>imdbID: ").append(filme.imdbID).append("</p>")
                    .append("<img src='").append(filme.poster).append("' alt='").append(filme.nome).append("'/>")
                    .append("</div><hr>");
        }

        html.append("</body></html>");
        return html.toString();
    }

    @GetMapping("/diretor/{diretor}")
    public List<Filme> buscarPorDiretor(@PathVariable String diretor) {
        return filmeRepository.findByDiretorIgnoreCase(diretor);
    }
    @GetMapping("/genero/{genero}")
    public List<Filme> buscaPorGenero(@PathVariable String genero) {
        return filmeRepository.findByGeneroIgnoreCase(genero);
    }
    @GetMapping("/ano/{ano}")
    public List<Filme> buscarPorAno(@PathVariable String ano) {
        return filmeRepository.findByAno(ano);
    }
    @GetMapping("/nome/{nome}")
    public List<Filme> buscarPorNome(@PathVariable String nome) {
        return filmeRepository.findByNomeIgnoreCase(nome);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarFilme(@PathVariable Long id) {
        filmeRepository.deleteById(id);
        return ResponseEntity.ok("Filme com ID " + id + " deletado com sucesso.");
    }

    @DeleteMapping
    public ResponseEntity<String> deletarTodosFilmes() {
        filmeRepository.deleteAll();
        return ResponseEntity.ok("Todos os filmes foram deletados com sucesso.");
    }




}
