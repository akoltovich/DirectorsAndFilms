import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static io.restassured.RestAssured.given;

public class DirectorControllerTest {

    private final String baseURI = "http://localhost:8080";

//    private static JSONObject jsonObject = new JSONObject();
//
//    protected void createJson() throws JSONException {
//        jsonObject.put("id", new Long((1)));
//        jsonObject.put("first name", "some name");
//        jsonObject.put("last name", "some last name");
//        jsonObject.put("birth date", new Date(System.currentTimeMillis()));
//    }

    @Test
    public void getAllTest() {
        Response response =
                given()
                        .baseUri(baseURI)
                        .when()
                        .get("/directors")
                        .then().statusCode(HttpStatus.OK.value())
                        .and().extract().response();
    }

    @Test
    public void getByIdTest() {
        Response response =
                given()
                        .baseUri(baseURI)
                        .when()
                        .get("/directors/byId")
                        .then().statusCode(HttpStatus.OK.value())
                        .and().extract().response();
    }

    @Test
    public void addTest() {
        Response response =
                given()
                        .baseUri(baseURI)
                        .contentType(ContentType.JSON)
                        //.body(jsonObject)
                        .when()
                        .post("/directors/add")
                        .then().statusCode(HttpStatus.OK.value())
                        .and().extract().response();
    }

    @Test
    public void changeTest() {
        Response response =
                given()
                        .baseUri(baseURI)
                        .contentType(ContentType.JSON)
                        .when()
                        .put("/directors/change/byId")
                        .then().statusCode(HttpStatus.OK.value())
                        .and().extract().response();
    }

    @Test
    public void deleteTest() {
        Response response =
                given()
                        .baseUri(baseURI)
                        .when()
                        .delete("/directors/delete")
                        .then().statusCode(HttpStatus.OK.value())
                        .and().extract().response();
    }
}
