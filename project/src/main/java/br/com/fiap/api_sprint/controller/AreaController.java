package br.com.fiap.api_sprint.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.api_sprint.dto.area.AreaRequest;
import br.com.fiap.api_sprint.dto.area.AreaResponse;
import br.com.fiap.api_sprint.service.AreaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/areas")
public class AreaController {

  @Autowired
  public AreaService areaService;

  // Create - POST
  @PostMapping
  public ResponseEntity<AreaResponse> createArea(@Valid @RequestBody AreaRequest areaRequest) {
    AreaResponse area = areaService.save(areaRequest);
    return new ResponseEntity<>(area, HttpStatus.CREATED);
  }

  // Read All - GET
  @GetMapping
  public ResponseEntity<Page<AreaResponse>> readAllAreas(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "10") int size,
      @RequestParam(value = "sort", defaultValue = "id") String field
  ) {
    Page<AreaResponse> areas = areaService.findAll(page, size, field);
    return new ResponseEntity<>(areas, HttpStatus.OK);
  }

  // Read by Filial Name - GET
  @GetMapping("/search")
  public ResponseEntity<Page<AreaResponse>> searchAreasByFilial(
      @RequestParam(value = "filial") String filial) {
    Page<AreaResponse> areas = areaService.searchAreasByFilial(filial);
    return new ResponseEntity<>(areas, HttpStatus.OK);
  }

  // Update
  @PutMapping("/{id}")
  public ResponseEntity<AreaResponse> updateArea(@RequestBody AreaRequest areaRequest,
      @PathVariable Long id) {
    Optional<AreaResponse> area = areaService.update(id, areaRequest);

    if (area.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(area.get(), HttpStatus.OK);
  }

  // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<AreaResponse> deleteArea(@PathVariable Long id) {
    Optional<AreaResponse> area = areaService.delete(id);

    if (area.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(area.get(), HttpStatus.NO_CONTENT);
  }
}
