package TestsUI.test;

import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

/**
 * Класс тестов для проверки перехода из личного кабинета в конструктор
 * через различные элементы интерфейса, такие как кнопка "Конструктор" и логотип "Stellar Burgers".
 * Параметризированный тест, который выполняется в разных браузерах.
 */
@RunWith(Parameterized.class)
public class FromAccountToConstructorTest extends BaseTest {

    /**
     * Параметризация тестов: получение списка браузеров, в которых будут выполняться тесты.
     * Данные берутся из класса {@link BrowserParameters}.
     *
     * @return Коллекция с браузерами для параметризированных тестов.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return BrowserParameters.getBrowserData(); // Вызов параметров из внешнего класса
    }

    /**
     * Конструктор для передачи браузера в родительский класс BaseTest.
     * Для запуска тестов в различных браузерах.
     *
     * @param browser Название браузера, в котором будет выполняться тест.
     */
    public FromAccountToConstructorTest(String browser) {
        super(browser);
    }

    private final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/";
    private final String LOGIN_URL2 = "https://stellarburgers.nomoreparties.site/account/profile";

    /**
     * Тест для проверки перехода в конструктор из личного кабинета по нажатию кнопки "Конструктор".
     * Проверяется, что после входа в личный кабинет и клика по кнопке "Конструктор", пользователь попадает на главную страницу.
     */
    @Test
    @Description("Тестирование перехода из личного кабинета в конструктор по клику на «Конструктор»")
    public void testConstructorButtonFromPersonalCabinet() {
        // Клик по кнопке личного кабинета для входа
        loginPage.clickPersonalCabinetButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("password123");
        loginPage.submitLogin();
        // Проверка, что после входа на главную страницу
        assertEquals("URL после входа должен быть главной страницей", LOGIN_URL, driver.getCurrentUrl());
        // Повторный клик по кнопке личного кабинета
        loginPage.clickPersonalCabinetButton();
        // Проверка, что после повторного клика URL изменился на страницу профиля
        assertEquals("URL после входа в аккаунт и повторного клика по кнопке «Личный кабинет» должен быть переход на страницу профиля", LOGIN_URL2, driver.getCurrentUrl());
        // Клик по кнопке "Конструктор"
        loginPage.clickConstructor();
        // Проверка, что после клика по кнопке "Конструктор" URL остается главной страницей
        assertEquals("URL после клика по кнопке Конструктор из личного кабинета должен быть главной страницей", LOGIN_URL, driver.getCurrentUrl());
    }

    /**
     * Тест для проверки перехода в конструктор из личного кабинета по нажатию на логотип "Stellar Burgers".
     * Проверяется, что после входа в личный кабинет и клика по логотипу, пользователь попадает на главную страницу.
     */
    @Test
    @Description("Тестирование перехода из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void testConstructorButtonFromStellarBurgersLogo() {
        // Клик по кнопке личного кабинета для входа
        loginPage.clickPersonalCabinetButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("password123");
        loginPage.submitLogin();
        // Проверка, что после входа на главную страницу
        assertEquals("URL после входа должен быть главной страницей", LOGIN_URL, driver.getCurrentUrl());
        // Повторный клик по кнопке личного кабинета
        loginPage.clickPersonalCabinetButton();
        // Проверка, что после повторного клика URL изменился на страницу профиля
        assertEquals("URL после входа в аккаунт и повторного клика по кнопке «Личный кабинет» должен быть переход на страницу профиля", LOGIN_URL2, driver.getCurrentUrl());
        // Клик по логотипу "Stellar Burgers"
        loginPage.clickStellarBurgersLogo();
        // Проверка, что после клика по логотипу URL остается главной страницей
        assertEquals("URL после клика по логотипу Stellar Burgers должен быть главной страницей", LOGIN_URL, driver.getCurrentUrl());
    }
}
