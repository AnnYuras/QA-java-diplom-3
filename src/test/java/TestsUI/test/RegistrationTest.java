package TestsUI.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.qameta.allure.Description;
import java.util.Collection;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Класс тестов для проверки различных сценариев регистрации пользователя.
 * Параметризированный тест, который выполняется в разных браузерах.
 */
@RunWith(Parameterized.class)
public class RegistrationTest extends BaseTest {

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
    public RegistrationTest(String browser) {
        super(browser);
    }

    /**
     * Тест для проверки успешной регистрации пользователя.
     * Проверяется, что при успешной регистрации URL изменится на страницу логина.
     */

    /**
     * Баг: после успешной регистрации пользователя
     * открывается страница логина, а не страница регистрации.
     */

     @Test
    @Description("Проверка успешной регистрации пользователя")
    public void testSuccessfulRegistration() {
        registrationPage.clickLoginAccountButton();
        registrationPage.scrollToRegisterButton(); // Прокрутка до кнопки "Зарегистрироваться"
        registrationPage.clickOnRegisterButton();
        assertEquals("URL после регистрации должен быть страницей регистрации",
                "https://stellarburgers.nomoreparties.site/register",
                driver.getCurrentUrl());
        registrationPage.waitForNameInputField(); // Нажатие на кнопку "Зарегистрироваться"
        registrationPage.inputName("Test User");
        registrationPage.waitForEmailInputField();
        registrationPage.inputEmail("idjbospwhe@test.com");
        registrationPage.waitForPasswordInputField();
        registrationPage.inputPassword("TestPassword123!");
        registrationPage.clickOnRegisterButtonClick();
        assertEquals("URL после регистрации должен быть страницей логина",
                "https://stellarburgers.nomoreparties.site/login",
                driver.getCurrentUrl());
    }

    /**
     * Тест для проверки регистрации с коротким паролем.
     * Проверяется, что при введении некорректного пароля (менее 6 символов) появляется ошибка.
     */
    @Test
    @Description("Проверка регистрации с коротким паролем")
    public void testRegistrationWithShortPassword() {
        // Переход на страницу регистрации
        registrationPage.clickLoginAccountButton();
        registrationPage.scrollToRegisterButton(); // Прокрутка страницы до кнопки "Зарегистрироваться"
        registrationPage.clickOnRegisterButton();
        // Проверка, что URL после перехода на страницу регистрации соответствует ожиданиям
        assertEquals("URL после перехода на страницу регистрации должен быть страницей регистрации",
                "https://stellarburgers.nomoreparties.site/register",
                driver.getCurrentUrl());

        // Ввод данных для регистрации с коротким паролем
        registrationPage.waitForNameInputField();
        registrationPage.inputName("Test User");
        registrationPage.waitForEmailInputField();
        registrationPage.inputEmail("idjbospwhe@test.com");
        registrationPage.waitForPasswordInputField();
        registrationPage.inputPassword("123"); // Короткий пароль
        registrationPage.clickOnRegisterButtonClick();

        // Проверка, что отображается ошибка из-за короткого пароля
        assertTrue("Сообщение об ошибке должно отображаться", registrationPage.isPasswordErrorVisible());
        assertEquals("Некорректный текст ошибки", "Некорректный пароль", registrationPage.getPasswordErrorMessage());
    }
}
