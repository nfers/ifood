package com.github.nfers.ifood.cadastro;

import static io.restassured.RestAssured.given;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;

import static org.hamcrest.CoreMatchers.is;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;



@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(CadastroTestLifecycleManager.class)
public class RestauranteResourceTest {

	@Test
	@DataSet("restaurantes-cenario-1.yml")
	private void testCriar() {
		String resultado = given().when().get("/restaurantes").then().statusCode(200).extract().asString();

		Approvals.verifyJson(resultado);
	}



}
