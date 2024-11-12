package br.com.senac.produto.repository;

import br.com.senac.produto.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

}
