package com.vallegrande.springsecurity.modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaRepository repository;

    @GetMapping
    List<Persona> findAll(){
        return repository.findAll();
    }

    // Save
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Persona newPersona(@RequestBody Persona newPersona) {
        return repository.save(newPersona);
    }

    // Find
    @GetMapping("/{id}")
    Persona findOne(@PathVariable @Min(1) Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    // Save or update
    @PutMapping("/{id}")
    Persona saveOrUpdate(@RequestBody Persona newPersona, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setName(newPersona.getName());
                    x.setAuthor(newPersona.getAuthor());
                    x.setPrice(newPersona.getPrice());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newPersona.setId(id);
                    return repository.save(newPersona);
                });
    }


    @DeleteMapping("/{id}")
    void deletePersona(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
