package stepDefinitions;

import io.cucumber.java.en.Given;
import serviceImpl.authJWTEndpointsImpl;

import java.io.IOException;

import static stepDefinitions.AbstractDefinition.Tokenresponse;

public class authJWTStepDefinition {
    authJWTEndpointsImpl authJWTEndpointsimpl = new authJWTEndpointsImpl();
    String authRequestBody;
    @Given("client token is generated with {string}")
    public void clientTokenIsGenerated(String scenario) throws IOException {
        authRequestBody =utilis.parseJsonFile("src/test/resources/test.json").getJSONObject(scenario).toString();
        authJWTEndpointsimpl.authenticateUser(authRequestBody);
        System.out.println("Generated Token is : " +Tokenresponse.jsonPath().getString("access_token"));
    }



}
