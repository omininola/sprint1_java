package br.com.fiap.api_sprint.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.fiap.api_sprint.dto.filial.FilialRequestDTO;
import br.com.fiap.api_sprint.dto.filial.FilialResponseDTO;
import br.com.fiap.api_sprint.entity.Filial;

public class FilialMapper {

  public static Filial requestToFilial(FilialRequestDTO filialRequestDTO) {
    Filial filial = new Filial();
    filial.setNome(filialRequestDTO.getNome());
    filial.setEndereco(filialRequestDTO.getEndereco());
    return filial;
  }

  public static FilialResponseDTO filialToResponse(Filial filial) {
    FilialResponseDTO filialResponseDTO = new FilialResponseDTO();
    filialResponseDTO.setNome(filial.getNome());
    filialResponseDTO.setEndereco(filial.getEndereco());
    filialResponseDTO.setAreas(filial.getAreas());
    return filialResponseDTO;
  }

  public static List<FilialResponseDTO> filialToResponse(List<Filial> filiais) {
    List<FilialResponseDTO> filiaisResponseDTO = filiais.stream().map(FilialMapper::filialToResponse).toList();
    return filiaisResponseDTO;
  }

  public static Page<FilialResponseDTO> filiaisToPage(List<Filial> filiais) {
    Pageable defaultPage = PageRequest.of(0, 10);
    Page<FilialResponseDTO> page = new PageImpl<FilialResponseDTO>(FilialMapper.filialToResponse(filiais), defaultPage,
        10);
    return page;
  }
}
