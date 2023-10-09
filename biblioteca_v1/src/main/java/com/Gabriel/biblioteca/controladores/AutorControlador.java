package com.Gabriel.biblioteca.controladores;

import com.Gabriel.biblioteca.MiException.MiException;
import com.Gabriel.biblioteca.servicios.AutorService;
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
@RequestMapping("/autor")
public class AutorControlador {

    @Autowired
    private AutorService autorService;

    @GetMapping("/registrar")
    public String registrar(){
        return "formAutor.html";
    }

  @PostMapping("/registro")
  public String registro(@RequestParam  String nombre, ModelMap modelo)  {

      try {
          autorService.crearAutor(nombre);
          modelo.put("exito", "El autor se cargo correctamente!" );
      } catch (MiException e) {
          modelo.put("error", e.getMessage());
          return "formAutor.html";
      }

      return "index.html";

  }



}
