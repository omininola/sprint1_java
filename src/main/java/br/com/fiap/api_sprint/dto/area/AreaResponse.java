package br.com.fiap.api_sprint.dto.area;

import br.com.fiap.api_sprint.entity.Filial;
// import br.com.fiap.api_sprint.entity.Filial;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaResponse {

  Long id;
  String status;
  Filial filial;
}
