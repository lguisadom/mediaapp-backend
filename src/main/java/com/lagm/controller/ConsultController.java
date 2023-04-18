package com.lagm.controller;

import com.lagm.dto.ConsultDTO;
import com.lagm.dto.ConsultListExamDTO;
import com.lagm.model.Consult;
import com.lagm.model.Exam;
import com.lagm.service.IConsultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
@RequestMapping("/consults")
@RequiredArgsConstructor
public class ConsultController {
    private final IConsultService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ConsultDTO>> findAll() {
        List<ConsultDTO> consultList = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(consultList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultDTO> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(this.convertToDto(service.findById(id)), HttpStatus.OK);
    }

    /*@PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ConsultDTO consultDTO) {
        Consult createdConsult = service.save(this.convertToEntity(consultDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdConsult.getIdConsult()).toUri();
        return ResponseEntity.created(location).build();
    }*/
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ConsultListExamDTO dto) {
        Consult consult = this.convertToEntity(dto.getConsult());
        // List<Exam> exams = dto.getLstExam().stream().map(e -> modelMapper.map(e, Exam.class)).collect(Collectors.toList());
        List<Exam> exams = modelMapper.map(dto.getLstExam(), new TypeToken<List<Exam>>(){}.getType()); // Lombok: DTO List -> Entity List
        Consult createdConsult = service.saveTransactional(consult, exams);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdConsult.getIdConsult()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ConsultDTO consultDTO) {
        consultDTO.setIdConsult(id);
        Consult updatedConsult = service.update(this.convertToEntity(consultDTO), id);
        return new ResponseEntity<>(this.convertToDto(updatedConsult), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<ConsultDTO> findByIdHateoas(@PathVariable("id") Integer id) {
        EntityModel<ConsultDTO> resource = EntityModel.of(this.convertToDto(service.findById(id)));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("consult-info1"));
        return resource;
    }
    private ConsultDTO convertToDto(Consult obj) {
        return modelMapper.map(obj, ConsultDTO.class);
    }

    private Consult convertToEntity(ConsultDTO dto) {
        return modelMapper.map(dto, Consult.class);
    }
}
