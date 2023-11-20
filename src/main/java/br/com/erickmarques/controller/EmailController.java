package br.com.erickmarques.controller;

import br.com.erickmarques.dto.EmailRequestDTO;
import br.com.erickmarques.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
