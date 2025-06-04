package org.example.descfrecuente.repositories;

import org.example.descfrecuente.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {

    // Método para buscar por email
    Optional<ClienteEntity> findByEmail(String email);

    // Método para buscar clientes por rango de visitas
    @Query("SELECT c FROM ClienteEntity c WHERE c.visitasMes BETWEEN :min AND :max")
    List<ClienteEntity> findByVisitasBetween(@Param("min") int min, @Param("max") int max);

    // Método para encontrar por RUT (ya viene heredado de JpaRepository como findById)
    // Optional<ClienteEntity> findByRut(String rut); // No es necesario si usas el RUT como ID
}