package stepDefinitions;

import io.cucumber.java.en.Given;
import serviceImpl.authJWTEndpointsImpl;

import java.io.IOException;

import static stepDefinitions.AbstractDefinition.fakeStoreAuthResponse;

public class fakeStoreStepDefinition {
    authJWTEndpointsImpl authJWTEndpointsimpl = new authJWTEndpointsImpl();

    String fakeStoreRequestBody, fakeStoreToken, createUserRequestBody;

    @Given("fake store client token is generated with {string}")
    public void fakeStoreClientTokenIsGeneratedWith(String scenario) throws Exception {
        fakeStoreRequestBody = utilis.parseJsonFile("src/test/resources/test.json").getJSONObject(scenario).toString();
        authJWTEndpointsimpl.authenticateFakeStoreUser(fakeStoreRequestBody);
        System.out.println("Generated Token is : " + fakeStoreAuthResponse.jsonPath().getString("token"));

        fakeStoreToken = fakeStoreAuthResponse.jsonPath().getString("token");
        System.out.println(fakeStoreToken);

    }

    @Given("I create a {string} for the fake store")
    public void iCreateANewuserForTheFakeStore(String scenario) throws IOException {
        createUserRequestBody = utilis.parseJsonFile("src/test/resources/test.json").getJSONObject(scenario).toString();
        createUserRequestBody= createUserRequestBody.replace("$email", utilis.getRandomEmail()).replace("$username", utilis.getRandomName());


        authJWTEndpointsimpl.createUser(createUserRequestBody, fakeStoreToken);


    }
}
