package br.com.fiap.api_sprint.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.fiap.api_sprint.dto.filial.FilialRequestDTO;
import br.com.fiap.api_sprint.dto.filial.FilialResponseDTO;
import br.com.fiap.api_sprint.entity.Filial;
import br.com.fiap.api_sprint.mapper.FilialMapper;
import br.com.fiap.api_sprint.repository.FilialRepository;

@Service
public class FilialService {

  @Autowired
  public FilialRepository filialReposotory;

  // Create Filiais
  public FilialResponseDTO save(FilialRequestDTO filialRequestDTO) {
    Filial filial = filialReposotory.save(FilialMapper.requestToFilial(filialRequestDTO));
    return FilialMapper.filialToResponse(filial);
  }

  // Read All Filiais
  public Page<FilialResponseDTO> findAll() {
    List<Filial> filiais = filialReposotory.findAll();
    Page<FilialResponseDTO> page = FilialMapper.filiaisToPage(filiais);
    return page;
  }

  // Read Filial By Id
  public Optional<FilialResponseDTO> findById(Long id) {
    Optional<Filial> filial = filialReposotory.findById(id);
    Optional<FilialResponseDTO> optionalFilialResponse = Optional
        .ofNullable(FilialMapper.filialToResponse(filial.get()));
    return optionalFilialResponse;
  }

  // Update Filial
  public Optional<FilialResponseDTO> update(Long id, FilialRequestDTO filialRequestDTO) {
    Optional<Filial> existingFilial = filialReposotory.findById(id);
    Filial updateFilial = FilialMapper.requestToFilial(filialRequestDTO);
    updateFilial.setId(id);
    FilialResponseDTO filialResponse = null;

    if (existingFilial.isPresent()) {
      filialReposotory.save(updateFilial);
      filialResponse = FilialMapper.filialToResponse(updateFilial);
    }

    Optional<FilialResponseDTO> optionalFilialResponse = Optional.ofNullable(filialResponse);
    return optionalFilialResponse;
  }

  // Delete Filial
  public Optional<FilialResponseDTO> delete(Long id) {
    Optional<Filial> filial = filialReposotory.findById(id);
    FilialResponseDTO filialResponse = null;

    if (filial.isPresent()) {
      filialResponse = FilialMapper.filialToResponse(filial.get());
      filialReposotory.delete(filial.get());
    }

    Optional<FilialResponseDTO> optionalFilialResponse = Optional.ofNullable(filialResponse);
    return optionalFilialResponse;
  }

}
