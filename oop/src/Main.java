public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Количество объектов: поставь своё число (зависит от номера в группе).
        // По умолчанию поставил 3 — если у тебя другой номер в списке, измени numHumans.
        int numHumans = 3;

        Human[] humans = new Human[numHumans];

        // Создаём людей. Можно поменять ФИО/возраст/скорости.
        humans[0] = new Human("Бенескул Игнат Максимович", 20, 1.0, 0.0, 0.0);
        if (numHumans > 1) humans[1] = new Human("Иванов Иван", 21, 1.2, 1.0, 0.0);
        if (numHumans > 2) humans[2] = new Human("Петров Петр", 19, 0.8, -1.0, 0.5);
        // при необходимости добавь ещё объектов

        int simulationTimeSec = 10; // время симуляции в секундах (измени если нужно)

        System.out.println("Simulation start — Random Walk. Time: " + simulationTimeSec + " s");
        for (int t = 0; t < simulationTimeSec; t++) {
            System.out.println("t = " + t + "s");
            for (Human h : humans) {
                if (h == null) continue;
                h.move();
                System.out.println(h);
            }
            System.out.println("-----");
            Thread.sleep(1000); // пауза 1 секунда между шагами
        }
        System.out.println("Simulation finished.");
    }
}
