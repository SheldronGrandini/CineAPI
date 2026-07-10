# 🎬 CineAPI

Uma API REST completa para gerenciamento de um catálogo de filmes e seus respectivos diretores. Desenvolvida como projeto acadêmico utilizando o ecossistema Spring Boot e persistência em banco de dados relacional.

## 🚀 Tecnologias Utilizadas

* **Java 17** ou superior
* **Spring Boot 3.3.4**
* **Spring Data JPA** (Persistência de dados)
* **PostgreSQL** (Banco de dados relacional)
* **Lombok** (Produtividade e redução de boilerplate)
* **Springdoc OpenAPI / Swagger** (Documentação automatizada da API)
* **Maven** (Gerenciador de dependências)

---

## 🏗️ Arquitetura do Banco de Dados

O projeto conta com um relacionamento bidirecional entre as entidades, além de uma tabela auxiliar gerenciada para os atributos multivalorados:

* **`director`**: Armazena os dados dos diretores.
* **`movie`**: Armazena os filmes e possui uma chave estrangeira (`director_id`) apontando para o seu diretor.
* **`movie_genres`**: Tabela auxiliar (`@ElementCollection`) que armazena os múltiplos gêneros de um filme.

---

## 🛣️ Rotas da API (Endpoints)

A API possui documentação automatizada via Swagger. Com a aplicação rodando, você pode acessar a especificação completa através do link:
> `http://localhost:8080/v3/api-docs`

### 🎬 Filmes (`/filmes`)
* `GET /filmes` - Lista todos os filmes com seus respectivos diretores aninhados.
* `POST /filmes` - Cadastra um novo filme (Requer o ID de um diretor válido).
* `PUT /filmes/{id}` - Atualiza os dados de um filme.
* `DELETE /filmes/{id}` - Remove um filme do catálogo.

### 👔 Diretores (`/diretores`)
* `GET /diretores` - Lista todos os diretores.
* `POST /diretores` - Cadastra um novo diretor.

---

## 🛠️ Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone [https://github.com/SEU_USUARIO/cineapi.git](https://github.com/SEU_USUARIO/cineapi.git)