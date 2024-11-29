package praktikum.objects;

public class User {
    private String email;
    private String password;
    private String name;

    // Конструктор по умолчанию
    public User() {}

    // Конструктор с параметрами
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Конструктор с параметрами без поля name
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Геттер для email
    public String getEmail() {
        return email;
    }

    // Сеттер для email
    public void setEmail(String email) {
        this.email = email;
    }

    // Геттер для password
    public String getPassword() {
        return password;
    }

    // Сеттер для password
    public void setPassword(String password) {
        this.password = password;
    }

    // Геттер для name
    public String getName() {
        return name;
    }

    // Сеттер для name
    public void setName(String name) {
        this.name = name;
    }
}


