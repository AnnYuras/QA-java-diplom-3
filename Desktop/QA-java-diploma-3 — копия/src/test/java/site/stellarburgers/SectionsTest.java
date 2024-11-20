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
import site.stellarburgers.model.ConstructorPage;
import site.stellarburgers.model.MainPage;

import java.time.Duration;

import static site.stellarburgers.Browser.browserChoice;
import static site.stellarburgers.Browser.closeNotChromeBrowser;

@RunWith(JUnit4.class)
@DisplayName("Вкладки конструктора")
public class SectionsTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private ConstructorPage constructorPage;

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

        mainPage = new MainPage(driver); // Создаем объект MainPage
        driver.get(MainPage.MAIN_PAGE_URL); // Открываем URL страницы
        constructorPage = new ConstructorPage(driver); // Создаем объект ConstructorPage
    }

    @After
    public void tearDown() {
        // Очищаем локальное хранилище браузера
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @AfterClass
    public static void afterAll() {
        closeNotChromeBrowser();
    }

    @Test
    public void bunTabValidWorking() {
        // Проверяем, что вкладка "Бун" выбрана
        WebElement bunTab = driver.findElement(By.xpath("//selector_for_bun_tab"));
        Assert.assertTrue(bunTab.isSelected());
    }

    @Test
    public void sauceTabValidWorking() {
        // Кликаем по вкладке "Соус"
        WebElement sauceTab = driver.findElement(By.xpath("//selector_for_sauce_tab"));
        sauceTab.click();

        // Проверяем, что вкладка "Соус" выбрана
        WebElement selectedSauceTab = driver.findElement(By.xpath("//selector_for_selected_sauce_tab"));
        Assert.assertTrue(selectedSauceTab.isSelected());
    }

    @Test
    public void fillingTabValidWorking() {
        // Кликаем по вкладке "Начинка"
        WebElement fillingTab = driver.findElement(By.xpath("//selector_for_filling_tab"));
        fillingTab.click();

        // Проверяем, что вкладка "Начинка" выбрана
        WebElement selectedFillingTab = driver.findElement(By.xpath("//selector_for_selected_filling_tab"));
        Assert.assertTrue(selectedFillingTab.isSelected());
    }
}


