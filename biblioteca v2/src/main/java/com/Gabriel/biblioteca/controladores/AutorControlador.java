package com.Gabriel.biblioteca.controladores;

import com.Gabriel.biblioteca.MiException.MiException;
import com.Gabriel.biblioteca.entidades.Autor;
import com.Gabriel.biblioteca.servicios.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

  @GetMapping("/lista")
    public  String listar(ModelMap modelo){

      List<Autor> autores = autorService.listaAutores();
      modelo.addAttribute("autores",autores);
      return "autorList.html";
  }

  @GetMapping("/modificar/{id}")
public String modificar(@PathVariable String id, ModelMap modelo){
modelo.put("autor", autorService.getOne(id));
return "autorModificar.html";
  }


  @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo){
      try {
          autorService.modificarAutor(nombre,id);
          return "redirect:../lista";
      } catch (MiException e) {
         modelo.put("error", e.getMessage());
         return "autorModificar.html";
      }
  }

}
