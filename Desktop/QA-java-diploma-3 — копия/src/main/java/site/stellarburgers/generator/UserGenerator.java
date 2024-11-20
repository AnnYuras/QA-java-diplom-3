package site.stellarburgers.generator;

public class UserGenerator {
    public static String DEFAULT_NAME = "yuras-anna";
    public static String DEFAULT_EMAIL = "yuras-anna@ya.ru";

    public static String WORKING_EMAIL = "yuras-anna@yandex.ru";//
    public static String SHORT_PASSWORD = "123";
    public static String DEFAULT_PASSWORD = "326781";//

    public static String getNewRandomEmail() {
        return Math.random() + DEFAULT_EMAIL;
    }
}

