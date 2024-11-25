package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BunTest {

    private static final float DELTA = 0.0001f; // Дельта для сравнения float

    private String name;
    private float price;
    private Bun bun;

    // Конструктор для параметризации
    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    // Параметризованный набор данных
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"white bun", 100},
                {"sesame bun", 120},
                {"", 150},
                {null, 150},
                {"zero price bun", 0},
                {"negative price bun", -50},
                {"premium bun", 1000000},
                {"max float price", Float.MAX_VALUE},
                {"min float price", Float.MIN_VALUE},
                {"NaN price", Float.NaN},
                {"infinity price", Float.POSITIVE_INFINITY},
                {"negative infinity price", Float.NEGATIVE_INFINITY}
        });
    }

    @Test
    public void testGetName() {
        bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @Test
    public void testGetPrice() {
        bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(), DELTA);
    }

    // Уникальный тест для проверки NaN
    @Test
    public void testPriceIsNaN() {
        if (Float.isNaN(price)) {
            bun = new Bun(name, price);
            assertTrue(Float.isNaN(bun.getPrice()));
        }
    }

//Лишние повторяющиеся тесты
//    // Тест с пустым значением имени
//    @Test
//    public void testGetNameWithEmptyString() {
//        bun = new Bun("", 150);
//        assertEquals("", bun.getName());
//    }
//
//    // Тест с null в имени
//    @Test
//    public void testGetNameWithNull() {
//        bun = new Bun(null, 150);
//        assertEquals(null, bun.getName());
//    }
//
//    // Тест с нулевой ценой
//    @Test
//    public void testGetPriceWithZeroValue() {
//        bun = new Bun("zero price bun", 0);
//        assertEquals(0, bun.getPrice(), 0);
//    }
//
//    // Тест с отрицательной ценой
//    @Test
//    public void testGetPriceWithNegativeValue() {
//        bun = new Bun("negative price bun", -50);
//        assertEquals(-50, bun.getPrice(), 0);
//    }
//
//    // Тест с очень большой ценой
//    @Test
//    public void testGetPriceWithLargeValue() {
//        bun = new Bun("premium bun", 1000000);
//        assertEquals(1000000, bun.getPrice(), 0);
//    }
//
//    // Тест с числовыми максимумом и минимумом значениями для цены
//    @Test
//    public void testGetPriceWithEdgeValues() {
//        bun = new Bun("max float price", Float.MAX_VALUE);
//        assertEquals(Float.MAX_VALUE, bun.getPrice(), 0);
//
//        bun = new Bun("min float price", Float.MIN_VALUE);
//        assertEquals(Float.MIN_VALUE, bun.getPrice(), 0);
//
//        bun = new Bun("NaN price", Float.NaN);
//        assertTrue(Float.isNaN(bun.getPrice()));
//
//        bun = new Bun("infinity price", Float.POSITIVE_INFINITY);
//        assertEquals(Float.POSITIVE_INFINITY, bun.getPrice(), 0);
//
//        bun = new Bun("negative infinity price", Float.NEGATIVE_INFINITY);
//        assertEquals(Float.NEGATIVE_INFINITY, bun.getPrice(), 0);
//    }
//
//
//
//    // Тест с пустым объектом булочки
//
//
//    @Test(expected = NullPointerException.class)
//    public void testNullBunThrowsException() {
//        Bun nullBun = null;
//        // Попытка вызова метода на null объекте должна выбросить NullPointerException
//        nullBun.getName();
//    }
//
//


}
