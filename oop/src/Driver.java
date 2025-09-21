public class Driver extends Human {
    private double angle; // направление движения (в радианах)

    // Конструктор наследника
    public Driver(String fio, int age, double speed, double x, double y, double angle) {
        super(fio, age, speed, x, y);
        this.angle = angle;
    }

    // Переопределяем метод move() для прямолинейного движения
    @Override
    public void move() {
        setX(getX() + getSpeed() * Math.cos(angle));
        setY(getY() + getSpeed() * Math.sin(angle));
    }
}
