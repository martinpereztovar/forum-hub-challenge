# 💬 ForumHub – API REST para Fórum de Discussões - Challenge One G9

Status do Projeto: ✔️ Concluído

## 📚 Tópicos

🔹 [Descrição do projeto](#descrição-do-projeto)  
🔹 [Funcionalidades](#funcionalidades)  
🔹 [Layout da Aplicação](#layout-da-aplicação-)  
🔹 [Pré-requisitos](#pré-requisitos)  
🔹 [Como rodar a aplicação](#como-rodar-a-aplicação-️)  
🔹 [Casos de Uso](#casos-de-uso)  
🔹 [Linguagens e tecnologias utilizadas](#linguagens-e-tecnologias-utilizadas-)  
🔹 [Estrutura do projeto](#estrutura-do-projeto-)  
🔹 [Modelo de Dados](#modelo-de-dados-)  
🔹 [Endpoints da API](#endpoints-da-api-)  
🔹 [Melhorias futuras](#melhorias-futuras)  
🔹 [Desenvolvedores](#desenvolvedorescontribuintes)

---

## Descrição do projeto

O **ForumHub** é uma API REST desenvolvida em Java com Spring Boot que replica o funcionamento de um fórum de discussões no nível back-end. A aplicação permite que usuários criem tópicos, consultem dúvidas, atualizem informações e gerenciem discussões sobre cursos.

Um fórum é um espaço colaborativo onde participantes de uma plataforma podem compartilhar perguntas, respostas e conhecimento sobre determinados assuntos. Esta API foi desenvolvida para gerenciar toda a lógica por trás dos bastidores: armazenamento de informações, relacionamento entre tópicos e usuários, autenticação segura e controle de acesso.

O foco do projeto é criar uma **API REST completa** seguindo as melhores práticas, permitindo:

- **CRUD completo** de tópicos (Create, Read, Update, Delete)
- **Autenticação e autorização** com Spring Security e JWT
- **Persistência de dados** em banco relacional MySQL
- **Validações** de regras de negócio
- **Paginação e ordenação** de resultados
- **Relacionamentos complexos** entre entidades (User, Topic, Course, Role)

Este projeto foi desenvolvido como parte do desafio **ForumHub** do programa **ONE G9 (Oracle Next Education)**, com ênfase em:

- Desenvolvimento de APIs REST seguindo padrões RESTful
- Segurança com Spring Security e tokens JWT
- Migrations de banco de dados com Flyway
- Validações com Bean Validation
- Arquitetura em camadas (layered architecture)
- Boas práticas de código e organização

---

## Funcionalidades

✔️ **Autenticação JWT** - Login seguro que gera token de acesso  
✔️ **Criar tópico** - Usuários autenticados podem criar novos tópicos  
✔️ **Listar tópicos** - Consulta paginada com ordenação por data de criação  
✔️ **Detalhar tópico** - Visualizar informações completas de um tópico específico  
✔️ **Atualizar tópico** - Editar título, mensagem e status de tópicos existentes  
✔️ **Deletar tópico** - Remover tópicos do sistema  
✔️ **Validações de negócio** - Campos obrigatórios, tamanhos máximos, etc.  
✔️ **Controle de acesso** - Rotas protegidas por autenticação  
✔️ **Relacionamentos ManyToOne** - Tópicos vinculados a autores e cursos  
✔️ **Sistema de Roles** - Controle de permissões por papéis de usuário  
✔️ **Paginação automática** - Resultados paginados (10 por página)  
✔️ **Migrations com Flyway** - Versionamento e controle do esquema do banco  
✔️ **Status de tópicos** - OPEN (aberto) ou CLOSED (fechado)  
✔️ **Respostas HTTP adequadas** - Status codes corretos (201, 204, 404, etc.)

---

## Layout da Aplicação 💨

Esta é uma **API REST** (back-end puro), sem interface gráfica. A comunicação é feita via requisições HTTP.

### Exemplos de requisições:

**Login (POST /auth/login)**
```json
{
  "email": "user@example.com",
  "password": "senha123"
}
```

**Criar Tópico (POST /topics)**
```json
{
  "title": "Como usar Spring Security?",
  "message": "Estou com dúvidas sobre configuração...",
  "courseId": 1
}
```

**Resposta de Tópico (TopicResponse)**
```json
{
  "id": 1,
  "title": "Como usar Spring Security?",
  "message": "Estou com dúvidas sobre configuração...",
  "createdAt": "2025-02-12T10:30:00",
  "status": "OPEN",
  "authorName": "João Silva",
  "courseName": "Spring Boot Avançado"
}
```

### Screenshots

[<img src="./assets/postman-login.png" width="500"><br><sub>Login - Postman</sub>](./assets/postman-login.png)

<br>

[<img src="./assets/postman-create-topic.png" width="500"><br><sub>Criar Tópico</sub>](./assets/postman-create-topic.png)

<br>

[<img src="./assets/postman-list-topics.png" width="500"><br><sub>Listar Tópicos</sub>](./assets/postman-list-topics.png)

<br>

[<img src="./assets/postman-update-topic.png" width="500"><br><sub>Atualizar Tópico</sub>](./assets/postman-update-topic.png)

---

## Pré-requisitos

✅ **Java 17** ou superior  
✅ **MySQL** instalado e rodando  
✅ **Maven** (ou usar o wrapper incluído no projeto)  
✅ **Postman/Insomnia** (para testar os endpoints)  
✅ **IntelliJ IDEA** ou outra IDE de sua preferência  

---

## Como rodar a aplicação ▶️

### 1. Clone o repositório

```bash
git clone https://github.com/martinpereztovar/forumhub-challenge-one-g9.git
cd forumhub-challenge-one-g9
```

### 2. Configure o banco de dados

Crie um banco de dados MySQL chamado `forumhub`:

```sql
CREATE DATABASE forumhub;
```

### 3. Configure as credenciais

Edite o arquivo `src/main/resources/application.yml` com suas credenciais do MySQL:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/forumhub
    username: seu_usuario
    password: sua_senha
  security:
  jwt:
    secret: sua_chave_secreta_com_pelo_menos_32_caracteres
    expiration-minutes: 60

```

### 4. Execute a aplicação

**Opção A - Via IDE:**
- Abra o projeto no IntelliJ IDEA
- Execute a classe `ForumhubApplication.java`

**Opção B - Via Maven:**
```bash
./mvnw spring-boot:run
```

**Opção C - Gerando JAR:**
```bash
./mvnw clean package
java -jar target/forumhub-0.0.1-SNAPSHOT.jar
```

### 5. Teste os endpoints

O Flyway executará automaticamente as migrations e criará:
- Tabelas do banco (users, topics, courses, roles, user_roles)
- Dados iniciais (usuário admin, roles, cursos de exemplo)

A aplicação estará disponível em: `http://localhost:8080`

---

## Casos de Uso

### 🔐 Autenticar no sistema

1. Enviar requisição **POST** para `/auth/login`
2. Passar email e senha no corpo da requisição
3. Receber token JWT na resposta
4. Usar o token no header `Authorization: Bearer {token}` nas próximas requisições

**Exemplo:**
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@forumhub.dev","password":"Admin123!"}'
```

---

### 📝 Criar um novo tópico

1. Estar autenticado (ter token JWT)
2. Enviar requisição **POST** para `/topics`
3. Passar título, mensagem e ID do curso
4. Tópico é criado com status OPEN e autor automático (usuário logado)

---

### 📋 Listar todos os tópicos

1. Enviar requisição **GET** para `/topics`
2. Opcionalmente passar parâmetros de paginação: `?page=0&size=10&sort=createdAt,desc`
3. Receber lista paginada de tópicos ordenados por data de criação

---

### 🔍 Consultar um tópico específico

1. Enviar requisição **GET** para `/topics/{id}`
2. Substituir `{id}` pelo ID do tópico desejado
3. Receber detalhes completos do tópico (título, mensagem, autor, curso, status, data)

---

### ✏️ Atualizar um tópico

1. Enviar requisição **PUT** para `/topics/{id}`
2. Passar novos valores de título, mensagem e/ou status
3. Tópico é atualizado no banco de dados

---

### 🗑️ Deletar um tópico

1. Enviar requisição **DELETE** para `/topics/{id}`
2. Tópico é removido permanentemente
3. Retorna status HTTP 204 (No Content)

---

## Linguagens e tecnologias utilizadas 📚

- **Java 17**
- **Spring Boot 4.0.2**
  - Spring Data JPA
  - Spring Security
  - Spring Validation
  - Spring Web
- **MySQL** (banco de dados relacional)
- **Flyway** (migrations de banco de dados)
- **JWT (JSON Web Token)** - Autenticação stateless
- **Lombok** (redução de boilerplate)
- **Maven** (gerenciamento de dependências)
- **Hibernate** (ORM)

---

## Estrutura do Projeto 🧱

```
src/main/java/com/forumhub/forumhub/
├── ForumhubApplication.java           # Classe principal
├── config/
│   ├── PasswordHashPrinter.java       # Utilitário para gerar hash de senhas
│   └── SecurityConfig.java            # Configuração Spring Security
├── controller/
│   ├── ApiExceptionHandler.java       # Tratamento global de exceções
│   ├── AuthController.java            # Endpoints de autenticação
│   └── TopicController.java           # Endpoints CRUD de tópicos
├── domain/
│   ├── Course.java                    # Entidade JPA - Curso
│   ├── Role.java                      # Entidade JPA - Papel/Permissão
│   ├── Topic.java                     # Entidade JPA - Tópico
│   ├── TopicStatus.java               # Enum - Status do tópico
│   └── User.java                      # Entidade JPA - Usuário
├── dto/
│   ├── CreateTopicRequest.java        # DTO para criar tópico
│   ├── LoginRequest.java              # DTO para login
│   ├── TokenResponse.java             # DTO para resposta com token
│   ├── TopicResponse.java             # DTO para resposta de tópico
│   └── UpdateTopicRequest.java        # DTO para atualizar tópico
├── exception/
│   └── TopicNotFoundException.java    # Exceção customizada
├── repository/
│   ├── CourseRepository.java          # Repository JPA - Curso
│   ├── TopicRepository.java           # Repository JPA - Tópico
│   └── UserRepository.java            # Repository JPA - Usuário
├── security/
│   ├── JwtAuthFilter.java             # Filtro JWT
│   ├── TokenService.java              # Serviço geração/validação tokens
│   └── UserDetailsServiceImpl.java    # Implementação UserDetailsService
└── service/
    └── TopicService.java              # Serviço de lógica de negócio

src/main/resources/
├── db/migration/
│   ├── V1__create_core_tables.sql     # Migration - Criação de tabelas
│   ├── V2__seed_initial_data.sql      # Migration - Dados iniciais
│   └── V3__update_admin_password_hash.sql  # Migration - Senha admin
└── application.yml                     # Configurações do Spring
```

### Responsabilidades das Camadas

**⚙️ Config:** Configurações de segurança e utilitários  
**🎮 Controller:** Endpoints REST e tratamento de requisições HTTP  
**🗄️ Domain:** Entidades JPA mapeadas para o banco de dados  
**📦 DTO:** Objetos de transferência de dados (Request/Response)  
**⚠️ Exception:** Exceções customizadas do domínio  
**💾 Repository:** Acesso a dados via Spring Data JPA  
**🔐 Security:** Autenticação, autorização e filtros JWT  
**⚙️ Service:** Lógica de negócio e orquestração  

---

## Modelo de Dados 🗂️

### Entidade: User (Usuário)

```java
- id (Long) - PK, auto-incremento
- name (String) - Nome do usuário
- email (String) - Email único, usado como username
- password (String) - Senha criptografada (BCrypt)
- roles (Set<Role>) - Papéis/permissões (ManyToMany)
```

### Entidade: Topic (Tópico)

```java
- id (Long) - PK, auto-incremento
- title (String) - Título do tópico (max 200 chars)
- message (String) - Mensagem/descrição (TEXT/LOB)
- createdAt (LocalDateTime) - Data de criação
- status (TopicStatus) - OPEN ou CLOSED
- author (User) - Autor do tópico (ManyToOne)
- course (Course) - Curso relacionado (ManyToOne)
```

### Entidade: Course (Curso)

```java
- id (Long) - PK, auto-incremento
- name (String) - Nome do curso
- category (String) - Categoria do curso
```

### Entidade: Role (Papel/Permissão)

```java
- id (Long) - PK, auto-incremento
- name (String) - Nome da role (ex: ROLE_USER, ROLE_ADMIN)
```

### Enum: TopicStatus

```java
- OPEN - Tópico aberto para discussão
- CLOSED - Tópico encerrado
```

### Relacionamentos

```
User ←→ user_roles ←→ Role (ManyToMany)
User ←── Topic (OneToMany - autor)
Course ←── Topic (OneToMany - curso)
```

**Características:**
- Todos os relacionamentos são mapeados com JPA/Hibernate
- Constraint de email único em User
- Relacionamento ManyToMany entre User e Role
- Topics obrigatoriamente vinculados a autor e curso
- Deleção em cascata não configurada (proteção de dados)

---

## Endpoints da API 🚀

### 🔐 Autenticação

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| POST | `/auth/login` | Realizar login e obter token JWT | ❌ Público |

**Request Body (LoginRequest):**
```json
{
  "email": "user@example.com",
  "password": "senha123"
}
```

**Response (TokenResponse):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

### 💬 Tópicos (CRUD Completo)

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| POST | `/topics` | Criar novo tópico | ✅ Requer token |
| GET | `/topics` | Listar todos os tópicos (paginado) | ✅ Requer token |
| GET | `/topics/{id}` | Buscar tópico por ID | ✅ Requer token |
| PUT | `/topics/{id}` | Atualizar tópico existente | ✅ Requer token |
| DELETE | `/topics/{id}` | Deletar tópico | ✅ Requer token |

#### POST /topics

**Request Body (CreateTopicRequest):**
```json
{
  "title": "Como configurar CORS no Spring?",
  "message": "Estou tendo problemas com CORS na minha API...",
  "courseId": 1
}
```

**Response (201 Created):**
```json
{
  "id": 15,
  "title": "Como configurar CORS no Spring?",
  "message": "Estou tendo problemas com CORS na minha API...",
  "createdAt": "2025-02-12T14:30:00",
  "status": "OPEN",
  "authorName": "Maria Santos",
  "courseName": "Spring Boot Avançado"
}
```

#### GET /topics

**Query Parameters:**
- `page` - Número da página (default: 0)
- `size` - Itens por página (default: 10)
- `sort` - Campo de ordenação (default: createdAt)

**Exemplo:** `/topics?page=0&size=5&sort=createdAt,desc`

**Response (200 OK):**
```json
{
  "content": [
    {
      "id": 1,
      "title": "Dúvida sobre JPA",
      "message": "Como fazer um relacionamento ManyToMany?",
      "createdAt": "2025-02-12T10:00:00",
      "status": "OPEN",
      "authorName": "João Silva",
      "courseName": "Spring Data JPA"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10
  },
  "totalElements": 25,
  "totalPages": 3
}
```

#### GET /topics/{id}

**Response (200 OK):**
```json
{
  "id": 1,
  "title": "Dúvida sobre JPA",
  "message": "Como fazer um relacionamento ManyToMany?",
  "createdAt": "2025-02-12T10:00:00",
  "status": "OPEN",
  "authorName": "João Silva",
  "courseName": "Spring Data JPA"
}
```

**Response (404 Not Found):**
```json
{
  "error": "Topic not found with id: 999"
}
```

#### PUT /topics/{id}

**Request Body (UpdateTopicRequest):**
```json
{
  "title": "Dúvida sobre JPA [RESOLVIDO]",
  "message": "Como fazer um relacionamento ManyToMany? Já consegui resolver!",
  "status": "CLOSED"
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "title": "Dúvida sobre JPA [RESOLVIDO]",
  "message": "Como fazer um relacionamento ManyToMany? Já consegui resolver!",
  "createdAt": "2025-02-12T10:00:00",
  "status": "CLOSED",
  "authorName": "João Silva",
  "courseName": "Spring Data JPA"
}
```

#### DELETE /topics/{id}

**Response:** `204 No Content`

---

### 🔑 Autenticação nas Requisições

Todas as rotas de tópicos requerem autenticação via token JWT no header:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**Exemplo com cURL:**
```bash
curl -X GET http://localhost:8080/topics \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

---

## Melhorias Futuras

- 📖 **Documentação Swagger/OpenAPI** - Interface visual para testar endpoints  
- 💬 **CRUD de Respostas** - Permitir usuários responderem tópicos  
- 👥 **CRUD de Usuários** - Endpoints para gerenciamento de usuários  
- 🔍 **Filtros avançados** - Buscar tópicos por curso, status, autor  
- ⭐ **Sistema de votação** - Upvotes/downvotes em tópicos e respostas  
- 🏷️ **Tags/categorias** - Organizar tópicos por tags  
- 🔔 **Notificações** - Alertar usuários sobre respostas em seus tópicos  
- 📊 **Dashboard de estatísticas** - Tópicos mais ativos, usuários mais participativos  
- 🧪 **Testes automatizados** - Testes unitários e de integração (JUnit, MockMvc)  
- 🚀 **Deploy em produção** - Dockerização e deploy em cloud (AWS, Heroku, Railway)  
- 🔐 **OAuth2/Social Login** - Login com Google, GitHub  
- 📧 **Emails de confirmação** - Verificação de email ao criar conta  
- 🌐 **Internacionalização (i18n)** - Suporte multi-idioma  
- 📱 **Rate limiting** - Proteção contra abuso de API  

---

## Desenvolvedores/Contribuintes

| [<img src="./assets/foto-martin.jpg" width=115><br><sub>Martín Pérez Tovar</sub>](https://github.com/martinpereztovar) |
| :----------------------------------------------------------------------------------------------------------------------: |

---

## Licença

The MIT License (MIT)

Copyright ©️ 2025 – ForumHub

---

## Agradecimentos

- **Oracle Next Education (ONE)** e **Alura** pelo desafio
- Comunidade Spring Boot pela excelente documentação
- Todos que contribuíram com feedback e sugestões
