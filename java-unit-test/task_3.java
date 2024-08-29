@RunWith(Parameterized.class) // Напиши аннотацию для параметризованных тестов
public class CheckIsAdultTest {
	
  private final int age;
  private final boolean expectedResult;

  public CheckIsAdultTest(int age, boolean expectedResult) {
	this.age = age;
    this.expectedResult = expectedResult; // Инициализируй поля класса в конструкторе
  }

  @Parameterized.Parameters // Пометь метод аннотацией для параметров
  public static Object[][] getTextData() {
	return new Object[][] {
		{17, false}, // Возраст меньше 18 лет
        {18, true},  // Возраст ровно 18 лет
        {20, true},  // Возраст больше 18 лет, но меньше 21 года
        {21, true}   // Возраст больше 21 года
        }; // Заполни массив тестовыми данными и ожидаемым результатом
    
  }

  @Test
  public void checkIsAdultWhenAgeThenResult() {
	Program program = new Program();
	  // Передай сюда возраст пользователя
    boolean isAdult = program.checkIsAdult(age);
	// Сравни полученный и ожидаемый результаты, не забудь про сообщение об ошибке
    assertEquals("Ожидался результат: " + expectedResult + " для возраста: " + age, expectedResult, 
isAdult);
    }
}
