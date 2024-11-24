package TestsUI.test;

import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AccountLoginTest extends BaseTest {

    // Метод для предоставления параметров теста
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return BrowserParameters.getBrowserData(); // Вызов параметров из внешнего класса
    }

    // Конструктор для передачи параметров в базовый тестовый класс
    // Позволяет запускать тесты в нескольких браузерах одной командой mvn clean test
    public AccountLoginTest(String browser) {
        super(browser);
    }

    // URL главной страницы
    private final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    // URL страницы профиля
    private final String PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    /**
     * Баг: после входа в аккаунт через клик по кнопке «Личный кабинет»,
     * открывается URL страницы логина, вместо главной страницы.
     */
    @Test
    @Description("Тестирование перехода в личный кабинет по клику на «Личный кабинет»")
    public void testLoginViaButtonPersonalCabinetIntoPersonalCabinet() {
        // Шаг 1: Клик по кнопке «Личный кабинет» на главной странице
        loginPage.clickPersonalCabinetButton();

        // Шаг 2: Ввод email и пароля для авторизации
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("password123");

        // Шаг 3: Отправка формы входа
        loginPage.submitLogin();

        // Проверка: после входа пользователь должен оказаться на главной странице
        assertEquals("URL после входа должен быть главной страницей", MAIN_PAGE_URL, driver.getCurrentUrl());

        // Шаг 4: Повторный клик по кнопке «Личный кабинет»
        loginPage.clickPersonalCabinetButton();

        // Проверка: после повторного клика пользователь должен попасть на страницу профиля
        assertEquals("URL после повторного клика по кнопке «Личный кабинет» должен вести на страницу профиля", PROFILE_PAGE_URL, driver.getCurrentUrl());
    }
}
