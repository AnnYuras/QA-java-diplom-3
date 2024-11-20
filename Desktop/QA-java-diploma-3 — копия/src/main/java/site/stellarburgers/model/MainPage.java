package site.stellarburgers.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends Header {

    public final static String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private WebDriver driver;
    private WebDriverWait wait;

    // локатор кнопки "Войти в аккаунт"
    @FindBy(xpath = "//button[contains(text(),'Войти в аккаунт')]")
    private WebElement SIGN_IN_BUTTON;

    // локатор кнопки "Оформить заказ"
    @FindBy(how = How.XPATH, using = "//button[text()='Оформить заказ']")
    private WebElement CHECKOUT_BUTTON;

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_BUTTON)).click();
    }

    public boolean checkIsCheckOutButtonEnabled() {
        return CHECKOUT_BUTTON.isEnabled();
    }

    public boolean checkIsSignInButtonEnabled() {
        return SIGN_IN_BUTTON.isEnabled();
    }

    // Метод для получения кнопки "Оформить заказ"
    public WebElement getCheckOutButton() {
        return CHECKOUT_BUTTON;
    }
}


