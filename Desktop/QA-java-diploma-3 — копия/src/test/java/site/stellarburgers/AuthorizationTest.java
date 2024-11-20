package site.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Step;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.stellarburgers.model.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static site.stellarburgers.Browser.browserChoice;
import static site.stellarburgers.Browser.closeNotChromeBrowser;
import static site.stellarburgers.generator.UserGenerator.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
@DisplayName("Авторизация")
public class AuthorizationTest {

    private String email;
    private String password;

    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    PasswordRecoveryPage passwordRecoveryPage;
    PersonalAccountPage personalAccountPage;

    @BeforeClass
    public static void beforeAll() {
        browserChoice();
    }

    @Before
    public void setUp() {
        // Запуск ChromeDriver (или другой браузер, если нужно)
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        driver.get(MainPage.MAIN_PAGE_URL);
    }

    @After
    public void tearDown() {
        driver.manage().deleteAllCookies();  // Удаление cookies для нового теста
        driver.quit();  // Закрытие браузера после каждого теста
    }

    @AfterClass
    public static void afterAll() {
        closeNotChromeBrowser();
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { WORKING_EMAIL, DEFAULT_PASSWORD },  // Пример рабочего email и пароля

        });
    }

    public AuthorizationTest(String email, String password) {
        this.email = email;
        this.password = password;
    }



//рабочий тест
    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Step("Проверка входа через кнопку «Войти в аккаунт» на главной")
    public void signInBySignInButtonOnMainPage() {
        mainPage.clickSignInButton();
        loginPage.login(email, password);

        // Явное ожидание появления кнопки выхода из аккаунта
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Ожидание 10 секунд

        // Ожидаем, пока кнопка выхода станет доступной для клика
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(mainPage.getCheckOutButton()));

        // Проверка, что кнопка выхода из аккаунта активна
        Assert.assertTrue("Кнопка выхода из аккаунта должна быть активной", checkoutButton.isEnabled());
    }


    //рабочий тест
    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Step("Проверка входа через кнопку «Личный кабинет»")
    public void signInByPersonalAccountLink() {
        mainPage.clickPersonalAccountLink();
        loginPage.login(email, password);

        // Ожидание появления кнопки "Оформить заказ"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Оформить заказ']")));

        // Клик по кнопке "Оформить заказ"
        orderButton.click();

        // Проверка, что кнопка выхода из аккаунта активна
        Assert.assertTrue("Кнопка выхода из аккаунта должна быть активной", mainPage.checkIsCheckOutButtonEnabled());
    }

    
   //рабочий тест
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Step("Проверка входа через кнопку в форме регистрации")
    public void signInBySignInButtonOnRegistrationPage() {
        mainPage.clickSignInButton();
        loginPage.clickRegisterLink();
        registrationPage.clickSignInLink();
        loginPage.login(email, password);

        // Создаем WebDriverWait непосредственно в тесте
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ожидаем появления кнопки "Оформить заказ"
        WebElement checkOutButton = wait.until(ExpectedConditions.elementToBeClickable(mainPage.getCheckOutButton()));

        // Проверка, что кнопка выхода из аккаунта активна
        Assert.assertTrue("Кнопка выхода из аккаунта должна быть активной", checkOutButton.isEnabled());
    }




    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Step("Проверка входа через кнопку в форме восстановления пароля")
    public void signInBySignInButtonOnPasswordRecoveryPage() {
        mainPage.clickSignInButton();
        loginPage.clickPasswordRecoveryLink();
        passwordRecoveryPage.clickSignInLink();
        loginPage.login(email, password);
        Assert.assertTrue("Кнопка выхода из аккаунта должна быть активной", mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Step("Проверка выхода из аккаунта")
    public void signOut() {
        mainPage.clickSignInButton();
        loginPage.login(email, password);
        mainPage.clickPersonalAccountLink();
        personalAccountPage.clickSignOutButton();
        loginPage.clickLogoLink();
        Assert.assertTrue("Кнопка входа в аккаунт должна быть активной", mainPage.checkIsSignInButtonEnabled());
    }
}

