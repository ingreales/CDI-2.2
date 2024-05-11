package org.jan.apiservlet.webapp.headers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jan.apiservlet.webapp.headers.mapping.dto.UsuarioDto;
import org.jan.apiservlet.webapp.headers.repositories.UsuarioRepository;
import org.jan.apiservlet.webapp.headers.repositories.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService{
    @Inject
    UsuarioRepository usuarioRepository;
    @Override
    public Optional<UsuarioDto> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u -> u.password().equals(password));
        } catch (SQLException throwables) {
            throw new org.jan.apiservlet.webapp.headers.services.ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}