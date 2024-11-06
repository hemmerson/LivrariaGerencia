package com.example.livrariagerencia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "livros")
@EqualsAndHashCode(of = "id")
@ToString(exclude = "autores")
public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 2, max = 100, message = "O título deve ter entre 2 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String titulo;

    @NotBlank(message = "A editora é obrigatória")
    @Size(min = 2, max = 50, message = "A editora deve ter entre 2 e 50 caracteres")
    @Column(nullable = false, length = 50)
    private String editora;

    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1500, message = "O ano deve ser maior que 1500")
    @Max(value = 2024, message = "O ano não pode ser maior que 2024")
    @Column(nullable = false)
    private Integer ano;

    @NotNull(message = "O número de páginas é obrigatório")
    @Min(value = 1, message = "O livro deve ter pelo menos 1 página")
    @Column(nullable = false)
    private Integer paginas;

    @NotEmpty(message = "O livro deve ter pelo menos um autor")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @PrePersist
    protected void onCreate() {
        if (active == null) {
            active = true;
        }
    }

    // Métodos utilitários
    public void addAutor(Autor autor) {
        this.autores.add(autor);
        autor.getLivros().add(this);
    }

    public void removeAutor(Autor autor) {
        this.autores.remove(autor);
        autor.getLivros().remove(this);
    }
}