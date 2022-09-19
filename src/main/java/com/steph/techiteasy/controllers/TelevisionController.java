package com.steph.techiteasy.controllers;

import com.steph.techiteasy.dtos.TelevisionDto;
import com.steph.techiteasy.dtos.TelevisionInputDto;
import com.steph.techiteasy.models.Television;
import com.steph.techiteasy.services.TelevisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TelevisionController {
    private final TelevisionService service;

    public TelevisionController(TelevisionService service) {
        this.service = service;
    }

    @GetMapping("/televisions")
    public ResponseEntity<Object> getAllTelevisions(@RequestParam(value = "brand", required = false) Optional<String> brand) {
        List<TelevisionDto> dtos;

        if(brand.isEmpty()) {
            dtos = service.getAllTelevisions();
        } else {
            dtos = service.getAllTelevisionsByBrand(brand.get());
        }

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable long id) {
        return new ResponseEntity<>(service.getTelevisionById(id), HttpStatus.OK);
    }

    @PostMapping("/televisions")
    public ResponseEntity<Object> addTelevision(@Valid @RequestBody TelevisionInputDto tvDto, BindingResult br) {
        if(br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        TelevisionDto dto = service.addTelevision(tvDto);

        return ResponseEntity.created(null).body(dto);
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable long id) {
        return new ResponseEntity<>(service.deleteTelevision(id), HttpStatus.OK);
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable Long id, @RequestBody TelevisionInputDto newTv) {
        TelevisionDto dto = service.updateTelevision(newTv, id);

        return ResponseEntity.ok().body(dto);
    }
}