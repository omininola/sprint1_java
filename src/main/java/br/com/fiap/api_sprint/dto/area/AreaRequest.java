package br.com.fiap.api_sprint.dto.area;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaRequest{
  
  @NotBlank(message = "Status não pode ser vazio")
  String status;

  @NotNull(message = "Filial id não pode ser nulo")
  Long filialId;
}
