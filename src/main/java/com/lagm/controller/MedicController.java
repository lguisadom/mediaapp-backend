package com.lagm.controller;

import com.lagm.dto.MedicDTO;
import com.lagm.model.Medic;
import com.lagm.service.IMedicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/medics")
@RequiredArgsConstructor
public class MedicController {
    private final IMedicService service;

    @Qualifier("medicMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MedicDTO>> findAll() {
        List<MedicDTO> medicList = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(medicList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicDTO> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(this.convertToDto(service.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MedicDTO medicDTO) {
        Medic createdMedic = service.save(this.convertToEntity(medicDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdMedic.getIdMedic()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody MedicDTO medicDTO) {
        medicDTO.setIdMedic(id);
        Medic updatedMedic = service.update(this.convertToEntity(medicDTO), id);
        return new ResponseEntity<>(this.convertToDto(updatedMedic), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<MedicDTO> findByIdHateoas(@PathVariable("id") Integer id) {
        EntityModel<MedicDTO> resource = EntityModel.of(this.convertToDto(service.findById(id)));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("patient-info1"));
        return resource;
    }
    private MedicDTO convertToDto(Medic obj) {
        return modelMapper.map(obj, MedicDTO.class);
    }

    private Medic convertToEntity(MedicDTO dto) {
        return modelMapper.map(dto, Medic.class);
    }
}
