package serviceImpl;

import io.restassured.response.Response;
import serviceEndpoint.authJWTEndpoints;
import serviceEndpoint.createProducts;
import stepDefinitions.AbstractDefinition;
import stepDefinitions.utilis;

import java.io.IOException;

import static io.restassured.RestAssured.given;


    public class createProductImpl extends AbstractDefinition implements createProducts {
        @Override
        public Response createUserProduct(String createUserRequestBody,String  token) throws IOException {

            createProductResponse =given(utilis.ecommerceRequestSpecification())
                    .header("Authorization","Bearer "+token)
                    .body(createUserRequestBody)
                    .when().log().all()
                    .post("/products")
                    .then()
                    .log().all()
                    .extract()
                    .response();

            return createProductResponse;
        }

    }
