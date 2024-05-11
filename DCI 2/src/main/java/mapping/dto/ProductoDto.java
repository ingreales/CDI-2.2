package org.jan.apiservlet.webapp.headers.mapping.dto;

import org.jan.apiservlet.webapp.headers.models.Categoria;

import java.time.LocalDate;

public record ProductoDto(Long id, String nombre, Categoria categoria, int precio, String sku, LocalDate fechaRegistro) {
}