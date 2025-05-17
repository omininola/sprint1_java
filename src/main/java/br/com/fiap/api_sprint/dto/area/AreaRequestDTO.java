package br.com.fiap.api_sprint.dto.area;

import br.com.fiap.api_sprint.entity.Filial;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaRequestDTO{
  
  @NotBlank(message = "Status não pode ser vazio")
  String status;

  @NotNull(message = "Filial não pode ser nula")
  Filial filial;
}
