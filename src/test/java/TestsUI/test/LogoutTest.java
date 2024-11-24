package TestsUI.test;



import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import static org.junit.Assert.assertEquals;


/**
 * Класс тестов для проверки различных сценариев выхода из аккаунта.
 * Параметризированный тест, который выполняется в разных браузерах.
 */
@RunWith(Parameterized.class)
public class LogoutTest extends BaseTest {

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
    public LogoutTest(String browser) {
        super(browser);
    }

    private final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/";
    private final String LOGIN_URL2 = "https://stellarburgers.nomoreparties.site/account/profile";

    /**
     * Тест для проверки выхода из аккаунта через кнопку «Выход» в личном кабинете.
     * Проверяется, что после клика по кнопке «Выход» пользователь выходит из аккаунта.
     */
    @Test
    @Description("Тестирование выхода из аккаунта по кнопке «Выйти» в личном кабинете")
    public void testExitButton() {
        // Клик по кнопке "Войти в аккаунт" для авторизации
        registrationPage.clickLoginAccountButton();
        loginPage.enterEmail("idjbospwhe@test.com");
        loginPage.enterPassword("TestPassword123!");
        loginPage.signInButton();
        // Проверка, что после входа URL соответствует главной странице
        assertEquals("URL после входа должен быть главной страницей", LOGIN_URL, driver.getCurrentUrl());

        // Переход в личный кабинет и проверка URL
        loginPage.clickUserAccountButton();
        assertEquals("URL после входа в аккаунт и клика по кнопке «Личный кабинет» должен быть профилем пользователя", LOGIN_URL2, driver.getCurrentUrl());

        // Клик по кнопке "Выход" для выхода из аккаунта
        loginPage.clickLogout();
        // Дополнительные проверки на выход можно добавить здесь, например, проверка URL или видимости кнопки входа
    }

    /**
     * Тест для проверки выхода из аккаунта через редирект после выхода из личного кабинета.
     * Проверяется, что после выхода URL должен быть главной страницей.
     */
    @Test
    @Description("Тестирование выхода из аккаунта через редирект на главную страницу")
    public void testLogoutRedirection() {
        // Авторизация пользователя
        registrationPage.clickLoginAccountButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("password123");
        loginPage.signInButton();
        // Проверка, что после входа URL соответствует главной странице
        assertEquals("URL после входа должен быть главной страницей", LOGIN_URL, driver.getCurrentUrl());

        // Клик по кнопке "Выход"
        loginPage.clickLogout();
        // Проверка редиректа на главную страницу после выхода
        assertEquals("После выхода из аккаунта URL должен быть главной страницей", LOGIN_URL, driver.getCurrentUrl());
    }
}
