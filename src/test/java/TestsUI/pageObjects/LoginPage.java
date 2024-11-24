package TestsUI.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;


public class LoginPage {
    private WebDriver driver;

    // Локатор для кнопки "Выход" на странице личного кабинета
    @FindBy(xpath = "//button[text()='Выход']")
    private WebElement exitButton;

    // Локатор для поля ввода пароля в форме восстановления пароля
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/form/fieldset/div/div/input")
    private WebElement passwordRestoreInput;

    // Локатор для кнопки "Зарегистрироваться" в форме логина
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/p[1]/a")
    private WebElement registerButton;

    // Локатор для кнопки "Конструктор" в шапке страницы
    @FindBy(xpath = "//*[@id=\"root\"]/div/header/nav/ul/li[1]/a/p")
    private WebElement constructorButton;

    // Локатор для поля ввода пароля в форме логина
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    // Локатор для кнопки "Войти" в форме регистрации
    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement registerLoginButton;

    // Локатор для кнопки "Войти в аккаунт" на главной странице
    @FindBy(xpath = "//button[contains(text(),'Войти в аккаунт')]")
    private WebElement loginAccountButton;

    // Локатор для поля ввода email в форме логина
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input")
    private WebElement emailInput;

    // Локатор для кнопки "Войти" в форме логина
    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement submitLoginButton;

    // Локатор для кнопки "Восстановить пароль" на странице логина
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/p[2]/a")
    private WebElement restorePasswordButton;

    // Локатор для кнопки "Личный кабинет" в шапке страницы
    @FindBy(xpath = "//*[@id=\"root\"]/div/header/nav/a/p")
    private WebElement personalCabinetButton;

    // Локатор для логотипа Stellar Burgers
    @FindBy(xpath = "//*[@id=\"root\"]/div/header/nav/div")
    private WebElement stellarBurgersLogo;

    // Локатор для кнопки "Войти" в форме восстановления пароля
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div/p/a")
    private WebElement submitRestoreLoginButton;


    // Конструктор страницы, инициализирующий элементы с использованием WebDriver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Методы для взаимодействия с элементами

    // Метод для ввода email в поле ввода
    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    // Метод для ввода пароля в поле ввода
    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    // Метод для ввода пароля в форме восстановления
    public void enterRestorePassword(String password) {
        passwordRestoreInput.sendKeys(password);
    }

    // Метод для нажатия на кнопку "Войти" в форме логина
    public void signInButton() {
        submitLoginButton.click();
    }

    // Метод для нажатия на кнопку "Восстановить пароль"
    public void clickPasswordRestoreButton() {
        restorePasswordButton.click();
    }

    // Метод для нажатия на кнопку "Войти" в форме восстановления пароля
    public void submitRestoreLoginButton() {
        submitRestoreLoginButton.click();
    }

    // Метод для нажатия на кнопку "Личный кабинет"
    public void clickUserAccountButton() {
        personalCabinetButton.click();
    }

    // Метод для нажатия на кнопку "Войти" в форме регистрации
    public void clickRegisterLoginButton() {
        registerLoginButton.click();
    }

    // Метод для нажатия на кнопку "Конструктор" в шапке страницы
    public void clickConstructor() {
        constructorButton.click();
    }

    // Метод для нажатия на кнопку "Выход"
    public void clickLogout() {
        exitButton.click();
    }

    // Метод для нажатия на логотип Stellar Burgers
    public void clickStellarLogo() {
        stellarBurgersLogo.click();
    }
}


