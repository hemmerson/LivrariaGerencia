# LivrariaGerencia

### Descrição
LivrariaGerencia é um sistema de gerenciamento para uma livraria, desenvolvido com Spring Boot, que permite realizar operações básicas de CRUD (Create, Read, Update e Delete) para gerenciar livros e autores em uma livraria. Este projeto foi criado para facilitar o cadastro e gerenciamento de informações de livros e seus respectivos autores, utilizando tecnologias como Spring Data JPA, Thymeleaf e um banco de dados em memória H2.

### Tecnologias
Este projeto utiliza as seguintes tecnologias:
- Java 21
- Spring Boot 3.3.5
- Spring Data JPA
- Spring Boot Validation
- Thymeleaf
- H2 Database (Runtime)
- Lombok (para reduzir o código boilerplate)
- Spring Boot DevTools (para desenvolvimento)

### Requisitos de Sistema
- Java 21 ou superior
- Maven 3.8 ou superior

### Configuração
1. **Clonar o repositório:**
   ```bash
   git clone git@github.com:hemmerson/LivrariaGerencia.git
   cd LivrariaGerencia
   ```
2. **Compilar e rodar o projeto:**
- Para compilar o projeto, execute o comando:
   ```bash
   mvn clean install
   ```
- Para iniciar o projeto localmente:
   ```bash
   mvn spring-boot:run
   ```
3. **Acessar o sistema:**

Com o projeto em execução, o sistema estará disponível em http://localhost:8080.

### Dependências

Abaixo estão as principais dependências utilizadas:
- spring-boot-starter-data-jpa: Facilita a integração com bancos de dados usando JPA.
- spring-boot-starter-validation: Validação de dados.
- spring-boot-starter-thymeleaf: Permite a renderização de páginas usando Thymeleaf.
- spring-boot-starter-web: Configurações básicas para aplicações web RESTful.
- spring-boot-devtools: Ferramentas adicionais para desenvolvimento, como reinicialização automática.
- h2: Banco de dados em memória H2.
- lombok: Reduz o código boilerplate com anotações.
- spring-boot-starter-test: Dependência de testes para JUnit e outras ferramentas.

### Licença
Este projeto é licenciado sob a Licença MIT.
