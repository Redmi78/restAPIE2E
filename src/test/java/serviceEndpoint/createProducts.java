package serviceEndpoint;

import io.restassured.response.Response;

import java.io.IOException;

public interface createProducts {
    Response createUserProduct(String createUserRequestBody,String  token) throws IOException;
    Response createUserCategories(String createUserCategoriesRequestBody,String  token) throws IOException;
}
