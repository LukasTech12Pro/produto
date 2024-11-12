package br.com.senac.produto.controller;

import br.com.senac.produto.entity.Loja;
import br.com.senac.produto.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lojas")
public class LojaController {
    @Autowired
    private LojaRepository lojaRepository;

    public LojaController(LojaRepository lojaRepository) {
        this.lojaRepository = lojaRepository;
    }

    @GetMapping
    public ResponseEntity<?> listarLojas() {
        return new ResponseEntity<>(lojaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> lojaGetById(@PathVariable int id) {
        return new ResponseEntity<>(lojaRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> salvarLoja(@RequestBody Loja loja) {
        Loja lojaSalva;
        try {
            lojaSalva = lojaRepository.save(loja);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro ao salvar Loja: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Loja>(lojaSalva, HttpStatus.OK);
    }
}
