package org.jan.apiservlet.webapp.headers.models;

import lombok.*;
import org.jan.apiservlet.webapp.headers.mapping.dto.ProductoDto;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ItemCarro {
    private int cantidad;
    private ProductoDto producto;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass())
            return false;
        ItemCarro itemCarro = (ItemCarro) object;
        return Objects.equals(producto.id(), itemCarro.producto.id()) &&
                Objects.equals(producto.nombre(), itemCarro.producto.nombre());
    }
    public int getTotal(){
        return cantidad * producto.precio();
    }

}