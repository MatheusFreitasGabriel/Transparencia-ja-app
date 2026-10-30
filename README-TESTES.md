# Atividade — Testes automatizados do Transparência Já

## Escopo entregue

- Mantém o aplicativo Android Kotlin/Jetpack Compose original em `app/`.
- Mantém os testes unitários e os cenários BDD/Cucumber de cadastro de usuário já presentes no aplicativo.
- Adiciona a API REST complementar de iniciativas ESG em `api/`, pois o app original não possui endpoints HTTP próprios.
- Adiciona testes automatizados de API com JUnit 5 e RestAssured.
- Valida contratos JSON usando JSON Schema.
- Adiciona workflow GitHub Actions para executar os testes da API e os testes unitários Android em `push` e `pull_request`.

## Como executar no Windows

Abra o PowerShell na pasta raiz do projeto (a pasta que contém `gradlew.bat`) e execute:

```powershell
.\gradlew.bat :app:testDebugUnitTest
.\gradlew.bat -p api clean test
```

O primeiro comando executa os testes unitários Android, incluindo os testes BDD/Cucumber configurados no app. O segundo executa os testes da API REST e as verificações de contrato JSON Schema.

## Relatórios

- Android: `app/build/reports/tests/testDebugUnitTest/index.html`
- API: `api/build/reports/tests/test/index.html`

Abra esses arquivos no navegador após a execução. O resultado `BUILD SUCCESSFUL` indica que a tarefa terminou sem falha; confira também o número de testes no relatório.

## Escopo e limitações

A API REST é um módulo complementar para demonstrar testes HTTP. Ela usa armazenamento em memória e não está conectada ao banco Oracle nem integrada ao app Android. Os cenários BDD/Cucumber existentes no aplicativo são separados dos testes de API RestAssured. Consulte `api/README.md` para endpoints, exemplos e contratos.
