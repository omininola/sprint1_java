package br.com.fiap.api_sprint.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tab_filial")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Filial {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "endereco")
  private String endereco;

  @OneToMany(mappedBy = "filial", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Area> areas = new ArrayList<>();
}
