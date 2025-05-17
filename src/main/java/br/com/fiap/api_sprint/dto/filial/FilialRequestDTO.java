package br.com.fiap.api_sprint.dto.filial;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilialRequestDTO {

  @NotBlank(message = "Nome não pode ser vazio")
  @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
  String nome;

  @NotBlank(message = "Endereço não pode ser vazio")
  String endereco;
}
