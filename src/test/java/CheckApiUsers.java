import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@Slf4j
public class CheckApiUsers {
    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";
    private static final String BASE_URI1 = "https://gorest.co.in//public/v1/";
    @Test
    void checkGetAllUsersEndpoint() {
        log.info("START: gerAllPosts");

        Response response = given()
                .baseUri(BASE_URI)
                .when()
                .get("/posts");
        response.prettyPrint();
        response.then().statusCode(HttpStatus.SC_OK);
        response.then().statusLine("HTTP/1.1 200 OK");
        response.then().body(Matchers.not(Matchers.empty()));
        response.then().header("access-control-allow-credentials","true");

        log.info("END: getAllPosts");
    }

    @Test
    void checkGetUser1Endpoint() {
        log.info("START: gerAllPosts");
        String expectedresponseBody = "{\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                "    \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                "}";

        Response response = given()
                .baseUri(BASE_URI)
                .when()
                .get("/posts/1");
        response.prettyPrint();
        response.then().statusCode(HttpStatus.SC_OK);
        response.then().body(Matchers.equalTo(expectedresponseBody));

        log.info("END: getAllPosts");
    }
    @Test
    void checkAddNewUserEndpoint_Returns_401_not_authorized() {
        log.info("START: gerAllPosts");
        Response postResponse = given()
                .baseUri(BASE_URI1)
                .when()
                .post("/users");
        postResponse.prettyPrint();
        postResponse.then().statusCode(HttpStatus.SC_UNAUTHORIZED);
        log.info("END: getAllPosts");
    }
    @Test
    void checkAddNewUserEndpoint_Returns_422_not_validation() {
        log.info("START: gerAllPosts");
        Response postResponse = given()
                .baseUri(BASE_URI1)
                .when()
                .header("Authorization","Bearer fb46e256aaaa6dba8ed49de58622fc5b802225b9c4e97a046cf920b98d087247")
                .header("Content-type", ContentType.JSON)
                .post("/users");
        postResponse.prettyPrint();
        postResponse.then().statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
        log.info("END: getAllPosts");
    }
    @Test
    void checkAddNewUserEndpoint_sucsess_unpuck_JSON() {
        String uniqeEmailGenerated = RandomString.make(5) + "@usertest.com";
        String name = "test name";
        ModelClient testClient = new ModelClient();

        testClient.setName(name);
        testClient.setEmail(uniqeEmailGenerated);
        testClient.setGender("male");
        testClient.setStatus("active");
        log.info("START: gerAllPosts");
        Response postResponse = given()
                .baseUri(BASE_URI1)
                .when()
                .header("Authorization","Bearer fb46e256aaaa6dba8ed49de58622fc5b802225b9c4e97a046cf920b98d087247")
                .header("Content-type", ContentType.JSON)
                .body(testClient)
                .post("/users");
        postResponse.prettyPrint();
        postResponse.then().statusCode(HttpStatus.SC_CREATED);
        UsersModel actualUserModel = postResponse.body().as(UsersModel.class);

        Assertions.assertThat(actualUserModel.getData().getName()).isEqualTo(name);
        Assertions.assertThat(actualUserModel.getData().getEmail()).isEqualTo(uniqeEmailGenerated);
        Assertions.assertThat(actualUserModel.getData().getGender()).isEqualTo("male");
        Assertions.assertThat(actualUserModel.getData().getStatus()).isEqualTo("active");
        org.junit.jupiter.api.Assertions.assertNotNull(actualUserModel.getData().getId());


        String actualName = postResponse.jsonPath().getString("data.name");
        Assertions.assertThat(actualName).isEqualTo(testClient.getName());


        log.info("END: getAllPosts");
    }
    @Test
    void checkAddNewUserEndpoint_sucsess() {
        String uniqeEmailGenerated = RandomString.make(5) + "@usertest.com";
        log.info("START: gerAllPosts");
        Response postResponse = given()
                .baseUri(BASE_URI1)
                .when()
                .header("Authorization","Bearer fb46e256aaaa6dba8ed49de58622fc5b802225b9c4e97a046cf920b98d087247")
                .header("Content-type", ContentType.JSON)
                .body(String.format("  {\n" +
                        "            \"name\": \"Andrii\",\n" +
                        "            \"email\": \"%s\",\n" +
                        "            \"gender\": \"male\",\n" +
                        "            \"status\": \"active\"\n" +
                        "        }",uniqeEmailGenerated))
                .post("/users");
        postResponse.prettyPrint();
        postResponse.then().statusCode(HttpStatus.SC_CREATED);
        postResponse.then().body("data",Matchers.hasEntry("email",uniqeEmailGenerated));
        postResponse.then().body("data",Matchers.hasEntry("name","Andrii"));
        postResponse.then().body("data",Matchers.hasEntry("gender","male"));
        postResponse.then().body("data",Matchers.hasEntry("status","active"));

        log.info("END: getAllPosts");
    }

    @Test
    void checkGetUsersOnThePage() {
        log.info("START: gerAllPosts");
        Response response = given()
                .baseUri(BASE_URI1)
                .when()
                .get("/users");
         response.prettyPrint();
         response.then().statusCode(HttpStatus.SC_OK);

        List<Object> listOfUsers = response.jsonPath().getList("data");
        int quantityUsersOnPage = response.jsonPath().getInt("meta.pagination.limit");

        Assertions.assertThat(listOfUsers.size()).isEqualTo(quantityUsersOnPage);

        log.info("END: getAllPosts");
    }
    @Test
    void checkGetUserDataWithJsonpath() {
        log.info("START: gerAllPosts");
        Response response = given()
                .baseUri(BASE_URI1)
                .when()
                .get("/users");

        int idOfFifthOfTheElement = response.jsonPath().getInt("data[5].id");
        String nameOfFifthOfTheElement = response.jsonPath().getString("data[5].name");
        String emailOfFifthOfTheElement = response.jsonPath().getString("data[5].email");
        String genderOfFifthOfTheElement = response.jsonPath().getString("data[5].gender");
        String statusOfFifthOfTheElement = response.jsonPath().getString("data[5].status");


        Assertions.assertThat(idOfFifthOfTheElement).isEqualTo(2488);
        Assertions.assertThat(nameOfFifthOfTheElement).isEqualTo("Bheeshma Ahuja");
        Assertions.assertThat(emailOfFifthOfTheElement).isEqualTo("bheeshma_ahuja@koch-steuber.org");
        Assertions.assertThat(genderOfFifthOfTheElement).isEqualTo("male");
        Assertions.assertThat(statusOfFifthOfTheElement).isEqualTo("inactive");
        log.info("END: getAllPosts");
    }
    @Test
    void checkUpdateUserEndpoint_sucsess_unpuck_JSON() {
        String uniqeEmailGenerated = RandomString.make(5) + "@baidentest.com";
        String name = "Jo Baiden";
        Long id = 21531L;
        ModelClient testClient = new ModelClient();

        testClient.setName(name);
        testClient.setEmail(uniqeEmailGenerated);
        testClient.setGender("male");
        testClient.setStatus("active");
        testClient.setId(id);

        log.info("START: gerAllPosts");
        Response postResponse = given()
                .baseUri(BASE_URI1)
                .when()
                .header("Authorization","Bearer fb46e256aaaa6dba8ed49de58622fc5b802225b9c4e97a046cf920b98d087247")
                .header("Content-type", ContentType.JSON)
                .body(testClient)
                .put(String.format("/users/%s",id));
        postResponse.prettyPrint();
        postResponse.then().statusCode(HttpStatus.SC_OK);

        UsersModel actualUserModel = postResponse.body().as(UsersModel.class);

        Assertions.assertThat(actualUserModel.getData().getName()).isEqualTo(testClient.getName());
        Assertions.assertThat(actualUserModel.getData().getEmail()).isEqualTo(uniqeEmailGenerated);
        Assertions.assertThat(actualUserModel.getData().getGender()).isEqualTo(testClient.getGender());
        Assertions.assertThat(actualUserModel.getData().getStatus()).isEqualTo(testClient.getStatus());
        Assertions.assertThat(actualUserModel.getData().getId()).isEqualTo(testClient.getId());


        String actualName = postResponse.jsonPath().getString("data.name");
        Assertions.assertThat(actualName).isEqualTo(testClient.getName());

        log.info("END: getAllPosts");
    }
    @Test
    void checkDeleteUserEndpoint() {

        Long id = 2817L;

        log.info("START: gerAllPosts");
        Response postResponse = given()
                .baseUri(BASE_URI1)
                .when()
                .header("Authorization", "Bearer fb46e256aaaa6dba8ed49de58622fc5b802225b9c4e97a046cf920b98d087247")
                .header("Content-type", ContentType.JSON)
                .delete(String.format("/users/%s", id));
        postResponse.prettyPrint();
        postResponse.then().statusCode(HttpStatus.SC_NOT_FOUND);

        Object metaAfterDeleting = postResponse.jsonPath().get("meta");
        Assertions.assertThat(metaAfterDeleting).isNull();

        log.info("END: getAllPosts");
    }

    @Test
    void checkDelUser_404_not_found() {
        log.info("START: gerAllPosts");
        String messageAfterDeleting = "Resource not found";
        Response postResponse = given()
                .baseUri(BASE_URI1)
                .when()
                .header("Authorization","Bearer fb46e256aaaa6dba8ed49de58622fc5b802225b9c4e97a046cf920b98d087247")
                .header("Content-type", ContentType.JSON)
                .get("/users/2817");
        postResponse.prettyPrint();
        postResponse.then().statusCode(HttpStatus.SC_NOT_FOUND);
        Object dataAfterDeleting = postResponse.jsonPath().get("data.message");
        Assertions.assertThat(dataAfterDeleting).isEqualTo(messageAfterDeleting);
        log.info("END: getAllPosts");
    }


}
