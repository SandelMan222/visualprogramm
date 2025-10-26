package org.example

// Driver наследует все свойства Human (ФИО, Скорость, Координаты)
class Driver(
    fullName: String,
    age: Int,
    currentSpeed: Double,
    x: Double = 0.0,
    y: Double = 0.0
) : Human(fullName, age, currentSpeed, x, y) { // Вызов конструктора родительского класса

    // Фиксированный угол для прямолинейного движения (0 градусов)
    private val fixedAngle = 0.0

    // Переопределение метода move()
    override fun move(timeStep: Double) {

        // Расстояние = Скорость * Время
        val distance = currentSpeed * timeStep

        // Преобразование угла в радианы
        val radians = Math.toRadians(fixedAngle)

        // Расчет изменения координат (dy всегда будет 0, потому что sin(0)=0)
        val dx = distance * Math.cos(radians)
        val dy = distance * Math.sin(radians)

        // Обновление координат (используем protected свойства Human)
        x += dx
        y += dy

        println("Driver ${fullName} made a straight step.")
        println(" -> New coordinates: ($x, $y)")
    }
}