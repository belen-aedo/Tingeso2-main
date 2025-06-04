package org.example.descfrecuente.services;

import lombok.RequiredArgsConstructor;
import org.example.descfrecuente.entities.ClienteEntity;
import org.example.descfrecuente.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<ClienteEntity> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteEntity> getClienteById(String rut) {
        return clienteRepository.findById(rut);
    }

    public ClienteEntity saveCliente(ClienteEntity cliente) {
        actualizarCategoriaFrecuencia(cliente);
        return clienteRepository.save(cliente);
    }

    public void actualizarCategoriaFrecuencia(ClienteEntity cliente) {
        int visitas = cliente.getVisitasMes();
        if (visitas >= 7) cliente.setDescuentoAplicable(30);
        else if (visitas >= 5) cliente.setDescuentoAplicable(20);
        else if (visitas >= 2) cliente.setDescuentoAplicable(10);
        else cliente.setDescuentoAplicable(0);
    }

    public void incrementarVisita(String rut) {
        clienteRepository.findById(rut).ifPresent(cliente -> {
            cliente.setVisitasMes(cliente.getVisitasMes() + 1);
            actualizarCategoriaFrecuencia(cliente);
            clienteRepository.save(cliente);
        });
    }

    public void resetVisitasMensuales() {
        List<ClienteEntity> clientes = clienteRepository.findAll();
        for (ClienteEntity cliente : clientes) {
            cliente.setVisitasMes(0);
            actualizarCategoriaFrecuencia(cliente);
            clienteRepository.save(cliente);
        }
    }
}
