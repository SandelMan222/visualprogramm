package org.example // Обязательный пакет, чтобы код работал

fun main() {
    val groupNumber = 2
    val timeStep = 1.0 // Шаг времени симуляции (в секундах)
    val totalTime = 10.0 // Общее время симуляции (в секундах)

    // 1. Создание массива экземпляров Human (по количеству N=2)
    val people = listOf(
        Human("Ivanov I.I.", 25, 1.0),
        Human("Petrov P.P.", 30, 1.5)
    )

    // Вывод на транслите для чистой консоли
    println("--- SIMULATION OF MOVEMENT (Random Walk) ---")

    var currentTime = 0.0

    // 2. Основной цикл "времени"
    while (currentTime < totalTime) {
        println("\n[Time: ${currentTime.toInt() + 1} sec.]")

        // Заставляем каждого Human ходить
        people.forEach { person ->
            person.move(timeStep)
        }

        currentTime += timeStep
    }

    // Вывод на транслите
    println("\n--- SIMULATION FINISHED ---")
}