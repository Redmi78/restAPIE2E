package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import serviceImpl.authJWTEndpointsImpl;
import serviceImpl.createProductImpl;

import java.io.IOException;

import static stepDefinitions.AbstractDefinition.Tokenresponse;
import static stepDefinitions.AbstractDefinition.createProductResponse;

public class authJWTStepDefinition {
    authJWTEndpointsImpl authJWTEndpointsimpl = new authJWTEndpointsImpl();
    createProductImpl createProductimpl = new createProductImpl();
    String authRequestBody, createProductRequestBody, access_token;

    @Given("client token is generated with {string}")
    public void clientTokenIsGenerated(String scenario) throws IOException {
        authRequestBody = utilis.parseJsonFile("src/test/resources/test.json").getJSONObject(scenario).toString();
        authJWTEndpointsimpl.authenticateUser(authRequestBody);
        System.out.println("Generated Token is : " + Tokenresponse.jsonPath().getString("access_token"));

        access_token = Tokenresponse.jsonPath().getString("access_token");
    }


   /* @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        Tokenresponse.then().assertThat().statusCode(statusCode);
    }*/

    @Given("a user created with {string} and {string}")
    public void aUserCreatedWith(String scenario, String token) throws IOException {
        createProductRequestBody = utilis.parseJsonFile("src/test/resources/test.json").getJSONObject(scenario).toString();
        createProductimpl.createUserProduct(createProductRequestBody, access_token);


    }

    @Then("I validate the {string} status code should be {int}")
    public void iValidateTheStatusCodeShouldBe(String scenario, int statusCode) {
        switch (scenario) {
            case "createproduct":
                createProductResponse.then().assertThat().statusCode(statusCode);
                break;
            case "clienttoken":
                Tokenresponse.then().assertThat().statusCode(statusCode);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + scenario);
        }

    }
}
