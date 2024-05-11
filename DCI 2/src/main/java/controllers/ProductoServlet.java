package org.jan.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jan.apiservlet.webapp.headers.mapping.dto.ProductoDto;
import org.jan.apiservlet.webapp.headers.services.LoginService;
import org.jan.apiservlet.webapp.headers.services.LoginServiceSessionImpl;
import org.jan.apiservlet.webapp.headers.services.ProductoService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    @Inject
    private  ProductoService service;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<ProductoDto> productos = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        req.setAttribute("productos",productos);
        req.setAttribute("usurios",usernameOptional);
        req.setAttribute("titulo",req.getAttribute("titulo") + ": Listado de productos");
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req,resp);
    }
}