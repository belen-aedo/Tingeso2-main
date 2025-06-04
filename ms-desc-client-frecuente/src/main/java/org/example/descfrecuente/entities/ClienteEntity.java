package org.example.descfrecuente.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {

    @Id
    private String rut;
    private String nombre;
    private String email;
    private int visitasMes;
    private LocalDate fechaCumple;
    private int descuentoAplicable;
}
