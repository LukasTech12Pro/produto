package br.com.senac.produto.controller;

import java.util.List;
import java.util.Optional;

import br.com.senac.produto.entity.Categoria;
import br.com.senac.produto.entity.Fornecedor;
import br.com.senac.produto.repository.CategoriaRepository;
import br.com.senac.produto.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.senac.produto.entity.Produto;
import br.com.senac.produto.repository.ProdutoRepository;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/produto")
    public ResponseEntity<?> getDadosProduto() {
        List<Produto> produtos = produtoRepository.findAll();

        for (int i = 0; i < produtos.size(); i++) {

            if (produtos.get(i).getCategoria() != null) {
                produtos.get(i).getCategoria().setProdutos(null);
            }
            
        }

        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("produto/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return new ResponseEntity<>(produtoRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("produto/nome/{nome}")
    public ResponseEntity<?> getByNome(@PathVariable String nome) {
        return new ResponseEntity<>(produtoRepository.findByNomeLike("%" + nome + "%"), HttpStatus.OK);
    }

    @GetMapping("produto/preco/maior/{preco}")
    public ResponseEntity<?> getByPrecoMaior(@PathVariable float preco) {
        return new ResponseEntity<>(produtoRepository.findByPrecoGreaterThanEqual(preco), HttpStatus.OK);
    }

    @GetMapping("produto/preco/menor/{preco}")
    public ResponseEntity<?> getByPrecoMenor(@PathVariable float preco) {
        return new ResponseEntity<>(produtoRepository.findByPrecoLessThanEqual(preco), HttpStatus.OK);
    }

    @PostMapping("/produto")
    public ResponseEntity<?> salvaProduto(@RequestBody Produto entity) {
        Produto produtoSalvo;
        Optional<Categoria> categoria = categoriaRepository.findById(entity.getCategoria().getId());
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(entity.getFornecedor().getId());

        try {
            if (categoria.isPresent()) {
                entity.setCategoria(categoria.get());
            }

            if (fornecedor.isPresent()) {
                entity.setFornecedor(fornecedor.get());
            }

            produtoSalvo = produtoRepository.save(entity);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro ao salvar o produto " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Produto>(produtoSalvo, HttpStatus.OK);
    }

    @PutMapping("produto/{id}")
    public ResponseEntity<?> atualizaProduto(@PathVariable int id, @RequestBody Produto entity) {
        Optional<Produto> produtoAtualizar = produtoRepository.findById(id);
        Produto p = null;
        if (produtoAtualizar.isPresent()) {
            p = produtoAtualizar.get();
            p.setNome(entity.getNome());
            p.setPreco(entity.getPreco());
            p.setDataCadastro(entity.getDataCadastro());
            p.setCategoria(entity.getCategoria());

            try {
                p = produtoRepository.save(p);
            } catch (Exception e) {
                return new ResponseEntity<String>("erro ao atualizar o produto", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<Produto>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("produto nao encontrado", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<String> deleteProduto(@PathVariable int id) {
        Optional<Produto> produtoExcluir = produtoRepository.findById(id);
        
        if (produtoExcluir.isPresent()) {
            try {
                produtoRepository.delete(produtoExcluir.get());
                return new ResponseEntity<>("Produto excluído com sucesso", HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>("Erro ao excluir o produto", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
    }
}

