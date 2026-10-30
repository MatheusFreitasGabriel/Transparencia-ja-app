# Testes automatizados — Transparência Já

## O que foi incluído

- Quatro cenários BDD em Gherkin em `app/src/test/resources/features/cadastro_usuario.feature`.
- Step definitions Cucumber/Kotlin em `app/src/test/java/br/com/fiap/transparenciaja/bdd/`.
- Testes unitários JUnit para cadastro válido, campos obrigatórios, e-mail duplicado e login inválido.
- Workflow GitHub Actions em `.github/workflows/android-tests.yml`.

## Executar localmente

Requisitos: Android Studio/Android SDK instalado e JDK 17 configurado.

No Windows (PowerShell):

```powershell
.\gradlew.bat :app:testDebugUnitTest
```

No Linux/macOS:

```bash
./gradlew :app:testDebugUnitTest
```

O relatório Cucumber é gerado em `app/build/reports/cucumber/cucumber.html` quando a execução BDD termina.

## Limitação importante sobre APIs e JSON Schema

O código recebido é um aplicativo Android Kotlin/Jetpack Compose. O `AuthRepository` valida cadastro/login em memória; não foram identificados controllers HTTP, endpoints REST nem respostas JSON de um backend neste repositório. Portanto, não seria correto afirmar que já foram implementados testes HTTP para todas as APIs ou contratos JSON Schema.

Para cumprir integralmente a parte da atividade que exige testes de todas as APIs, status HTTP, corpo JSON e JSON Schema, é necessário localizar o backend usado pelo projeto anterior ou confirmar com o professor que é permitido acrescentar um backend. Não inventamos endpoints no projeto atual.

## GitHub Actions

Após enviar estes arquivos ao GitHub, acesse a aba **Actions** para acompanhar a execução do workflow em cada push ou pull request.

## Observação

Os testes e o workflow precisam ser executados no ambiente com Java/Android SDK para confirmar compatibilidade. A inclusão dos arquivos não significa, por si só, que a execução já passou.
