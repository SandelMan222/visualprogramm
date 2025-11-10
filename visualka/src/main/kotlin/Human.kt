package org.example

import kotlin.random.Random

// Класс Human
class Human(
    private val fullName: String, // ФИО (private val)
    private val age: Int, // Возраст (private val)
    private var currentSpeed: Double, // Текущая скорость (private var)
    private var x: Double = 0.0, // Координата X
    private var y: Double = 0.0 // Координата Y
) {


    // Геттеры

    fun getFullName(): String = fullName
    fun getAge(): Int = age
    fun getCurrentSpeed(): Double = currentSpeed
    fun getX(): Double = x
    fun getY(): Double = y

    // Сеттеры
    fun setCurrentSpeed(newSpeed: Double) {
        if (newSpeed >= 0) {
            currentSpeed = newSpeed
        }
    }


    // Метод move() - Random Walk

    fun move(timeStep: Double) {
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

        println("${fullName} shagnul")
        println(" -> new coord: ($x, $y)")
    }
}