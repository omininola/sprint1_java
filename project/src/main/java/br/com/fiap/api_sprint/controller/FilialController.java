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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.api_sprint.dto.filial.FilialRequest;
import br.com.fiap.api_sprint.dto.filial.FilialResponse;
import br.com.fiap.api_sprint.service.FilialService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/filiais")
public class FilialController {

  @Autowired
  public FilialService filialService;

  // Create - POST
  @PostMapping
  public ResponseEntity<FilialResponse> createFilial(@Valid @RequestBody FilialRequest filialRequest) {
    FilialResponse filial = filialService.save(filialRequest);
    return new ResponseEntity<>(filial, HttpStatus.CREATED);
  }

  // Read All - GET
  @GetMapping
  public ResponseEntity<Page<FilialResponse>> readAllFiliais(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "10") int size,
      @RequestParam(value = "sort", defaultValue = "id") String field
  ) {
    Page<FilialResponse> pageResponse = filialService.findAll(page, size, field);
    return new ResponseEntity<>(pageResponse, HttpStatus.OK);
  }

  // Read By Filial Name - GET
  @GetMapping("/search")
  public ResponseEntity<Page<FilialResponse>> readFilialByName(@RequestParam String nome) {
    Page<FilialResponse> page = filialService.searchByName(nome);
    return new ResponseEntity<>(page, HttpStatus.OK);
  }

  // Read By Id = GET
  @GetMapping("/{id}")
  public ResponseEntity<FilialResponse> readFilial(@PathVariable Long id) {
    Optional<FilialResponse> filial = filialService.findById(id);

    if (filial.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(filial.get(), HttpStatus.OK);
  }

  // Update - PUT
  @PutMapping("/{id}")
  public ResponseEntity<FilialResponse> updateFilial(@PathVariable Long id,
      @Valid @RequestBody FilialRequest filialRequest) {
    Optional<FilialResponse> filial = filialService.update(id, filialRequest);

    if (filial.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(filial.get(), HttpStatus.OK);
  }

  // Delete - Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<FilialResponse> deleteFilial(@PathVariable Long id) {
    Optional<FilialResponse> filial = filialService.delete(id);

    if (filial.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(filial.get(), HttpStatus.NO_CONTENT);
  }

}
