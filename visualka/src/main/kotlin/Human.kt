package org.example

import kotlin.random.Random

class Human(
    private val fullName: String,
    private val age: Int,
    private var currentSpeed: Double,
    private var x: Double = 0.0,
    private var y: Double = 0.0
) {

    fun getFullName(): String = fullName
    fun getAge(): Int = age
    fun getCurrentSpeed(): Double = currentSpeed
    fun getX(): Double = x
    fun getY(): Double = y

    fun setCurrentSpeed(newSpeed: Double) {
        if (newSpeed >= 0) {
            currentSpeed = newSpeed
        }
    }

    fun move(timeStep: Double) {
        val randomAngle = Random.nextDouble(0.0, 360.0)

        val distance = currentSpeed * timeStep

        val radians = Math.toRadians(randomAngle)

        val dx = distance * Math.cos(radians)
        val dy = distance * Math.sin(radians)

        x += dx
        y += dy

        println("${fullName} shagnul")
        println(" -> new coord: ($x, $y)")
    }
}