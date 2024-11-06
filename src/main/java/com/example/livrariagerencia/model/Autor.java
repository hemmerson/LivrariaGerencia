package com.example.livrariagerencia.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "autores")
@EqualsAndHashCode(exclude = "livros")
@ToString(exclude = "livros")
public class Autor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 50)
    private String sobrenome;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.LAZY)
    private List<Livro> livros = new ArrayList<>();
}
