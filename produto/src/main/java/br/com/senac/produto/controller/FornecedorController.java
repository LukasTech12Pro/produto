package br.com.senac.produto.controller;

import br.com.senac.produto.entity.Fornecedor;
import br.com.senac.produto.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @GetMapping
    public ResponseEntity<?> getAllFornecedor() {
        return new ResponseEntity<>(fornecedorRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByIdFornecedor(@PathVariable int id) {
        return new ResponseEntity<>(fornecedorRepository.findById(id), HttpStatus.OK);
    }
}
