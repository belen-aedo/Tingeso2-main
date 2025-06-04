package org.example.descfrecuente.controllers;

import lombok.RequiredArgsConstructor;
import org.example.descfrecuente.entities.ClienteEntity;
import org.example.descfrecuente.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public List<ClienteEntity> getAll() {
        return service.getAllClientes();
    }

    @GetMapping("/{rut}")
    public ResponseEntity<ClienteEntity> getByRut(@PathVariable String rut) {
        return service.getClienteById(rut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClienteEntity crear(@RequestBody ClienteEntity cliente) {
        return service.saveCliente(cliente);
    }

    @PutMapping("/{rut}/visita")
    public void sumarVisita(@PathVariable String rut) {
        service.incrementarVisita(rut);
    }

    @PutMapping("/reset")
    public void resetVisitas() {
        service.resetVisitasMensuales();
    }
}
