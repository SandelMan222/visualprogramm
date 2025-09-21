public class Main {
    public static void main(String[] args) {
        int simulationTime = 10; // время симуляции в "шагах"

        // Создаем людей (Human)
        Human h1 = new Human("Иванов Иван", 20, 1.0, 0, 0);
        Human h2 = new Human("Петров Петр", 22, 1.2, 5, 5);

        // Создаем водителя (Driver), который движется прямо
        Driver d1 = new Driver("Сидоров Сидор", 30, 1.5, 10, 10, Math.PI / 4); // угол = 45 градусов

        // Запускаем параллельные потоки
        Thread t1 = new Thread(() -> simulate(h1, simulationTime));
        Thread t2 = new Thread(() -> simulate(h2, simulationTime));
        Thread t3 = new Thread(() -> simulate(d1, simulationTime));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void simulate(Human human, int time) {
        for (int t = 0; t < time; t++) {
            human.move();
            System.out.printf("%s: шаг %d, позиция (%.2f, %.2f)%n",
                    human.getFio(), t + 1, human.getX(), human.getY());
            try {
                Thread.sleep(500); // задержка для наглядности
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
