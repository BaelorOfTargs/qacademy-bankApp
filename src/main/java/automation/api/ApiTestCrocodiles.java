package automation.api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static automation.api.CrocodileUtils.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTestCrocodiles {

    @BeforeTest
    void setup(){
        RestAssured.baseURI = BASE_URI;
        CrocodileUtils.crocsLogin();
    }

    @Test(priority = 1)
    public void getPublicCrocodiles(){
        given().log().all().header("Content-Type","application/json")
                .when().get("/public/crocodiles/")
                .then().log().all().assertThat().statusCode(200)
                .time(lessThan(600L));
    }
    @Test(priority = 2)
    public void createNewCrocsUser(){
        String response = given().header("Content-Type","application/json")
                .body(CrocodileUtils.userData("dusan"+getRandomString(5),getRandomString(7)+"@yahoo.com"))
                .when().post("/user/register/")
                .then().assertThat().body("last_name", equalTo("Marinkovic"))
                .body("username",containsString("dusan"))
                .statusCode(201).and().time(lessThan(2000L))
                .extract().response().asString();
        JsonPath userNameJson = new JsonPath(response);
        String username = userNameJson.getString("username");
        System.out.println("Username je: "+username);
    }
    @Test(priority = 3)
    public void createNewCrocodile(){
        String response = given().log().all().header("Content-Type","application/json").header("Authorization", token)
                .body(newCrocData('"'+"Miss Croc"+getRandomString(2)+'"','"'+randomBirthDate()+'"'))
                .when().post(MY_CROCODILES_PATH)
                .then().log().all().assertThat().statusCode(201).and()
                .time(lessThan(600L))
                .extract().response().asString();
        JsonPath path = new JsonPath(response);
        crocodileId = path.getInt("id");
    }
    @Test(priority = 4)
    public void getMyCrocs(){
        given().log().all().header("Content-Type","application/json").header("Authorization", token)
                .when().get(MY_CROCODILES_PATH)
                .then().log().all().assertThat().statusCode(200)
                .time(lessThan(600L));
    }
    @Test(priority = 5)
    public void getMySigleCroc(){
        listMyCrocs();
        given().log().all().header("Content-Type","application/json").header("Authorization", token)
                .when().get(MY_CROCODILES_PATH +crocodileId+"/")
                .then().log().all().assertThat().statusCode(200)
                .assertThat().body("id",equalTo(crocodileId))
                .time(lessThan(600L));
    }


    @Test(priority = 6)
    public void patchCrocs(){
        listMyCrocs();
        given().log().all().header("Content-Type","application/json").header("Authorization",token)
                .body("{\n" +
                        "    \"name\": \"Sienna\"\n" +
                        "}")
                .when().patch(MY_CROCODILES_PATH +crocodileId+"/")
                .then().log().all().assertThat().statusCode(200)
                .time(lessThan(600L));
    }
    @Test(priority = 7)
    public void putCrocs(){
        listMyCrocs();
        given().log().all().header("Content-Type","application/json").header("Authorization",token)
                .body(newCrocData('"'+"Miss Croc"+getRandomString(3)+'"','"'+randomBirthDate()+'"'))
                .when().put(MY_CROCODILES_PATH +crocodileId+"/")
                .then().log().all().assertThat().statusCode(200)
                .time(lessThan(600L));
    }

    @Test(priority = 8)
    public void deleteCrocodile(){
        createTestCrocodile();
        given().log().all().header("Content-Type","application/json").header("Authorization",token)
                .when().delete(MY_CROCODILES_PATH +crocodileId+"/")
                .then().log().all().assertThat().statusCode(204)
                .time(lessThan(600L));
    }
}
