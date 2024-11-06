package com.example.livrariagerencia.repository;

import com.example.livrariagerencia.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
