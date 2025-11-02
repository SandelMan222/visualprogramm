package org.example

import kotlin.math.*

// Класс Driver наследует от Human и переопределяет его движение
class Driver(
    fullName: String,
    age: Int,
    currentSpeed: Double,
    x: Double = 0.0,
    y: Double = 0.0
) : Human(fullName, age, currentSpeed, x, y) {

    // Фиксированный угол для прямолинейного движения (0 градусов, движение только по оси X)
    private val fixedAngle = 0.0

    // Переопределение метода move() для прямолинейного движения
    override fun move(timeStep: Double) {

        // Расстояние = Скорость * Время
        val distance = currentSpeed * timeStep

        // Преобразование угла в радианы
        val radians = Math.toRadians(fixedAngle)

        // Расчет изменения координат (dy всегда 0, потому что sin(0)=0)
        val dx = distance * Math.cos(radians)
        // dy здесь не нужен, так как он всегда 0, но оставим для наглядности
        val dy = distance * Math.sin(radians)

        // Обновление координат (используем protected свойства Human)
        x += dx
        y += dy

        println("Driver ${fullName} made a straight step.")
        println(" -> New coordinates: ($x, $y)")
    }
}