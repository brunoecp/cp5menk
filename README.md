## Endspoints

- [Cadastro de viagem](#cadastro-de-viagens)
- [Detalhes Viagem](#detalhes-viagem)
- [Apagar viagem](#deletar-viagem)
- [Editar Viagem](#editar-viagem)
- [Mostrar todas viagens](#todas-as-viagens)

### Cadastro de viagens

`POST` rm95315.azurewebsites.net/api/viagem

**Exemplo de Entrada**

````js
{
    "id" : 1,
    "destino": 'florianopolis',
    "agencia": 'Latam',
    "dia_ida": '2023-03-06',
    "dia_volta": '2023-03-13',
    "categoria": 'Negocios',
    "itens":[
        {"nome": "shorts"
        "quantidade": 3},
        {"nome": "camisa"
        "quantidade": 5},
        {"nome": "calça"
        "quantidade": 2}
    ]
}
````

**Campos da Requisição**
| Campo | Obrigatório | Tipo  | Descrição |
|-------|:-------------:|-------|-----------|
|destino  |sim          |texto|O destino deve ser um país valido
|agencia|sim|texto| A agencia de viagens que proporcionará o voou
|dia_ida|sim|data| O dia da ida deve ser menor que o dia da volta
|dia_volta|sim|data| O dia da volta deve ser maior do que o da ida
|categoria|sim|texto| O objetivo da viagem(ex: trabalho, diversão)
|itens|não|objeto| itens cadastrados pelo usuario, que ele levará para a viagem em questão

**Códigos da Resposta**

|código|descrição
|-|-
201 | a viagem foi cadastrada com sucesso
400 | os dados enviados são inválidos

---

### Detalhes viagem

`GET` rm95315.azurewebsites.net/api/viagem/{id}

**Exemplo de Resposta**

````js
{
    "destino": 'florianopolis',
    "agencia": 'Latam',
    "dia_ida": '2023-03-06',
    "dia_volta": '2023-03-13',
    "categoria": 'Negocios',
    "itens":[
        {"nome": "shorts"
        "quantidade": 3},
        {"nome": "camisa"
        "quantidade": 5},
        {"nome": "calça"
        "quantidade": 2}
    ],
    "Condições_Climaticas":{
        "temperatura_atual": 21,
        "temperatura_max":28,
        "temperatura_min": 18,
        "condicao": 'ensolarado'
    }
}
````
|código|descrição
|-|-
200 | A viagem foi retornada com sucesso
404 | Viagem não encontrada

---

### Todas as viagens

`GET` rm95315.azurewebsites.net/api/viagem

**Exemplo de Resposta**

````js
{
    [
    "destino": 'florianopolis',
    "agencia": 'Latam',
    "dia_ida": '2023-03-06',
    "dia_volta": '2023-03-13',
    "categoria": 'Negocios',
    "itens":[
        {"nome": "shorts"
        "quantidade": 3},
        {"nome": "camisa"
        "quantidade": 5},
        {"nome": "calça"
        "quantidade": 2}
    ],
    "Condições_Climaticas":{
        "temperatura_atual": 21,
        "temperatura_max":28,
        "temperatura_min": 18,
        "condicao": 'ensolarado'
        }
    ]
}
````
|código|descrição
|-|-
200 | As viagens foram retornada com sucesso
404 | Viagem não encontrada
---

### Deletar Viagem
`DELETE` rm95315.azurewebsites.net/api/viagem/{id}

|código|descrição
|-|-
200 | A viagem foi deletada com sucesso
204 | A viagem não existe

---

### Editar Viagem
`PUT` rm95315.azurewebsites.net/api/viagem/{id}

**Exemplo de Entrada**

````js
{
    "id" : 1,
    "destino": 'kyoto',
    "agencia": 'avianca',
    "dia_ida": '2023-03-09',
    "dia_volta": '2023-03-18',
    "categoria": 'diversao',
    "itens":[
        {"nome": "shorts"
        "quantidade": 5},
        {"nome": "camisa"
        "quantidade": 10},
        {"nome": "calça"
        "quantidade": 1},
        {"nome": "boia"
        "quantidade": 1}
    ]
}
````

|código|descrição
|-|-
204 | A viagem foi modificada com sucesso
404 | A viagem não existe