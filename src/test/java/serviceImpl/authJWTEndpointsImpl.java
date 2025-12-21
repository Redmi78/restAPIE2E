package serviceImpl;

import io.restassured.response.Response;
import serviceEndpoint.authJWTEndpoints;
import stepDefinitions.AbstractDefinition;
import stepDefinitions.utilis;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class authJWTEndpointsImpl extends AbstractDefinition implements authJWTEndpoints   {
    @Override
    public Response authenticateUser(String authRequestBody) throws IOException {

        Tokenresponse =given(utilis.ecommerceRequestSpecification())
                .body(authRequestBody)
                .when().log().all()
                .post("/auth/login")
                .then()
                .log().all()
                .extract()
                .response();

        return Tokenresponse;
    }

    @Override
    public Response authenticateFakeStoreUser(String fakeStoreAuthRequestBody) throws IOException {

        fakeStoreAuthResponse =given(utilis.fakeStoreRequestSpecification())
                .body(fakeStoreAuthRequestBody)
                .when().log().all()
                .post("/auth/login")
                .then()
                .log().all()
                .extract()
                .response();

        return fakeStoreAuthResponse;
    }

    /*@Override
    public Response authenticateUser2(Map<String,String> username, String token) throws IOException {

        response =given(utilis.ecommerceRequestSpecification()).header("Authorization","Bearer "+token)
                .queryParam(username)
                .when()
                .get("/Account/v1/GenerateToken")
                .then()
                .log().all()
                .extract()
                .response();

        return response;
    }*/
}
