package br.com.senac.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senac.produto.entity.Produto;

import java.util.ArrayList;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {
    
    ArrayList<Produto> findByNomeLike(String nome);    
    
    ArrayList<Produto> findByPrecoGreaterThanEqual(float preco);

    ArrayList<Produto> findByPrecoLessThanEqual(float preco);
}
