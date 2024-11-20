package site.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browser {

    private static WebDriver driver;

    // Метод для выбора браузера по умолчанию
    public static void browserChoice() {
        driver = getDriver("chrome"); // Укажите браузер по умолчанию
    }

    // Возвращает драйвер для указанного браузера
    public static WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            case "yandex":
                System.setProperty("webdriver.chrome.driver", "/Users/annaurasova/Desktop/chromedriver-mac-arm64v128/chromedriver");
                ChromeOptions options = new ChromeOptions();
                options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                return new ChromeDriver(options);

            default:
                throw new IllegalArgumentException("Unknown browser: " + browser);
        }
    }

    // Метод для получения текущего экземпляра драйвера
    public static WebDriver getDriverInstance() {
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized. Call browserChoice() first.");
        }
        return driver;
    }

    // Закрывает драйвер и завершает работу
    public static void closeNotChromeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
