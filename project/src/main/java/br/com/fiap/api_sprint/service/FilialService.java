package br.com.fiap.api_sprint.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.fiap.api_sprint.dto.filial.FilialRequest;
import br.com.fiap.api_sprint.dto.filial.FilialResponse;
import br.com.fiap.api_sprint.entity.Filial;
import br.com.fiap.api_sprint.mapper.FilialMapper;
import br.com.fiap.api_sprint.repository.FilialRepository;

@Service
public class FilialService {

  @Autowired
  public FilialRepository filialReposotory;

  // Create Filiais
  public FilialResponse save(FilialRequest filialRequest) {
    Filial filial = filialReposotory.save(FilialMapper.requestToFilial(filialRequest));
    return FilialMapper.filialToResponse(filial);
  }

  // Read All
  public Page<FilialResponse> findAll(int page, int size, String field) {
    Sort sort = Sort.by(Sort.Direction.ASC, field);
    Pageable pageable = PageRequest.of(page, size, sort);
    List<Filial> filiais = filialReposotory.findAll(pageable).getContent();
    Page<FilialResponse> pageResponse = FilialMapper.filiaisToPage(filiais);
    return pageResponse;
  }

  // Read Filial By Name
  @Cacheable("filialByName")
  public Page<FilialResponse> searchByName(String nome) {
    List<Filial> filiais = filialReposotory.findByNome(nome);
    Page<FilialResponse> page = FilialMapper.filiaisToPage(filiais);
    return page;
  }

  // Read Filial By Id
  @Cacheable("filialById")
  public Optional<FilialResponse> findById(Long id) {
    Optional<Filial> filial = filialReposotory.findById(id);
    Optional<FilialResponse> optionalFilialResponse = Optional
        .ofNullable(FilialMapper.filialToResponse(filial.get()));
    return optionalFilialResponse;
  }

  // Update Filial
  public Optional<FilialResponse> update(Long id, FilialRequest filialRequest) {
    Optional<Filial> existingFilial = filialReposotory.findById(id);
    Filial updateFilial = FilialMapper.requestToFilial(filialRequest);
    updateFilial.setId(id);
    FilialResponse filialResponse = null;

    if (existingFilial.isPresent()) {
      filialReposotory.save(updateFilial);
      filialResponse = FilialMapper.filialToResponse(updateFilial);
    }

    Optional<FilialResponse> optionalFilialResponse = Optional.ofNullable(filialResponse);
    return optionalFilialResponse;
  }

  // Delete Filial
  public Optional<FilialResponse> delete(Long id) {
    Optional<Filial> filial = filialReposotory.findById(id);
    FilialResponse filialResponse = null;

    if (filial.isPresent()) {
      filialResponse = FilialMapper.filialToResponse(filial.get());
      filialReposotory.delete(filial.get());
    }

    Optional<FilialResponse> optionalFilialResponse = Optional.ofNullable(filialResponse);
    return optionalFilialResponse;
  }

}
