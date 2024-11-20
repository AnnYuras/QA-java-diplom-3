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

import java.time.Duration;

@RunWith(JUnit4.class)
@DisplayName("Переходы на страницы")
public class GoToPageTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private LoginPage loginPage;

    @BeforeClass
    public static void beforeAll() {
        // Можно оставить этот метод, если вам нужно, например, установить настройки для браузера
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

        // Инициализируем страницу логина
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        // Очищаем локальное хранилище браузера
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void goToPersonalAccount() {
        // Кликаем по ссылке "Личный кабинет"
        WebElement personalAccountLink = driver.findElement(By.linkText("Личный кабинет"));
        personalAccountLink.click();

        // Ожидаем, что URL изменится на URL страницы логина
        wait.until(ExpectedConditions.urlToBe(LoginPage.LOGIN_PAGE_URL));

        // Проверяем URL
        Assert.assertEquals(driver.getCurrentUrl(), LoginPage.LOGIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void goToConstructorFromPersonalAccount() {
        // Переходим в личный кабинет
        WebElement personalAccountLink = driver.findElement(By.linkText("Личный кабинет"));
        personalAccountLink.click();

        // Кликаем на ссылку "Конструктор"
        WebElement constructorLink = driver.findElement(By.linkText("Конструктор"));
        constructorLink.click();

        // Ожидаем, что URL изменится на URL главной страницы
        wait.until(ExpectedConditions.urlToBe(MainPage.MAIN_PAGE_URL));

        // Проверяем URL
        Assert.assertEquals(driver.getCurrentUrl(), MainPage.MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void goToConstructorFromPersonalAccountByLogo() {
        // Переходим в личный кабинет
        WebElement personalAccountLink = driver.findElement(By.linkText("Личный кабинет"));
        personalAccountLink.click();

        // Кликаем на логотип
        WebElement logoLink = driver.findElement(By.xpath("//img[@alt='Stellar Burgers logo']"));
        logoLink.click();

        // Ожидаем, что URL изменится на URL главной страницы
        wait.until(ExpectedConditions.urlToBe(MainPage.MAIN_PAGE_URL));

        // Проверяем URL
        Assert.assertEquals(driver.getCurrentUrl(), MainPage.MAIN_PAGE_URL);
    }
}

