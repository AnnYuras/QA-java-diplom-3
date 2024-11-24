package TestsUI.test;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import TestsUI.pageObjects.LoginPage;
import io.qameta.allure.Step;
import TestsUI.pageObjects.ConstructorPage;
import TestsUI.pageObjects.RegistrationPage;
import java.util.concurrent.TimeUnit;
import org.junit.After;

/**
 * Базовый класс для тестов, содержащий общую настройку и завершение работы WebDriver.
 * Используется для инициализации необходимых объектов и упрощения тестов.
 */
public abstract class BaseTest {
    // WebDriver для управления браузером
    protected WebDriver driver;

    // Объекты страниц, используемых в тестах
    protected RegistrationPage registrationPage;
    protected LoginPage loginPage;
    protected ConstructorPage constructorPage;

    // URL тестируемого приложения
    protected final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    // Название браузера, передаваемое через параметризированный тест
    protected final String browser;

    /**
     * Конструктор для передачи имени браузера.
     * @param browser название браузера для запуска теста.
     */
    public BaseTest(String browser) {
        this.browser = browser;
    }

    /**
     * Метод для предварительной настройки WebDriver перед началом теста.
     * Включает:
     * - Инициализацию WebDriver через фабрику драйверов.
     * - Переход на базовый URL приложения.
     * - Установку неявного ожидания.
     * - Инициализацию объектов страниц.
     */
    @Step("Настройка WebDriver")
    @Before
    public void setUp() {
        // Инициализация драйвера для указанного браузера
        driver = WebDriverProvider.getDriver(browser);

        // Переход на главную страницу приложения
        driver.get(BASE_URL);

        // Настройка неявного ожидания
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        // Инициализация страниц, используемых в тестах
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        constructorPage = new ConstructorPage(driver);

        // Логирование используемого браузера
        System.out.println("Тест запущен в браузере: " + browser);
    }

    /**
     * Метод для завершения работы WebDriver после завершения теста.
     * Включает:
     * - Завершение работы драйвера.
     */
    @Step("Завершение работы с WebDriver")
    @After
    public void tearDown() {
        // Проверка наличия драйвера и завершение работы
        if (driver != null) {
            driver.quit();
        }
    }
}


