public class Human {
    private String fio;
    private int age;
    private double speed;
    private double x;
    private double y;

    // Конструктор
    public Human(String fio, int age, double speed, double x, double y) {
        this.fio = fio;
        this.age = age;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    // Метод случайного шага (Random Walk)
    public void move() {
        double angle = Math.random() * 2 * Math.PI;
        setX(getX() + getSpeed() * Math.cos(angle));
        setY(getY() + getSpeed() * Math.sin(angle));
    }

    // Геттеры
    public String getFio() { return fio; }
    public int getAge() { return age; }
    public double getSpeed() { return speed; }
    public double getX() { return x; }
    public double getY() { return y; }

    // Сеттеры
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setSpeed(double speed) { this.speed = speed; }
}

