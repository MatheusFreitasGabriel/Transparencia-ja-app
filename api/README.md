# API REST complementar — Transparência Já

O projeto original é um aplicativo Android em Kotlin/Jetpack Compose. Para exercitar testes de API sem substituir nem remover o aplicativo existente, esta pasta adiciona uma API REST demonstrativa de iniciativas ESG.

## Requisitos

- JDK 17.
- Conexão com a internet na primeira execução para baixar a distribuição Gradle e as dependências.

## Executar os testes no Windows

Na pasta raiz `Transparencia-ja-app-main`:

```powershell
.\gradlew.bat -p api clean test
```

No Linux/macOS:

```bash
./gradlew -p api clean test
```

Relatório HTML dos testes Java: `api/build/reports/tests/test/index.html`. Relatório BDD: `api/build/reports/cucumber/cucumber.html`.

## Iniciar a API

```powershell
.\gradlew.bat -p api bootRun
```

Base URL: `http://localhost:8080`.

## Endpoints

| Método | Endpoint | Resultado esperado |
|---|---|---|
| GET | `/api/iniciativas` | `200 OK` e lista JSON |
| GET | `/api/iniciativas/{id}` | `200 OK` ou `404 Not Found` |
| POST | `/api/iniciativas` | `201 Created` ou `400 Bad Request` |
| PUT | `/api/iniciativas/{id}` | `200 OK`, `400 Bad Request` ou `404 Not Found` |
| DELETE | `/api/iniciativas/{id}` | `204 No Content` ou `404 Not Found` |

Exemplo de corpo JSON válido:

```json
{
  "nome": "Coleta seletiva",
  "descricao": "Ampliação da coleta seletiva no bairro",
  "pilarEsg": "Ambiental",
  "quantidadeRecicladaKg": 12.5
}
```

## BDD com Gherkin (requisito da atividade)

Foram incluídos três cenários em `src/test/resources/features/iniciativas_esg.feature`:

1. **Positivo:** criação de iniciativa válida (HTTP 201 e conferência do JSON).
2. **Negativo:** tentativa de criação sem nome (HTTP 400 e mensagem de validação).
3. **Negativo:** consulta de iniciativa inexistente (HTTP 404 e mensagem de erro).

Os cenários são executados pelo Cucumber/JUnit Platform junto com a suíte de testes Java. Relatório Cucumber: `api/build/reports/cucumber/cucumber.html`.

## Estratégia de testes

A suíte `IniciativaApiTest` usa JUnit 5 e RestAssured e verifica status HTTP, campos e conteúdo JSON e contratos JSON Schema. Os cenários cobrem listagem, criação válida, validação de entrada inválida, consulta de registro existente, rota/registro inexistente, atualização e exclusão.

Os schemas ficam em `src/test/resources/schemas/`:

- `lista-iniciativas.schema.json`: contrato de uma lista de iniciativas.
- `iniciativa.schema.json`: campos obrigatórios, tipos, valores aceitos para o pilar ESG e restrições numéricas.
- `erro.schema.json`: formato padronizado de erro.

## Limitações

A API é complementar e usa armazenamento em memória; os dados são perdidos ao reiniciar o processo. Ela não está conectada ao banco Oracle nem ao app Android original. O workflow GitHub Actions executa os testes da API e os testes unitários Android em cada `push` e `pull_request`.
