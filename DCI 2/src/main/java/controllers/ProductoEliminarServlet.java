package org.jan.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jan.apiservlet.webapp.headers.mapping.dto.ProductoDto;
import org.jan.apiservlet.webapp.headers.services.ProductoService;
import org.jan.apiservlet.webapp.headers.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {
    @Inject
    private ProductoService service;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        if (id > 0) {
            Optional<ProductoDto> o = service.porId(id);
            if (o.isPresent()) {
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/productos");
            }else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,"no existe un producto asi en la data");
            }
        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"Error, el id es null, manda un parametro url");
        }
    }
}