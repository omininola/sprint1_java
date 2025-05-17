package br.com.fiap.api_sprint.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.fiap.api_sprint.dto.area.AreaRequestDTO;
import br.com.fiap.api_sprint.dto.area.AreaResponseDTO;
import br.com.fiap.api_sprint.entity.Area;

public class AreaMapper {

  public static Area requestToArea(AreaRequestDTO areaRequestDTO) {
    Area area = new Area();
    area.setStatus(areaRequestDTO.getStatus());
    area.setFilial(areaRequestDTO.getFilial());
    return area;
  }

  public static AreaResponseDTO areaToResponse(Area area) {
    AreaResponseDTO areaReponse = new AreaResponseDTO();
    areaReponse.setId(area.getId());
    areaReponse.setStatus(area.getStatus());
    areaReponse.setFilial(area.getFilial());
    return areaReponse;
  }

  public static List<AreaResponseDTO> areaToResponse(List<Area> areas) {
    List<AreaResponseDTO> areasResponse = areas.stream().map(AreaMapper::areaToResponse).toList();
    return areasResponse;
  }

  public static Page<AreaResponseDTO> areasToPage(List<Area> areas) {
    Pageable defaultPage = PageRequest.of(0, 10);
    Page<AreaResponseDTO> page = new PageImpl<AreaResponseDTO>(AreaMapper.areaToResponse(areas), defaultPage, 10);
    return page;
  }

}
