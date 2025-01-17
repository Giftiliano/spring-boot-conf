package com.example.springbootconf.repositories;



import com.example.springbootconf.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, String> {
}
