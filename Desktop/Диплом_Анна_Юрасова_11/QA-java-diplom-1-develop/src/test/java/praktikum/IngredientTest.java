package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IngredientTest {

    private IngredientType ingredientType;
    private String name;
    private int price;

    // Параметризованный конструктор
    public IngredientTest(IngredientType ingredientType, String name, int price) {
        this.ingredientType = ingredientType;
        this.name = name;
        this.price = price;
    }

    // Данные для параметризации
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { IngredientType.FILLING, "cutlet", 100 },
                { IngredientType.SAUCE, "hot sauce", 50 },
                { IngredientType.FILLING, "cheese", 80 },
                { IngredientType.SAUCE, "spicy sauce", 60 },
                // Дополнительные тесты
                { IngredientType.FILLING, "", 0 },  // Пустое имя
                { IngredientType.SAUCE, null, 30 }, // Имя null
                { IngredientType.FILLING, "bacon", -10 }, // Отрицательная цена
                { IngredientType.SAUCE, "tomato", 0 } // Цена 0
        });
    }

    @Test
    public void testIngredientProperties() {
        Ingredient ingredient = new Ingredient(ingredientType, name, price);

        // Проверка имени
        if (name == null) {
            assertNull(ingredient.getName());
        } else {
            assertEquals(name, ingredient.getName());
        }

        // Проверка цены
        assertEquals(price, ingredient.getPrice(), 0);

        // Проверка типа
        assertEquals(ingredientType, ingredient.getType());
    }

    @Test
    public void testIngredientTypeDifferentiation() {
        Ingredient fillingIngredient = new Ingredient(IngredientType.FILLING, "cheese", 80);
        Ingredient sauceIngredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 50);

        // Проверяем, что типы ингредиентов различны
        assertNotEquals(fillingIngredient.getType(), sauceIngredient.getType());
    }
}


//    @Test
//    public void testGetName() {
//        Ingredient ingredient = new Ingredient(ingredientType, name, price);
//        assertEquals(name, ingredient.getName());
//    }
//
//    @Test
//    public void testGetPrice() {
//        Ingredient ingredient = new Ingredient(ingredientType, name, price);
//        assertEquals(price, ingredient.getPrice(), 0);
//    }
//
//    @Test
//    public void testGetType() {
//        Ingredient ingredient = new Ingredient(ingredientType, name, price);
//        assertEquals(ingredientType, ingredient.getType());
//    }
//
//    // Тест с отрицательной ценой
//    @Test
//    public void testNegativePrice() {
//        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", -50);
//        assertEquals(-50, ingredient.getPrice(), 0);
//    }
//
//    // Тест с нулевой ценой
//    @Test
//    public void testZeroPrice() {
//        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "tomato sauce", 0);
//        assertEquals(0, ingredient.getPrice(), 0);
//    }
//
//    // Тест на null имя
//    @Test
//    public void testNullName() {
//        Ingredient ingredient = new Ingredient(ingredientType, null, price);
//        assertNull(ingredient.getName()); // Проверяем, что имя действительно null
//    }
//
//    // Тест на пустое имя
//    @Test
//    public void testEmptyName() {
//        Ingredient ingredient = new Ingredient(ingredientType, "", price);
//        assertEquals("", ingredient.getName()); // Проверяем, что имя пустое
//    }
//
//    // Тест на тип ингредиента
//    @Test
//    public void testIngredientType() {
//        Ingredient ingredient = new Ingredient(ingredientType, name, price);
//        assertEquals(ingredientType, ingredient.getType()); // Проверяем тип
//    }
//
//    // Тест с различными ингредиентами
//    @Test
//    public void testDifferentIngredients() {
//        Ingredient fillingIngredient = new Ingredient(IngredientType.FILLING, "cheese", 80);
//        Ingredient sauceIngredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 50);
//        assertNotEquals(fillingIngredient.getType(), sauceIngredient.getType()); // Проверяем, что типы разные
//    }
//}


