package praktikum.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver webDriver;

    // Локаторы для элементов на странице
    private final By constructorButton = By.xpath(".//p[starts-with(@class,'AppHeader_header__linkText') and text()='Конструктор']");
    private final By orderFeedButton = By.xpath(".//p[starts-with(@class,'AppHeader_header__linkText') and text()='Лента заказов']");
    private final By profileButton = By.xpath(".//p[starts-with(@class,'AppHeader_header__linkText') and text()='Личный Кабинет']");
    private final By bunsButton = By.xpath(".//section[starts-with(@class, 'BurgerIngredients_ingredients')]/div/div/span[text()='Булки']");
    private final By saucesButton = By.xpath(".//section[starts-with(@class, 'BurgerIngredients_ingredients')]/div/div/span[text()='Соусы']");
    private final By fillingsButton = By.xpath(".//section[starts-with(@class, 'BurgerIngredients_ingredients')]/div/div/span[text()='Начинки']");
    private final By bunsTypes = By.xpath(".//div[starts-with(@class, 'BurgerIngredients_ingredients__menuContainer')]//h2[text()='Булки']");
    private final By saucesTypes = By.xpath(".//div[starts-with(@class, 'BurgerIngredients_ingredients__menuContainer')]//h2[text()='Соусы']");
    private final By fillingsTypes = By.xpath(".//div[starts-with(@class, 'BurgerIngredients_ingredients__menuContainer')]//h2[text()='Начинки']");
    private final By authButton = By.xpath(".//div[starts-with(@class,'BurgerConstructor_basket__container')]/button");
    private final By header = By.xpath(".//main//h1[text()='Соберите бургер']");
    private final By modalOverlay = By.xpath(".//div[starts-with(@class, 'App_App')]/div/div[starts-with(@class, 'Modal_modal_overlay')]");

    // Конструктор, инициализирующий WebDriver
    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Метод для клика по кнопке аутентификации
    public void clickAuthButton() {
        waitButtonIsClickable();
        webDriver.findElement(authButton).click();
    }

    // Метод для ожидания, когда кнопка становится кликабельной
    public void waitButtonIsClickable() {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.invisibilityOf(webDriver.findElement(modalOverlay)));
    }

    // Метод для ожидания видимости заголовка
    public void waitHeaderIsVisible() {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    // Метод для прокрутки страницы к элементу и ожидания его появления
    public void scrollToElementAndWait(By elementLocator) {
        WebElement element = new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(elementLocator));

        new Actions(webDriver)
                .moveToElement(element)
                .perform();

        new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(element));
    }

    // Метод для клика по кнопке конструктора
    public void clickConstructorButton() {
        waitButtonIsClickable();
        webDriver.findElement(constructorButton).click();
    }

    // Метод для клика по кнопке ленты заказов
    public void clickOrderFeedButton() {
        waitButtonIsClickable();
        webDriver.findElement(orderFeedButton).click();
    }

    // Метод для клика по кнопке личного кабинета
    public void clickLinkToProfile() {
        waitButtonIsClickable();
        webDriver.findElement(profileButton).click();
    }

    // Метод для получения текста с кнопки аутентификации
    public String getAuthButtonText() {
        return webDriver.findElement(authButton).getText();
    }

    // Метод для клика по кнопке "Булки" и прокрутки к списку булок
    public void clickBunsButton() {
        waitButtonIsClickable();
        webDriver.findElement(bunsButton).click();
        scrollToElementAndWait(bunsTypes);
    }

    // Метод для клика по кнопке "Соусы" и прокрутки к списку соусов
    public void clickSaucesButton() {
        waitButtonIsClickable();
        webDriver.findElement(saucesButton).click();
        scrollToElementAndWait(saucesTypes);
    }

    // Метод для клика по кнопке "Начинки" и прокрутки к списку начинок
    public void clickFillingsButton() {
        waitButtonIsClickable();
        webDriver.findElement(fillingsButton).click();
        scrollToElementAndWait(fillingsTypes);
    }

    // Метод для получения локатора для булочек
    public By getBunsTypes() {
        return bunsTypes;
    }

    // Метод для получения локатора для соусов
    public By getSaucesTypes() {
        return saucesTypes;
    }

    // Метод для получения локатора для начинок
    public By getFillingsTypes() {
        return fillingsTypes;
    }
}

