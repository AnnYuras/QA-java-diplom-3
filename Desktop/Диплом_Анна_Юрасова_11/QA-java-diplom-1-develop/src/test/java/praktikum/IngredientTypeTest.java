package praktikum;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;
import java.util.EnumSet;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private IngredientType ingredientType;
    private String ingredientTypeName;

    // Параметризованный конструктор
    public IngredientTypeTest(IngredientType ingredientType, String ingredientTypeName) {
        this.ingredientType = ingredientType;
        this.ingredientTypeName = ingredientTypeName;
    }

    // Данные для параметризации
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { IngredientType.SAUCE, "SAUCE" },
                { IngredientType.FILLING, "FILLING" }
        });
    }

    // Проверка метода valueOf()
    @Test
    public void testValueOf() {
        assertEquals(ingredientType, IngredientType.valueOf(ingredientTypeName));
    }

    // Проверка метода name()
    @Test
    public void testName() {
        assertEquals(ingredientTypeName, ingredientType.name());
    }

    // Проверка метода toString()
    @Test
    public void testToString() {
        assertEquals(ingredientTypeName, ingredientType.toString());
    }

    // Проверка всех значений перечисления
    @Test
    public void testEnumValues() {
        IngredientType[] types = IngredientType.values();
        assertEquals(2, types.length); // Должно быть два значения
        assertTrue(EnumSet.allOf(IngredientType.class).contains(IngredientType.SAUCE));
        assertTrue(EnumSet.allOf(IngredientType.class).contains(IngredientType.FILLING));
    }

    // Проверка, что выбрасывается исключение для несуществующего значения
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEnumValue() {
        IngredientType.valueOf("INVALID");
    }

    // Проверка равенства и неравенства элементов перечисления
    @Test
    public void testEqualityAndInequality() {
        assertEquals(IngredientType.SAUCE, IngredientType.SAUCE);
        assertEquals(IngredientType.FILLING, IngredientType.FILLING);
        assertNotEquals(IngredientType.SAUCE, IngredientType.FILLING);
    }

    // Проверка метода ordinal()
    @Test
    public void testOrdinal() {
        assertEquals(0, IngredientType.SAUCE.ordinal());
        assertEquals(1, IngredientType.FILLING.ordinal());
    }
}

//    @Test
//    public void testIngredientTypeValues() {
//        assertEquals(ingredientType, IngredientType.valueOf(ingredientTypeName));
//    }
//
//    @Test
//    public void testIngredientTypeName() {
//        assertEquals(ingredientTypeName, ingredientType.name());
//    }
//
//    @Test
//    public void testIngredientTypeValuesArray() {
//        // Проверка массива всех значений перечисления
//        IngredientType[] types = IngredientType.values();
//        assertEquals(2, types.length); // Должно быть два элемента в перечислении
//        assertTrue(Arrays.asList(types).contains(IngredientType.SAUCE));
//        assertTrue(Arrays.asList(types).contains(IngredientType.FILLING));
//    }
//
//    // Проверяем, что выбрасывается исключение IllegalArgumentException для неверного имени
//    @Test(expected = IllegalArgumentException.class)
//    public void testIngredientTypeValueOfInvalid() {
//        IngredientType.valueOf("INVALID"); // Это значение не существует, должно быть исключение
//    }
//
//
//
//    // Дополнительный тест для проверки метода toString()
//    @Test
//    public void testIngredientTypeToString() {
//        assertEquals("SAUCE", IngredientType.SAUCE.toString());
//        assertEquals("FILLING", IngredientType.FILLING.toString());
//    }
//
//    // Дополнительный тест для проверки ordinal() — индекс элемента в перечислении
//    @Test
//    public void testIngredientTypeOrdinal() {
//        assertEquals(0, IngredientType.SAUCE.ordinal());
//        assertEquals(1, IngredientType.FILLING.ordinal());
//    }
//
//    // Дополнительный тест для проверки невалидного enum значения через исключение
//    @Test(expected = IllegalArgumentException.class)
//    public void testInvalidEnum() {
//        IngredientType.valueOf("NON_EXISTENT"); // Ожидаем исключение
//    }
//
//    @Test
//    public void testIngredientTypeEquality() {
//        // Проверка, что два одинаковых элемента перечисления равны
//        assertEquals(IngredientType.SAUCE, IngredientType.SAUCE);
//        assertEquals(IngredientType.FILLING, IngredientType.FILLING);
//    }
//
//    @Test
//    public void testIngredientTypeNotEqual() {
//        // Проверка, что разные элементы перечисления не равны
//        assertNotEquals(IngredientType.SAUCE, IngredientType.FILLING);
//    }
//
//    @Test
//    public void testIngredientTypeToStringConsistency() {
//        // Дополнительная проверка, что метод toString() возвращает строковое представление элемента перечисления
//        assertEquals("SAUCE", IngredientType.SAUCE.toString());
//        assertEquals("FILLING", IngredientType.FILLING.toString());
//    }
//
//
//
//}

