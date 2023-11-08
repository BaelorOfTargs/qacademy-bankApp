package automation.api;
import io.restassured.path.json.JsonPath;

import java.nio.charset.Charset;
import java.util.GregorianCalendar;
import java.util.Random;

import static io.restassured.RestAssured.given;
public class CrocodileUtils {
    public static String token ="Bearer ";
    public static int crocodileId;
    public static final String MY_CROCODILES_PATH = "my/crocodiles/";
    public static final String BASE_URI = "https://test-api.k6.io/";
    public static String userData(String username, String email){
        return "{\n"
                + "    \"username\": \""+username+"\",\n"
                + "    \"first_name\": \"Dusan\",\n"
                + "    \"last_name\": \"Marinkovic\",\n"
                + "    \"email\": \""+email+"\",\n"
                + "    \"password\": \"zbrckajNesto123\"\n"
                + "}";
    }
    public static void crocsLogin() {
        String response = given().log().all().header("Content-Type", "application/json").body(crocodilessLogin())
                .when().post("/auth/token/login/")
                .then().log().all().extract().response().asString();
        JsonPath path = new JsonPath(response);
        token = token + path.getString("access");
    }

    public static void createTestCrocodile(){
        String response = given().log().all().header("Content-Type","application/json").header("Authorization", token)
                .body(newCrocData('"'+"Miss Croc"+getRandomString(2)+'"','"'+randomBirthDate()+'"'))
                .when().post("my/crocodiles/")
                .then()
                .extract().response().asString();
        JsonPath path = new JsonPath(response);
        crocodileId = path.getInt("id");
    }
    public static int listMyCrocs() {
        String crocs = given().log().all().header("Content-Type", "application/json").header("Authorization", token)
                .when().get("my/crocodiles/")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath path = new JsonPath(crocs);
        String ideja = path.getString("id").substring(1,9);
        //String arTry = path.getString("id").trim()
        crocodileId = Integer.parseInt(ideja);
        return crocodileId;
    }

    public static String crocodilessLogin(){
        return "{\n" +
                "    \"username\": \"BaelorOfTargs\",\n" +
                "    \"password\": \"Baelor123\"\n" +
                "}";
    }
    public static String randomBirthDate(){
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(1970, 2022);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        String birthDate = gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH);
        return birthDate.toString();
    }
    protected static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
    public static String newCrocData(String name, String date){
        return "{\n" +
                "    \"name\": "+name+",\n" +
                "    \"sex\": \"F\",\n" +
                "    \"date_of_birth\": "+date+" \n" +
                "}";

    }
    public static String getRandomString(int n){
        byte[] array = new byte[256];
        new Random().nextBytes(array);
        String randomString = new String(array, Charset.forName("UTF-8"));
        StringBuffer r = new StringBuffer();
        for (int k = 0; k < randomString.length(); k++) {
            char ch = randomString.charAt(k);
            if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    && (n > 0)) {
                r.append(ch);
                n--;
            }
        }
        return r.toString();
    }
}
