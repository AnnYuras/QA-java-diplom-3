package TestsUI.test;

import org.junit.Test;
import io.qameta.allure.Description;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import java.util.Collection;


/**
 * Класс тестов для проверки разделов на странице конструктора.
 * Параметризированный тест, который выполняется в разных браузерах.
 */
@RunWith(Parameterized.class)
public class ConstructorTest extends BaseTest {

    /**
     * Параметризация тестов: получение списка браузеров, в которых будут выполняться тесты.
     * Данные берутся из класса {@link BrowserParameters}.
     *
     * @return Коллекция с браузерами для параметризированных тестов.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return BrowserParameters.getBrowserData(); // Вызов параметров из внешнего класса
    }

    /**
     * Конструктор для передачи браузера в родительский класс BaseTest.
     * Для запуска тестов в различных браузерах.
     *
     * @param browser Название браузера, в котором будет выполняться тест.
     */
    public ConstructorTest(String browser) {
        super(browser);
    }

    /**
     * Тест для проверки перехода в раздел "Булки".
     * Проверяется, что при переходе в раздел отображается правильный заголовок.
     */
    @Test
    @Description("Проверка перехода в раздел 'Булки'")
    public void testBunsSection() {
        // Переход к разделу соусов
        constructorPage.clickSaucesSection();
        // Переход к разделу булок
        constructorPage.clickBunsSection();
        // Проверка заголовка раздела
        assertEquals("Булки", constructorPage.getBunsHeaderText());
    }

    /**
     * Тест для проверки перехода в раздел "Соусы".
     * Проверяется, что при переходе в раздел отображается правильный заголовок.
     */
    @Test
    @Description("Проверка перехода в раздел 'Соусы'")
    public void testSaucesSection() {
        // Переход к разделу соусов
        constructorPage.clickSaucesSection();
        // Проверка заголовка раздела
        assertEquals("Соусы", constructorPage.getSaucesHeaderText());
    }

    /**
     * Тест для проверки перехода в раздел "Начинки".
     * Проверяется, что при переходе в раздел отображается правильный заголовок.
     */
    @Test
    @Description("Проверка перехода в раздел 'Начинки'")
    public void testFillingsSection() {
        // Переход к разделу начинок
        constructorPage.clickFillingsSection();
        // Проверка заголовка раздела
        assertEquals("Начинки", constructorPage.getFillingsHeaderText());
    }
}

