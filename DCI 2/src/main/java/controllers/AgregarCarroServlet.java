package org.jan.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jan.apiservlet.webapp.headers.mapping.dto.ProductoDto;
import org.jan.apiservlet.webapp.headers.models.Carro;
import org.jan.apiservlet.webapp.headers.models.ItemCarro;
import org.jan.apiservlet.webapp.headers.services.ProductoService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {
    @Inject
    private Carro carro;
    @Inject
    private ProductoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        Optional<ProductoDto> producto = service.porId(id);
        if (producto.isPresent()) {
            ItemCarro item = new ItemCarro(1, producto.get());

            carro.addItemCarro(item);
        }
        resp.sendRedirect(req.getContextPath() + "/ver-carro");
    }
}