package org.jan.apiservlet.webapp.headers.services;

import jakarta.enterprise.inject.Alternative;
import org.jan.apiservlet.webapp.headers.mapping.ProductoMapper;
import org.jan.apiservlet.webapp.headers.mapping.dto.CategoriaDto;
import org.jan.apiservlet.webapp.headers.mapping.dto.ProductoDto;
import org.jan.apiservlet.webapp.headers.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Alternative
public class ProductoServiceImpl implements org.jan.apiservlet.webapp.headers.services.ProductoService {
    private static final List<Producto> producto = Arrays.asList(new Producto(1L, "notebook", "computacion", 175000),
            new Producto(2L, " escritorio", "Oficina", 100000),
            new Producto(3L, " mecánico", "Computación", 40000));

    @Override
    public List<ProductoDto> listar() {
        return producto.stream().map(ProductoMapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductoDto> porId(Long id) {
        return listar().stream().filter(p -> p.id().equals(id)).findAny();
    }

    @Override
    public void guardar(ProductoDto producto) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public List<CategoriaDto> listarCategoria() {
        return null;
    }

    @Override
    public Optional<CategoriaDto> porIdCategoria(Long id) {
        return Optional.empty();
    }
}