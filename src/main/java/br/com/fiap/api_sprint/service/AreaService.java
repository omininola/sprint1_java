package br.com.fiap.api_sprint.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.api_sprint.dto.area.AreaRequest;
import br.com.fiap.api_sprint.dto.area.AreaResponse;
import br.com.fiap.api_sprint.entity.Area;
import br.com.fiap.api_sprint.entity.Filial;
import br.com.fiap.api_sprint.mapper.AreaMapper;
import br.com.fiap.api_sprint.repository.AreaRepository;
import br.com.fiap.api_sprint.repository.FilialRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AreaService {

  @Autowired
  public AreaRepository areaRepository;

  @Autowired
  public FilialRepository filialRepository;

  // Create Areas
  public AreaResponse save(AreaRequest areaRequest) {
    Filial filial = filialRepository.findById(areaRequest.getFilialId())
        .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));
    Area area = areaRepository.save(AreaMapper.requestToArea(areaRequest, filial));
    return AreaMapper.areaToResponse(area);
  }

  // Read All Areas
  public Page<AreaResponse> findAll() {
    List<Area> areas = areaRepository.findAll();
    Page<AreaResponse> page = AreaMapper.areasToPage(areas);
    return page;
  }

  // Read Areas filtered by their filial name
  public Page<AreaResponse> searchAreasByFilial(String filial) {
    Pageable defaultPage = PageRequest.of(0, 10);
    List<Area> areas = areaRepository.findByFilialNome(filial, defaultPage);
    Page<AreaResponse> page = AreaMapper.areasToPage(areas);
    return page;
  }

  // Update Area
  public Optional<AreaResponse> update(Long id, AreaRequest areaRequest) {
    Optional<Area> existingArea = areaRepository.findById(id);
    Filial filial = filialRepository.findById(areaRequest.getFilialId())
        .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));
    Area areaUpdated = AreaMapper.requestToArea(areaRequest, filial);
    areaUpdated.setId(id);
    AreaResponse areaResponse = null;

    if (existingArea.isPresent()) {
      areaRepository.save(areaUpdated);
      areaResponse = AreaMapper.areaToResponse(areaUpdated);
    }

    Optional<AreaResponse> optionalAreaResponse = Optional.ofNullable(areaResponse);
    return optionalAreaResponse;
  }

  // Delete Area
  public Optional<AreaResponse> delete(Long id) {
    Optional<Area> area = areaRepository.findById(id);
    AreaResponse areaResponse = null;

    if (area.isPresent()) {
      areaResponse = AreaMapper.areaToResponse(area.get());
      areaRepository.delete(area.get());
    }

    Optional<AreaResponse> optionalAreaResponse = Optional.ofNullable(areaResponse);
    return optionalAreaResponse;
  }

}
