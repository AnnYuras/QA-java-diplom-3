package site.stellarburgers.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends Header {

    // локатор поля ввода имени
    @FindBy(how = How.XPATH, using = "//div[label[text()='Имя']]/input")
    private WebElement NAME_INPUT_FIELD;

    // локатор поля ввода email
    @FindBy(how = How.XPATH, using = "//div[label[text()='Email']]/input")
    private WebElement EMAIL_INPUT_FIELD;

    // локатор поля ввода пароля
    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private WebElement PASSWORD_INPUT_FIELD;

    // локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private WebElement REGISTER_BUTTON;

    // локатор текста ошибки "Некорректный пароль"
    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private WebElement INCORRECT_PASSWORD_TEXT;

    // локатор ссылки "Войти"
    @FindBy(how = How.XPATH, using = "//a[@href='/login']")
    private WebElement SIGN_IN_LINK;

    // Конструктор для инициализации элементов через PageFactory
    public RegistrationPage(WebDriver driver) {
        super(driver); // Вызов конструктора родительского класса
        PageFactory.initElements(driver, this); // Инициализация элементов для текущего класса
    }

    public void setName(String name) {
        NAME_INPUT_FIELD.sendKeys(name);
    }

    public void setEmail(String email) {
        EMAIL_INPUT_FIELD.sendKeys(email);
    }

    public void setPassword(String password) {
        PASSWORD_INPUT_FIELD.sendKeys(password);
    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(REGISTER_BUTTON)).click();
    }

    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    public boolean checkIsIncorrectPasswordTextVisible() {
        return INCORRECT_PASSWORD_TEXT.isDisplayed();
    }

    public void clickSignInLink() {
        wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_LINK)).click();
    }
}

