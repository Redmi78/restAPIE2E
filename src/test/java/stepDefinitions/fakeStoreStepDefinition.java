package stepDefinitions;

import io.cucumber.java.en.Given;
import serviceImpl.authJWTEndpointsImpl;

import static stepDefinitions.AbstractDefinition.fakeStoreAuthResponse;

public class fakeStoreStepDefinition {
    authJWTEndpointsImpl authJWTEndpointsimpl = new authJWTEndpointsImpl();

   String fakeStoreRequestBody,fakeStoreToken;
    @Given("fake store client token is generated with {string}")
    public void fakeStoreClientTokenIsGeneratedWith(String scenario) throws Exception {
        fakeStoreRequestBody = utilis.parseJsonFile("src/test/resources/test.json").getJSONObject(scenario).toString();
        authJWTEndpointsimpl.authenticateFakeStoreUser(fakeStoreRequestBody);
        System.out.println("Generated Token is : " + fakeStoreAuthResponse.jsonPath().getString("token"));

        fakeStoreToken = fakeStoreAuthResponse.jsonPath().getString("token");
        System.out.println(fakeStoreToken);

    }
}
