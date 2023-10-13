package com.Gabriel.biblioteca.servicios;

import com.Gabriel.biblioteca.MiException.MiException;
import com.Gabriel.biblioteca.entidades.Autor;
import com.Gabriel.biblioteca.entidades.Editorial;
import com.Gabriel.biblioteca.repositorio.AutorRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
   AutorRepositorio autorRepositorio;
    @Transactional
    public void crearAutor(String nombre) throws MiException {

        validar(nombre);

        Autor autor = new Autor();

        autor.setNombre(nombre);

        autorRepositorio.save(autor);

    }
    public List<Autor> listaAutores(){
        List<Autor> autores = new ArrayList<>();
        autores = autorRepositorio.findAll();
        return autores;
    }

    @Transactional
    public void modificarAutor(String nombre, String id) throws MiException {
        validar(nombre);
        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()){
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }
    }
    public Autor getOne(String id){
        return autorRepositorio.getOne(id);
    }


    private void validar(String nombre) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede estar vacio ni puede ser nulo");
        }

    }
}
