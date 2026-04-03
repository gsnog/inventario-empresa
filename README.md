# 💻 API de Inventário de Equipamentos (Infraestrutura Real)

Esta API foi desenvolvida para o time de TI gerenciar o cadastro e a alocação de equipamentos corporativos (notebooks, monitores, etc.). Este projeto marca a transição do ambiente de testes para uma **infraestrutura de nível de produção**, utilizando contêineres e bancos de dados persistentes.

---

## 🛠️ Tecnologias Utilizadas

* **Java 21+**
* **Spring Boot 3.x**
* **Spring Data JPA**
* **PostgreSQL** (Banco de dados relacional robusto)
* **Docker & Docker Compose** (Para conteinerização e orquestração do banco de dados)
* **Flyway** (Versionamento e Migrations do banco de dados)
* **Swagger / OpenAPI** (Documentação interativa)

---

## 🏗️ Arquitetura e Diferenciais Técnicos

* **Infraestrutura via Código (IaC):** Utilização do `docker-compose.yml` para subir e configurar o servidor PostgreSQL localmente sem poluir o sistema operacional host.
* **Database Migrations:** Substituição da criação automática de tabelas do Hibernate (`ddl-auto=update`) pelo **Flyway**. O esquema do banco é estritamente versionado através de scripts SQL (ex: `V1__create_table_equipamento.sql`), garantindo previsibilidade e segurança em deploy.
* **Integridade de Dados no SGBD:** Implementação da restrição `UNIQUE` diretamente na tabela SQL para a coluna `numero_serie`, delegando a garantia de não-duplicidade para o motor do banco de dados relacional.
* **Boas Práticas de API:** Uso rigoroso de DTOs (`records`) para entrada e saída de dados, Validações do Jakarta Bean (`@NotBlank`), e respostas HTTP padronizadas com tratamento de exceções nos Controllers.

---

## 🚀 Endpoints da API

A documentação interativa (Swagger) pode ser acessada em: `http://localhost:8080/swagger-ui/index.html`

### 💻 Equipamentos
* `POST /equipamentos` - Cadastra um novo equipamento.
* `GET /equipamentos` - Lista todos os equipamentos do inventário.
* `GET /equipamentos/{id}` - Busca os detalhes de um equipamento específico.
* `PUT /equipamentos/{id}` - Atualiza os dados de um equipamento.
* `DELETE /equipamentos/{id}` - Remove um equipamento do sistema (Retorna `204 No Content`).

---

## ⚙️ Como executar localmente

1. Clone o repositório:
   ```bash
   git clone [https://github.com/gsnog/inventario-empresa.git](https://github.com/gsnog/inventario-empresa.git)
   
2. Acesse a pasta do projeto e suba o banco de dados via Docker:

```bash
docker-compose up -d

3. Execute a aplicação Spring Boot:

```bash
./mvnw spring-boot:run
