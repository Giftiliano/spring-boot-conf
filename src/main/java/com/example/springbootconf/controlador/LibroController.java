package com.example.springbootconf.controlador;



import com.example.springbootconf.repositories.LibroRepository;
import com.example.springbootconf.models.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    // Obtener todos los libros
    @GetMapping()
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }


    // Crear un libro
    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    // Actualizar un libro
    @PutMapping("/{isbn}")
    public Libro updateLibro(@PathVariable String isbn, @RequestBody Libro libroDetails) {
        return libroRepository.findById(isbn)
                .map(libro -> {
                    libro.setTitulo(libroDetails.getTitulo());
                    libro.setAutor(libroDetails.getAutor());
                    return libroRepository.save(libro);
                })
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ISBN: " + isbn));
    }

    // Eliminar un libro
    @DeleteMapping("/{isbn}")
    public void deleteLibro(@PathVariable String isbn) {
        libroRepository.deleteById(isbn);
    }
}
