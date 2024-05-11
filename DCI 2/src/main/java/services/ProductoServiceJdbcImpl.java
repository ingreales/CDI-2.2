package org.jan.apiservlet.webapp.headers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jan.apiservlet.webapp.headers.mapping.dto.CategoriaDto;
import org.jan.apiservlet.webapp.headers.mapping.dto.ProductoDto;
import org.jan.apiservlet.webapp.headers.repositories.Repository;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class ProductoServiceJdbcImpl implements ProductoService {
    @Inject
    private Repository<ProductoDto> repositoryJdbc;
    @Inject
    private Repository<CategoriaDto> repositoryJdbcCategpria;

    @Override
    public List<ProductoDto> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<ProductoDto> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void guardar(ProductoDto producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public List<CategoriaDto> listarCategoria() {
        try {
            return repositoryJdbcCategpria.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public Optional<CategoriaDto> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbcCategpria.porId(id));
        } catch (SQLException throwables) {
            throw new org.jan.apiservlet.webapp.headers.services.ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }
}