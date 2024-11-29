package praktikum;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import praktikum.objects.User;
import praktikum.objects.UserResponse;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class UserAPI extends UserHTTP {



    @Step("Создание тестового пользователя")
    public static void createUser(String name, String email, String password) {
        UserAPI operators = new UserAPI();
        Response response = given(baseRequest("application/json")) // Статический вызов baseRequest
                .body(new User(email, password, name))
                .when()
                .post(ServerURLs.API_REGISTER_USER);

        if (response.getStatusCode() == SC_OK) {
            System.out.println("Пользователь успешно создан: " + email);
        } else {
            System.err.println("Ошибка при создании пользователя: " + response.getStatusCode());
        }
    }

    @Step("Удаление тестового пользователя")
    public static void deleteUser(String email, String password) {
        UserAPI operators = new UserAPI();
        Response response = performLogin(email, password);
        if (response.getStatusCode() != SC_OK) {
            System.err.println("Ошибка при логине пользователя: " + response.getStatusCode());
            return;
        }

        String token = response.body().as(UserResponse.class).getAccessToken().split(" ")[1];
        Response deleteResponse = given(baseRequest("application/json")) // Статический вызов baseRequest
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(ServerURLs.API_DELETE_USER);

        if (deleteResponse.getStatusCode() == SC_OK) {
            System.out.println("Пользователь успешно удален: " + email);
        } else {
            System.err.println("Ошибка при удалении пользователя: " + deleteResponse.getStatusCode());
        }
    }

    private static Response performLogin(String email, String password) {
        return given(baseRequest("application/json")) // Статический вызов baseRequest
                .body(new User(email, password))
                .when()
                .post(ServerURLs.API_LOGIN);
    }

    // Статический метод baseRequest для выполнения запросов
    public static RequestSpecification baseRequest(String contentType) {
        return new RequestSpecBuilder()
                .addHeader("Content-type", contentType)
                .addFilter(new AllureRestAssured())
                .setRelaxedHTTPSValidation()
                .build();
    }
}
