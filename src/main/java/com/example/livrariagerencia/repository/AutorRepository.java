package com.example.livrariagerencia.repository;

import com.example.livrariagerencia.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
