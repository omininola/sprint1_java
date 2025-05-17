package br.com.fiap.api_sprint.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.fiap.api_sprint.dto.filial.FilialRequest;
import br.com.fiap.api_sprint.dto.filial.FilialResponse;
import br.com.fiap.api_sprint.entity.Filial;

public class FilialMapper {

  public static Filial requestToFilial(FilialRequest filialRequest) {
    Filial filial = new Filial();
    filial.setNome(filialRequest.getNome());
    filial.setEndereco(filialRequest.getEndereco());
    return filial;
  }

  public static FilialResponse filialToResponse(Filial filial) {
    FilialResponse filialResponse = new FilialResponse();
    filialResponse.setId(filial.getId());
    filialResponse.setNome(filial.getNome());
    filialResponse.setEndereco(filial.getEndereco());
    filialResponse.setAreas(filial.getAreas());
    return filialResponse;
  }

  public static List<FilialResponse> filialToResponse(List<Filial> filiais) {
    List<FilialResponse> filiaisResponseDTO = filiais.stream().map(FilialMapper::filialToResponse).toList();
    return filiaisResponseDTO;
  }

  public static Page<FilialResponse> filiaisToPage(List<Filial> filiais) {
    Pageable defaultPage = PageRequest.of(0, 10);
    Page<FilialResponse> page = new PageImpl<FilialResponse>(FilialMapper.filialToResponse(filiais), defaultPage,
        10);
    return page;
  }
}
