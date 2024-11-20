package site.stellarburgers.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // локатор кнопки "Выход"
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private WebElement SIGN_OUT_BUTTON;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickSignOutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(SIGN_OUT_BUTTON)).click();
    }
}
