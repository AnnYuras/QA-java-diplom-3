package TestsUI.test;

import java.util.Arrays;
import java.util.Collection;

/**
 * Класс для параметризации тестов с использованием разных браузеров.
 * Позволяет запускать тесты в различных браузерах, например, Chrome и Yandex, одной командой.
 */
public class BrowserParameters {

    /**
     * Метод для получения данных о браузерах, которые будут использоваться в параметризированных тестах.
     * Возвращает список с браузерами, которые можно передавать в тесты.
     *
     * @return Коллекция с параметрами для запуска тестов в разных браузерах.
     */
    public static Collection<Object[]> getBrowserData() {
        // Возвращаем список браузеров для запуска тестов
        return Arrays.asList(new Object[][]{
                {"chrome"},  // Параметр для браузера Chrome
                {"yandex"}   // Параметр для браузера Yandex
        });
    }
}
