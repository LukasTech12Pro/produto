package br.com.senac.produto.repository;

import br.com.senac.produto.entity.Loja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaRepository extends JpaRepository<Loja, Integer> {

}
