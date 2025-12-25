package stepDefinitions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.json.JSONArray;
import org.json.JSONObject;
import serviceImpl.authJWTEndpointsImpl;
import serviceImpl.createProductImpl;

import java.io.IOException;

import static stepDefinitions.AbstractDefinition.*;

public class authJWTStepDefinition {
    authJWTEndpointsImpl authJWTEndpointsimpl = new authJWTEndpointsImpl();
    createProductImpl createProductimpl = new createProductImpl();
    String authRequestBody, createProductRequestBody, access_token, createUserCategoryRequestBody;

    @Given("client token is generated with {string}")
    public void clientTokenIsGenerated(String scenario) throws IOException {
        authRequestBody = utilis.parseJsonFile("src/test/resources/test.json").getJSONObject(scenario).toString();
        authJWTEndpointsimpl.authenticateUser(authRequestBody);
        System.out.println("Generated Token is : " + Tokenresponse.jsonPath().getString("access_token"));

        access_token = Tokenresponse.jsonPath().getString("access_token");
    }


    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        Tokenresponse.then().assertThat().statusCode(statusCode);
    }

    @Given("a user created with {string} and {string}")
    public void aUserCreatedWith(String scenario, String token) throws IOException {
        createProductRequestBody = utilis.parseJsonFile("src/test/resources/test.json").getJSONObject(scenario).toString();
        createProductRequestBody = createProductRequestBody.replace("$name", utilis.getRandomName())
                .replace("$title", utilis.getRandomName())
                .replace("$description", "This is a description for " + utilis.getRandomName()).replace("$categoryId", createUserCategoriesResponse.jsonPath().getString("id"));

        createProductimpl.createUserProduct(createProductRequestBody, access_token);


    }


    @Given("a categories created with {string} and {string}")
    public void aUserCategories(String scenario, String token) throws IOException {
        createUserCategoryRequestBody = utilis.parseJsonFile("src/test/resources/test.json").getJSONObject(scenario).toString();
        createUserCategoryRequestBody = createUserCategoryRequestBody.replace("$name", utilis.getRandomName())
                .replace("$image", "https://placehold.co/" + utilis.getRandomName() + ".jpg");
        createProductimpl.createUserCategories(createUserCategoryRequestBody, access_token);


    }

    @Given("I retrieve {string}")
    public void iRetrieveAllCategories(String scenario) throws IOException {

        getListCategoriesResponse = createProductimpl.getListCategories(scenario);

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
            case "createUserCategories":
                createUserCategoriesResponse.then().assertThat().statusCode(statusCode);
                break;

            case "fakestoreclienttoken":
                fakeStoreAuthResponse.then().assertThat().statusCode(statusCode);
                break;
            case "createUser":
                createUserResponse.then().assertThat().statusCode(statusCode);
                break;
            case "addCart":
                createCartResponse.then().assertThat().statusCode(statusCode)
                        .and().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/addToCart.json"));
                break;
            case "getSearchListCategories":
                getListCategoriesResponse.then().assertThat().statusCode(statusCode);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + scenario);

        }

    }


    @And("validate the response contains categories list")
    public void validateTheResponseContainsCategoriesList() {
        JSONArray array = new JSONArray(getListCategoriesResponse.asString());

        for(int i=0;i<array.length();i++)
        {
            JSONObject category =array.getJSONObject(i);
            System.out.println("Category ID: " + category.getInt("id"));
            System.out.println("Category Name: " + category.getString("name"));
            System.out.println("Category Image: " + category.getString("image"));
            System.out.println("--------------------------------------------------");
        }
    }
}
