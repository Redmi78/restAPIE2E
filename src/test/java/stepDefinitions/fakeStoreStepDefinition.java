package stepDefinitions;

import io.cucumber.java.en.Given;
import serviceImpl.authJWTEndpointsImpl;

import java.io.IOException;

import static stepDefinitions.AbstractDefinition.createUserResponse;
import static stepDefinitions.AbstractDefinition.fakeStoreAuthResponse;

public class fakeStoreStepDefinition {
    authJWTEndpointsImpl authJWTEndpointsimpl = new authJWTEndpointsImpl();

    String fakeStoreRequestBody, fakeStoreToken, createUserRequestBody, createCartRequestBody;

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
        createUserRequestBody = createUserRequestBody.replace("$email", utilis.getRandomEmail()).replace("$username", utilis.getRandomName());


        authJWTEndpointsimpl.createUser(createUserRequestBody, fakeStoreToken);


    }

    @Given("I create a {string} for the {string}")
    public void iCreateACartForThe(String scenario, String storeType) throws IOException {

        createCartRequestBody = utilis.parseJsonFile("src/test/resources/test.json").getJSONObject(scenario).toString();
        createCartRequestBody = createCartRequestBody.replace("$email", utilis.getRandomEmail()).replace("$username", utilis.getRandomName());


        authJWTEndpointsimpl.createUser(createCartRequestBody, fakeStoreToken);
    }

    @Given("I create a {string} for the fake store user")
    public void iCreateACartForTheFakeStoreUser(String scenario) throws IOException {
        createCartRequestBody = utilis.parseJsonFile("src/test/resources/test.json").getJSONObject(scenario).toString();
        createCartRequestBody = createCartRequestBody.replace("$Id", createUserResponse.jsonPath().getString("id")).
                replace("$userId", createUserResponse.jsonPath().getString("id"));


        authJWTEndpointsimpl.createCart(createCartRequestBody, fakeStoreToken);

    }
}
