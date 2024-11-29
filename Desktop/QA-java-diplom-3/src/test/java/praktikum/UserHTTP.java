package praktikum;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import praktikum.objects.User;

import static io.restassured.RestAssured.given;

public class UserHTTP {

    // Сделаем метод статическим
    public static RequestSpecification baseRequest(String contentType) {
        return new RequestSpecBuilder()
                .addHeader("Content-type", contentType)
                .addFilter(new AllureRestAssured())
                .setRelaxedHTTPSValidation()
                .build();
    }

    // Пример использования baseRequest
    protected Response loginUser(String email, String password) {
        return given(baseRequest("application/json"))
                .body(new User(email, password))
                .when()
                .post(ServerURLs.API_LOGIN);
    }

    protected Response deleteUser(String token) {
        return given(baseRequest("application/json"))
                .auth().oauth2(token)
                .delete(ServerURLs.API_DELETE_USER);
    }
}
