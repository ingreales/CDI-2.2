package org.jan.apiservlet.webapp.headers.services;

import org.jan.apiservlet.webapp.headers.mapping.dto.CategoriaDto;
import org.jan.apiservlet.webapp.headers.mapping.dto.ProductoDto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<ProductoDto> listar();
    Optional<ProductoDto> porId(Long id);
    void guardar(ProductoDto producto);
    void eliminar(Long id);
    List<CategoriaDto> listarCategoria();
    Optional<CategoriaDto> porIdCategoria(Long id);
}