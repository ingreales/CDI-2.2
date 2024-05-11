package org.jan.apiservlet.webapp.headers.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jan.apiservlet.webapp.headers.mapping.UsuarioMapper;
import org.jan.apiservlet.webapp.headers.mapping.dto.UsuarioDto;
import org.jan.apiservlet.webapp.headers.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UsuarioRepositoryImpl implements UsuarioRepository {
    @Inject
    @Named("conn")
    private Connection conn;
    @Override
    public UsuarioDto porUsername(String username) throws SQLException {
        Usuario usuario;
        UsuarioDto u = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuario  username=?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                    u = UsuarioMapper.mapFrom(usuario);
                }
            }
        }
        return u;
    }
    @Override
    public List<UsuarioDto> listar() throws SQLException {
        List<UsuarioDto> usuarios = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT usuario")) {
            while (rs.next()) {
                Usuario u = getUsuario(rs);
                UsuarioDto usuarioDto = UsuarioMapper.mapFrom(u);
                usuarios.add(usuarioDto);
            }
        }
        return usuarios;
    }

    @Override
    public UsuarioDto porId(Long id) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement stmt = conn.prepareStatement(" usuario as u WHERE u.id=?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                }

            }
            return UsuarioMapper.mapFrom(usuario);
        }
    }

    @Override
    public void guardar(UsuarioDto usuarioDto) throws SQLException {
        String sql;
        if (usuarioDto.id() != null && usuarioDto.id() > 0) {
            sql = "UPDATE usuarios set username=?, password=?,email=? where id=?";
        } else {
            sql = "INSERT INTO usuarios (username, password,email) VALUES(?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuarioDto.username());
            stmt.setString(2, usuarioDto.password());
            stmt.setString(3, usuarioDto.email());

            if (usuarioDto.id() != null && usuarioDto.id() > 0) {
                stmt.setLong(4, usuarioDto.id());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id =?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }


    private Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setEmail(rs.getString("email"));
        return usuario;
    }
}