
# Springboot API

É um projeto desenvolvido com a Alura, sobre o forum da Alura realizando
manuntenções (CRUD) nos topicos onde irão demonstrar o Usuário e o Curso

Fase de teste.





## Stack utilizada

**Front-end:** HTML Puro 

**Back-end:** Java, Springboot, Spring Data JPA, JPQL, 
H2 (SGDB em Memória)




## Funcionalidades

- Cadastrar Topicos
- Alterar Topicos
- Excluir Topicos
- Listar Topicos


## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/AndrewAscar742/Springboot-API.git
```

Entre no Eclipse e importe o projeto

```bash
  O projeto deve estar no seu workspace
```

Espere o download do Projeto

```bash
  O projeto está baixando as dependências e construindo
```

Inicie a API

```bash
  Rode seu programa, usando o atalho Ctrl + F11
```


## Documentação da API

#### Retorna todos os itens

```http
  GET /topicos
    Parâmetro é opcional
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `null` | `List<TopicoDto>` | Retorna todos os topicos|
| `?nomeCurso` | `List<TopicoDto>` | Retorna os topicos em que o nome do curso esteja presente|

#### Retorna os itens de acordo com a filtração

```http
  GET /topicos/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `nomeCurso`      | `List<DetalhesTopicoDto>` | **Obrigatório**. O nome do curso que você quer |

#### Cadastra um novo Topico

```http
  POST
    Header: Content-Type: JSON
      Body: titulo, mensagem e nomeCurso
        titulo & mensagem: length = min(5);
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `null` | `TopicoForm` | **Obrigatório**. titulo, mensagem e nomeCurso não devem ser nulos|

#### Alterar um Topico

```http
  PUT
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `{id}` | `AtualizarTopicoForm` | **Obrigatório**. deve passar o ID no endpoint|


#### Excluir um Topico

```http
  DELETE
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `{id}` | `Long` | **Obrigatório**. deve passar o ID no endpoint|



## Autores

- [AndrewAscar742(EU)](https://www.github.com/AndrewAscar742)


## Feedback

Se você tiver algum feedback, por favor nos deixe saber por meio de andrewmatosascar@outlook.com

