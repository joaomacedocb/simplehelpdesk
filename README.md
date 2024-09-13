# Simple Helpdesk API

A **Simple Helpdesk API** é uma API RESTful para o gerenciamento de técnicos, clientes e chamados. Com ela, é possível realizar o cadastro, gerenciamento e consulta de técnicos e clientes, além de registrar e acompanhar chamados que relacionam essas entidades. A API foi projetada para ser eficiente e segura, utilizando autenticação baseada em tokens JWT e permitindo uma fácil integração com sistemas externos.

## :rocket: Tecnologias Utilizadas

- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-brightgreen?style=flat-square&logo=spring)  
  *Framework Java para criar aplicações standalone e web, com mínima configuração.*

- ![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-3.1.0-brightgreen?style=flat-square&logo=hibernate)  
  *Abstração para persistência de dados, facilitando a integração com bancos de dados.*

- ![Spring Security](https://img.shields.io/badge/Spring%20Security-3.1.0-brightgreen?style=flat-square&logo=spring)  
  *Framework que provê segurança para a aplicação, com autenticação e controle de acesso.*

- ![OAuth2](https://img.shields.io/badge/OAuth2-JWT-brightgreen?style=flat-square&logo=oauth)  
  *Utiliza OAuth2 e JWT para autenticação segura e autorização baseada em tokens.*

- ![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql)  
  *Banco de dados relacional utilizado para armazenamento persistente.*

- ![H2 Database](https://img.shields.io/badge/H2%20Database-in%20Memory-blue?style=flat-square&logo=h2)  
  *Banco de dados em memória usado para desenvolvimento e testes.*

## :link: Endpoints Disponíveis

Aqui estão os principais endpoints da API:

- **Autenticação**:  
  `POST /authenticate`  
  Endpoint responsável por autenticar os usuários. O formato da requisição é o seguinte:
  
  ```json
  {
    "email": "joao@mail.com",
    "senha": "1234"
  }

  Retorna um token JWT que deverá ser utilizado nas demais requisições.

### Técnicos:
- `GET /tecnicos` - Lista todos os técnicos.
- `POST /tecnicos` - Cria um novo técnico.
- `GET /tecnicos/{id}` - Retorna um técnico específico.
- `PUT /tecnicos/{id}` - Atualiza as informações de um técnico.
- `DELETE /tecnicos/{id}` - Remove um técnico (se não houver relação com chamados).

### Clientes:
- `GET /clientes` - Lista todos os clientes.
- `POST /clientes` - Cria um novo cliente.
- `GET /clientes/{id}` - Retorna um cliente específico.
- `PUT /clientes/{id}` - Atualiza as informações de um cliente.
- `DELETE /clientes/{id}` - Remove um cliente (se não houver relação com chamados).

### Chamados:
- `GET /chamados` - Lista todos os chamados.
- `POST /chamados` - Cria um novo chamado.
- `GET /chamados/{id}` - Retorna um chamado específico.
- `PUT /chamados/{id}` - Atualiza o status ou informações de um chamado.
  
  ⚠️ **Nota**: Chamados não podem ser excluídos após a criação.

---

## :lock: Segurança

A única rota liberada sem autenticação é a `/authenticate`. Todas as outras rotas requerem o envio de um token JWT no cabeçalho da requisição:

Authorization: Bearer <token>
  
Para a geração e validação do JWT, o sistema utiliza chaves RSA pública e privada que devem estar na pasta `src/main/resources` com os seguintes nomes:

- **app.key**: Chave privada RSA usada para assinar os tokens.
- **app.pub**: Chave pública RSA usada para verificar os tokens.

Siga este [tutorial para geração de chaves RSA](https://example.com).

---

## :busts_in_silhouette: Perfis de Usuário

A API possui três perfis de usuário:

- **CLIENTE**: Acesso restrito a chamados e dados relacionados a si mesmo.
- **TÉCNICO**: Acesso a dados de chamados e gerenciamento de clientes e chamados.
- **ADMIN**: Acesso total ao sistema, incluindo gerenciamento de técnicos e clientes.

---

## :clipboard: Status e Prioridades dos Chamados

Os chamados criados na API podem ter os seguintes **status**:

- **ABERTO**: Chamado recém-criado, aguardando resolução.
- **EM ANDAMENTO**: Chamado em processo de resolução.
- **ENCERRADO**: Chamado finalizado.

As **prioridades** de cada chamado podem ser definidas como:

- **BAIXA**
- **MÉDIA**
- **ALTA**
