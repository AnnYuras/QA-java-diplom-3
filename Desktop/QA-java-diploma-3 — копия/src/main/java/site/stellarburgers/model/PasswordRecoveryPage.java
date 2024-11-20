package site.stellarburgers.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordRecoveryPage extends Header {

    private WebDriver driver;
    private WebDriverWait wait;

    // локатор ссылки "Войти"
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private WebElement SIGN_IN_LINK;

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickSignInLink() {
        wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_LINK)).click();
    }
}


