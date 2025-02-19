package org.iesvdm.videoclub.service;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> all() {
        return this.categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria) {
        categoria.setUltimaActualizacion(new Date());
        return this.categoriaRepository.save(categoria);
    }

    public Categoria one(Long id) {
        return this.categoriaRepository.findById(id)
                .orElse(null);
    }

    public Categoria replace(Long id, Categoria categoria) {
    return this.categoriaRepository.findById(id).map(existingCategoria -> {
        existingCategoria.setNombre(categoria.getNombre());
        existingCategoria.setUltimaActualizacion(new Date());
        return this.categoriaRepository.save(existingCategoria);
    }).orElse(null);
}

    public void delete(Long id) {
        this.categoriaRepository.findById(id).map(p -> {this.categoriaRepository.delete(p);
                    return p;})
                .orElse(null);
    }
}
