package TestsUI.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;


import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;

    // Локатор кнопки "Войти в аккаунт" на главной странице
    @FindBy(xpath = "//button[contains(text(),'Войти в аккаунт')]")
    private WebElement loginAccountButton;

    // Локатор кнопки "Регистрация" на главной странице
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/p[1]/a")
    private WebElement registerButton;

    // Локатор поля для ввода имени пользователя
    @FindBy(xpath = "//input[@name='name']")
    private WebElement nameInput;

    // Локатор поля для ввода email
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input")
    private WebElement emailInput;

    // Локатор поля для ввода пароля
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input")
    private WebElement passwordInput;

    // Локатор кнопки "Зарегистрироваться" в форме регистрации
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/form/button")
    private WebElement registerButtonClick;

    // Локатор сообщения об ошибке для поля пароля
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p")
    private WebElement passwordError;

    // Конструктор для инициализации веб-элементов страницы
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Методы ожидания отображения элементов
    public void waitForNameInputField() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(nameInput));
    }

    public void waitForEmailInputField() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(emailInput));
    }

    public void waitForPasswordInputField() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
    }

    // Метод для прокрутки страницы до кнопки "Регистрация"
    public void scrollToRegisterButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerButton);
    }

    // Методы для ввода данных в поля формы
    public void inputName(String name) {
        nameInput.sendKeys(name);
    }

    public void inputEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void inputPassword(String password) {
        passwordInput.sendKeys(password);
    }

    // Методы для кликов по кнопкам
    public void clickLoginAccountButton() {
        loginAccountButton.click();
    }

    public void clickOnRegisterButton() {
        registerButton.click();
    }

    public void clickOnRegisterButtonClick() {
        registerButtonClick.click();
    }

    // Методы для проверки ошибок в форме
    public boolean isPasswordErrorVisible() {
        return passwordError.isDisplayed();
    }

    public String getPasswordErrorMessage() {
        return passwordError.getText();
    }
}
