package org.jan.apiservlet.webapp.headers.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jan.apiservlet.webapp.headers.mapping.CategoriaMapper;
import org.jan.apiservlet.webapp.headers.mapping.dto.CategoriaDto;
import org.jan.apiservlet.webapp.headers.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CategoriaRepositoryJdbcImpl implements Repository<CategoriaDto>{

    private Connection conn;

    @Inject
    public CategoriaRepositoryJdbcImpl(@Named("conn") Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<CategoriaDto> listar() throws SQLException {
        List<CategoriaDto> categorias = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM categories")) {
            while (rs.next()) {
                Categoria categoria = getCategoria(rs);
                CategoriaDto categoriaDto = CategoriaMapper.mapFrom(categoria);
                categorias.add(categoriaDto);
            }
        }
        return categorias;
    }



    @Override
    public CategoriaDto porId(Long id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categorie as c WHERE c.id=?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoria = getCategoria(rs);
                }

            }
            return CategoriaMapper.mapFrom(categoria);
        }
    }

    @Override
    public void guardar(CategoriaDto categoriaDto) throws SQLException {
        String sql;
        if (categoriaDto.id() != null && categoriaDto.id() > 0) {
            sql = "UPDATE categories set nombre=? where id=?";
        } else {
            sql = "INSERT INTO categories (nombre) VALUES(?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoriaDto.nombre());

            if (categoriaDto.id() != null && categoriaDto.id() > 0) {
                stmt.setLong(2, categoriaDto.id());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM categories WHERE id =?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }
    private Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("id"));
        categoria.setNombre(rs.getString("name"));
        return categoria;
    }
}