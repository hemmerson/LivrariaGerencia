-- Inserindo autores
INSERT INTO autores (id, nome, sobrenome) VALUES (1,'João', 'Silva');
INSERT INTO autores (id, nome, sobrenome) VALUES (2,'Maria', 'Pereira');
INSERT INTO autores (id, nome, sobrenome) VALUES (3,'Carlos', 'Oliveira');

-- Inserindo livros
INSERT INTO livros (id, titulo, editora, ano, paginas, active, created_at, updated_at) VALUES (1,'Java para Iniciantes', 'Editora A', 2020, 300, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO livros (id, titulo, editora, ano, paginas, active, created_at, updated_at) VALUES (2,'Spring Boot Avançado', 'Editora B', 2021, 250, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO livros (id, titulo, editora, ano, paginas, active, created_at, updated_at) VALUES (3,'Hibernate com JPA', 'Editora C', 2019, 200, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Relacionando livros com autores na tabela intermediária
INSERT INTO livro_autor (livro_id, autor_id) VALUES (1, 1);  -- Java para Iniciantes por João Silva
INSERT INTO livro_autor (livro_id, autor_id) VALUES (2, 2);  -- Spring Boot Avançado por Maria Pereira
INSERT INTO livro_autor (livro_id, autor_id) VALUES (3, 3);  -- Hibernate com JPA por Carlos Oliveira
INSERT INTO livro_autor (livro_id, autor_id) VALUES (2, 1);  -- Spring Boot Avançado por João Silva
