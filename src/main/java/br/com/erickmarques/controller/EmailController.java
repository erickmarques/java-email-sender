package br.com.erickmarques.controller;

import br.com.erickmarques.dto.EmailRequestDTO;
import br.com.erickmarques.dto.EmailStatusDTO;
import br.com.erickmarques.enumeration.EmailStatus;
import br.com.erickmarques.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/email")
public class EmailController {

    @Autowired
    EmailService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody EmailRequestDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }

   @PutMapping(value ="/{id}")
    public ResponseEntity<?> setStatus(@PathVariable(value = "id") Long id,
                                       @Valid @RequestBody EmailStatusDTO dto) {
       return ResponseEntity.ok(service.setStatus(id, dto.getStatus())); 
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
       return ResponseEntity.ok(service.findById(id)); 
    } 
}
