package com.Gabriel.biblioteca.controladores;

import com.Gabriel.biblioteca.MiException.MiException;
import com.Gabriel.biblioteca.servicios.AutorService;
import com.Gabriel.biblioteca.servicios.EditorialService;
import com.Gabriel.biblioteca.servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private AutorService autorService;
    @Autowired
    private EditorialService editorialService;
    @Autowired
    private LibroService libroService;

    @GetMapping("/registrar")
    public String registrar(){
        return "formLibro.html";
    }


    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
                           @RequestParam(required = false) Integer ejemplares,
                           @RequestParam String idAutor,
                           @RequestParam String idEditorial, ModelMap modelo)  {

        try {
            libroService.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "El libro se cargo correctamente!" );
        } catch (MiException e) {
           modelo.put("error", e.getMessage());
            return "formLibro.html";
        }

        return "index.html";

    }



}
