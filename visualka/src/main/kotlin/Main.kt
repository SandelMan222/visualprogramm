package org.example

import kotlin.random.Random

open class Human(
    protected val fullName: String,
    private val age: Int,
    protected var currentSpeed: Double,
    protected var x: Double = 0.0,
    protected var y: Double = 0.0
) {

    fun getFullName(): String = fullName

    fun getAge(): Int = age

    fun getCurrentSpeed(): Double = currentSpeed

    fun getX(): Double = x

    fun getY(): Double = y

    fun setCurrentSpeed(newSpeed: Double) {
        currentSpeed = newSpeed
    }

    open fun move(timeStep: Double) {
        val randomAngle = Random.nextDouble(0.0, 360.0)

        val distance = currentSpeed * timeStep

        val radians = Math.toRadians(randomAngle)

        val dx = distance * Math.cos(radians)
        val dy = distance * Math.sin(radians)

        x += dx
        y += dy

        println("${fullName} made a random step.")
        println(" -> New coordinates: ($x, $y)")
    }
}

class Driver(
    fullName: String,
    age: Int,
    currentSpeed: Double,
) : Human(fullName, age, currentSpeed, 0.0, 0.0) {

    override fun move(timeStep: Double) {
        val angleDeviation = Random.nextDouble(-15.0, 15.0)
        val radians = Math.toRadians(angleDeviation)

        val distance = currentSpeed * timeStep

        val dx = distance * Math.cos(radians)
        val dy = distance * Math.sin(radians)

        x += dx
        y += dy

        println("${fullName} (Driver) drove forward.")
        println(" -> New coordinates: ($x, $y)")
    }
}

fun main() {
    val timeStep = 1.0
    val totalTime = 5.0

    val people = listOf(
        Human("Ivanov I.I.", 25, 1.0),
        Human("Petrov P.P.", 30, 1.5),
        Human("Sidorov A.V.", 22, 0.8),
        Driver("Kozlov D.M.", 40, 5.0)
    )

    println("--- SIMULATION OF MOVEMENT (Inheritance and Threads) ---")

    var currentTime = 0.0

    while (currentTime < totalTime) {
        println("\n[Time: ${currentTime.toInt() + 1} sec.]")

        val threads = people.map { person ->
            Thread {
                person.move(timeStep)
            }
        }

        threads.forEach { it.start() }

        threads.forEach { it.join() }

        currentTime += timeStep
    }

    println("\n--- SIMULATION FINISHED ---")
}