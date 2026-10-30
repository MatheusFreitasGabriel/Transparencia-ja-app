package br.com.fiap.transparenciaja.api.bdd;

import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import static io.restassured.RestAssured.given;

public class IniciativaSteps {
    @Autowired
    private Environment environment;

    private String corpo;
    private int status;
    private String resposta;

    @Before
    public void configurarApi() {
        RestAssured.port = Integer.parseInt(environment.getProperty("local.server.port", "8080"));
        RestAssured.basePath = "/api/iniciativas";
    }

    @Dado("que informo uma iniciativa ESG válida chamada {string}")
    public void iniciativaValida(String nome) {
        corpo = "{\"nome\":\"" + nome + "\",\"descricao\":\"Ação de sustentabilidade\",\"pilarEsg\":\"Ambiental\",\"quantidadeRecicladaKg\":12.5}";
    }

    @Dado("que informo uma iniciativa ESG sem nome")
    public void iniciativaSemNome() {
        corpo = "{\"nome\":\"\",\"descricao\":\"Descrição válida\",\"pilarEsg\":\"Social\",\"quantidadeRecicladaKg\":0}";
    }

    @Quando("envio a solicitação para criar a iniciativa")
    public void criarIniciativa() {
        var response = given().contentType(ContentType.JSON).body(corpo).when().post();
        status = response.statusCode();
        resposta = response.asString();
    }

    @Quando("consulto uma iniciativa com identificador inexistente")
    public void consultarInexistente() {
        var response = given().when().get("/99999999");
        status = response.statusCode();
        resposta = response.asString();
    }

    @Então("a API deve responder com status {int}")
    public void verificarStatus(int esperado) {
        org.junit.jupiter.api.Assertions.assertEquals(esperado, status, "Status HTTP inesperado. Resposta: " + resposta);
    }

    @Então("a resposta deve conter o nome {string} e o pilar {string}")
    public void verificarCorpoSucesso(String nome, String pilar) {
        org.junit.jupiter.api.Assertions.assertEquals(nome, io.restassured.path.json.JsonPath.from(resposta).getString("nome"));
        org.junit.jupiter.api.Assertions.assertEquals(pilar, io.restassured.path.json.JsonPath.from(resposta).getString("pilarEsg"));
    }

    @Então("a resposta deve informar que o nome é obrigatório")
    public void verificarErroNome() {
        org.junit.jupiter.api.Assertions.assertTrue(resposta.contains("O nome é obrigatório"), "Mensagem de validação ausente: " + resposta);
    }

    @Então("a resposta deve informar que a iniciativa não foi encontrada")
    public void verificarNaoEncontrada() {
        org.junit.jupiter.api.Assertions.assertTrue(resposta.contains("Iniciativa não encontrada"), "Mensagem de erro ausente: " + resposta);
    }
}