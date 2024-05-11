package org.jan.apiservlet.webapp.headers.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Producto {
    private Long id;
    private String nombre;
    private Categoria categoria;
    private int precio;
    private String sku;
    private LocalDate fechaRegistro;

    public Producto(Long id, String nombre, String tipo, int precio) {
        this.id = id;
        this.nombre = nombre;
        Categoria categoria = new Categoria();
        categoria.setNombre(tipo);
        this.categoria = categoria;
        this.precio = precio;
    }
}