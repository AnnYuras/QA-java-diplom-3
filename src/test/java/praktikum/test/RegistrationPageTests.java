package praktikum.test;

import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import praktikum.ServerURLs;

import praktikum.UserAPI;
import praktikum.pageobjects.RegistrationPage;

import static org.hamcrest.Matchers.equalTo;


import static org.hamcrest.MatcherAssert.assertThat;



@DisplayName("Регистрация нового пользователя")
public class RegistrationPageTests {

    private WebDriver webDriver;
    private RegistrationPage regPage;
    private String name;
    private String email;
    private String password;
    private Faker faker = new Faker(); // Генератор случайных данных





    // Подготовка тестовой среды перед запуском каждого теста
    @Before
    @Step("Запуск браузера, подготовка тестовых данных")
    public void startUp() {
        String browserName = System.getProperty("browser", "chrome");
        webDriver = WebDriverProvider.getDriver(browserName); // Получаем драйвер для выбранного браузера
        webDriver.get(ServerURLs.REGISTER_PAGE_URL);
        regPage = new RegistrationPage(webDriver);

        // Генерация случайных данных
        name = faker.name().firstName();
        email = faker.internet().safeEmailAddress();
        password = faker.letterify("????????");

        // Прикрепление тестовых данных в отчеты Allure
        Allure.addAttachment("Имя", name);
        Allure.addAttachment("Email", email);
        Allure.addAttachment("Пароль", password);
    }

    // Очистка данных и закрытие браузера после выполнения каждого теста
    @After
    @Step("Закрытие браузера, очистка тестовых данных")
    public void tearDown() {
        webDriver.quit();
        new UserAPI().deleteUser(email, password);
    }

    // Тест: Регистрация нового пользователя с валидными данными
    @Test
    @DisplayName("Регистрация нового пользователя с валидными данными")
    public void registerNewUserIsSuccess() {
        Allure.parameter("Браузер", System.getProperty("browser", "chrome"));

        regPage.setName(name);
        regPage.setEmail(email);
        regPage.setPassword(password);

        regPage.clickRegisterButton();

        regPage.waitFormSubmitted("Вход");
    }

    // Тест: Регистрация нового пользователя с коротким паролем (менее 6 символов)
    @Test
    @DisplayName("Регистрация нового пользователя с коротким паролем (4 символа)")
    public void registerNewUserIncorrectPasswordIsFailed() {
        Allure.parameter("Браузер", System.getProperty("browser", "chrome"));

        regPage.setName(name);
        regPage.setEmail(email);
        regPage.setPassword(faker.letterify("????")); // Ввод короткого пароля

        regPage.clickRegisterButton();

        regPage.waitErrorIsVisible();
        checkErrorMessage();
    }

    @Step("Проверка появления сообщения об ошибке")
    private void checkErrorMessage() {
        // Сравнение текста ошибки с ожидаемым значением
        assertThat(
                "Некорректное сообщение об ошибке",
                regPage.getErrorMessage(), // Получение сообщения об ошибке
                equalTo("Некорректный пароль") // Проверка на ожидаемое сообщение
        );
    }
}



