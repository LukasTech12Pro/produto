package br.com.senac.produto.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.senac.produto.entity.Categoria;
import br.com.senac.produto.entity.Produto;
import br.com.senac.produto.repository.CategoriaRepository;
import br.com.senac.produto.repository.ProdutoRepository;
import jakarta.validation.Valid;

@Controller
public class AplicacaoController {

    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;

    public AplicacaoController(ProdutoRepository produtoRepository,
                                CategoriaRepository categoriaRepository) {
        this.produtoRepository  = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/index")
    public String showProdutosLista(Model model) {        
        model.addAttribute("produtos", produtoRepository.findAll());
        return "index";
    }

    @GetMapping("/cadproduto")
    public String showCadProduto(Model model, Produto produto) {
        model.addAttribute("categorias", categoriaRepository.findAll());        
        return "cadProduto";        
    }

    @PostMapping("/addproduto")
    public String addProduto(@Valid Produto produto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaRepository.findAll());
            return "cadProduto";
        }
        
        produtoRepository.save(produto);
        return "redirect:/index";
    }
}
