package org.jan.apiservlet.webapp.headers.services;

import org.jan.apiservlet.webapp.headers.mapping.dto.UsuarioDto;

import java.util.Optional;

public interface UsuarioService {
    Optional<UsuarioDto> login(String username,String password);
}