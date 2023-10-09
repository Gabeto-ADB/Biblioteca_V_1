package com.Gabriel.biblioteca.servicios;

import com.Gabriel.biblioteca.MiException.MiException;
import com.Gabriel.biblioteca.entidades.Autor;
import com.Gabriel.biblioteca.entidades.Editorial;
import com.Gabriel.biblioteca.repositorio.EditorialRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EditorialService {
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    @Transactional
    public void crearEditorial(String nombre) throws MiException {
        validar(nombre);

        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);
    }
    public List<Editorial> listaEditoriales(){
        List<Editorial> editoriales = new ArrayList<>();
        editoriales = editorialRepositorio.findAll();
        return editoriales;
    }

    public void modificarEditorial(String nombre, String id) throws MiException {

        validar(nombre);
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()){
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
        }
    }

    private void validar(String nombre) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("La editorialno puede estar vacio ni puede ser nulo");
        }

    }

}
