package com.Gabriel.biblioteca.controladores;

import com.Gabriel.biblioteca.MiException.MiException;
import com.Gabriel.biblioteca.servicios.EditorialService;
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
@RequestMapping("/editorial")
public class EditorialControlador {
    @Autowired
    private EditorialService editorialService;

    @GetMapping("/registrar")
    public String registrar(){
        return "formEditorial.html";
    }


    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo)  {

        try {
            editorialService.crearEditorial(nombre);
            modelo.put("exito", "La editorial se cargo correctamente!" );
        } catch (MiException e) {
            modelo.put("error", e.getMessage());
            return "formEditoral.html";
        }

        return "index.html";

    }


}
