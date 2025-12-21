package serviceEndpoint;

import io.restassured.response.Response;

import java.io.IOException;

public interface authJWTEndpoints {

     Response authenticateUser(String authRequestBody) throws IOException;


     Response authenticateFakeStoreUser(String fakeStoreAuthRequestBody) throws IOException;
}
