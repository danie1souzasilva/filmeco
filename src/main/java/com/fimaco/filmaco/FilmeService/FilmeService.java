package com.fimaco.filmaco.FilmeService;

import com.fimaco.filmaco.modelo.Filme;
import com.fimaco.filmaco.modelo.FilmeRecord;
import com.fimaco.filmaco.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FilmeService {

    private final RestTemplate restTemplate = new RestTemplate();
    public static final String API_KEY = "f59e1a9";

    @Autowired
    private FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }
    public Filme buscarFilme(String imdbID) {
        String URL ="http://www.omdbapi.com/?i=" + imdbID +"&apikey=" + API_KEY;
        FilmeRecord filmeRecord = restTemplate.getForObject(URL, FilmeRecord.class);

        // 2. Converter para entidade
        Filme filme = new Filme();
        filme.nome = filmeRecord.titulo();
        filme.ano = filmeRecord.ano();
        filme.diretor = filmeRecord.diretor();
        filme.genero = filmeRecord.genero();
        filme.imdbID = filmeRecord.imdbID();
        filme.poster = filmeRecord.poster();

        // 3. Salvar no banco (gera id automaticamente)
        return filmeRepository.save(filme);
    }

    // 🔹 Busca direto no banco pelo ID interno
    public Filme buscarPorId(Long id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));
    }

    // 🔹 Lista todos os filmes do banco
    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }
}
