package praktikum.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Allure;
import org.hamcrest.MatcherAssert;

import praktikum.ServerURLs;
import praktikum.pageobjects.MainPage;

import static org.hamcrest.Matchers.equalTo;


@DisplayName("Проверка конструктора (главной страницы)")
public class MainPageTests {

    // Объявление переменных для тестов
    private WebDriver webDriver;
    private MainPage mainPage;


    @Parameterized.Parameters(name = "Тестирование на {0}")
    public static Object[] browsers() {
        return new Object[]{"chrome", "yandex"}; // Добавляем поддержку Яндекс браузера
    }

    @Before
    @Step("Запуск браузера")
    public void startUp() {

        String browserName = System.getProperty("browser", "chrome");
        webDriver = WebDriverProvider.getDriver(browserName);
        webDriver.get(ServerURLs.MAIN_PAGE_URL); // Переходим на главную страницу

        // Инициализация страницы для тестов
        mainPage = new MainPage(webDriver);
    }

    @After
    @Step("Закрытие браузера")
    public void tearDown() {
        // Закрываем браузер после теста
        webDriver.quit();
    }

    @Test
    @Step("Нажатие на вкладку Булочки")
    @DisplayName("Проверка работы вкладки Булочки в разделе с ингредиентами")
    public void checkScrollToBunsIsSuccess() {
        // Указываем, какой браузер используется в тесте
        Allure.parameter("Браузер", System.getProperty("browser", "chrome"));

        mainPage.clickFillingsButton();
        mainPage.clickBunsButton();
        mainPage.scrollToElementAndWait(mainPage.getBunsTypes());

        // Проверяем, что список булочек виден на странице
        MatcherAssert.assertThat(
                "Список 'Булочки' не видно на странице",
                webDriver.findElement(mainPage.getBunsTypes()).isDisplayed(),
                equalTo(true)
        );
    }

    @Test
    @Step("Нажатие на вкладку Соусы")
    @DisplayName("Проверка работы вкладки Соусы в разделе с ингредиентами")
    public void checkScrollToSaucesIsSuccess() {
        Allure.parameter("Браузер", System.getProperty("browser", "chrome"));

        // Нажатие на кнопку "Соусы" для перехода в раздел соусов
        mainPage.clickSaucesButton();
        mainPage.scrollToElementAndWait(mainPage.getSaucesTypes());

        // Проверяем, что список соусов виден на странице
        MatcherAssert.assertThat(
                "Список 'Соусы' не видно на странице",
                webDriver.findElement(mainPage.getSaucesTypes()).isDisplayed(),
                equalTo(true)
        );
    }

    @Test
    @Step("Нажатие на вкладку Начинки")
    @DisplayName("Проверка работы вкладки Начинки в разделе с ингредиентами")
    public void checkScrollToFillingsIsSuccess() {
        Allure.parameter("Браузер", System.getProperty("browser", "chrome"));

        // Нажатие на кнопку "Начинки" для перехода в раздел начинок
        mainPage.clickFillingsButton();
        mainPage.scrollToElementAndWait(mainPage.getFillingsTypes());

        // Проверяем, что список начинок виден на странице
        MatcherAssert.assertThat(
                "Список 'Начинки' не видно на странице",
                webDriver.findElement(mainPage.getFillingsTypes()).isDisplayed(),
                equalTo(true)
        );
    }
}

