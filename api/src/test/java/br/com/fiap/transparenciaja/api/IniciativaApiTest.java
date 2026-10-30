package br.com.fiap.transparenciaja.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IniciativaApiTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void configurarRestAssured() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/iniciativas";
    }

    private String iniciativaValida(String nome) {
        return "{\"nome\":\"" + nome + "\",\"descricao\":\"Ação de sustentabilidade\",\"pilarEsg\":\"Ambiental\",\"quantidadeRecicladaKg\":12.5}";
    }

    @Test
    void listarIniciativasDeveRetornarStatus200EJsonCorreto() {
        given().accept(ContentType.JSON)
            .when().get()
            .then().statusCode(200)
            .contentType(ContentType.JSON)
            .body(matchesJsonSchemaInClasspath("schemas/lista-iniciativas.schema.json"));
    }

    @Test
    void criarIniciativaDeveRetornar201EContratoJsonValido() {
        given().contentType(ContentType.JSON).body(iniciativaValida("Coleta seletiva teste"))
            .when().post()
            .then().statusCode(201)
            .contentType(ContentType.JSON)
            .body(matchesJsonSchemaInClasspath("schemas/iniciativa.schema.json"))
            .body("nome", equalTo("Coleta seletiva teste"))
            .body("pilarEsg", equalTo("Ambiental"));
    }

    @Test
    void criarIniciativaSemNomeDeveRetornar400EContratoDeErro() {
        String json = "{\"nome\":\"\",\"descricao\":\"Descrição válida\",\"pilarEsg\":\"Social\",\"quantidadeRecicladaKg\":0}";
        given().contentType(ContentType.JSON).body(json)
            .when().post()
            .then().statusCode(400)
            .body(matchesJsonSchemaInClasspath("schemas/erro.schema.json"))
            .body("erro", equalTo("O nome é obrigatório"));
    }

    @Test
    void buscarIniciativaExistenteDeveRetornar200EContratoValido() {
        int id = given().contentType(ContentType.JSON).body(iniciativaValida("Busca teste"))
            .when().post().then().statusCode(201).extract().path("id");

        given().when().get("/{id}", id)
            .then().statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/iniciativa.schema.json"))
            .body("id", equalTo(id));
    }

    @Test
    void rotaInexistenteDeveRetornar404EContratoDeErro() {
        given().when().get("/999999")
            .then().statusCode(404)
            .body(matchesJsonSchemaInClasspath("schemas/erro.schema.json"))
            .body("erro", equalTo("Iniciativa não encontrada"));
    }

    @Test
    void atualizarIniciativaDeveRetornar200EJsonAtualizado() {
        int id = given().contentType(ContentType.JSON).body(iniciativaValida("Antes da atualização"))
            .when().post().then().statusCode(201).extract().path("id");

        given().contentType(ContentType.JSON).body(iniciativaValida("Depois da atualização"))
            .when().put("/{id}", id)
            .then().statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/iniciativa.schema.json"))
            .body("nome", equalTo("Depois da atualização"));
    }

    @Test
    void excluirIniciativaDeveRetornar204() {
        int id = given().contentType(ContentType.JSON).body(iniciativaValida("Excluir teste"))
            .when().post().then().statusCode(201).extract().path("id");

        given().when().delete("/{id}", id).then().statusCode(204);
        given().when().get("/{id}", id).then().statusCode(404)
            .body(matchesJsonSchemaInClasspath("schemas/erro.schema.json"));
    }
}
