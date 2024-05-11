package org.jan.apiservlet.webapp.headers.repositories;

import org.jan.apiservlet.webapp.headers.mapping.dto.UsuarioDto;

import java.sql.SQLException;

public interface UsuarioRepository extends Repository<UsuarioDto>{
    UsuarioDto porUsername(String username) throws SQLException;
}