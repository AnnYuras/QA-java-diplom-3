package site.stellarburgers.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends Header {

    public final static String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    private WebDriver driver;
    private WebDriverWait wait;

    // локатор ссылки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = "//a[text()='Зарегистрироваться']")
    private WebElement REGISTER_LINK;

    // локатор поля ввода email
    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    private WebElement EMAIL_INPUT_FIELD;

    // локатор поля ввода пароля
    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private WebElement PASSWORD_INPUT_FIELD;

    // локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private WebElement SIGN_IN_BUTTON;

    // локатор ссылки "Восстановить пароль"
    @FindBy(how = How.XPATH, using = "//a[text()='Восстановить пароль']")
    private WebElement PASSWORD_RECOVERY_LINK;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(REGISTER_LINK)).click();
    }

    public void setEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(EMAIL_INPUT_FIELD));
        EMAIL_INPUT_FIELD.clear();  // Очистить поле, если оно уже содержит значения
        EMAIL_INPUT_FIELD.sendKeys(email);  // Установить значение email
    }

    public void setPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(PASSWORD_INPUT_FIELD));
        PASSWORD_INPUT_FIELD.clear();  // Очистить поле, если оно уже содержит значения
        PASSWORD_INPUT_FIELD.sendKeys(password);  // Установить значение пароля
    }

    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_BUTTON)).click();
    }

    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickSignInButton();
    }

    public void clickPasswordRecoveryLink() {
        wait.until(ExpectedConditions.elementToBeClickable(PASSWORD_RECOVERY_LINK)).click();
    }
}

