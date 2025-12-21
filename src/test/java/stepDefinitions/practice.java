package stepDefinitions;


import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;


import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class practice {
    public static void main(String[]args)  {

        given().cookie("session_id","12345")
        .when().get("https://example.com/api/data")
        .then().statusCode(200);

    HashMap<String, String> cookies = new HashMap<>();
        cookies.put("session_id", "12345abcd");
        cookies.put("user_preference", "dark_mode");
        Cookies cookies1 = new Cookies();

        cookies.put("test1","test2");
        given().cookies(cookies)
                .when().get("https://example.com/api/data")
                .then().statusCode(200);

        Headers h = new Headers(new Header("testHeader", "headerValue"));

        given().headers(h)
                .when().get("https://example.com/api/data")
                .then().statusCode(200);

    }
}
