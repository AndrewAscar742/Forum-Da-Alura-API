
# Springboot API

É um projeto desenvolvido com a Alura, sobre o forum da Alura realizando
manuntenções (CRUD) nos topicos onde irão demonstrar o Usuário e o Curso

Fase de teste.





## Stack utilizada

**Front-end:** HTML Puro 

**Back-end:** Java, Springboot, Spring Data JPA, JPQL




## Funcionalidades

- Cadastrar Topicos
- Alterar Topicos
- Excluir Topicos
- Listar Topicos


## Documentação da API

#### Retorna todos os itens

```http
  GET /topicos
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `null` | `List<Topico>` | Retorna os dados do SGDB
 |

#### Retorna os itens de acordo com a filtração

```http
  GET /topicos?nomeCurso=
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `nomeCurso`      | `List<Topico>` | **Obrigatório**. O nome do curso que você quer |



