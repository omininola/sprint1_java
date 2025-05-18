package br.com.fiap.api_sprint.dto.filial;

import java.util.List;

import br.com.fiap.api_sprint.entity.Area;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilialResponse {
  Long id;
  String nome;
  String endereco;
  List<Area> areas;
}
