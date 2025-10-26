package org.example

import kotlin.random.Random

// 1. Класс должен быть open для наследования
open class Human(
    protected val fullName: String,
    private val age: Int,
    protected var currentSpeed: Double,
    protected var x: Double = 0.0,
    protected var y: Double = 0.0      // 2. Protected для доступа в классе Driver
) {

    // ... (Геттеры и Сеттеры остаются без изменений) ...

    // 3. Метод move() должен быть open для переопределения
    open fun move(timeStep: Double) {
        // Random Walk: Направление (угол) выбирается случайно
        val randomAngle = Random.nextDouble(0.0, 360.0)

        // Расстояние, пройденное за шаг: Distance = Speed * Time
        val distance = currentSpeed * timeStep

        // Преобразование угла в радианы
        val radians = Math.toRadians(randomAngle)

        // Рассчитываем изменение координат
        val dx = distance * Math.cos(radians)
        val dy = distance * Math.sin(radians)

        // Обновляем координаты
        x += dx
        y += dy

        println("${fullName} made a random step.")
        println(" -> New coordinates: ($x, $y)")
    }
}