package org.jan.apiservlet.webapp.headers.mapping;

import org.jan.apiservlet.webapp.headers.mapping.dto.CategoriaDto;
import org.jan.apiservlet.webapp.headers.models.Categoria;
import org.jan.apiservlet.webapp.headers.models.Producto;

public class CategoriaMapper {
    public static CategoriaDto mapFrom(Categoria categoria){
        return new CategoriaDto(categoria.getId(),categoria.getNombre());
    }
    public static Producto mapFromDto(CategoriaDto categoriaDto) {
        return Producto.builder()
                .id(categoriaDto.id())
                .nombre(categoriaDto.nombre())
                .build();
    }
}