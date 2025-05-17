package br.com.fiap.api_sprint.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.api_sprint.dto.area.AreaRequestDTO;
import br.com.fiap.api_sprint.dto.area.AreaResponseDTO;
import br.com.fiap.api_sprint.entity.Area;
import br.com.fiap.api_sprint.mapper.AreaMapper;
import br.com.fiap.api_sprint.repository.AreaRepository;

@Service
public class AreaService {

  @Autowired
  public AreaRepository areaRepository;

  // Create Areas
  public AreaResponseDTO save(AreaRequestDTO areaRequestDTO) {
    Area area = areaRepository.save(AreaMapper.requestToArea(areaRequestDTO));
    return AreaMapper.areaToResponse(area);
  }

  // Read All Areas
  public Page<AreaResponseDTO> findAll() {
    List<Area> areas = areaRepository.findAll();
    Page<AreaResponseDTO> page = AreaMapper.areasToPage(areas);
    return page;
  }

  // Read Areas filtered by their filial name
  public Page<AreaResponseDTO> searchAreasByFilial(String filial) {
    Pageable defaultPage = PageRequest.of(0, 10);
    List<Area> areas = areaRepository.findByFilialNome(filial, defaultPage);
    Page<AreaResponseDTO> page = AreaMapper.areasToPage(areas);
    return page;
  }

  // Update Area
  public Optional<AreaResponseDTO> update(Long id, AreaRequestDTO areaRequestDTO) {
    Optional<Area> existingArea = areaRepository.findById(id);
    Area areaUpdated = AreaMapper.requestToArea(areaRequestDTO);
    areaUpdated.setId(id);
    AreaResponseDTO areaResponse = null;

    if (existingArea.isPresent()) {
      areaRepository.save(areaUpdated);
      areaResponse = AreaMapper.areaToResponse(areaUpdated);
    }

    Optional<AreaResponseDTO> optionalAreaResponse = Optional.ofNullable(areaResponse);
    return optionalAreaResponse;
  }

  // Delete Area
  public Optional<AreaResponseDTO> delete(Long id) {
    Optional<Area> area = areaRepository.findById(id);
    AreaResponseDTO areaResponse = null;

    if (area.isPresent()) {
      areaResponse = AreaMapper.areaToResponse(area.get());
      areaRepository.delete(area.get());
    }

    Optional<AreaResponseDTO> optionalAreaResponseDTO = Optional.ofNullable(areaResponse);
    return optionalAreaResponseDTO;
  }

}
