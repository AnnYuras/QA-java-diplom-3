package praktikum.test;

import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import praktikum.ServerURLs;

import praktikum.UserAPI;
import praktikum.pageobjects.LoginPage;
import praktikum.pageobjects.MainPage;
import praktikum.pageobjects.ProfilePage;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;




@DisplayName("Проверка личного кабинета пользователя")
public class ProfilePageTests {
    private WebDriver webDriver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private ProfilePage profilePage;
    private String name;
    private String email;
    private String password;
    Faker faker = new Faker();



    @Before
    @Step("Запуск браузера, подготовка тестовых данных")
    public void startUp() {
        String browserName = System.getProperty("browser", "chrome");
        webDriver = WebDriverProvider.getDriver(browserName);
        webDriver.get(ServerURLs.MAIN_PAGE_URL);

        // Инициализация страниц
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
        profilePage = new ProfilePage(webDriver);

        // Генерация случайных данных для теста
        name = faker.name().firstName();
        email = faker.internet().safeEmailAddress();
        password = faker.letterify("????????");

        // Прикрепляем данные к отчёту Allure
        Allure.addAttachment("Имя", name);
        Allure.addAttachment("Email", email);
        Allure.addAttachment("Пароль", password);

        // Создание нового пользователя через API
        new UserAPI().createUser(name, email, password);
    }

    @After
    @Step("Закрытие браузера, очистка тестовых данных")
    public void tearDown() {
        webDriver.quit();
        new UserAPI().deleteUser(email, password);
    }

    @Step("Процесс авторизации")
    private void authUser() {
        // Вводим данные для авторизации
        loginPage.setEmail(email);
        loginPage.setPassword(password);

        // Нажатие на кнопку для авторизации
        loginPage.clickAuthButton();
        loginPage.waitFormSubmitted();
    }

    @Step("Переход в личный кабинет")
    private void goToProfile() {
        // Переход на страницу логина и авторизация
        webDriver.get(ServerURLs.LOGIN_PAGE_URL);
        loginPage.waitAuthFormVisible();
        authUser();

        // Переход в личный кабинет
        mainPage.clickLinkToProfile();
        profilePage.waitAuthFormVisible();
    }

    @Test
    @DisplayName("Проверка перехода по клику на 'Личный кабинет'")
    public void checkLinkToProfileIsSuccess() {
        Allure.parameter("Браузер", System.getProperty("browser", "chrome"));
        goToProfile(); // Переход в личный кабинет
        MatcherAssert.assertThat(
                "Некорректный URL страницы Личного кабинета",
                webDriver.getCurrentUrl(),
                containsString("/account/profile") // Проверяем, что URL содержит "/account/profile"
        );
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета по клику на 'Конструктор'")
    public void checkLinkToConstructorIsSuccess() {
        Allure.parameter("Браузер", System.getProperty("browser", "chrome"));

        goToProfile();

        profilePage.clickConstructorButton(); // Нажатие на кнопку "Конструктор"
        mainPage.waitHeaderIsVisible(); // Ожидаем, пока заголовок на главной странице не станет видимым

        MatcherAssert.assertThat(
                "Текст на кнопке 'Войти в аккаунт' должен поменяться на 'Оформить заказ'",
                mainPage.getAuthButtonText(),
                equalTo("Оформить заказ") // Проверяем, что текст на кнопке изменился
        );
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета по клику на логотип Stellar Burgers")
    public void checkLinkOnLogoIsSuccess() {
        Allure.parameter("Браузер", System.getProperty("browser", "chrome"));

        goToProfile();

        profilePage.clickLinkOnLogo();
        mainPage.waitHeaderIsVisible();

        MatcherAssert.assertThat(
                "Текст на кнопке 'Войти в аккаунт' должен поменяться на 'Оформить заказ'",
                mainPage.getAuthButtonText(),
                equalTo("Оформить заказ") // Проверяем, что текст на кнопке изменился
        );
    }

    @Test
    @DisplayName("Проверка выхода из личного кабинета по клику на кнопку 'Выйти'")
    public void checkLinkLogOutIsSuccess() {
        Allure.parameter("Браузер", System.getProperty("browser", "chrome"));

        goToProfile();

        profilePage.clickLogoutButton();
        loginPage.waitAuthFormVisible();

        MatcherAssert.assertThat(
                "Некорректный URL страницы Авторизации",
                webDriver.getCurrentUrl(),
                containsString("/login") // Проверяем, что URL после выхода содержит "/login"
        );
    }
}

