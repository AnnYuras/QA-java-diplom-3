package TestsUI.pageObjects;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;


public class ConstructorPage {
    private WebDriver driver;

    // Локатор заголовка активного раздела "Начинки"
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[3]")
    private WebElement saucesHeaderFillings;

    // Локатор для раздела "Начинки" на главной странице конструктора
    @FindBy(xpath = "//span[text()='Начинки']")
    private WebElement fillingsSection;

    // Локатор для раздела "Булки" на главной странице конструктора
    @FindBy(xpath = "//span[text()='Булки']")
    private WebElement bunsSection;

    // Локатор заголовка активного раздела "Соусы"
    @FindBy(xpath = "//*[@id='root']/div/main/section[1]/div[2]/h2[2]")
    private WebElement saucesHeaderSauces;

    // Локатор для раздела "Соусы" на главной странице конструктора
    @FindBy(xpath = "//span[text()='Соусы']")
    private WebElement saucesSection;

    // Локатор заголовка активного раздела "Булки"
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[1]")
    private WebElement saucesHeaderBuns;


    // Конструктор страницы, инициализирующий элементы с использованием WebDriver
    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Метод для нажатия на раздел "Булки"
    public void clickBunsSection() {
        bunsSection.click();
    }

    // Метод для нажатия на раздел "Соусы"
    public void clickSaucesSection() {
        saucesSection.click();
    }

    // Метод для нажатия на раздел "Начинки"
    public void clickFillingsSection() {
        fillingsSection.click();
    }

    // Метод для получения текста заголовка активного раздела "Булки"
    public String getBunsHeaderText() {
        return saucesHeaderBuns.getText();
    }

    // Метод для получения текста заголовка активного раздела "Соусы"
    public String getSaucesHeaderText() {
        return saucesHeaderSauces.getText();
    }

    // Метод для получения текста заголовка активного раздела "Начинки"
    public String getFillingsHeaderText() {
        return saucesHeaderFillings.getText();
    }
}

