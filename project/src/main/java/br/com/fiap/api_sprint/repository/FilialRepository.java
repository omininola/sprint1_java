package br.com.fiap.api_sprint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.fiap.api_sprint.entity.Filial;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long> {
  List<Filial> findByNome(String nome);
}
