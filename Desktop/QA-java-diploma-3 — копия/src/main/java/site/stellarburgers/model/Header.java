package site.stellarburgers.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Header {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final Logger logger = LoggerFactory.getLogger(Header.class);

    // Конструктор с WebDriver
    public Header(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // локатор ссылки "Конструктор"
    @FindBy(how = How.XPATH, using = "//a[contains(@class, 'AppHeader_header__link__3D_hX') and contains(@class, 'AppHeader_header__link_active__1IkJo')]")
    protected WebElement constructorLink;

    // локатор ссылки-логотипа
    @FindBy(how = How.CSS, using = "div.AppHeader_header__logo__2D0X2")
    protected WebElement logoLink;

    // локатор ссылки "Личный кабинет"
    @FindBy(how = How.XPATH, using = "//a[@href='/account']")
    protected WebElement personalAccountLink;

    public void clickConstructorLink() {
        logger.info("Переход на страницу конструктора");
        wait.until(ExpectedConditions.visibilityOf(constructorLink)); // Ожидаем видимость элемента
        constructorLink.click();
    }

    public void clickLogoLink() {
        logger.info("Переход на главную страницу");
        wait.until(ExpectedConditions.visibilityOf(logoLink)); // Ожидаем видимость элемента
        logoLink.click();
    }

    public void clickPersonalAccountLink() {
        logger.info("Переход на страницу логина через ссылку 'Личный кабинет'");
        wait.until(ExpectedConditions.visibilityOf(personalAccountLink)); // Ожидаем видимость элемента
        personalAccountLink.click();
    }
}

