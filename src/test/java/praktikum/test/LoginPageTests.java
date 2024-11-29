package praktikum.test;

import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.ServerURLs;

import praktikum.UserAPI;
import praktikum.pageobjects.LoginPage;
import praktikum.pageobjects.ForgotPasswordPage;
import praktikum.pageobjects.MainPage;
import praktikum.pageobjects.RegistrationPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;




@RunWith(Parameterized.class)
@DisplayName("Авторизация пользователя")
public class LoginPageTests {

    private WebDriver webDriver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private RegistrationPage regPage;
    private ForgotPasswordPage forgotPasswordPage;
    private String name;
    private String email;
    private String password;
    Faker faker = new Faker(); // Для генерации случайных данных

    private String browserName;

    public LoginPageTests(String browserName) {
        this.browserName = browserName;
    }

    @Parameterized.Parameters(name = "Тестирование на {0}")
    public static Object[] browsers() {
        return new Object[]{"chrome", "yandex"}; // Параметры для тестирования на разных браузерах
    }

    @Before
    @Step("Запуск браузера, подготовка тестовых данных")
    public void startUp() {
        // Инициализация WebDriver
        webDriver = WebDriverProvider.getDriver(browserName);
        webDriver.get(ServerURLs.MAIN_PAGE_URL);

        // Инициализация страниц
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
        regPage = new RegistrationPage(webDriver);
        forgotPasswordPage = new ForgotPasswordPage(webDriver);

        // Генерация случайных данных
        name = faker.name().firstName();
        email = faker.internet().safeEmailAddress();
        password = faker.letterify("???????");

        // Привязка данных в Allure отчёт
        Allure.addAttachment("Имя", name);
        Allure.addAttachment("Email", email);
        Allure.addAttachment("Пароль", password);

        // Создание пользователя через API
        new UserAPI().createUser(name, email, password);
    }

    @After
    @Step("Закрытие браузера, очистка тестовых данных")
    public void tearDown() {
        // Закрытие браузера
        webDriver.quit();

        // Удаление пользователя
        new UserAPI().deleteUser(email, password);
    }

    @Step("Авторизация пользователя")
    private void authUser() {
        // Авторизация
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickAuthButton();
        loginPage.waitFormSubmitted();
    }

    @Test
    @DisplayName("Вход через клик по кнопке 'Войти в аккаунт' на главной")
    public void authFromMainPageButtonIsSuccess() {
        Allure.parameter("Браузер", browserName);

        // Переход к форме авторизации через главную страницу
        mainPage.clickAuthButton();
        loginPage.waitAuthFormVisible();
        authUser();

        // Проверка текста на кнопке
        assertThat(
                "Текст на кнопке 'Войти в аккаунт' должен поменяться на 'Оформить заказ'",
                mainPage.getAuthButtonText(),
                equalTo("Оформить заказ")
        );
    }

    @Test
    @DisplayName("Вход через клик по кнопке 'Личный Кабинет' в хеддере страницы")
    public void authFromProfileButtonIsSuccess() {
        Allure.parameter("Браузер", browserName);

        // Переход к форме авторизации через кнопку "Личный Кабинет"
        mainPage.clickLinkToProfile();
        loginPage.waitAuthFormVisible();
        authUser();

        // Проверка текста на кнопке
        assertThat(
                "Текст на кнопке 'Войти в аккаунт' должен поменяться на 'Оформить заказ'",
                mainPage.getAuthButtonText(),
                equalTo("Оформить заказ")
        );
    }

    @Test
    @DisplayName("Вход через форму восстановления пароля")
    public void authLinkFromForgotPasswordFormIsSuccess() {
        Allure.parameter("Браузер", browserName);

        // Переход на страницу восстановления пароля
        webDriver.get(ServerURLs.FORGOT_PASSWORD_URL);

        // Переход к форме авторизации через ссылку на регистрацию
        regPage.clickAuthLink();
        loginPage.waitAuthFormVisible();
        authUser();

        // Проверка текста на кнопке
        assertThat(
                "Текст на кнопке 'Войти в аккаунт' должен поменяться на 'Оформить заказ'",
                mainPage.getAuthButtonText(),
                equalTo("Оформить заказ")
        );
    }
}






