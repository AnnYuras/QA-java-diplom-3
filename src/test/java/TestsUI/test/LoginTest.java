package TestsUI.test;

import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

/**
 * Класс тестов для проверки различных способов входа в аккаунт через главную страницу,
 * кнопку "Личный кабинет", форму регистрации и форму восстановления пароля.
 * Параметризированный тест, который выполняется в разных браузерах.
 */
@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {

    /**
     * Параметризация тестов: получение списка браузеров для выполнения тестов.
     * Данные берутся из внешнего класса {@link BrowserParameters}.
     *
     * @return Коллекция с браузерами для параметризированных тестов.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return BrowserParameters.getBrowserData(); // Вызов параметров из внешнего класса
    }

    /**
     * Конструктор для передачи параметров в родительский класс BaseTest.
     * Для запуска тестов в разных браузерах.
     *
     * @param browser Название браузера, в котором будет выполняться тест.
     */
    public LoginTest(String browser) {
        super(browser);
    }

    private final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";

    /**
     * Тест для проверки входа через кнопку "Личный кабинет".
     * Проверяется, что после клика по кнопке "Личный кабинет" пользователь попадает на страницу логина.
     */
    @Test
    @Description("Тестирование входа через кнопку 'Личный кабинет'")
    public void testLoginViaPersonalCabinetButton() {
        // Клик по кнопке "Личный кабинет"
        loginPage.clickUserAccountButton();
        loginPage.enterEmail("idjbospwhe@test.com");
        loginPage.enterPassword("TestPassword123!");
        loginPage.signInButton();
        // Проверка, что после входа URL соответствует странице логина
        assertEquals("URL после входа должен быть страницей логина", LOGIN_URL, driver.getCurrentUrl());
    }

    /**
     * Тест для проверки входа через кнопку "Войти в аккаунт" на главной странице.
     * После клика по кнопке "Войти в аккаунт", пользователь должен попасть на страницу логина.
     */
    @Test
    @Description("Тестирование входа по кнопке 'Войти в аккаунт' на главной странице")
    public void testLoginViaLoginAccountButton() {
        // Клик по кнопке "Войти в аккаунт"
        registrationPage.clickLoginAccountButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("password123");
        loginPage.signInButton();
        // Проверка, что после входа URL соответствует странице логина
        assertEquals("URL после входа должен быть страницей логина", LOGIN_URL, driver.getCurrentUrl());
    }

    /**
     * Тест для проверки входа через форму регистрации.
     * Проверяется, что после перехода в форму регистрации, и клика по кнопке для входа, пользователь попадает на страницу логина.
     */
    @Test
    @Description("Тестирование входа через кнопку в форме регистрации")
    public void testLoginViaRegisterFormButton() {
        // Переход в форму регистрации и клик по кнопке для входа
        registrationPage.clickLoginAccountButton();
        registrationPage.scrollToRegisterButton();
        registrationPage.clickOnRegisterButton();
        loginPage.clickRegisterLoginButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("password123");
        loginPage.signInButton();
        // Проверка, что после входа URL соответствует странице логина
        assertEquals("URL после входа должен быть страницей логина", LOGIN_URL, driver.getCurrentUrl());
    }

    /**
     * Тест для проверки входа через форму восстановления пароля.
     * После клика по кнопке восстановления пароля, пользователь должен попасть на страницу логина.
     */
    @Test
    @Description("Тестирование входа через кнопку в форме восстановления пароля")
    public void testLoginViaForgotPasswordButton() {
        // Переход в форму восстановления пароля
        registrationPage.clickLoginAccountButton();
        registrationPage.scrollToRegisterButton();
        loginPage.clickPasswordRestoreButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterRestorePassword("password123");
        loginPage.submitRestoreLoginButton();
        // Проверка, что после восстановления пароля URL соответствует странице логина
        assertEquals("URL после входа должен быть страницей логина", LOGIN_URL, driver.getCurrentUrl());
    }
}
