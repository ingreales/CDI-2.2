package org.jan.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jan.apiservlet.webapp.headers.mapping.ProductoMapper;
import org.jan.apiservlet.webapp.headers.mapping.dto.ProductoDto;
import org.jan.apiservlet.webapp.headers.models.Categoria;
import org.jan.apiservlet.webapp.headers.models.Producto;
import org.jan.apiservlet.webapp.headers.services.ProductoService;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFromServlet extends HttpServlet {
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
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        if (id > 0) {
            Optional<ProductoDto> o = service.porId(id);
            if (o.isPresent()) {
                producto = ProductoMapper.mapFromDto(o.get());
            }
        }
        req.setAttribute("categories", service.listarCategoria());
        req.setAttribute("product", ProductoMapper.mapFrom(producto));
        req.setAttribute("title", req.getAttribute("title") + ": gestion de productos");
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");

        Integer precio;
        try {
            precio = Integer.valueOf(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio = 0;
        }
        String sku = req.getParameter("sku");
        String fechaStr = req.getParameter("fecha_del_registro");
        Long categoriaId;
        try {
            categoriaId = Long.valueOf(req.getParameter("categories"));
        } catch (NumberFormatException e) {
            categoriaId = 0L;
        }
        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es requerido");
        }
        if (sku == null || sku.isBlank()) {
            errores.put("sku", "El sku es nesecitado");
        } else if (sku.length() < 4) {
            errores.put("sku", "El sku debe tener maximo 5 caracteres: 000-0");
        }
        if (fechaStr == null || fechaStr.isBlank()) {
            errores.put("fecha_registro", "La fecha es nesecitada");
        }
        if (precio.equals(0)) {
            errores.put("precio", "El precio es nesesario");
        }
        if (categoriaId <= 0) {
            errores.put("categoria", "La categories es nesesario");
        }

        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            fecha = null;
        }
        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setSku(sku);
        producto.setPrecio(precio);
        producto.setFechaRegistro(fecha);

        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        producto.setCategoria(categoria);

        if (errores.isEmpty()) {
            service.guardar(ProductoMapper.mapFrom(producto));
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", service.listarCategoria());
            req.setAttribute("producto", ProductoMapper.mapFrom(producto));
            req.setAttribute("title", req.getAttribute("title") + ": Listado de product");

            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);

        }
    }
}