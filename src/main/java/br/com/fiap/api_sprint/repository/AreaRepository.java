package br.com.fiap.api_sprint.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.fiap.api_sprint.entity.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
  List<Area> findByFilialNome(String filial, Pageable pageable);
}
