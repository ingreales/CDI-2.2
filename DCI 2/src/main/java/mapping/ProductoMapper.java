package org.jan.apiservlet.webapp.headers.mapping;

import org.jan.apiservlet.webapp.headers.mapping.dto.ProductoDto;
import org.jan.apiservlet.webapp.headers.models.Producto;

public class ProductoMapper {
    public static ProductoDto mapFrom(Producto producto){
        return new ProductoDto(producto.getId(),producto.getNombre(),producto.getCategoria(),producto.getPrecio(), producto.getSku(), producto.getFechaRegistro());
    }
    public static Producto mapFromDto(ProductoDto productoDto){
        return Producto.builder()
                .id(productoDto.id())
                .nombre(productoDto.nombre())
                .categoria(productoDto.categoria())
                .precio(productoDto.precio())
                .sku(productoDto.sku())
                .fechaRegistro(productoDto.fechaRegistro())
                .build();
    }
}