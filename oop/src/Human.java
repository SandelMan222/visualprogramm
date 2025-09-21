public class Human {
    private String fio;
    private int age;
    private double speed; // шаг (единицы расстояния за тик)
    private double x;
    private double y;

    // Конструктор со всеми свойствами (startX, startY — начальные координаты)
    public Human(String fio, int age, double speed, double startX, double startY) {
        this.fio = fio;
        this.age = age;
        this.speed = speed;
        this.x = startX;
        this.y = startY;
    }

    // Геттеры и сеттеры
    public String getFio() { return fio; }
    public void setFio(String fio) { this.fio = fio; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }

    public double getX() { return x; }
    public double getY() { return y; }

    // Random Walk: шаг в случайном направлении
    public void move() {
        double angle = Math.random() * 2.0 * Math.PI; // случайный угол в радианах
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);
    }

    @Override
    public String toString() {
        return String.format("%s (age %d): x=%.3f, y=%.3f", fio, age, x, y);
    }
}
