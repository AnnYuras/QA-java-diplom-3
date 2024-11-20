package site.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import site.stellarburgers.model.LoginPage;
import site.stellarburgers.model.MainPage;
import site.stellarburgers.model.RegistrationPage;

import java.time.Duration;

import static site.stellarburgers.Browser.browserChoice;
import static site.stellarburgers.Browser.closeNotChromeBrowser;
import static site.stellarburgers.generator.UserGenerator.*;

@RunWith(JUnit4.class)
@DisplayName("Регистрация")
public class RegisterUserTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private String newEmail;

    @BeforeClass
    public static void beforeAll() {
        browserChoice();
    }

    @Before
    public void setUp() {
        // Указываем путь к драйверу Chrome
        System.setProperty("webdriver.chrome.driver", "path_to_your_chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Открываем главную страницу
        driver.get(MainPage.MAIN_PAGE_URL);
        mainPage = new MainPage(driver);

        // Переходим на страницу логина
        mainPage.clickSignInButton();
        loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        // Переходим на страницу регистрации
        registrationPage = new RegistrationPage(driver);

        // Генерируем новый email для регистрации
        newEmail = getNewRandomEmail();
    }

    @After
    public void tearDown(){
        // Очищаем локальное хранилище браузера
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @AfterClass
    public static void afterAll() {
        closeNotChromeBrowser();
    }

    @Test
    @DisplayName("Регистрация с валидными данными")
    public void registerUserSuccessfully() {
        // Регистрируем пользователя с валидными данными
        registrationPage.register(DEFAULT_NAME, newEmail, DEFAULT_PASSWORD);

        // Входим в систему с только что зарегистрированным пользователем
        loginPage.login(newEmail, DEFAULT_PASSWORD);

        // Проверяем, что кнопка оформления заказа активна
        WebElement checkOutButton = driver.findElement(By.xpath("//button[@class='check-out-button']"));
        Assert.assertTrue(checkOutButton.isEnabled());
    }

    @Test
    @DisplayName("Регистрация со слишком коротким паролем")
    public void registerUserWithShortPassword() {
        // Регистрируем пользователя с коротким паролем
        registrationPage.register(DEFAULT_NAME, newEmail, SHORT_PASSWORD);

        // Проверяем, что отображается ошибка о некорректном пароле
        WebElement errorText = driver.findElement(By.xpath("//p[text()='Некорректный пароль']"));
        Assert.assertTrue(errorText.isDisplayed());
    }
}

