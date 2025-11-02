package org.example

import kotlin.random.Random
import kotlin.math.*

// Класс Human реализует интерфейс Movable
open class Human(
    protected val fullName: String,
    // Заменяем private val age: Int, на публичный для упрощения:
    val age: Int,
    // var currentSpeed теперь будет с ручной логикой set:
    currentSpeed: Double,
    protected var x: Double = 0.0,
    protected var y: Double = 0.0
) : Movable {

    // Свойство currentSpeed (protected) с ручным сеттером для проверки >= 0
    protected var currentSpeed: Double = currentSpeed
        set(value) {
            if (value >= 0) {
                field = value
            }
        }

    // Метод move()
    override fun move(timeStep: Double) {
        // ... (код движения Random Walk без изменений) ...
        val randomAngle = Random.nextDouble(0.0, 360.0)
        val distance = this.currentSpeed * timeStep // Используем this.currentSpeed
        val radians = Math.toRadians(randomAngle)
        val dx = distance * Math.cos(radians)
        val dy = distance * Math.sin(radians)
        x += dx
        y += dy

        println("${fullName} made a random step.")
        println(" -> New coordinates: ($x, $y)")
    }
}