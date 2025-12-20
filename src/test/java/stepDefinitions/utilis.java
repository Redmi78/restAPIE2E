package stepDefinitions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


public class utilis {

    public static String stack;
  public static Properties prop = new Properties();
    public static RequestSpecification ecommerceRequestSpecification() throws IOException {
      return new RequestSpecBuilder()
                .setBaseUri(getEcommerceData("ecommerceBaseURI"))
                .setContentType(ContentType.JSON)
                .build();

    }


    public static String getEcommerceData(String dataPath) throws IOException {
        String path;
        switch (getEnvironment()) {
            case "dev":
                path = new File("src/main/java/config/dev.properties").getAbsolutePath();
                break;
            case "qa":
                path = new File("src/main/java/config/qa.properties").getAbsolutePath();
                break;
            case "prod":
                path = new File("src/main/java/config/prod.properties").getAbsolutePath();
                break;
            default:
              throw new IllegalArgumentException("Invalid environment: " + getEnvironment());
        }
    FileInputStream fis = new FileInputStream(path);

            prop.load(fis);

        return prop.getProperty(dataPath);
    }


    public static String getEnvironment()
    {
        if(System.getProperty("stack") == null)
        {
            stack= System.getProperty("stack","qa").toLowerCase();
        }
        return stack;
    }

    public static JSONObject parseJsonFile(String filename) throws IOException {
        String content= new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }

    public  static String getRandomName() {
        String generatedString = RandomStringUtils.randomAlphanumeric(10);

        System.out.println(generatedString);

        return generatedString;

    }


}
