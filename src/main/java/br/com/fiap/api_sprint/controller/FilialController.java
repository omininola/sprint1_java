package br.com.fiap.api_sprint.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.api_sprint.dto.filial.FilialRequestDTO;
import br.com.fiap.api_sprint.dto.filial.FilialResponseDTO;
import br.com.fiap.api_sprint.service.FilialService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/filiais")
public class FilialController {

  @Autowired
  public FilialService filialService;

  // Create - POST
  @PostMapping
  public ResponseEntity<FilialResponseDTO> createFilial(@Valid @RequestBody FilialRequestDTO filialRequestDTO) {
    FilialResponseDTO filial = filialService.save(filialRequestDTO);
    return new ResponseEntity<>(filial, HttpStatus.CREATED);
  }

  // Read All - GET
  @GetMapping
  public ResponseEntity<Page<FilialResponseDTO>> readAllFiliais() {
    Page<FilialResponseDTO> page = filialService.findAll();
    return new ResponseEntity<>(page, HttpStatus.OK);
  }

  // Read By Id = GET
  @GetMapping("/{id}")
  public ResponseEntity<FilialResponseDTO> readFilial(@PathVariable Long id) {
    Optional<FilialResponseDTO> filial = filialService.findById(id);

    if (filial.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(filial.get(), HttpStatus.OK);
  }

  // Update - PUT
  @PutMapping("/{id}")
  public ResponseEntity<FilialResponseDTO> updateFilial(@PathVariable Long id,
      @Valid @RequestBody FilialRequestDTO filialRequestDTO) {
    Optional<FilialResponseDTO> filial = filialService.update(id, filialRequestDTO);

    if (filial.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(filial.get(), HttpStatus.OK);
  }

  // Delete - Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<FilialResponseDTO> deleteFilial(@PathVariable Long id) {
    Optional<FilialResponseDTO> filial = filialService.delete(id);

    if (filial.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(filial.get(), HttpStatus.NO_CONTENT);
  }

}
