package br.com.senac.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senac.produto.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

}
