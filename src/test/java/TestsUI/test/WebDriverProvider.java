import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Класс для создания экземпляра WebDriver в зависимости от выбранного браузера.
 * Поддерживает настройку для браузеров Chrome и Yandex.
 */
public class WebDriverProvider {

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
                // Установка конкретной версии драйвера для браузера Yandex (версии 128)
                WebDriverManager.chromedriver().browserVersion("128").setup(); // Указываем версию для драйвера
                ChromeOptions options = new ChromeOptions();
                // Указываем путь к бинарному файлу Yandex для запуска браузера
                options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex"); // Путь для macOS, может измениться на других ОС
                return new ChromeDriver(options); // Возвращаем экземпляр ChromeDriver с настроенными параметрами для Yandex

            default:
                // Исключение выбрасывается, если передан неизвестный браузер
                throw new IllegalArgumentException("Unknown browser: " + browser);
        }
    }
}

