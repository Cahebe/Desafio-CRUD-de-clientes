package com.devsuperior.clients.controllers;

import com.devsuperior.clients.dto.ClientDTO;
import com.devsuperior.clients.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    @Autowired
    private ClientService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        ClientDTO dto = service.findByID(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping()
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
        Page<ClientDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping()
    public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}