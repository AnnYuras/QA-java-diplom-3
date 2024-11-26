package praktikum.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private WebDriver webDriver;
    private final By authLink = By.xpath(".//a[starts-with(@class,'Auth_link')]");
    private final By modalOverlay = By.xpath(".//div[starts-with(@class, 'App_App')]/div[starts-with(@class, 'Modal_modal')]");

    public ForgotPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Метод для клика по ссылке на страницу авторизации
    public void clickAuthLink() {
        waitButtonIsClickable();
        webDriver.findElement(authLink).click();
    }
    private void waitButtonIsClickable() {
        new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.invisibilityOf(webDriver.findElement(modalOverlay)));
    }
}
