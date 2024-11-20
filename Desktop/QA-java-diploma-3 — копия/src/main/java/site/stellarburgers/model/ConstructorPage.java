package site.stellarburgers.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // локатор таба "Булки"
    @FindBy(how = How.XPATH, using = "//span[text()='Булки']")
    private WebElement bunsSection;

    // локатор таба "Соусы"
    @FindBy(how = How.XPATH, using = "//span[text()='Соусы']")
    private WebElement saucesSection;

    // локатор таба "Начинки"
    @FindBy(how = How.XPATH, using = "//span[text()='Начинки']")
    private WebElement fillingsSection;

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickSauceTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesSection)).click();
    }

    public void clickFillingTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsSection)).click();
    }

    public boolean checkIsBunTabSelected() {
        return bunsSection.getAttribute("class").contains("current");
    }

    public boolean checkIsSauceTabSelected() {
        return saucesSection.getAttribute("class").contains("current");
    }

    public boolean checkIsFillingTabSelected() {
        return fillingsSection.getAttribute("class").contains("current");
    }
}

