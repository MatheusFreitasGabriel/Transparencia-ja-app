# language: pt
Funcionalidade: API de iniciativas ESG
  Para apoiar a transparência de ações sustentáveis
  Como consumidor da API
  Quero validar os comportamentos principais da gestão de iniciativas

  Cenário: Criar iniciativa ESG válida
    Dado que informo uma iniciativa ESG válida chamada "Horta comunitária BDD"
    Quando envio a solicitação para criar a iniciativa
    Então a API deve responder com status 201
    E a resposta deve conter o nome "Horta comunitária BDD" e o pilar "Ambiental"

  Cenário: Rejeitar iniciativa sem nome
    Dado que informo uma iniciativa ESG sem nome
    Quando envio a solicitação para criar a iniciativa
    Então a API deve responder com status 400
    E a resposta deve informar que o nome é obrigatório

  Cenário: Consultar iniciativa inexistente
    Quando consulto uma iniciativa com identificador inexistente
    Então a API deve responder com status 404
    E a resposta deve informar que a iniciativa não foi encontrada
