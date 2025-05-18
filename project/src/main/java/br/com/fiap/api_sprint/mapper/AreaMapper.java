package br.com.fiap.api_sprint.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.fiap.api_sprint.dto.area.AreaRequest;
import br.com.fiap.api_sprint.dto.area.AreaResponse;
import br.com.fiap.api_sprint.entity.Area;
import br.com.fiap.api_sprint.entity.Filial;

public class AreaMapper {

  public static Area requestToArea(AreaRequest areaRequest, Filial filial) {
    Area area = new Area();
    area.setStatus(areaRequest.getStatus());
    area.setFilial(filial);
    return area;
  }

  public static AreaResponse areaToResponse(Area area) {
    AreaResponse areaReponse = new AreaResponse();
    areaReponse.setId(area.getId());
    areaReponse.setStatus(area.getStatus());
    areaReponse.setFilial(area.getFilial());
    return areaReponse;
  }

  public static List<AreaResponse> areaToResponse(List<Area> areas) {
    List<AreaResponse> areasResponse = areas.stream().map(AreaMapper::areaToResponse).toList();
    return areasResponse;
  }

  public static Page<AreaResponse> areasToPage(List<Area> areas) {
    Pageable defaultPage = PageRequest.of(0, 10);
    Page<AreaResponse> page = new PageImpl<AreaResponse>(AreaMapper.areaToResponse(areas), defaultPage, 10);
    return page;
  }

}
