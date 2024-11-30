package praktikum.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс для создания экземпляра WebDriver в зависимости от выбранного браузера.
 * Поддерживает настройку для браузеров Chrome и Yandex.
 */
public class WebDriverProvider {

    private static String getYandexBinaryPath() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
            return properties.getProperty("yandex.binary.path");
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке конфигурации: " + e.getMessage());
            return null;
        }
    }

    /**
     * Получение экземпляра WebDriver в зависимости от переданного браузера.
     * Поддерживаются браузеры Chrome и Yandex.
     *
     * @param browser Название браузера (например, "chrome", "yandex").
     * @return WebDriver для выбранного браузера.
     * @throws IllegalArgumentException если указан неизвестный браузер.
     */
    public static WebDriver getDriver(String browser) {
        // Преобразуем название браузера в нижний регистр для обеспечения нечувствительности к регистру
        switch (browser.toLowerCase()) {
            case "chrome":
                // Автоматическое определение версии браузера Chrome и настройка драйвера
                WebDriverManager.chromedriver().setup(); // Устанавливает драйвер для Chrome
                return new ChromeDriver(); // Возвращаем новый экземпляр ChromeDriver

            case "yandex":
                // Получаем путь к бинарному файлу Yandex из конфигурационного файла
                String yandexBinaryPath = getYandexBinaryPath();
                if (yandexBinaryPath == null) {
                    throw new IllegalArgumentException("Путь к бинарному файлу Yandex не найден.");
                }

                WebDriverManager.chromedriver().browserVersion("128").setup(); // Указываем версию для драйвера
                ChromeOptions options = new ChromeOptions();
                options.setBinary(yandexBinaryPath); // Указываем путь к бинарному файлу Yandex
                return new ChromeDriver(options); // Возвращаем экземпляр ChromeDriver с настроенными параметрами для Yandex

            default:
                // Исключение выбрасывается, если передан неизвестный браузер
                throw new IllegalArgumentException("Unknown browser: " + browser);
        }
    }
}
